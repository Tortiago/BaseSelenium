package br.com.tortiago.utils.evidencias.pdf;

import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getAmbiente;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getDtExec;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getHrFim;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getHrIni;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getNavegador;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getSistema;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getTmpTotalExec;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getURL;

import java.util.ArrayList;
import java.util.List;

public class ConstPDF {

	public static final String
		TTL_SEC_OBJETIVO	= "Objetivo:",
		TTL_SEC_PR_COND 	= "Pré-condições:",
		TTL_SEC_EXECUCAO 	= "Dados de Execução:",
		TTL_SEC_TEST 		= "Dados do Teste:",
		TTL_SEC_FLUXO 		= "Fluxo do teste:";
	
	/**
	 * Variaveis utilizadas para criar os detalhes da secao "Dados de execucao" no relatorio em pdf
	 */
	private static final String 
		EXEC_DT 		= "DATA: " 		+ getDtExec(),
		EXEC_HR_INI 	= "HR Ini: " 	+ getHrIni(),
		EXEC_HR_FIM 	= "HR Fim: " 	+ getHrFim(),
		EXEC_NAV 		= "Navegador: " + getNavegador(),
		EXEC_SISTEMA 	= "Sistema: " 	+ getSistema(),
		EXEC_AMBIENTE 	= "Ambiente: "	+ getAmbiente(),
		EXEC_URL 		= "URL: " 		+ getURL(),
		EXEC_TMP_TOTAL 	= "Tempo de execução: " + getTmpTotalExec();
	
	public static List<String> detalhesExec(){
		List<String> detalhes = new ArrayList<String>();
		detalhes.add(EXEC_DT);
		detalhes.add(EXEC_HR_INI);
		detalhes.add(EXEC_HR_FIM);
		detalhes.add(EXEC_TMP_TOTAL);
		detalhes.add(EXEC_NAV);
		detalhes.add(EXEC_SISTEMA);
		detalhes.add(EXEC_AMBIENTE);
		detalhes.add(EXEC_URL);
		return detalhes;
	}
		
}
