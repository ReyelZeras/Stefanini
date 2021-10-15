package br.com.stefanini.vulnerabilidades.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.stefanini.vulnerabilidades.entity.Vulnerabilidades;
import br.com.stefanini.vulnerabilidades.persistence.interfaces.IVulnerabilidadesDAO;

public class ArquivoDAO extends DAO implements IVulnerabilidadesDAO {

	public void insertTeste(Vulnerabilidades v) throws Exception {
		open();
		try {
			stmt = con.prepareStatement(
					"insert into teste values (null, ?, ?, ?);",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, v.getVulnerabilidade());
			stmt.setString(2, v.getQuantidade());
			stmt.setString(3, v.getNomeArquivo());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Deu ruim no banco");
		} finally {
			close();
		}
	}
	
	public List<Vulnerabilidades> bucarRelatorio(String nomeArquivo) throws Exception {
		open();
		stmt = con.prepareStatement("select * from teste t inner join "
				+ "dados d on d.Nome = t.Nome where t.nomeArquivo = ?;");
		stmt.setString(1, nomeArquivo);
		rs = stmt.executeQuery();
		List<Vulnerabilidades> vul = new ArrayList<>();
		while (rs.next()) {
			Vulnerabilidades v = new Vulnerabilidades();
			v.setId(rs.getLong(1));
			v.setVulnerabilidade(rs.getString(2));
			v.setQuantidade(rs.getString(3));
			v.setNomeArquivo(rs.getString(4));
			vul.add(v);
		}
		close();
		return vul;
	}

	@Override
	public List<Vulnerabilidades> findAllVulnerabilidades() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vulnerabilidades findById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String createVulnerabilidade(Vulnerabilidades vulnerabilidades) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateVulnerabilidade(Vulnerabilidades vulnerabilidades) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteVulnerabilidade(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
