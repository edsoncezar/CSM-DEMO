package teste;

import br.com.alcatellucent.csm.beans.PassHistory;
import br.com.alcatellucent.csm.bo.PassHistoryBO;
import br.com.alcatellucent.csm.exception.ExceptionSys;

public class TesteHistory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		PassHistory passHistory;
		
		PassHistoryBO pbo = new PassHistoryBO();
		
		try {
			passHistory = pbo.edit("");
			
			System.out.println("Acesso ok.");
		} catch (ExceptionSys e) {
			e.printStackTrace();
			System.exit(-1);
			
		}
	}
}