package net.bootsfaces.ut.common;
import javax.faces.context.FacesContext;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public abstract class FacesContextMocker extends FacesContext {

	private static FacesContext context = null;

	private FacesContextMocker() {
	}

	private static final Release RELEASE = new Release();

	private static class Release implements Answer<Void> {
		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			setCurrentInstance(null);
			return null;
		}
	}

	public static FacesContext mockFacesContext() {
		if (null == context)
			context = Mockito.mock(FacesContext.class);
		setCurrentInstance(context);
		Mockito.doAnswer(RELEASE).when(context).release();
		return context;
	}
}