package br.com.tortiago.utils.selenium;

import static br.com.tortiago.constantes.ConstTagsHTML.TAG_LI;
import static br.com.tortiago.constantes.ConstTagsHTML.TAG_TD;
import static br.com.tortiago.utils.selenium.ByUtils.encontraByTextoContains;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.tortiago.enums.Formato;
import br.com.tortiago.utils.LocalDateTimeUtils;
import br.com.tortiago.utils.NumberUtils;
import br.com.tortiago.utils.TempoTimeouts;
import br.com.tortiago.utils.modelo.BaseTela;

/**
 * Classe que trabalha com wait do WebElement, permitindo que o objeto esteja
 * clicavel antes de executar o codigo.
 */

public class ElementoWebUtils {

	public static WebDriver getDriver() {
		return BaseTela.getDriver();
	}

	public static void espera(int segundos) {
		try {
			Thread.sleep(segundos * 1000);
		} catch (Exception e) {
		}
	}
	
	/**
	 * Declara wait padrao para utuilizacao na espera implicita na execucao
	 * dos testes automatizados.
	 * Tempo configurado conforme valores encontrados na classe {$ TempoTimeouts}    
	 * @return wait configurado
	 */
	private static Wait<WebDriver> getWait() {
		Wait<WebDriver> wait = (Wait<WebDriver>) new WebDriverWait(getDriver(), TempoTimeouts.TEMPO_WAIT)
				.withTimeout(Duration.ofSeconds(TempoTimeouts.TEMPO_WAIT))
				.pollingEvery(Duration.ofSeconds(TempoTimeouts.TEMPO_POLLING))
				.ignoring(StaleElementReferenceException.class);
		return wait;
	}
	
