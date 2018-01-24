package select;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import parameter.ParseProperties;
import parameter.Wait;
import browsers.Browsers;
import browsers.BrowsersType;

public class TestSelect {

	private static WebDriver driver;
	private String projectpath=System.getProperty("user.dir");
	ParseProperties sdata;
	ParseProperties sxpath;
	Wait wait;
	@Parameters({"selectdata","selectxpath"})
	@BeforeClass
	public void startLaunch(String sedata,String sexpath){
		sdata=new ParseProperties(projectpath+sedata);
		sxpath=new ParseProperties(projectpath+sexpath);
		Browsers browser=new Browsers(BrowsersType.firefox);
		driver=browser.driver;
		wait=new Wait(driver);
		driver.manage().window().maximize();
	}
	
	@Test
	public void selectItemFromDropDownList(){
		driver.get(sdata.getValue("url"));
		wait.waitForElementPresent(sxpath.getValue("linklogin"));
		driver.findElement(By.xpath(sxpath.getValue("linklogin"))).click();
		wait.waitForElementPresent(sxpath.getValue("accountloing"));
		driver.findElement(By.xpath(sxpath.getValue("accountloing"))).click();
		wait.waitForElementPresent(sxpath.getValue("loginname"));
		driver.findElement(By.xpath(sxpath.getValue("loginname"))).clear();
		driver.findElement(By.xpath(sxpath.getValue("loginname"))).sendKeys(sdata.getValue("username"));
		driver.findElement(By.xpath(sxpath.getValue("password"))).sendKeys(sdata.getValue("password"));
		driver.findElement(By.xpath(sxpath.getValue("loginBtn"))).click();
		wait.waitForElementPresent(sxpath.getValue("myorder"));		
		driver.findElement(By.xpath(sxpath.getValue("myorder"))).click();
		wait.waitFor(10000);
		
		String[] handles=new String[driver.getWindowHandles().size()];//定义一个数组，开辟空间大小为3
		driver.getWindowHandles().toArray(handles);//获取所有的窗口
		for(String handle:handles){
			System.out.println("1==="+handle);
		}
		driver.switchTo().window(handles[1]);
		Actions action=new Actions(driver);
		//鼠标悬浮在导航标签上
		action.moveToElement(driver.findElement(By.xpath(sxpath.getValue("accountsetting")))).perform();;
		wait.waitFor(5000);
		driver.findElement(By.xpath(sxpath.getValue("personalinfo"))).click();
		wait.waitFor(5000);
		
		driver.getWindowHandles().toArray(handles);
		for(String handle:handles){
			System.out.println("2==="+handle);
		}
		driver.switchTo().window(handles[1]);
		WebElement brithdayyear= driver.findElement(By.xpath(sxpath.getValue("brithdayyear")));
		//brithdayyear.click();
		Select year=new Select(brithdayyear);//选择年
		year.selectByVisibleText("1988");
		WebElement birthdaymonth=driver.findElement(By.xpath(sxpath.getValue("birthdaymonth")));
		Select month=new Select(birthdaymonth);//选择月
		List<WebElement> allMonthElements=month.getOptions();
		for(WebElement eachMonth:allMonthElements){
			System.out.println(eachMonth.getText());
		}
		wait.waitFor(5000);
	}
	
	@AfterClass
	public void realseBrowser(){
		driver.quit();
	}
}














