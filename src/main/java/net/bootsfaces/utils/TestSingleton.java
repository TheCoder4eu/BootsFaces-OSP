package net.bootsfaces.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.logging.Logger;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestSingleton {
	
	private static final Logger log = Logger.getLogger(TestSingleton.class.getName());
	
	private static TestSingleton instance = null;
	private ScriptEngine scriptEngine;
	private int counter;

	protected TestSingleton() {
		// Exists only to defeat instantiation.
	}

	public static TestSingleton getInstance() {
		if (instance == null)
			instance = new TestSingleton();
		// load script engine
		if (instance.getCounter() == 0)
			instance.loadMomentSource();
		// reset counter to clean
		if (instance.getCounter() > 2000)
			instance.setCounter(0);
		return instance;
	}

	private void loadMomentSource() {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("/META-INF/resources/bsf/js/moment.min.js");
		Reader reader = new InputStreamReader(is);
		ScriptEngineManager manager = new ScriptEngineManager();
		scriptEngine = manager.getEngineByName("JavaScript");
		try {
			scriptEngine.eval(reader);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}

	public String formatDate(Date date, String format) {
		try {
			String variableName = "z" + (new Date()).getTime();
			String jsFormat = format.replace("'", "\\'");
			String javascript = "var " + variableName + " = new moment(" + date.getTime() + ").format('" + jsFormat
					+ "');";
			scriptEngine.eval(javascript);
			String variable = (String) scriptEngine.get(variableName);
			log.info("FORMAT1: " + variable + ", using: " + format);
			return variable;
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
	}

	public Date formatDate(String value, String format) {
		try {
			String variableName = "z" + (new Date()).getTime();
			String jsFormat = format.replace("'", "\\'");
			String javascript = "var " + variableName + " = moment(\"" + value + "\", \"" + jsFormat + "\").valueOf();";
			scriptEngine.eval(javascript);
			long ts = Long.parseLong((String) scriptEngine.get(variableName));
			log.info("FORMAT2: " + ts + ", using: " + format);
			return new Date(ts);
		} catch (ScriptException e) {
			throw new RuntimeException(e);
		}
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}