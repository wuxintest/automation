package action;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.Browsers;
import browsers.BrowsersType;
import parameter.ParseProperties;
import parameter.Wait;

public class TestAction {
	private WebDriver driver;
	private String projectpath=System.getProperty("user.dir");
	ParseProperties acdata;
	ParseProperties acxpath;
	Wait wait;
	@Parameters({"actiondata","actionxpath"})
	@BeforeClass
	public void lanuchBrowser(String ad,String ap){
		acdata=new ParseProperties(projectpath+ad);
		acxpath=new ParseProperties(projectpath+ap);
		Browsers browser=new Browsers(BrowsersType.firefox);
		driver=browser.driver;
		wait=new Wait(driver);
	}
	@Test
	public void testAction(){
		driver.get(acdata.getValue("url"));
		WebElement iframe=driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iframe);
		wait.waitFor(5000);
		driver.findElement(By.xpath(acxpath.getValue("uesrname"))).clear();
		driver.findElement(By.xpath(acxpath.getValue("uesrname"))).sendKeys(acdata.getValue("username"));
		driver.findElement(By.xpath(acxpath.getValue("password"))).sendKeys(acdata.getValue("password"));
		driver.findElement(By.xpath(acxpath.getValue("submit"))).click();
		wait.waitForElementPresent("//span[@title='收件箱']");
		Actions action=new Actions(driver);
		//鼠标右击
		action.contextClick(driver.findElement(By.xpath("//span[@title='收件箱']"))).build().perform();
		driver.findElement(By.xpath("//span[text()='打开']")).click();
		wait.waitFor(5000);
	}
	@AfterClass
	public void realseBrowser(){
		driver.quit();
	}
	
}















