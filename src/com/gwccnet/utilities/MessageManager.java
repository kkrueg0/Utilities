package com.gwccnet.utilities;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

import com.gwccnet.utility.ToolBox;
/**
 * Insert the type's description here.
 * Creation date: (3/20/2002 4:37:22 PM)
 * @author: Blake
 */
public class MessageManager {
	private static final String MESSAGE_PROPERTIES_LOCATION = "application_messages.properties";
	private static MessageManager mySingleton = new MessageManager();
		
	private Properties messages = null;
	
	/**
	 * MessageManager constructor comment.
	 */
	protected MessageManager() {
		super();
		messages = new Properties();
		InputStream messagesInputStream = ToolBox.getResourceAsStream(MESSAGE_PROPERTIES_LOCATION);
		try {
			messages.load(messagesInputStream);
		} catch (IOException e) {
			 //unelegant, but it shouldn't happen - the file should always be available
			 // (see com.gwccnet.WebEnhancement.BusinessModel.AuthenticatedUser.authenticateUsingLdap() for a better solution)
        	System.out.println("IOException");
        	System.out.println(
        		"Error creating input stream for application message properties (resource name: \""+ MESSAGE_PROPERTIES_LOCATION +"\"");
        	System.out.println("Application will be unable to display messages.");
        	System.out.println(e);
        	e.printStackTrace();
		}
	}
	
	public static synchronized MessageManager singleton() {
		if (null == mySingleton)
			mySingleton = new MessageManager();
		return mySingleton;
	}
	
	/**
	 * Use this method to look up a message for display to a user or for logging purposes.  Pass in
	 * the key associated with that message, and you receive the message text back.  If no message was
	 * found this method will return null, which should be handled appropriately.  No exceptions are
	 * thrown, since this method may be called at the JSP level.
	 * Creation date: (3/20/2002 4:39:39 PM)
	 * @return java.lang.String - the desired message text
	 * @param key java.lang.String - the key associated with the desired message
	 */
	public final String getMsgForKey(String key) {
		return messages.getProperty(key);
	}
	
}
