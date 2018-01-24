package scroll;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.Browsers;
import browsers.BrowsersType;
import parameter.ParseProperties;
import parameter.Wait;

public class TestScroll {

	 private WebDriver driver;
	 ParseProperties tddata;
	 ParseProperties txxpath;
	 Wait wait;
	 private String projectpath=System.getProperty("user.dir");
	@Parameters({"tdscroll","txscroll"})
	@BeforeMethod
	public void startLanuch(String tds,String txs){
		tddata=new ParseProperties(projectpath+tds);
		txxpath=new ParseProperties(projectpath+txs);
		Browsers browser=new Browsers(BrowsersType.firefox);
		driver=browser.driver;
		wait=new Wait(driver);
	}
	@Test
	public void testScroll(){
		driver.get(tddata.getValue("url"));
		//使用javascript拖动滚动条
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0,4000)","");
		wait.waitFor(5000);
		WebElement zhineng=driver.findElement(By.xpath(txxpath.getValue("scrollpoint")));
		System.out.println("+++++"+zhineng);
		Point znxianfeng=zhineng.getLocation();
		System.out.println("x:"+znxianfeng.getX()+","+"y:"+znxianfeng.getY());
	}
	
	@Test
	public void dragAndDrop(){
		driver.get("https://www.jd.com/");
		WebElement scroll=driver.findElement(By.xpath(""));
		/*	
		((JavascriptExecutor)driver).executeScript("window.scrollBy("+znxianfeng.getX()+","+znxianfeng.getY()+")");
		wait.waitFor(5000);*/
	}
	@AfterMethod
	public void realseBrowser(){
		driver.quit();
	}
}

