package br.com.tortiago.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.math3.util.Precision;

/**
 * Classe que facilita a conversao entre Integer e String
 */
public abstract class NumberUtils {
	/**
	 * Metodo que trata e converte um valor int para {@link String}.
	 * 
	 * @param value valor int a ser convertido.
	 * @return {@link String} com o numero.
	 */
	public static String converteIntegerParaString(int value) {
		return Integer.toString(value);
	}

	/**
	 * Metodo que trata e converte um texto {@link String} para int.
	 * 
	 * @param value {@link String} com o valor a ser convertido.
	 * @return int com o texto convertido.
	 */
	public static int converteStringParaInteger(String value) {
		int retorno = 0;

		if ((!value.equals("-")) || (!value.isEmpty())) {
			try {
				retorno = Integer.parseInt(value);
			} catch (NumberFormatException ex) {
				System.out.println("Erro na conversao de String para integer");
				System.out.println("Erro: " + ex.toString());
			}
		}

		return retorno;
	}

	/**
	 * Metodo que trata e converte um valor float para {@link String}.
	 * 
	 * @param value valor float a ser convertido.
	 * @return {@link String} com o numero.
	 */
	public static String converteFloatParaString(float value) {
		return Float.toString(value);
	}

	/**
	 * Metodo que trata e converte valores de {@link String} para float.
	 * 
	 * @param value {@link String} com o valor a ser convertido.
	 * @return float com o valor convertido.
	 */
	public static float converteStringParaFloat(String value) {
		float retorno = 0;
		value = removeCaracteresFloat(value);

		if ((!value.equals("-")) || (!value.isEmpty())) {
			try {
				retorno = Float.parseFloat(value);
			} catch (NumberFormatException ex) {
				System.out.println("Erro na conversao de String para float");
				System.out.println("Erro: " + ex.toString());
			}
		}

		return retorno;
	}

	/**
	 * Metodo que trata e converte valores de {@link String} para o wrapper
	 * {@link Double}.
	 * 
	 * @param value {@link String} com o valor a ser convertido.
	 * @return {@link Double} com o valor convertido.
	 */
	public static Double converteStringParaDouble(String value) {
		double retorno = 0;
		value = removeCaracteresFloat(value);
		value = transformaVirgulaEmPonto(value);

		if ((!value.equals("-")) || (!value.isEmpty())) {
			try {
				retorno = Double.parseDouble(value);
			} catch (NumberFormatException ex) {
				System.out.println("Erro na conversao de String para double");
				System.out.println("Erro: " + ex.toString());
			}
		}

		return retorno;
	}

	/**
	 * Metodo que converte de double para {@link String}.
	 * 
	 * @param valorDouble
	 * @return
	 */
	public static String converteDoubleParaString(double valorDouble) {
		int valorInt = Double.valueOf(valorDouble).intValue();

		return Integer.valueOf(valorInt * 100).toString();
	}

	/**
	 * Metodo que trata e soma valores em um ArrayList de String
	 * 
	 * @param listaItens ArrayList com os valores em String
	 * @return String com os valores somados
	 */
	public static String somaItemsListaString(List<String> listaItens) {
		List<Integer> listaNumeros = new ArrayList<Integer>();

		for (String item : listaItens) {
			listaNumeros.add(NumberUtils.converteStringParaInteger(item));
		}

		int soma = 0;

		for (Integer numero : listaNumeros) {
			soma += numero;
		}

		return NumberUtils.converteIntegerParaString(soma);
	}

	/**
	 * Metodo que trata e soma valores em um ArrayList de Strings
	 * 
	 * @param listaItens ArrayList com os valores em String
	 * @return int com os valores somados
	 */
	public static int somaItemsListaInt(List<String> listaItens) {
		List<Integer> listaNumeros = new ArrayList<Integer>();

		for (String item : listaItens) {
			listaNumeros.add(NumberUtils.converteStringParaInteger(item));
		}

		int soma = 0;

		for (Integer numero : listaNumeros) {
			soma += numero;
		}

		return soma;
	}

