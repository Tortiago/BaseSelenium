package br.com.tortiago.utils.selenium;

import static br.com.tortiago.constantes.ConstTagsHTML.TAG_IMG;
import static br.com.tortiago.constantes.ConstTagsHTML.TAG_SELECT;
import static br.com.tortiago.constantes.ConstTagsHTML.TAG_TD;
import static br.com.tortiago.constantes.ConstTagsHTML.TAG_TR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Classe que efetua encapsulamento das funcoes da classe {@link By} do
 * Selenium, com metodos de busca de objetos Web por texto, classe e id
 */
public abstract class ByUtils {

	/**
	 * Constantes que servem como parametro 'elemento' dos metodos, que sao as TAGs
	 * HTML
	 */
	public static final String HTML_STRING_TEXT = "html-string-text";

	/**
	 * Busca elemento {@link By} utilizando o parametro ID
	 * 
	 * @param elemento tag HTML do elemento.
	 * @param id       nome do @id do elemento.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraByID(String elemento, String id) {
		return By.xpath(String.format(".//%1$s[@id='%2$s']", elemento, id));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro ID
	 * 
	 * @param elemento tag HTML do elemento.
	 * @param id       nome do @id do elemento.
	 * @param index    indice do elemento.
	 * @return objeto {@link By}encontrado.
	 */
	public static By encontraByID(String elemento, String id, int index) {
		return By.xpath(String.format("(//%1$s[@id='%2$s'])[%3$d]", elemento, id, index));
	}

