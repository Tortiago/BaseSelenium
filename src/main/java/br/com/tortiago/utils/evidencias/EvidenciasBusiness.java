package br.com.tortiago.utils.evidencias;

import static br.com.tortiago.constantes.ConstMsgInformativas.MSG_ERRO_FLUXO_TESTE;
import static br.com.tortiago.constantes.ConstMsgInformativas.MSG_EVIDENCIAS;
import static br.com.tortiago.constantes.ConstMsgInformativas.MSG_LOG_INFO;
import static br.com.tortiago.utils.LocalDateTimeUtils.getData_DDMMYY_Atual;
import static br.com.tortiago.utils.LocalDateTimeUtils.getHoraMinutoAtual;
import static br.com.tortiago.utils.email.EnviaEmail.enviaeEmail;
import static br.com.tortiago.utils.evidencias.ConstEvidencias.ASWM;
import static br.com.tortiago.utils.evidencias.ConstEvidencias.DIR_IMG;
import static br.com.tortiago.utils.evidencias.ConstEvidencias.EXTENSAO_HTML;
import static br.com.tortiago.utils.evidencias.ConstEvidencias.EXTENSAO_PDF;
import static br.com.tortiago.utils.evidencias.ConstEvidencias.EXTENSAO_PNG;
import static br.com.tortiago.utils.evidencias.ConstEvidencias.EXTENSAO_TXT;
import static br.com.tortiago.utils.evidencias.ConstEvidencias.FALHA;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.addNomePrintPNG;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.addPathImgPrint;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.getDirEvidencias;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.getDirImgEvidencias;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.getExtent;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.getPathImgPrint;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.getTest;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.setDirEvidencias;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.setDirImgEvidencias;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.setExtent;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.setNomeLogTXT;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.setNomeRelatorioPDF;
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.setTest;
import static br.com.tortiago.utils.evidencias.TXT_business.alteraNomeLog_Falhou;
import static br.com.tortiago.utils.evidencias.TXT_business.salvaLogTxt;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Business.addFluxoPDF;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getContFluxo;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getNomeTest;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.setMsgErro;
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.setStatus;
import static br.com.tortiago.utils.modelo.BaseTela.getDriver;
import static java.io.File.separator;
import static org.apache.commons.io.FileUtils.copyFile;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.itextpdf.text.DocumentException;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

import br.com.tortiago.utils.evidencias.pdf.PDF_Business;

public class EvidenciasBusiness {

	private static final Logger log = Logger.getLogger(EvidenciasBusiness.class.getName());
	public static PDF_Business pdf = new PDF_Business();
	
	// ### DEFINE | DIRETORIO | PDF | TXT | PRINT | 
	private static void defineDirEvidencias(String nomeTest) {
		String dir = ASWM + getData_DDMMYY_Atual() + separator + nomeTest + separator + getHoraMinutoAtual() + separator;
		String dirPrint = dir + DIR_IMG;
		setDirEvidencias(dir);
		setDirImgEvidencias(dirPrint);
		criaDirEvidencias(dirPrint);
	}
	
	private static void inicializaExtentReport(String testName) {
		String dir = ASWM + getData_DDMMYY_Atual();
		setExtent(new ExtentReports(dir + separator + getData_DDMMYY_Atual() + EXTENSAO_HTML, false));
		getExtent().loadConfig(new File("Config_ExtentReport.xml"));
		setTest(getExtent().startTest(testName));
	}
		
	private static void defineNomeRelatorioPDF(String nomeTest) {
		String pdf = getDirEvidencias() + nomeTest + EXTENSAO_PDF; 
		setNomeRelatorioPDF(pdf);
	}
	
	private static void defineNomeRelatorioPDFError() {
		String pdf = getDirEvidencias() + getNomeTest() + FALHA + EXTENSAO_PDF; 
		setNomeRelatorioPDF(pdf);
	}
	
	private static void defineNomeLogTxt(String nomeTest) {
		String txt = getDirEvidencias() + nomeTest + EXTENSAO_TXT;
		setNomeLogTXT(txt);
	}
	
	public static String defineNomeLogTxtError() {
		String txtError = getDirEvidencias() + getNomeTest() + FALHA + EXTENSAO_TXT;
		setNomeLogTXT(txtError);
		return txtError;
	}
	
	public static String defineCaminhoImgPrint(String imgPrint) {
		String print = getDirImgEvidencias() + imgPrint + EXTENSAO_PNG;
		addNomePrintPNG(imgPrint);
		addPathImgPrint(print);
		return print;
	}
	
	
	public static void getScreenshot(String imgPrint) {
		File evidence = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			copyFile(evidence, new File(defineCaminhoImgPrint(imgPrint)));
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * Cria um novo diretorio para centralizar as evidencias do teste
	 */
	private static void criaDirEvidencias(String dir) {
		File diretorio = new File(dir);
		diretorio.mkdirs();

		try {
			diretorio.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inicializa as principais variaveis de Evidencias
	 * @param nomeTest Nome do teste que está em execucao
	 */
	public static void iniEvidencias(String nomeTest) {
		defineDirEvidencias(nomeTest);
		defineNomeRelatorioPDF(nomeTest);
		defineNomeLogTxt(nomeTest);
		inicializaExtentReport(nomeTest);
	}
	
	public static void salvaRegistro(int cod, String cont) throws IOException {
		addFluxoPDF(cod, cont);
		salvaLogTxt(cont);
		log.info(MSG_LOG_INFO + cont);
		getTest().log(LogStatus.PASS, cont);
		if(cod==0){
			getScreenshot(cont);
		}
	}
	
	public static void salvaLogError(String msgErro) throws IOException, DocumentException {
		setStatus(false);
		defineNomeRelatorioPDFError();
		alteraNomeLog_Falhou();
		int i = getContFluxo().size() -1;
		int j = getPathImgPrint().size() -1;
		String error = MSG_ERRO_FLUXO_TESTE + getContFluxo().get(i) + "*\r " + msgErro;
		setMsgErro(error);
		salvaLogTxt(error);
		log.severe(error  + MSG_EVIDENCIAS + getDirEvidencias());
		getTest().log(LogStatus.FAIL, getTest().addScreenCapture(getPathImgPrint().get(j)) + error);
		pdf.criaPdf();
		getExtent().endTest(getTest());
		getExtent().flush();
		enviaeEmail(error);
		throw new IOException(error);
	}
	
}
