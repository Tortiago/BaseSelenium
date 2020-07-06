package br.com.tortiago.arquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeArquivoTxt {

	
	public static List<String> leArquivoTXT(String caminhoArquivo) {
		try {
			
			List<String> registros = new ArrayList<String>();
			File arquivo = new File(caminhoArquivo);
			FileReader lerArquivo = new FileReader(arquivo);
			BufferedReader read = new BufferedReader(lerArquivo);

			/*
			 * Fazemos um loop linha a linha do arquivo enquanto ele seja diferente de null
			 * o método readLine() devolve a linha na posição do loop para a variavel linha
			 */
			while (read.ready()) {
				String texto = read.readLine();
				registros.add(texto);
			}
			read.close();
			return registros;
		} catch (IOException e) {
			System.out.println("Arquivo não encontrado");
		}
		return null;
	}
}
