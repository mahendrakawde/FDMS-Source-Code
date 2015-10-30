package com.aldorsolutions.dashboard.struts;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.MessageResourcesFactory;
import org.apache.struts.util.PropertyMessageResourcesFactory;

public abstract class BaseForm extends ActionForm {

	private static MessageResources resources = null;
	
	static {
		MessageResourcesFactory messageFactory = null;
		messageFactory = (PropertyMessageResourcesFactory) PropertyMessageResourcesFactory.createFactory();
		//resources = messageFactory.createResources("com.aldorsolutions.dashboard.struts.ApplicationResources");
		resources = messageFactory.createResources("com.aldorsolutions.dashboard.struts.DashboardResources");
	}
	
	public static MessageResources getResources () 
	{
		return ( resources );
	}
}
