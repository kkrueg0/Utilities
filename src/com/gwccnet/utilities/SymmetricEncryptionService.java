/*
 * SymmetricEncryptionService.java
 * 
 * Created by rlacyx0
 * 
 * Created on September 11, 2006
 *
 * Copyright 2006 Great West Casualty Company
 */
 
package com.gwccnet.utilities;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import com.gwccnet.utilites.Exception.SymmetricEncryptionException;
import com.gwccnet.utility.ToolBox;
import com.ibm.crypto.provider.IBMJCE;

/**
 * Singleton class that can be used to perform symmetric (only one key)
 * encryption and decryption.  The key used is generated when the singleton is 
 * instantiated.  The bytes returned by the cipher during encryption are converted 
 * to a hex string.  It is not possible to instantiate a string directly from 
 * the bytes returned by the API unless the character coding is explicitly specificied. 
 * However, URL encoding something like this might cause the query string to grow beyond 
 * its maximum size.  Better to just convert the string to hex and back.
 * 
 * RC4 encryption was used at one point.  However, this was changed at the behest of our
 * security personnel.  Apparently, RC4 has been broken in lab tests.
 * 
 * Currently Blowfish symmetric encryption with a key size of 128 bits is used.
 */

public class SymmetricEncryptionService 
{
	private static final String ALGORITHM = "Blowfish";
	private static final int KEY_SIZE = 128;
		
	private KeyGenerator keyGenerator;
	private Key key;
	private Cipher cipher;
	
	private static SymmetricEncryptionService encryptionService;

	/**
	 * Constructor comment.
	 * 
	 * Initializes the cipher used to encrypt / decrypt, as well
	 * as the related variables.
	 */
	
	private SymmetricEncryptionService() throws SymmetricEncryptionException
	{
		try
		{
			keyGenerator = KeyGenerator.getInstance(SymmetricEncryptionService.ALGORITHM, new IBMJCE());
			keyGenerator.init(SymmetricEncryptionService.KEY_SIZE);
			key = keyGenerator.generateKey();
			cipher = Cipher.getInstance(SymmetricEncryptionService.ALGORITHM);
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			throw new SymmetricEncryptionException("Failed to initialize security service: NoSuchAlgorithmException: " + e.getMessage());
		}
		catch(NoSuchPaddingException e)
		{
			e.printStackTrace();
			throw new SymmetricEncryptionException("Failed to initialize security service: NoSuchPaddingException: " + e.getMessage());
		}
	}

	/**
	 * Accepts the string to decrypt as a parameter, decrypts it, and returns
	 * the decrypted string.
	 * 
	 * @param String - The encrypted string.
	 * @return String - The decrypted string.
	 */
	
	public synchronized String decrypt(String stringToDecrypt) throws SymmetricEncryptionException
	{
		if(CommonUtils.isNullOrEmpty(stringToDecrypt))
		{
			throw new SymmetricEncryptionException("Failed to decrypt string: string was null or empty");
		}
		
		try
		{		
			cipher.init(Cipher.DECRYPT_MODE, key);
			
			return new String(cipher.doFinal(ToolBox.getByteArrayFromHexString(stringToDecrypt)));
		}
		catch(IllegalStateException e)
		{
			e.printStackTrace();
			throw new SymmetricEncryptionException("Failed to decrypt string: IllegalStateException: " + e.getMessage());
		}
		catch(IllegalBlockSizeException e)
		{
			e.printStackTrace();
			throw new SymmetricEncryptionException("Failed to decrypt string: IllegalBlockSizeException: " + e.getMessage());
		}
		catch(BadPaddingException e)
		{
			e.printStackTrace();
			throw new SymmetricEncryptionException("Failed to decrypt string: BadPaddingException: " + e.getMessage());
		}
		catch(InvalidKeyException e)
		{
			e.printStackTrace();
			throw new SymmetricEncryptionException("Failed to decrypt string: InvalidKeyException: " + e.getMessage());
		}
	}

	/**
	 * Accepts the string to encrypt as parameter, encrypts it, and returns 
	 * the encrypted string.
	 * 
	 * @param String - The string to encrypt.
	 * @return String - The encrypted string.
	 */
	
	public synchronized String encrypt(String stringToEncrypt) throws SymmetricEncryptionException
	{
		if(CommonUtils.isNullOrEmpty(stringToEncrypt))
		{
			throw new SymmetricEncryptionException("Failed to encrypt string: string was null or empty");
		}
		try
		{		
			cipher.init(Cipher.ENCRYPT_MODE, key);
			
			return ToolBox.getHexString(cipher.doFinal(stringToEncrypt.getBytes()));
		}
		catch(IllegalStateException e)
		{
			e.printStackTrace();
			throw new SymmetricEncryptionException("Failed to encrypt string: IllegalStateException: " + e.getMessage());
		}
		catch(IllegalBlockSizeException e)
		{
			e.printStackTrace();
			throw new SymmetricEncryptionException("Failed to encrypt string: IllegalBlockSizeException: " + e.getMessage());
		}
		catch(BadPaddingException e)
		{
			e.printStackTrace();
			throw new SymmetricEncryptionException("Failed to encrypt string: BadPaddingException: " + e.getMessage());
		}
		catch(InvalidKeyException e)
		{
			e.printStackTrace();
			throw new SymmetricEncryptionException("Failed to encrypt string: InvalidKeyException: " + e.getMessage());
		}
	}

	/**
	 * Method that returns a reference to the singleton encryption service.
	 * Lazy initialization is used; the encryption service is not created until
	 * the first time it is needed.
	 * 
	 * @return SymmetricEncryptionService 
	 */
	
	public static synchronized SymmetricEncryptionService getInstance() throws SymmetricEncryptionException
	{
		if(encryptionService == null)
		{
			encryptionService = new SymmetricEncryptionService();
		}
	
		return encryptionService;
	}
}
