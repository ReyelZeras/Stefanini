package br.com.stefanini.vulnerabilidades.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.stefanini.vulnerabilidades.persistence.interfaces.IDAO;

public class DAO implements IDAO {

	Connection con;
	PreparedStatement stmt;
	ResultSet rs;

	public void open() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Stefanini", "root", "senha");
	}

	public void close() throws Exception {
		con.close();

	}

}
