package br.com.tortiago.utils;

public class MetodosUtils {
	
	public static String obtemNomeTeste(){
		return Thread.currentThread().getStackTrace()[3].getMethodName();
	}

	public static String getNomeDoMetodo() {
		return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
}
