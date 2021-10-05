package br.com.stefanini.vulnerabilidades.teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import br.com.stefanini.vulnerabilidades.entity.BaseDados;
import br.com.stefanini.vulnerabilidades.persistence.BaseDadosDAO;

public class Teste2 {
	
public static void main(String[] args) throws Exception {
	
//	BASE DE DADOS
	
	
	BaseDados bd = new BaseDados();
	
	BaseDadosDAO bdDAO = new BaseDadosDAO();

	String path2 = "C:\\Users\\Reyel Soares\\Documents\\base de dados.txt";
	FileReader fr2 = null;
	BufferedReader br2 = null;

	try {
		fr2 = new FileReader(path2);
		br2 = new BufferedReader(fr2);

		String line = br2.readLine();
		Long contador = (long) 11;

		while (line != null) {
			line = br2.readLine();
			bd.setNome(line.split(",")[0]);
			bd.setCriticidade(line.split(",")[1]);
			bd.setHora(line.split(",")[2]);
			bd.setId(contador);
			System.out.println(bd);
			contador++;
			bdDAO.insertTeste(bd);
			
			
		}
	} catch (IOException e) {
		System.out.println("Error: " + e.getMessage());
	} finally {
		try {
			if (br2 != null) {
				br2.close();
			}
			if (fr2 != null) {
				fr2.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}

}
