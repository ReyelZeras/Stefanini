package br.com.stefanini.vulnerabilidades.teste;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import br.com.stefanini.vulnerabilidades.entity.Vulnerabilidades;
import br.com.stefanini.vulnerabilidades.persistence.ArquivoDAO;

public class Teste {

	public static void main(String[] args) throws Exception {

Vulnerabilidades v = new Vulnerabilidades();
		
		ArquivoDAO arq = new ArquivoDAO();

		String path = "C:\\Users\\Reyel Soares\\Documents\\massa de teste.txt";
		FileReader fr = null;
		BufferedReader br = null;

		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);

			String line = br.readLine();
			Long contador = (long) 11;

			while (line != null) {
				line = br.readLine();
				v.setVulnerabilidade(line.split(",")[0]);
				v.setQuantidade(line.split(",")[1]);
				v.setId(contador);
				System.out.println(v);
				contador++;
				arq.insertTeste(v);
				
				
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

}