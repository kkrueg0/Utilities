/**
 * Created on Feb 27, 2004
 */
package com.gwccnet.utilities.xmlutil;

import java.io.IOException;
import java.io.InputStream;

import com.gwccnet.utility.ToolBox;

/**
 * This class laods the XML file specified into a String.
 * It does so statically because for some reason we have
 * yet to discover, our 4.x WAS won't do it non-
 * statically.  This is a workaround, then, I guess.  
 * Workarounds are nice to get going, but often times our
 * workarounds become standard and petrified.  Too often.
 * 
 * @author Kris Krueger
 * 
 * Modified by Michael A. Davis
 * 20040227 1109
 * 
 */
public class XMLLoader 
{
    /**
     * Holds the XML used by the MFMessageFactory object 
     * to load mainframe call definitions.
     */
    private static String  messageFactoryXML;
    
    /** 
     * Indicates whether or not the static properties have been initialized. 
     */
    private static boolean initialized = false;

    private XMLLoader()
    {
    }

    /**
     * Method initVars: receives a file name and loads that file from 
     * the class path.
     * 
     * @param String The XML file name.
     */
    public static void initVars (String xmlfileName) 
    throws IOException, ClassNotFoundException
    {
        InputStream in = null;
        byte xmlByteArray[] = null;
        try
        {
            in = ToolBox.getResourceAsStream( xmlfileName );           
            xmlByteArray = new byte[in.available()];
            in.read(xmlByteArray);
            messageFactoryXML = new String(xmlByteArray).toString();

            in.close();
            initialized = true; 
        }
        catch(IOException e)
        {
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * This getter returns the XML string.  Any post processing
     * (there isn't any today) added in the future would go
     * in here.
     * 
     * @return String XML string which determines the calls 
     * that can be made to CICS, their names, arguments and
     * returns.
     */
    public static String getMessageFactoryXml()
    {
        return messageFactoryXML;
    }
    
    /**
     * This accessor simply returns the state of the 
     * initialized boolean.
     */
    public static boolean isInitialized()
    {
        return initialized;
    }
}
