package br.com.tortiago.utils.modelo;

import static br.com.tortiago.utils.selenium.WebDriverUtils.instanciaChromeDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.com.tortiago.utils.selenium.ElementoWebUtils;

/**
 * PageObject base do projeto. Todo PageObject deve herdar desta classe.
 */
public abstract class BaseTela {
	protected static WebDriver driver;
	protected static ElementoWebUtils elemento;

	/**
	 * Construtor da classe.
	 * 
	 * @param driver Objeto {@link WebDriver} do Selenium.
	 */
	public BaseTela(WebDriver driver) {
		driver = getDriver();
		elemento = new ElementoWebUtils();
	}

	/**
	 * Metodo que retorna o WebDriver.
	 * 
	 * @return retorna o {@link WebDriver} instanciado no pageObject.
	 */
	public static WebDriver getDriver() {
		if (driver == null) {
			return driver = instanciaChromeDriver();
		} else {
			return driver;
		}
	}

	/**
	 * Metodo que acessa uma URL no browser.
	 * 
	 * @param url Endereco web da pagina a ser acessada.
	 */
	public static void navega(String url) {
		getDriver().get(url);
	}

	/**
	 * Metodo que efetua uma pausa no script.
	 * 
	 * @param milissegundos quantidade de milissegundos em long.
	 */
	public static void sleep(long milissegundos) {
		try {
			Thread.sleep(milissegundos);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Metodo que efetua uma pausa no script.
	 * 
	 * @param segundos quantidade de segundos em int.
	 */
	public static void sleep(int segundos) {
		try {
			Thread.sleep(TimeUnit.SECONDS.toMillis(segundos));
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * Metodo que maximiza a janela do browser.
	 */
	public void maximizaBrowser() {
		getDriver().manage().window().maximize();
	}

	/**
	 * Metodo que efetua o fechamento da instancia do browser.
	 */
	public static void fecha() {
		try {
			getDriver().close();
		} catch (Exception e) {
			System.out.println("Erro ao chamar o metodo close(): " + e.toString());
		}

		try {
			getDriver().quit();
		} catch (Exception e) {
			System.out.println("Erro ao chamar o metodo quit(): " + e.toString());
		}
	}

	/**
	 * Metodo que retorna instancia do utilitario de manipulacao de elementos web.
	 * 
	 * @return objeto {@link ElementoWebUtils}.
	 */
	public static ElementoWebUtils getElemento() {
		return elemento;
	}

	/**
	 * Metodo que altera o timeout implicito do Selenium.
	 * 
	 * @param tempo tempo em segundos de TimeOut.
	 */
	public void setTimeOut(int tempo) {
		getDriver().manage().timeouts().implicitlyWait(tempo, TimeUnit.SECONDS);
	}

	/**
	 * Metodo que retorna a URL da pagina atual.
	 * 
	 * @return {@link String} com a URL atual.
	 */
	public String pegaUrlAtual() {
		return getDriver().getCurrentUrl();
	}

	/**
	 * Metodo que aguarda o carregamento completo da pagina.
	 */
	protected void aguardaCarregamento() {
		new WebDriverWait(getDriver(), 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	/**
	 * Metodo que insere uma acao com a tecla PAGE UP.
	 */
	public void inserePageUp() {
		new Actions(getDriver()).sendKeys(Keys.PAGE_UP).perform();
	}

	/**
	 * Metodo que insere uma acao com a tecla PAGE DOWN.
	 */
	public void inserePageDown() {
		new Actions(getDriver()).sendKeys(Keys.PAGE_DOWN).perform();
	}
}