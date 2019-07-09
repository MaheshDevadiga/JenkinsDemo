package takeScreenshot;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
@Listeners(ListenersClass.class)
public class ScreenshotDemo extends BaseClass{
	BaseClass bClass=new BaseClass();
	
	@Test(priority=1,description="Take screenshot",enabled=true)
	@Severity(SeverityLevel.NORMAL)
	@Description("This test case is for the demo of the screenshot")
	@Story("Story Name: To demonstrate the use of allure report")
	public void test1() {
		driver.findElement(By.name("username")).sendKeys("admin");
		log.debug("Entered username");
		driver.findElement(By.name("pwd")).sendKeys("manager");
		log.debug("Entered password");
		driver.findElement(By.id("loginButton")).click();
		log.debug("Clicked on login button");
		Assert.assertEquals(true, true);
		log.debug("asserted the condition");
		WebDriverWait wait=new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='content users']//img[@class='sizer']")));
		driver.findElement(By.xpath("//a[@class='content users']//img[@class='sizer']")).click();
	}

	@BeforeTest
	@Step("Initializing the browser")
	public void beforeTest() {
		bClass.initialization();
	}

	@AfterTest
	@Step("Closing the browser")
	public void afterTest() {
		driver.quit();
		log.debug("Quit the driver");
	}

}