	/**
	 * @return o codigo HTML da pagina
	 */
	public static String getCodigoHTML() {
		getDriver().getCurrentUrl();
		return getDriver().getPageSource(); 
	}
	/**
	 * Metodo que Aguarda ate que o elemento esteja pronto para uso.
	 * @param by o identificador By do elemento a ser encontrado
	 * @return WebElement encontrado na pagina atraves do By
	 */
	public static WebElement elementoWebAchaElementoClicavel(By by) {
		WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(by));		
		return element;
	}

	/**
	 * Metodo que aguarda ate que o elemento esteja visivel na tela.
	 * 
	 * @param by o identificador By do elemento a ser encontrado
	 * @return WebElement encontrado na pagina atraves do By
	 */
	public static WebElement elementoWebAchaElementoVisivel(By by) {
		WebElement elemento = getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
		return elemento;
	}

	/**
	 * Metodo que acha a referencia WebElement na pagina. Usufrui do recurso
	 * WebDriverWait: A cada x segundos definidos pela constante
	 * {@link TempoTimeouts}.TEMPOPOLLING, sera efetuada uma busca pelo elemento, em
	 * ate y segundos, com timeout definido pela constante
	 * {@link TempoTimeouts}.TEMPOWAIT
	 * 
	 * @param by o identificador By do elemento a ser encontrado
	 * @return lista de WebElements encontrados
	 */
	public List<WebElement> elementoWebAchaElementosWait(By by) {
		List<WebElement> listaElementos = getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		return listaElementos;
	}

	/**
	 * Metodo que executa um Thread.sleep, efetuando uma pausa no teste.
	 * 
	 * @param l quantidade de milissegundos que o teste deve aguardar
	 */
	public static void sleep(long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * metodo para selecao de opcao em combobox
	 * 
	 * @param by
	 * @param option
	 */
	public void elementoWebSelecionaRadio(By by, int option) {
		List<WebElement> radios =getDriver().findElements(by);
		if (option > 0 && option <= radios.size()) {
			WebElement elemento = radios.get(option);
			elemento.click();
		}
	}

	/**
	 * Metodo que permite selecionar uma opcao do combobox pelo index. Seleciona o
	 * indice na lista e aperta o enter.
	 * 
	 * @param by    o identificador By do elemento a ser selecionado
	 * @param index o indice a ser escolhido dentro do combobox
	 */
	public void elementoWebSelecionaListaSelect(By by, int index) {
		try {
			Select listaSelect = new Select(elementoWebAchaElementoClicavel(by));
			listaSelect.selectByIndex(index);
		} catch (Exception ex) {
			System.out.println("Erro ao selecionar opcao por index.");
		}
	}

	/**
	 * Metodo que permite selecionar uma opcao do combobox pelo texto. Seleciona o
	 * texto da lista e aplica o enter.
	 * 
	 * @param by    o identificador By do elemento a ser selecionado
	 * @param texto o texto a ser escolhido dentro do combobox
	 */
	public void elementoWebSelecionaListaSelect(By by, String texto) {
		try {
			Select listaSelect = new Select(elementoWebAchaElementoClicavel(by));
			listaSelect.selectByVisibleText(texto);
		} catch (Exception ex) {
			System.out.println("Erro ao selecionar opcao por texto.");
		}
	}

	/**
	 * Metodo que permite selecionar uma opcao do combobox pelo texto. Seleciona o
	 * texto da lista com um clique.
	 * 
	 * @param by    o identificador By do elemento a ser selecionado.
	 * @param texto o texto a ser escolhido dentro do combobox.
	 */
	public void elementoWebSelecionaListaPorClique(By by, String texto) {
		elementoWebClica(by);
		sleep(TempoTimeouts.TEMPO_PADRAO_TELA);
		By elementoLista = encontraByTextoContains(TAG_LI, texto);
		elementoWebMoveParaOElemento(elementoLista);
		sleep(TempoTimeouts.TEMPO_PADRAO_TELA);
		elementoWebClica(elementoLista);
	}

	/**
	 * Metodo que permite selecionar uma opcao do combobox pelo texto. Seleciona o
	 * texto da lista com movimentacao do teclado atraves do Keys.
	 * 
	 * @param by    objeto {@link By} mapeado.
	 * @param texto texto a ser escolhido dentro do combobox.
	 */
	public void elementoWebSelecionaListaPorKeys(By by, String texto) {
		elementoWebInsereTexto(by, texto);
		sleep(TempoTimeouts.TEMPO_PADRAO_TELA);
		elementoWebInsereTexto(by, Keys.DOWN);
		sleep(TempoTimeouts.TEMPO_PADRAO_TELA);
		elementoWebInsereTexto(by, Keys.ENTER);
	}

	/**
	 * Metodo que permite inserir texto em um input WebElement. Recebe como
	 * parametros o By do elemento e o texto a ser inserido.
	 * 
	 * @param by    o identificador By do elemento a ser selecionado
	 * @param texto o texto a ser inserido dentro do campo de texto
	 */
	public static void elementoWebInsereTexto(By by, String texto) {
		sleep(TempoTimeouts.TEMPO_PADRAO_TELA);
		elementoWebAchaElementoVisivel(by);
		elementoWebAchaElementoClicavel(by).sendKeys(texto);
	}

	/**
	 * Metodo que permite inserir uma key (tecla) em um input WebElement.
	 * 
	 * @param by  o identificador By do elemento a ser selecioando
	 * @param key a key (tecla) a ser inserida no campo de texto
	 */
	public void elementoWebInsereTexto(By by, Keys key) {
		elementoWebAchaElementoClicavel(by).sendKeys(key);
	}

	/**
	 * Metodo que permite utilizar o metodo click() em um WebElement.
	 * 
	 * @param by o identificador By do elemento a ser selecionado
	 */
	public static void elementoWebClica(By by) {
		WebElement elemento = elementoWebAchaElementoClicavel(by);
		elemento.click();
	}

	/**
	 * Metodo que efetua um clique em um elemento, atraves de {@link Actions}.
	 * 
	 * @param by
	 */
	public void elementoWebClicaActions(By by) {
		WebElement elementoSVG = elementoWebAchaElementoClicavel(by);
		Actions acaoSVG = new Actions(getDriver());
		acaoSVG.moveToElement(elementoSVG).click().build().perform();
	}

	/**
	 * Metodo que efetua um clique em um elemento, atraves de
	 * {@link JavascriptExecutor}.
	 * 
	 * @param by objeto {@link By} mapeado.
	 */
	public void elementoWebClicaJavaScript(By by) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
		jsExecutor.executeScript("var evt = document.createEvent('MouseEvents');"
				+ "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
				+ "arguments[0].dispatchEvent(evt);", elementoWebAchaElementoClicavel(by));
	}

	/**
	 * Metodo que permite utilizar o botao ESC em um WebElement.
	 * 
	 * @param by o identificador By do elemento a ser digitado ESC
	 */
	public void pressionaTeclaEsc(By by) {
		elementoWebAchaElementoClicavel(by).sendKeys(Keys.ESCAPE);
	}

	/**
	 * Metodo para buscar determinado item por texto.
	 * 
	 * @param texto o texto a ser buscado.
	 * @return By do elemento da tela
	 */
	public By elementoWebBuscaPorTexto(String texto) {
		String busca = "(//*[text()='%1$s'])";
		sleep(TempoTimeouts.TEMPO_LONGO);
		By elemento = By.xpath(String.format(busca, texto));
		return elemento;
	}

	/**
	 * Metodo que limpa o texto do elemento web.
	 * 
	 * @param by o identificador By do elemento a ser selecionado
	 */
	public void elementoWebLimpa(By by) {
		sleep(TempoTimeouts.TEMPO_PADRAO_TELA);
		elementoWebAchaElementoClicavel(by).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	/**
	 * Metodo que retorna se elemento web esta visivel na tela.
	 * 
	 * @param by o identificador By do elemento a ser selecionado
	 * @return boolean que identifica se o objeto esta visivel ou nao
	 */
	public boolean elementoWebEstaVisivel(By by) {
		try {
			return getDriver().findElement(by).isDisplayed();
		} catch (NoSuchElementException ex) {
			System.out.println("Elemento nao visivel (NoSuchElementException).");
			return false;
		} catch (StaleElementReferenceException ex) {
			System.out.println("Elemento nao visivel (StaleElementReferenceException).");
			return false;
		} catch (Exception ex) {
			System.out.println("Elemento nao visivel " + ex.getMessage());
			return false;
		}
	}

	/**
	 * Metodo que retorna se elemento web esta visivel na tela, utilizando o recurso
	 * WebDriverWait que espera um determinado tempo ate o elemento ficar visivel.
	 * 
	 * @param by o identificador By do elemento a ser selecionado
	 * @return boolean que identifica se o objeto esta visivel ou nao
	 */
	public static boolean elementoWebEstaVisivelWait(By by) {
		try {
			boolean retorno = elementoWebAchaElementoVisivel(by).isDisplayed();
			return retorno;
		} catch (NoSuchElementException ex) {
			System.out.println("Elemento nao visivel (NoSuchElementException).");
			return false;
		} catch (StaleElementReferenceException ex) {
			System.out.println("Elemento nao visivel (StaleElementReferenceException).");
			return false;
		} catch (Exception ex) {
			System.out.println(("Elemento nao visivel " + ex.getMessage()));
			return false;
		}
	}

	/**
	 * Metodo que retorna se elemento web esta visivel na tela.
	 * 
	 * @param by o identificador By do elemento a ser selecionado
	 * @return boolean que identifica se o objeto esta visivel ou nao
	 */
	public boolean elementoWebEstaVisivelWait(WebElement elemento) {
		try {
			return elemento.isDisplayed();
		} catch (NoSuchElementException ex) {
			System.out.println("Elemento nao visivel (NoSuchElementException).");
			return false;
		} catch (StaleElementReferenceException ex) {
			System.out.println("Elemento nao visivel (StaleElementReferenceException).");
			return false;
		} catch (Exception ex) {
			System.out.println("Elemento nao visivel " + ex.getMessage());
			return false;
		}
	}

	/**
	 * Metodo que retorna se elemento web esta habilitado na tela.
	 * 
	 * @param by o identificador By do elemento a ser selecionado
	 * @return o identificador By do elemento a ser selecionado
	 */
	public boolean elementoWebEstaHabilitado(By by) {
		boolean retorno = getDriver().findElement(by).isEnabled();
		return retorno;
	}

	/**
	 * Metodo que retorna se elemento web esta habilitado na tela. Recebe como
	 * parametro o By do elemento. Utiliza o metodo WebDriverWait
	 * 
	 * @param by
	 * @return
	 */
	public boolean elementoWebEstaHabilitadoWait(By by) {
		boolean retorno = elementoWebAchaElementoVisivel(by).isEnabled();
		return retorno;
	}

	/**
	 * Metodo que retorna uma string com o texto do elemento encontrado
	 * 
	 * @param by objeto By da pagina
	 * @return String do texto contido no WebElement encontrado atraves do parametro
	 *         By
	 */
	public String elementoWebPegaTexto(By by) {
		return elementoWebAchaElementoClicavel(by).getText();
	}

	/**
	 * Metodo que retorna um inteiro com o numero do elemento encontrado
	 * 
	 * @param by objeto By da pagina
	 * @return int do texto contido no WebElement encontrado atraves do parametro By
	 */
	public int elementoWebPegaTextoInt(By by) {
		String texto = elementoWebAchaElementoClicavel(by).getText();
		int numero = NumberUtils.converteStringParaInteger(texto);

		return numero;
	}

	/**
	 * Metodo que valida se regiao final das validacoes dos testes esta visivel.
	 * Caso esteja, retorna true. Caso nao esteja, lanca uma NoSuchElementException,
	 * para que o teste falhe
	 * 
	 * @param by objeto By da pagina
	 * @return boolean true caso objeto esteja visivel
	 */
	public boolean procuraRegiaoValidacaoFinal(By by) {
		return elementoWebAchaElementoClicavel(by).isDisplayed();
	}

	/**
	 * Metodo que efetua mouseover dentro de um WebElement.
	 * 
	 * @param by objeto {@link By} mapeado.
	 */
	public void elementoWebMouseOver(By by) {
		WebElement elemento = elementoWebAchaElementoClicavel(by);

		Actions builder = new Actions(getDriver());
		builder.moveToElement(elemento).perform();
	}

	/**
	 * Metodo que efetua um MouseOver no lado direito do elemento.
	 * 
	 * @param by objeto {@link By} mapeado.
	 */
	public void elementoWebMouseOverLadoDireito(By by) {
		WebElement elemento = elementoWebAchaElementoClicavel(by);
		int width = elemento.getSize().width;

		Actions builder = new Actions(getDriver());
		builder.moveToElement(elemento, width, 1).perform();
	}

	/**
	 * Metodo que permite a selecao de uma data em um objeto datepicker.
	 * 
	 * @param by   objeto {@link By} mapeado com o datepicker.
	 * @param date {@link LocalDate} com a data a ser inserida.
	 */
	public void elementoWebSelecionaDatePicker(By by, LocalDate date) {
		String dataBusca = LocalDateTimeUtils.formataData(Formato.DAY, date);

		WebElement dateWidget = elementoWebAchaElementoClicavel(by);
		List<WebElement> colunas = dateWidget.findElements(By.tagName(TAG_TD));

		for (WebElement celula : colunas) {
			if (celula.getText().equals(dataBusca)) {
				celula.click();
				break;
			}
		}
	}

	/**
	 * Metodo que permite a selecao de uma data em um objeto datepicker.
	 * 
	 * @param by   objeto {@link By} mapeado com o datepicker.
	 * @param data {@link String} com a data a ser inserida.
	 */
	public void elementoWebSelecionaDatePicker(By by, String data) {
		LocalDate localDate = LocalDateTimeUtils.converteData(Formato.DDMMYYYY, data);
		this.elementoWebSelecionaDatePicker(by, localDate);
	}

	/**
	 * Metodo que efetua uma acao JavaScript, movendo para o elemento.
	 * 
	 * @param by objeto {@link By} mapeado.
	 */
	public void elementoWebMoveParaOElemento(By by) {
		WebElement elemento = elementoWebAchaElementoClicavel(by);
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", elemento);
	}

	/**
	 * Metodo que permite o envio de arquivos para o servidor.
	 * 
	 * @param by      objeto {@link By} mapeado com a propriedade //*[@type =
	 *                'file'].
	 * @param arquivo objeto {@link File} a ser enviado.
	 */
	public void elementoWebEnviaArquivoUpload(By by, File arquivo) {
		sleep(TempoTimeouts.TEMPO_CURTO);
		try {
			getDriver().findElement(by).sendKeys(arquivo.getCanonicalPath());
		} catch (IOException e) {
			System.out.println("Erro ao enviar imagem ao servidor.");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que permite o envio de arquivos para o servidor.
	 * 
	 * @param by      objeto {@link By} mapeado com a propriedade //*[@type =
	 *                'file']
	 * @param caminho {@link String} com o caminho do arquivo.
	 */
	public void elementoWebEnviaArquivoUpload(By by, String caminho) {
		sleep(TempoTimeouts.TEMPO_CURTO);
		File arquivo = new File(caminho);
		this.elementoWebEnviaArquivoUpload(by, arquivo);
	}

	/**
	 * Metodo que verifica se um objeto esta selecionado.
	 * 
	 * @param by objeto {@link By} mapeado.
	 * @return booolean com o retorno do metodo isSelected().
	 */
	public boolean elementoWebEstaSelecionado(By by) {
		boolean retorno = elementoWebAchaElementoVisivel(by).isSelected();
		return retorno;
	}

	/**
	 * Metodo que tenta efetuar um clique em uma lista de elementos possiveis.
	 * 
	 * @param listaElementos {@link List} de objetos {@link By} com os possiveis
	 *                       elementos a serem clicados.
	 */
	public void elementoWebClicaPossiveisElementos(List<By> listaElementos) {
		for (By elementoBy : listaElementos) {
			try {
				elementoWebClica(elementoBy);
				break;
			} catch (Exception ex) {
				System.out.println("Elemento da lista nao visivel ou nao clicavel.");
				System.out.println("Mensagem de erro: " + ex.toString());
			}

		}
	}
}