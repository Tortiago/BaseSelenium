package br.com.tortiago.utils.evidencias.pdf;

import java.util.ArrayList;
import java.util.List;

public class PDF_Pojo {

	private static String nomeTest;
	private static String sistema;
	private static String URL;
	private static String ambiente;
	private static String navegador;
	private static String usuarioLogado;
	private static String objetivoTest;
	private static String dtExec;
	private static String hrIni;
	private static String hrFim;
	private static String tmpTotalExec;
	private static String preCond;
	private static String msgErro;
	private static List<Integer> codfluxo;
	private static List<String> contfluxo;
	private static List<String> dadosTest;
	private static List<String> detExec;
	private static boolean status;
	
	public static String getNomeTest() {
		return nomeTest;
	}
	public static void setNomeTest(String nomeTest) {
		PDF_Pojo.nomeTest = nomeTest;
	}
	public static String getSistema() {
		return sistema;
	}
	public static void setSistema(String sistema) {
		PDF_Pojo.sistema = sistema;
	}
	public static String getURL() {
		return URL;
	}
	public static void setURL(String uRL) {
		URL = uRL;
	}
	public static String getAmbiente() {
		return ambiente;
	}
	public static void setAmbiente(String ambiente) {
		PDF_Pojo.ambiente = ambiente;
	}
	public static String getNavegador() {
		return navegador;
	}
	public static void setNavegador(String navegador) {
		PDF_Pojo.navegador = navegador;
	}
	public static String getUsuarioLogado() {
		return usuarioLogado;
	}
	public static void setUsuarioLogado(String usuarioLogado) {
		PDF_Pojo.usuarioLogado = usuarioLogado;
	}
	public static String getObjetivoTest() {
		return objetivoTest;
	}
	public static void setObjetivoTest(String objetivoTest) {
		PDF_Pojo.objetivoTest = objetivoTest;
	}
	public static String getDtExec() {
		return dtExec;
	}
	public static void setDtExec(String dtExec) {
		PDF_Pojo.dtExec = dtExec;
	}
	public static String getHrIni() {
		return hrIni;
	}
	public static void setHrIni(String hrIni) {
		PDF_Pojo.hrIni = hrIni;
	}
	public static String getHrFim() {
		return hrFim;
	}
	public static void setHrFim(String hrFim) {
		PDF_Pojo.hrFim = hrFim;
	}
	public static String getTmpTotalExec() {
		return tmpTotalExec;
	}
	public static void setTmpTotalExec(String tmpTotalExec) {
		PDF_Pojo.tmpTotalExec = tmpTotalExec;
	}
	public static String getPreCond() {
		return preCond;
	}
	public static void setPreCond(String preCond) {
		PDF_Pojo.preCond = preCond;
	}
	public static List<Integer> getCodFluxo() {
		return codfluxo;
	}
	public static String getMsgErro() {
		return msgErro;
	}
	public static void setMsgErro(String msgErro) {
		PDF_Pojo.msgErro = msgErro;
	}
	public static void addCodFluxo(int cod) {
		if(PDF_Pojo.codfluxo == null) {
			PDF_Pojo.codfluxo = new ArrayList<Integer>();
		}
		PDF_Pojo.codfluxo.add(cod);
	}
	public static List<String> getContFluxo() {
		return contfluxo;
	}
	public static void addContFluxo(String conteudo) {
		if(PDF_Pojo.contfluxo == null) {
			PDF_Pojo.contfluxo = new ArrayList<String>();
		}
		PDF_Pojo.contfluxo.add(conteudo);
	}
	public static List<String> getDadosTest() {
		return dadosTest;
	}
	public static void setDadosTest(List<String> dadosTest) {
		PDF_Pojo.dadosTest = dadosTest;
	}
	public static List<String> getDetExec() {
		return detExec;
	}
	public static void setDetExec(List<String> detExec) {
		PDF_Pojo.detExec = detExec;
	}
	public static boolean isStatus() {
		return status;
	}
	public static void setStatus(boolean status) {
		PDF_Pojo.status = status;
	}
	
}
