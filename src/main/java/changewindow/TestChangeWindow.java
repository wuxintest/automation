package changewindow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.Browsers;
import browsers.BrowsersType;
import parameter.ParseProperties;
import parameter.Wait;

public class TestChangeWindow {
	private static WebDriver driver;
	private String projectpath=System.getProperty("user.dir");
	ParseProperties changewcd;
	ParseProperties changewcx;
	Wait wait;
	@Parameters({"changewd","changewx"})
	@BeforeMethod
	public void launucBrowser(String cd,String cx){
		changewcd=new ParseProperties(projectpath+cd);
		changewcx=new ParseProperties(projectpath+cx);
		Browsers browser=new Browsers(BrowsersType.firefox);
		driver=browser.driver;
		wait=new Wait(driver);
		driver.manage().window().maximize();
	}
	
	@Test
	public void changeWindow(){
		driver.get(changewcd.getValue("url"));
		driver.findElement(By.xpath(changewcx.getValue("search"))).sendKeys(changewcd.getValue("searchkey"));
		wait.waitFor(5000);
		driver.findElement(By.xpath("//div[@id='1']/h3/a")).click();
		String[] handles=new String[driver.getWindowHandles().size()];
		driver.getWindowHandles().toArray(handles);
		for(String handle:handles){
			System.out.println("handle1:"+handle);
		}
		driver.switchTo().window(handles[1]);
		wait.waitForElementPresent("//a[@id='translate-button']");
		driver.switchTo().window(handles[0]);
		driver.findElement(By.xpath("//input[@id='kw']")).clear();
		wait.waitFor(5000);
	}
	
	@Test
	public void testSwitch(){
		driver.get(changewcd.getValue("url"));
		driver.findElement(By.xpath(changewcx.getValue("search"))).sendKeys(changewcd.getValue("searchkey"));
		wait.waitFor(2000);
		driver.findElement(By.xpath("//div[@id='1']/h3/a")).click();
		Switch switch1=new Switch(driver);
		switch1.toSpecificWindow("百度翻译");
		wait.waitForElementPresent("//a[@id='translate-button']");
		switch1.toSpecificWindow("百度搜索");
		driver.findElement(By.xpath("//input[@id='kw']")).clear();
		driver.findElement(By.xpath("//input[@id='kw']")).sendKeys("hashmap");
		wait.waitFor(5000);
	}
	
	@AfterMethod
	public void realse(){
		driver.quit();
	}
	
}
















