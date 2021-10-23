package br.com.stefanini.vulnerabilidades.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.stefanini.vulnerabilidades.entity.BaseDados;
import br.com.stefanini.vulnerabilidades.persistence.interfaces.IBaseDadosDAO;

public class BaseDadosDAO extends DAO implements IBaseDadosDAO {

	public void insertTeste(BaseDados bd) throws Exception {
		open();
		try {
			stmt = con.prepareStatement("insert into dados values (null, ?, ?, ?);",
					PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, bd.getNome());
			stmt.setString(2, bd.getCriticidade());
			stmt.setString(3, bd.getHora());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Deu ruim no banco");
		} finally {
			close();
		}
	}

	@Override
	public List<BaseDados> findAllBaseDados() throws Exception {

		open();
		stmt = con.prepareStatement("select * from dados;");
		rs = stmt.executeQuery();
		List<BaseDados> bd = new ArrayList<>();
		while (rs.next()) {
			BaseDados bdados = new BaseDados();
			bdados.setId(rs.getInt(1));
			bdados.setNome(rs.getString(2));
			bdados.setCriticidade(rs.getString(3));
			bdados.setHora(rs.getString(4));
			bdados.setSolucao(rs.getString(5));
			bd.add(bdados);
		}
		close();
		return bd;

	}

	@Override
	public BaseDados findByCriticidade(String nomeCriticidade) throws Exception {
		open();
		stmt = con.prepareStatement("select * from dados where nome=?;");
		stmt.setString(1, nomeCriticidade);
		rs = stmt.executeQuery();
		BaseDados bdados = null;
		if (rs.next()) {
			bdados = new BaseDados();
			bdados.setId(rs.getInt(1));
			bdados.setNome(rs.getString(2));
			bdados.setCriticidade(rs.getString(3));
			bdados.setHora(rs.getString(4));
		}
		close();
		return bdados;
	}
	
	@Override
	public void updateBaseDados(BaseDados baseDados) throws Exception {
		open();
		stmt = con.prepareStatement(
				"update dados set nome=?, criticidade=?, horas=?, solucao=? where idDados=?");
		stmt.setString(1, baseDados.getNome());
		stmt.setString(2, baseDados.getCriticidade());
		stmt.setString(3, baseDados.getHora());
		stmt.setString(4, baseDados.getSolucao());
		stmt.setInt(5, baseDados.getId());
		stmt.executeUpdate();
		close();
	}

//	public List<BaseDados> findByResolucao(String idVulnerabilidade) throws Exception {
//		open();
//		stmt = con.prepareStatement("select idDados, nome, solucao from dados where idDados=?;");
//		stmt.setString(1, idVulnerabilidade);
//		rs = stmt.executeQuery();
//		List<BaseDados> bd = new ArrayList<>();
//		if (rs.next()) {
//			BaseDados bdados = new BaseDados();
//			bdados.setId(rs.getInt(1));
//			bdados.setNome(rs.getString(2));
//			bdados.setSolucao(rs.getString(3));
//			bd.add(bdados);
//		}
//		close();
//		return bd;
//	}
	
	

	@Override
	public BaseDados findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createBaseDados(BaseDados baseDados) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String deleteBaseDados(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
