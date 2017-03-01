package com.sdi.persistence.impl;

import java.sql.*;
import java.util.*;

import com.sdi.model.Alumno;
import com.sdi.persistence.AlumnosDao;
import com.sdi.persistence.exception.*;


/**
 * Implementaci��n de la interfaz de fachada al servicio de persistencia para
 * Alumnos. En este caso es Jdbc pero podr��a ser cualquier otra tecnologia 
 * de persistencia, por ejemplo, la que veremos m��s adelante JPA 
 * (mapeador de objetos a relacional)
 * 
 * @author alb
 *
 */
public class AlumnoJdbcDAO implements AlumnosDao  {

	// En una implemenntación más sofisticada estas constantes habría 
	// que sacarlas a un sistema de configuración: 
	// xml, properties, descriptores de despliege, etc 
	String SQL_DRV = "org.hsqldb.jdbcDriver";
	String SQL_URL = "jdbc:hsqldb:hsql://localhost/localDB";

	public List<Alumno> getAlumnos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		List<Alumno> alumnos = new ArrayList<Alumno>();

		try {
	
			// Obtenemos la conexión a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from alumno");
			rs = ps.executeQuery();

			while (rs.next()) {
				Alumno alumno = new Alumno();
				alumno.setId(rs.getLong("ID"));
				alumno.setNombre(rs.getString("NOMBRE"));
				alumno.setApellidos(rs.getString("APELLIDOS"));
				alumno.setEmail(rs.getString("EMAIL"));
				alumno.setIduser(rs.getString("IDUSER"));
				
				alumnos.add(alumno);
			}
		}
       catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		} finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return alumnos;
	}

	public void delete(Long id) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("delete from alumno where id = ?");
			
			ps.setLong(1, id);

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Alumno " + id + " not found");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}

	public Alumno findById(Long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		Alumno alumno = null;
		
		try {
			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement("select * from alumno where id = ?");
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			if (rs.next()) {
				alumno = new Alumno();
				
				alumno.setId(rs.getLong("ID"));
				alumno.setNombre(rs.getString("NOMBRE"));
				alumno.setApellidos(rs.getString("APELLIDOS"));
				alumno.setEmail(rs.getString("EMAIL"));
				alumno.setIduser(rs.getString("IDUSER"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (rs != null) {try{ rs.close(); } catch (Exception ex){}};
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
		
		return alumno;
	}

	public void save(Alumno a) throws AlreadyPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"insert into alumno (nombre, apellidos, iduser, email) " +
					"values (?, ?, ?, ?)");
			
			ps.setString(1, a.getNombre());
			ps.setString(2, a.getApellidos());
			ps.setString(3, a.getIduser());
			ps.setString(4, a.getEmail());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new AlreadyPersistedException("Alumno " + a + " already persisted");
			} 

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}

	public void update(Alumno a) throws NotPersistedException {
		PreparedStatement ps = null;
		Connection con = null;
		int rows = 0;
		
		try {
			// Obtenemos la conexi��n a la base de datos.
			Class.forName(SQL_DRV);
			con = DriverManager.getConnection(SQL_URL, "sa", "");
			ps = con.prepareStatement(
					"update alumno " +
					"set nombre = ?, apellidos = ?, iduser = ?, email = ?" +
					"where id = ?");
			
			ps.setString(1, a.getNombre());
			ps.setString(2, a.getApellidos());
			ps.setString(3, a.getIduser());
			ps.setString(4, a.getEmail());
			ps.setLong(5, a.getId());

			rows = ps.executeUpdate();
			if (rows != 1) {
				throw new NotPersistedException("Alumno " + a + " not found");
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PersistenceException("Driver not found", e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new PersistenceException("Invalid SQL or database schema", e);
		}
		finally  {
			if (ps != null) {try{ ps.close(); } catch (Exception ex){}};
			if (con != null) {try{ con.close(); } catch (Exception ex){}};
		}
	}

}
