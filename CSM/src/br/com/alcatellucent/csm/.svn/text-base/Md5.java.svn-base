package br.com.alcatellucent.csm;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.alcatellucent.csm.exception.ExceptionSys;

public abstract class Md5 {

    private final static String KEY = "!R9NMAIDEN";

    /**
     * Method to generate the one way hash (MD5) of a password.<BR>
     * Uses an internal key to distinguish from the classic MD5 result.
     * 
     * @param textToHash
     *                Text to be compute the hash
     * @return {@code String} containing the HASH
     * @throws ExceptionSys
     */
    public static final String md5(final String textToHash) throws ExceptionSys {
	String md5Password;
	MessageDigest msgDigest;
	BigInteger hash;
	try {
	    msgDigest = MessageDigest.getInstance("MD5");
	    hash = new BigInteger(1, msgDigest.digest((textToHash + KEY)
		    .getBytes()));
	    md5Password = hash.toString(16);
	    return md5Password;
	} catch (NoSuchAlgorithmException e) {
	    throw new ExceptionSys(
		    "Could not getInstance of MD5. Check your VM configuration",
		    e);
	}
    }
}
