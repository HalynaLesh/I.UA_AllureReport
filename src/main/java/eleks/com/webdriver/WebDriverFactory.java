package eleks.com.webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import eleks.com.util.ConfigProperties;

public class WebDriverFactory {

	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";
	private static final String IE = "ie";

	private static WebDriver webDriver;

	private WebDriverFactory() {

	}

	public static WebDriver getInstance() {
		if (webDriver == null) {
			setFireFoxDriver();
			webDriver = new FirefoxDriver();
		}
		return webDriver;
		
	}

	public static WebDriver getInstance(String browser) throws Exception {
		if (webDriver == null) {
			if (FIREFOX.equals(browser)) {
				setFireFoxDriver();
				webDriver = new FirefoxDriver();
			} else if (CHROME.equals(browser)) {
				setChromeDriver();
				webDriver = new ChromeDriver();
			} else if (IE.equals(browser)) {
				setIEDriver();
				webDriver = new InternetExplorerDriver();
			} else
				throw new Exception(
						"Invalid browser property set in configuration file");
			webDriver.manage().window().maximize();
			webDriver
					.manage()
					.timeouts()
					.implicitlyWait(
							Integer.parseInt(ConfigProperties
									.getProperties("wait")), TimeUnit.SECONDS);
		}
		return webDriver;
	}

	private static void setFireFoxDriver() {
		String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
		String fireFoxBinary = "src/main/resources/1drivers/firefox/geckodriver"
				+ (os.equals("win") ? ".exe" : "");
		System.setProperty("webdriver.gecko.driver", fireFoxBinary);

	}

	private static void setIEDriver() {
		String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
		String ieBinary = "src/main/resources/1drivers/ie/IEDriverServer"
				+ (os.equals("win") ? ".exe" : "");
		System.setProperty("webdriver.ie.driver", ieBinary);

	}

	private static void setChromeDriver() {
		String os = System.getProperty("os.name").toLowerCase().substring(0, 3);
		String chromeBinary = "src/main/resources/1drivers/chrome/chromedriver"
				+ (os.equals("win") ? ".exe" : "");
		System.setProperty("webdriver.chrome.driver", chromeBinary);

	}

	public static void killDriverInstance() {
		if (webDriver != null) {
			webDriver.close();
			webDriver.quit();
		}
	}

}
