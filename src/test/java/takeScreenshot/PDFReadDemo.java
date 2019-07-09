package takeScreenshot;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PDFReadDemo {

	ChromeDriver driver;
	
	@BeforeTest
	public void url() {
		System.setProperty("webdriver.chrome.driver", "D:\\Edureka\\Driver\\chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		driver = new ChromeDriver(options);
		driver.get("file:///D:/E-Books/advanced_syllabus_.pdf");
		driver.manage().window().maximize();
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void readDemo() {

		try {
			URL url=new URL(driver.getCurrentUrl());
			InputStream is=url.openStream();
			BufferedInputStream bis=new BufferedInputStream(is);
			PDDocument document=null;
			document=PDDocument.load(bis);
			String pdfContent=new PDFTextStripper().getText(document);
			System.out.println("PDF Doucment Contents="+pdfContent);
			Assert.assertTrue(pdfContent.contains("mahesh devadiga"), "fail");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
