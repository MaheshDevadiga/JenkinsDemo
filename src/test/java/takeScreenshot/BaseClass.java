package takeScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseClass {
	
	public static WebDriver driver;
	public static Logger log;
	
	
	
	public void initialization() {
		String configFilePath="D:\\Edureka\\Demo\\src\\main\\java\\resources\\log4j.properties";
		PropertyConfigurator.configure(configFilePath);
		System.setProperty("webdriver.chrome.driver", "D:\\Edureka\\Driver\\chromedriver.exe");
		log=Logger.getLogger("devpinoyLogger");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		log.debug("Initiating Chrome Browser");
		driver.manage().deleteAllCookies();
		log.debug("Deleting all the cookies");
		driver.manage().window().maximize();
		log.debug("Maximizing the windows");
		/*driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);*/
		driver.get("http://127.0.0.1:85/");
		log.debug("Launching the website");
	}
	
	public void failed() {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File("./Screenshots/Screen.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
