package news.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class CheckColumnLabel {

	private ResultSet resultSet;
	
		
	public CheckColumnLabel(ResultSet resultSet) {
		super();
		this.resultSet = resultSet;
	}


	public Boolean checkColumnLabel(String columnName) {
		try {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columns = rsmd.getColumnCount();
			for (int x = 1; x <= columns; x++) {
		        if (columnName.equals(rsmd.getColumnName(x))) {
		            return true;
		        }
		    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	
	public Boolean checkColumnIndex(int index,String columnName) {
		try {
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int columns = rsmd.getColumnCount();
			for (int x = 1; x <= columns; x++) {
		        if (index == x && rsmd.getColumnLabel(x).equals(columnName)) {
		            return true;
		        }
		    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}
}
