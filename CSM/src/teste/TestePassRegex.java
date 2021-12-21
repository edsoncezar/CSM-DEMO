package teste;

import br.com.alcatellucent.csm.beans.PassRegex;
import br.com.alcatellucent.csm.bo.PassRegexBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;

public class TestePassRegex {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		PassRegex passRegex;
		
		PassRegexBO pbo = new PassRegexBO();
		
		try {
			passRegex = pbo.edit("");
			
			System.out.println("Acesso ok.");
		} catch (ExceptionSys e) {
			e.printStackTrace();
			System.exit(-1);
			
		}
	}
}