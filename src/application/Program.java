package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String args[]) {
		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false);
			
			st = conn.createStatement();
			
			int rows1 = st.executeUpdate("Update seller set BaseSalary = 2100 where DepartmentId = 1");
			/*int x = 1;
			if(x < 2) {
				throw new SQLException("Fake error");
			}*/
			int rows2 = st.executeUpdate("Update seller set BaseSalary = 3090 where DepartmentId = 2");
			
			conn.commit();
			
			System.out.println("Rows1: " + rows1 + "\nRows2: " + rows2);
			
			
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction Rolled Back!\nCaused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! \nCaused by: " + e.getMessage());
			}
			
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
