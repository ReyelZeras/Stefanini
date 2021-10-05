package br.com.stefanini.vulnerabilidades.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.stefanini.vulnerabilidades.entity.BaseDados;
import br.com.stefanini.vulnerabilidades.persistence.interfaces.IBaseDadosDAO;

public class BaseDadosDAO extends DAO implements IBaseDadosDAO {

	public void insertTeste(BaseDados bd) throws Exception {
		open();
		try {
			stmt = con.prepareStatement(
					"insert into dados values (null, ?, ?, ?);",
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
		// TODO Auto-generated method stub
		return null;
	}

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
	public String updateBaseDados(BaseDados baseDados) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteBaseDados(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