	/**
	 * Busca elemento considerando o seletor CSS do elemento
	 * 
	 * @param elemento seletor CSS do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByCssSelector(String elemento) {
		return By.cssSelector(String.format(".%1$s", elemento));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro da classe
	 * 
	 * @param elemento tag HTML do elemento
	 * @param classe   classe do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByClass(String elemento, String classe) {
		return By.xpath(String.format("//%1$s[@class = '%2$s']", elemento, classe));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro da classe, buscando pelo
	 * indice de exibicao
	 * 
	 * @param elemento tag HTML do elemento
	 * @param classe   classe do elemento
	 * @param index    indice do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByClass(String elemento, String classe, int index) {
		return By.xpath(String.format("(//%1$s[@class = '%2$s'])[%3$d]", elemento, classe, index));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro da classe com o metodo
	 * contains()
	 * 
	 * @param elemento tag HTML do elemento
	 * @param classe   classe do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByClassContains(String elemento, String classe) {
		return By.xpath(String.format("//%1$s[contains(@class,'%2$s')]", elemento, classe));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro da classe com o metodo
	 * contains(), buscando pelo indice de exibicao
	 * 
	 * @param elemento tag HTML do elemento
	 * @param classe   classe do elemento
	 * @param index    indice do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByClassContains(String elemento, String classe, int index) {
		return By.xpath(String.format("(//%1$s[contains(@class,'%2$s')])[%3$d]", elemento, classe, index));
	}

	/**
	 * Busca elemento buscando por classe e por texto
	 * 
	 * @param elemento tag HTML do elemento
	 * @param classe   classe do elemento
	 * @param texto    texto do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByClassTextoContains(String elemento, String classe, String texto) {
		return By.xpath(String.format("//%1$s[@class='%2$s' and contains(text(), '%3$s')]", elemento, classe, texto));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro do texto absoluto do
	 * elemento
	 * 
	 * @param elemento tag HTML do elemento
	 * @param texto    texto absoluto do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByTexto(String elemento, String texto) {
		return By.xpath(String.format("//%1$s[text()='%2$s']", elemento, texto));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro do texto absoluto do
	 * elemento, buscando pelo indice de exibicao
	 * 
	 * @param elemento tag HTML do elemento
	 * @param texto    texto absoluto do elemento
	 * @param index    indice do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByTexto(String elemento, String texto, int index) {
		return By.xpath(String.format("(//%1$s[text()='%2$s'])[%3$d]", elemento, texto, index));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro do texto absoluto do
	 * elemento, considerando as letras em minusculo
	 * 
	 * @param elemento tag HTML do elemento
	 * @param texto    texto absoluto do elemento em minusculas
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByTextoLowerCase(String elemento, String texto) {
		return By.xpath(String.format("(//%1$s[contains(lower-case(@title)='%2$s')])", elemento, texto));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro do texto absoluto do
	 * elemento, considerando as letras em minusculo, buscando pelo indice de
	 * exibicao
	 * 
	 * @param elemento tag HTML do elemento
	 * @param texto    texto absoluto do elemento em minusculas
	 * @param index    indice do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByTextoLowerCase(String elemento, String texto, int index) {
		return By.xpath(String.format("(//%1$s[lower-case(@title)='%2$s'])[%3$d]", elemento, texto, index));

	}

	/**
	 * Busca elemento {@link By} utilizando o parametro do texto contido no
	 * elemento, usando o metodo contains()
	 * 
	 * @param elemento tag HTML do elemento
	 * @param texto    texto contido no elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByTextoContains(String elemento, String texto) {
		return By.xpath(String.format("//%1$s[contains(text(),'%2$s')]", elemento, texto));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro do texto contido no
	 * elemento, usando o metodo contains(), buscando pelo indice de exibicao
	 * 
	 * @param elemento tag HTML do elemento
	 * @param texto    texto contido no elemento
	 * @param index    indice do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByTextoContains(String elemento, String texto, int index) {
		return By.xpath(String.format("(//%1$s[contains(text(),'%2$s')])[%3$d]", elemento, texto, index));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro do texto, desconsiderando os
	 * espaaos a esquerda e a direita (normalize-space(.))
	 * 
	 * @param elemento tag HTML do elemento
	 * @param texto    texto do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByTextoTrim(String elemento, String texto) {
		return By.xpath(String.format("//%1$s[normalize-space(.)='%2$s']", elemento, texto));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro do texto, desconsiderando os
	 * espaaos a esquerda e a direita (normalize-space(.))
	 * 
	 * @param elemento tag HTML do elemento
	 * @param texto    texto do elemento
	 * @param index    indice do elemento
	 * @return objeto {@link By} encontrado
	 */
	public static By encontraByTextoTrim(String elemento, String texto, int index) {
		return By.xpath(String.format("(//%1$s[normalize-space(.)='%2$s'])[%3$d]", elemento, texto, index));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro @type
	 * 
	 * @param elemento tag HTML do elemento.
	 * @param type     parametro @type a ser considerado.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraByType(String elemento, String type) {
		return By.xpath(String.format("//%1$s[@type = '%2$s']", elemento, type));
	}

	/**
	 * Busca elemento {@link By} utilizando o parametro @type
	 * 
	 * @param elemento tag HTML do elemento.
	 * @param type     parametro @type a ser considerado.
	 * @param index    indice do elemento.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraByType(String elemento, String type, int index) {
		return By.xpath(String.format("(//%1$s[@type = '%2$s'])[%3$d]", elemento, type, index));
	}

	/**
	 * Busca elemento considerando o parametro IMG SRC
	 * 
	 * @param src parametro SRC da imagem.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraByImgSrc(String src) {
		return By.xpath(String.format("//%1$s[@src='%2$s']", TAG_IMG, src));
	}

	/**
	 * Busca elemento considerando o parametro IMG SRC
	 * 
	 * @param src   parametro SRC da imagem
	 * @param index indice do objeto a ser encontrado.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraByImgSrc(String src, int index) {
		return By.xpath(String.format("(//%1$s[@src='%2$s'])[%3$s]", TAG_IMG, src, index));
	}

	/**
	 * Busca elemento considerando o metodo @role='gridcell' dentro de uma table,
	 * buscando pelo indice da linha.
	 * 
	 * @param index indice da linha a ser considerada na table.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraByTableRow(String classe, int index) {
		return By.xpath(String.format("//%1$s[@class='%2$s']//%3$s[%4$d]", TAG_TR, classe, TAG_TD, index));
	}

	/**
	 * Busca elemento considerando um select, com parametro @size='1', buscando pelo
	 * indice do select.
	 * 
	 * @param index indice do select a ser considerado.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraBySelect(int index) {
		return By.xpath(String.format("(//%1$s[1])[%2$d]", TAG_SELECT, index));
	}

	/**
	 * Busca elemento considerando uma linha de tabela, retornando uma table row.
	 * 
	 * @param idTabela id da tablea a ser considerado.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraLinhasTabela(String idTabela) {
		return By.xpath(String.format("id('%1$s')/tbody/tr", idTabela));
	}

	/**
	 * Busca elemento considerando o atributo @name.
	 * 
	 * @param elemento tag HTML do elemento.
	 * @param nome     valor do atributo @name a ser considerado.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraByName(String elemento, String nome) {
		return By.xpath(String.format("//%1$s[@name='%2$s']", elemento, nome));
	}

	/**
	 * Busca elemento considerando o atributo @name.
	 * 
	 * @param elemento tag HTML do elemento.
	 * @param nome     valor do atributo @name a ser considerado.
	 * @param index    indice do elemento.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraByName(String elemento, String nome, int index) {
		return By.xpath(String.format("(//%1$s[@name='%2$s'])[%3$d]", elemento, nome, index));
	}

	/**
	 * Busca elemento considerando o atributo @formcontrolname.
	 * 
	 * @param elemento tag HTML do elemento.
	 * @param nome     valor do atributo @formcontrolname a ser considerado.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraByFormControlName(String elemento, String nome) {
		return By.xpath(String.format("//%1$s[@formcontrolname='%2$s']", elemento, nome));
	}

	/**
	 * Busca elemento considerando o atributo @formcontrolname.
	 * 
	 * @param elemento tag HTML do elemento.
	 * @param nome     valor do atributo @formcontrolname a ser considerado.
	 * @param index    indice do elemento.
	 * @return objeto {@link By} encontrado.
	 */
	public static By encontraByFormControlName(String elemento, String nome, int index) {
		return By.xpath(String.format("(//%1$s[@formcontrolname='%2$s'])[%3$d]", elemento, nome, index));
	}
	
	public static By encontraByAtributo(String elemento, String atributo, String valor) {
		return By.xpath(String.format(".//%1$s[@%2$s='%3$s']", elemento, atributo, valor));
	}
	
	
}