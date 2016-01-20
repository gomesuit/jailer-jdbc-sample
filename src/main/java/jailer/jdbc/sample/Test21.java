package jailer.jdbc.sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;


public class Test21 {

	public static void main(String[] args) throws Exception {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		ds.setUrl("jdbc:log4jdbc:mysql://localhost/jailer");
		ds.setUsername("jailer");
		ds.setPassword("password");
		ds.setRemoveAbandoned(true);
		ds.setRemoveAbandonedTimeout(60);
		ds.setLogAbandoned(true);
		
		boolean flg = true;
		while(flg){
			System.out.println("===============================START===============================");
			Thread.sleep(1000);
			
			try{
				try(Connection conn = ds.getConnection()){
					System.out.println(conn.getClass());
					try(Statement stmt = conn.createStatement()){
						System.out.println(stmt.getClass());
						try(ResultSet rset = stmt.executeQuery("SELECT id FROM test")){
							while (rset.next()) {
								System.out.println(rset.getInt(1));
							}
						}
					}
				}
			}catch(Exception e){
				System.out.println(e.getClass());
			}
			
			System.out.println("===============================END===============================");
		}
		ds.close();
	}

}
