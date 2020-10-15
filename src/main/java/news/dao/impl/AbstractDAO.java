package news.dao.impl;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import news.dao.GenericDAO;
import news.mapper.RowMapper;


public class AbstractDAO<T> implements GenericDAO<T>{
	
	public Connection getConnection() {
		String name = "root";
		String pass = "123456";
		String url  = "jdbc:mysql://localhost:3306/news_manager";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url,name,pass);
		} catch (SQLException | ClassNotFoundException e) {
			return null;
		}	
		
	}
	
	public void setParameter(PreparedStatement statement,Object... parameters) {
		try {
			for(int i = 0; i < parameters.length;i++) {
				int index = i + 1;
				Object parameter = parameters[i];
				if(parameter instanceof String) {
					statement.setString(index, (String) parameter);
				}else if(parameter instanceof Integer) {
					statement.setInt(index, (Integer) parameter);
				}else if(parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) parameter);
				}else if(parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				}				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Long id = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			setParameter(statement, parameters);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			connection.commit();
			if(resultSet.next()) {
				id = resultSet.getLong(1);
			}
			return id;
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
				
			}
			return null;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		
	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
				
			}
			System.out.println(e.getMessage());
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
		
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				T t = rowMapper.mapRow(resultSet);
				list.add(t);
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
	}

	@Override
	public Integer count(String sql, Object... parameters) {
		Integer count = null;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			return null;
		}finally {
			try {
				if(connection != null) {
					connection.close();
				}
				if(statement != null) {
					statement.close();
				}
				if(resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				System.out.println(e2.getMessage());
			}
		}
	}
	
	

}
