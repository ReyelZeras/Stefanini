package br.com.stefanini.vulnerabilidades.persistence;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.stefanini.vulnerabilidades.entity.Vulnerabilidades;
import br.com.stefanini.vulnerabilidades.persistence.interfaces.IVulnerabilidadesDAO;

public class ArquivoDAO extends DAO implements IVulnerabilidadesDAO {

	@Override
	public void insertTeste(Vulnerabilidades v) throws Exception {
		open();
		try {
			stmt = con.prepareStatement("insert into teste values (null, ?, ?, ?);",
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

	@Override
	public List<Vulnerabilidades> bucarVulnerabilidadeNaoCadastradas(String nomeArquivo) throws Exception {
		open();
		stmt = con.prepareStatement("select t.Nome, t.Quantidade from teste t where "
				+ "t.Nome not in (select d.Nome from dados d where t.nomeArquivo = ?); ");
		stmt.setString(1, nomeArquivo);
		rs = stmt.executeQuery();
		List<Vulnerabilidades> vul = new ArrayList<>();
		while (rs.next()) {
			Vulnerabilidades v = new Vulnerabilidades();
			v.setVulnerabilidade(rs.getString(1));
			v.setQuantidade(rs.getString(2));
			v.setNomeArquivo(nomeArquivo);
			vul.add(v);
		}
		close();
		return vul;
	}

	@Override
	public List<Vulnerabilidades> bucarRelatorio(String nomeArquivo) throws Exception {
		open();
		stmt = con.prepareStatement("select t.Nome, d.Criticidade, t.Quantidade, (t.Quantidade * d.Horas) "
				+ "as TotalHoras from teste t inner join dados d on d.Nome = t.Nome where " + "t.nomeArquivo = ?;");
		stmt.setString(1, nomeArquivo);
		rs = stmt.executeQuery();
		List<Vulnerabilidades> vul = new ArrayList<>();
		while (rs.next()) {
			Vulnerabilidades v = new Vulnerabilidades();
			v.setVulnerabilidade(rs.getString(1));
			v.setCriticidade(rs.getString(2));
			v.setQuantidade(rs.getString(3));
			v.setTotalHoras(rs.getString(4));
			v.setNomeArquivo(nomeArquivo);
			vul.add(v);
		}
		close();
		return vul;
	}

//	public Boolean buscarArquivo(String nomeArquivo) throws Exception {
//		try {
//			open();
//			stmt = con.prepareStatement("select nomeArquivo from teste where nomeArquivo = ?");
//			stmt.setString(1, nomeArquivo);
//			rs = stmt.executeQuery();
//			Vulnerabilidades vul = null;
//			while (rs.next()) {
//				Vulnerabilidades v = new Vulnerabilidades();
//				v.setNomeArquivo(rs.getString(1));
//			}
//			return true;
//		} catch (Exception ex) {
//			return false;
//		} finally {
//			close();
//		}
//	}

}
