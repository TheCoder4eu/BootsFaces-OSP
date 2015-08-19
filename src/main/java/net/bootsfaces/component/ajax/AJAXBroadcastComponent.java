package net.bootsfaces.component.ajax;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.PropertyNotFoundException;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.faces.event.FacesEvent;

public class AJAXBroadcastComponent extends UIComponentBase {
	private UIComponent source;
	
	public AJAXBroadcastComponent(UIComponent source) {
		this.source=source;
	}
	
	/**
	 * <p>
	 * In addition to to the default {@link UIComponent#broadcast} processing,
	 * pass the {@link ActionEvent} being broadcast to the method referenced by
	 * <code>actionListener</code> (if any), and to the default
	 * {@link ActionListener} registered on the
	 * {@link javax.faces.application.Application}.
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

//		 Perform standard superclass processing 
		source.broadcast(event);
		if (event instanceof BootsFacesAJAXEvent) {
			System.out.println("BootsFaceAJAXEvent" + ((BootsFacesAJAXEvent) event).getEvent());
			Object result = executeAjaxCalls(FacesContext.getCurrentInstance(),
					((BootsFacesAJAXEvent) event).getJsCallback());
			if (result != null) {
				System.out.println("Redirection has not yet been implemented.");
			}

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
		ValueExpression vex = expressionFactory.createValueExpression(elContext, p_expression, String.class);
		return vex;
	}

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
			// MethodExpression method = evalAsMethodExpression(el);
			// method.invoke(FacesContext.getCurrentInstance().getELContext(),
			// null);
			ValueExpression vex = evalAsValueExpression("#{" + el + "}");
			result = vex.getValue(context.getELContext());

			// look for the next AJAX call (if any)
			pos = command.indexOf("ajax:", pos + 1);
		}
		return result;
	}

	@Override
	public String getFamily() {
		// TODO Auto-generated method stub
		return null;
	}

}
