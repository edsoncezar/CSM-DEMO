package br.com.alcatellucent.csm.utils;

public abstract class NumberManipulationUtil {

	private static final int	HEX_RADIX	= 16;

	/**
	 * Return a number formated as 0xAB. If srcNumber is an Decimal Integer, the
	 * result is converted to an HEX, otherwise its returned unmodified.<BR>
	 * Example:
	 * <li>srcNumber = 42, returns 0x2A</li>
	 * <li>srcNumber = 0x42, returns 0x42</li>
	 * <li>srcNumber = x42, ERROR </li>
	 * 
	 * @param srcNumber
	 *           Number to convert
	 * @return Formated Hex Number
	 */
	public static String getHexNumberFromString(String srcNumber, int bytes) {
		String retVal = null;
		int ZeroxPos = srcNumber.toLowerCase().indexOf("0x");
		if (ZeroxPos == -1) { // srcNumber is a decimal
			retVal = Integer.toString(Integer.valueOf(srcNumber), HEX_RADIX);
			retVal = Utils.fillZerosLeft(retVal, bytes - retVal.length());
			retVal = "0x" + retVal;
		} else {
			retVal = srcNumber;
		}
		return retVal;
	}

	public static int getDecIntFromHexString(String hexString) {
		return Integer.parseInt(hexString.substring(2), HEX_RADIX);
	}

}
