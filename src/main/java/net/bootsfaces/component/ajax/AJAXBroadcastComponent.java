package net.bootsfaces.component.ajax;

import jakarta.el.ELContext;
import jakarta.el.ExpressionFactory;
import jakarta.el.MethodExpression;
import jakarta.el.PropertyNotFoundException;
import jakarta.el.ValueExpression;
import jakarta.faces.FacesException;
import jakarta.faces.application.ProjectStage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.component.UIComponentBase;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.AbortProcessingException;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.event.ActionListener;
import jakarta.faces.event.FacesEvent;

public class AJAXBroadcastComponent extends UIComponentBase {
	private UIComponent source;

	public AJAXBroadcastComponent(UIComponent source) {
		this.source = source;
	}

	/**
	 * <p>
	 * In addition to to the default {@link UIComponent#broadcast} processing,
	 * pass the {@link ActionEvent} being broadcast to the method referenced by
	 * <code>actionListener</code> (if any), and to the default
	 * {@link ActionListener} registered on the
	 * {@link jakarta.faces.application.Application}.
	 * </p>
	 *
	 * @param event
	 *            {@link FacesEvent} to be broadcast
	 *
	 * @throws AbortProcessingException
	 *             Signal the JavaServer Faces implementation that no further
	 *             processing on the current event should be performed
	 * @throws IllegalArgumentException
	 *             if the implementation class of this {@link FacesEvent} is not
	 *             supported by this component
	 * @throws NullPointerException
	 *             if <code>event</code> is <code>null</code>
	 */
	@SuppressWarnings("deprecation")
	public void broadcast(FacesEvent event) throws AbortProcessingException {

		// Perform standard superclass processing
		source.broadcast(event);
		if (event instanceof BootsFacesAJAXEvent) {
			Object result = executeAjaxCalls(FacesContext.getCurrentInstance(),
					((BootsFacesAJAXEvent) event).getJsCallback());
			// if (result != null) {
			// System.out.println("Redirection has not yet been implemented.");
			// }
		}
	}

	/**
	 * Evaluates an EL expression into an object.
	 *
	 * @param p_expression
	 *            the expression
	 * @throws PropertyNotFoundException
	 *             if the attribute doesn't exist at all (as opposed to being
	 *             null)
	 * @return the object
	 */
	public static ValueExpression evalAsValueExpression(String p_expression) throws PropertyNotFoundException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
		ELContext elContext = context.getELContext();
		ValueExpression vex = expressionFactory.createValueExpression(elContext, p_expression, Object.class);
		return vex;
	}
	
	/**
	 * Evaluates an EL expression into an object.
	 *
	 * @param p_expression
	 *            the expression
	 * @throws PropertyNotFoundException
	 *             if the attribute doesn't exist at all (as opposed to being
	 *             null)
	 * @return the object
	 */
	public static MethodExpression evalAsMethodExpression(String p_expression) throws PropertyNotFoundException {
		FacesContext context = FacesContext.getCurrentInstance();
		ExpressionFactory expressionFactory = context.getApplication().getExpressionFactory();
		ELContext elContext = context.getELContext();
		MethodExpression mex = expressionFactory.createMethodExpression(elContext, p_expression, Object.class, new Class[0]);
		return mex;
	}


	/**
	 * Execute the ajax call when ajax syntax was found ajax:<command>
	 *
	 * @param context
	 * @param command
	 * @return
	 */
	private Object executeAjaxCalls(FacesContext context, String command) {
		Object result = null;
		int pos = command.indexOf("ajax:");
		while (pos >= 0) { // the command may contain several AJAX and
							// JavaScript calls, in arbitrary order

			String el = command.substring(pos + "ajax:".length());
			if (el.contains("javascript:")) {
				int end = el.indexOf("javascript:");
				el = el.substring(0, end);
			}
			el = el.trim();
			while (el.endsWith(";")) {
				el = el.substring(0, el.length() - 1).trim();
			}

			if (context.isProjectStage(ProjectStage.Development)) {
				checkELSyntax(el, context.getELContext());
			}
			ValueExpression vex = evalAsValueExpression("#{" + el + "}");
			try {
				result = vex.getValue(context.getELContext());
			} catch (jakarta.el.PropertyNotFoundException ex) {
				MethodExpression mex = evalAsMethodExpression("#{" + el + "}");
				result = mex.invoke(context.getELContext(), null);
			}

			// look for the next AJAX call (if any)
			pos = command.indexOf("ajax:", pos + 1);
		}
		return result;
	}

	/**
	 * Evaluate the expression syntax
	 *
	 * @param el
	 * @param context
	 */
	private void checkELSyntax(String el, ELContext context) {
		int pos = el.indexOf('.');
		if (pos<0) {
			throw new FacesException("The EL expression doesn't contain a method call: " + el);
		}
		int end = el.indexOf('(');
		if (end < 0)
			end = el.length();
		if (el.indexOf('[') >= 0)
			end = Math.min(end, el.indexOf('['));
		while (pos < end) {
			String object = el.substring(0, pos);
			ValueExpression vex = evalAsValueExpression("#{" + object + "}");
			vex.getValue(context);
			Object result = vex.getValue(context);
			if (result == null) {
				System.out.println("Please check your EL expression - intermediate term " + object + " is null");
			}
			pos = el.indexOf('.', pos + 1);
			if (pos < 0)
				break;
		}

	}

	@Override
	public String getFamily() {
		// TODO Auto-generated method stub
		return null;
	}
}
