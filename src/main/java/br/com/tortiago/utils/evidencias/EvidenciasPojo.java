package br.com.tortiago.utils.evidencias;

import java.util.ArrayList;
import java.util.List;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class EvidenciasPojo {

	private static String dirEvidencias;
	private static String dirImgEvidencias;
	private static String nomeRelatorioPDF;
	private static String nomeLogTXT;
	private static List<String> nomePrintPNG;
	private static List<String> pathImgPrint;
	private static ExtentReports extent;
	private static ExtentTest test;
	
	public static String getDirEvidencias() {
		return dirEvidencias;
	}
	public static void setDirEvidencias(String dirEvidencias) {
		EvidenciasPojo.dirEvidencias = dirEvidencias;
	}
	public static String getDirImgEvidencias() {
		return dirImgEvidencias;
	}
	public static void setDirImgEvidencias(String dirImgEvidencias) {
		EvidenciasPojo.dirImgEvidencias = dirImgEvidencias;
	}
	public static String getNomeRelatorioPDF() {
		return nomeRelatorioPDF;
	}
	public static void setNomeRelatorioPDF(String nomeRelatorioPDF) {
		EvidenciasPojo.nomeRelatorioPDF = nomeRelatorioPDF;
	}
	public static String getNomeLogTXT() {
		return nomeLogTXT;
	}
	public static void setNomeLogTXT(String nomeLogTXT) {
		EvidenciasPojo.nomeLogTXT = nomeLogTXT;
	}
	public static List<String> getNomePrintPNG() {
		return nomePrintPNG;
	}
	public static void addNomePrintPNG(String nomePrintPNG) {
		if(EvidenciasPojo.nomePrintPNG==null) {
			EvidenciasPojo.nomePrintPNG = new ArrayList<String>();
		}
		EvidenciasPojo.nomePrintPNG.add(nomePrintPNG);
	}
	public static List<String> getPathImgPrint() {
		return pathImgPrint;
	}
	public static void addPathImgPrint(String pathImgPrint) {
		if(EvidenciasPojo.pathImgPrint == null) {
			EvidenciasPojo.pathImgPrint = new ArrayList<String>();
		}
		EvidenciasPojo.pathImgPrint.add(pathImgPrint);
	}
	public static ExtentReports getExtent() {
		return extent;
	}
	public static void setExtent(ExtentReports extent) {
		EvidenciasPojo.extent = extent;
	}
	public static ExtentTest getTest() {
		return test;
	}
	public static void setTest(ExtentTest test) {
		EvidenciasPojo.test = test;
	}
	
}
