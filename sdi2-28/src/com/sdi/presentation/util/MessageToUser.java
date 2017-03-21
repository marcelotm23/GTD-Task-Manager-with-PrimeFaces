package com.sdi.presentation.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessageToUser {

	public static void writeGrowlMessageERROR(String message){
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
		String i18message = bundle.getString(message);
        context.addMessage("businessMesg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",  i18message) );
	}
	
	public static void writeGrowlMessageINFO(String message){
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msgs");
		String i18message = bundle.getString(message);
        context.addMessage("businessMesg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",  i18message) );
	}
}