	/**
	 * Metodo que arredonda um valor decimal em duas casas.
	 * 
	 * @param value float com o valor original.
	 * @return float com o valor arredondado.
	 */
	public static float arredondaDuasCasas(float value) {
		BigDecimal bd = new BigDecimal(Float.toString(value));
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}

	/**
	 * Metodo que arredonda um valor decimal para cima.
	 * 
	 * @param value         float com o valor original.
	 * @param casasDecimais numero de casas decimais para arredondar.
	 * @return float com o valor arredondado.
	 */
	public static float arredondaNumero(float value, int casasDecimais) {
		BigDecimal retorno = new BigDecimal(value).setScale(casasDecimais, BigDecimal.ROUND_HALF_UP);
		return retorno.floatValue();
	}

	/**
	 * Metodo que arredonda um valor decimal para cima.
	 * 
	 * @param value         double com o valor original.
	 * @param casasDecimais numero de casas decimais para arredondar.
	 * @return double com valor arredondado.
	 */
	public static double arredondaNumero(double value, int casasDecimais) {
		BigDecimal retorno = new BigDecimal(value).setScale(casasDecimais, BigDecimal.ROUND_HALF_UP);
		return retorno.doubleValue();
	}

	/**
	 * Metodo que tranforma um valor decimal em porcentagem.
	 * 
	 * @param valor float com o valor original.
	 * @return float com o valor em porcentagem.
	 */
	public static float converteParaPorcentagem(float valor) {
		float retorno = Precision.round(valor, 2) * 100;

		return retorno;
	}

	/**
	 * Metodo que remove caracteres invalidos de um texto.
	 * 
	 * @param texto {@link String} com o texto original.
	 * @return {@link String} com o texto transformado.
	 */
	public static String removeCaracteres(String texto) {
		return texto.replaceAll("\\D+", "");
	}

	/**
	 * Metodo que remove caractere de %.
	 * 
	 * @param texto {@link String} com o texto original.
	 * @return {@link String} com o texto transformado.
	 */
	public static String removeCaracteresFloat(String texto) {
		return texto.replaceAll("%", "");
	}

	/**
	 * Metodo que transforma o caractere virgula (,) em ponto (.).
	 * 
	 * @param texto {@link String} com o texto original.
	 * @return {@link String} com o texto transformado.
	 */
	public static String transformaVirgulaEmPonto(String texto) {
		if (!texto.contains(","))
			return texto;

		texto = texto.replace(".", ";");
		texto = texto.replace(",", ".");
		texto = texto.replace(";", ",");

		return texto;
	}

	/**
	 * Metodo que gera numeros inteiros aleatorios.
	 * 
	 * @return int com valor entre 1 e 100.
	 */
	public static int pegaNumeroAleatorio() {
		int retorno = ThreadLocalRandom.current().nextInt(1, 101);
		return retorno;
	}

	/**
	 * Metodo que gera numeros inteiros aleatorios.
	 * 
	 * @param valorMinimo valor minimo a ser gerado
	 * @param valorMaximo valor maximo a ser gerado (inclusivo)
	 * @return int com valor aleatorio entre os dois valores enviados como
	 *         parametro.
	 */
	public static int pegaNumeroAleatorio(int valorMinimo, int valorMaximo) {
		int retorno = ThreadLocalRandom.current().nextInt(valorMinimo, valorMaximo + 1);
		return retorno;
	}

	/**
	 * Metodo que gera numeros inteiros aleatorios.
	 * 
	 * @param valorMinimo valor minimo a ser gerado
	 * @param valorMaximo valor maximo a ser gerado (inclusivo)
	 * @return long com valor aleatorio entre os dois valores enviados como
	 *         parametro.
	 */
	public static long pegaNumeroAleatorio(long valorMinimo, long valorMaximo) {
		long retorno = ThreadLocalRandom.current().nextLong(valorMinimo, valorMaximo + 1);
		return retorno;
	}
}