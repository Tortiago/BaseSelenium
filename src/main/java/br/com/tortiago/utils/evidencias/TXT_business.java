package br.com.tortiago.utils.evidencias;

import static br.com.tortiago.utils.LocalDateTimeUtils.getHoraAtual;
import static br.com.tortiago.utils.evidencias.EvidenciasBusiness.defineNomeLogTxtError;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.getNomeLogTXT;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TXT_business {

	/**
	 * Salva o texto no arquivo de Log corrente.
	 * @param registro Mensagem informativa
	 */
	public static void salvaLogTxt(String registro) throws IOException {
		try {
			FileWriter fw = new FileWriter((getNomeLogTXT()), true);
			BufferedWriter conexao = new BufferedWriter(fw);
			conexao.write(getHoraAtual() + " - " + registro);
			conexao.newLine();
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void alteraNomeLog_Falhou() {
		File oldFile = new File(getNomeLogTXT());
		File newFile = new File(defineNomeLogTxtError());
		oldFile.renameTo(newFile);
	}

}
