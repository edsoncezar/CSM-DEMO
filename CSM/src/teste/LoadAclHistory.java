package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoadAclHistory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Connection con;
		
		System.out.println("Inicio de carga...");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			// con = DriverManager.getConnection("jdbc:mysql://128.251.233.44/agyadb","csmadmin","lab123");
			con = DriverManager.getConnection("jdbc:mysql://localhost/agyadb","root","admin");
			
			PreparedStatement tOu = con.prepareStatement("insert into aclhistory values (?, ?, ?, ?, ?, ?, ?, ?)");
			
			PreparedStatement tIn = con.prepareStatement("select * from acl");
			ResultSet rs_acl = tIn.executeQuery();

			tIn = con.prepareStatement("select * from csmprocessorpacket");
			ResultSet rs_dppm = tIn.executeQuery();
			rs_dppm.next();
			
			tIn = con.prepareStatement("select * from csmuser");
			ResultSet rs_user = tIn.executeQuery();
			rs_user.next();
			
			tIn = con.prepareStatement("select * from alert");
			ResultSet rs_alert = tIn.executeQuery();
			rs_alert.next();
			
			java.util.Date today = new java.util.Date();
			long t = today.getTime();
			java.sql.Date dt = new java.sql.Date(t);
			
			Integer i = 0;
			
			while (rs_acl.next()) {
				
				i++;
				
				tOu.setString(1, i.toString());
				tOu.setString(2, rs_acl.getString("id"));
				tOu.setString(3, rs_dppm.getString("id"));
				tOu.setDate(4, dt);
				tOu.setString(5, rs_user.getString("id"));
				tOu.setBoolean(6, true);
				tOu.setString(7, "Teste de carga " + i.toString());
				tOu.setString(8, rs_alert.getString("id"));
				
				tOu.executeUpdate();
		
				System.out.println("Carregando... " + i.toString());
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("Final de carga...");

	}

}
