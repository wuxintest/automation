package iframeandscroll;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import parameter.ParseProperties;
import parameter.Wait;
import browsers.Browsers;
import browsers.BrowsersType;

public class TestIframe {
	public static WebDriver driver;
	private String projectpath=System.getProperty("user.dir");
	private Wait wait;
	ParseProperties ifxpath;
	ParseProperties ifdata;
	@Parameters({"iframexpath","iframedata"})
	@BeforeClass
	public void startLanuch(String iframexpath,String iframedata){
		ifxpath=new ParseProperties(projectpath+iframexpath);
		ifdata=new ParseProperties(projectpath+iframedata);
		Browsers browser=new Browsers(BrowsersType.firefox);
		driver=browser.driver;
		wait=new Wait(driver);
		driver.manage().window().maximize();
	}
	//拖动滑块
	@Test
	public void changIframe(){
		driver.get(ifdata.getValue("url"));//登录http://jqueryui.com/slider
		wait.waitForElementPresent(ifxpath.getValue("jqueryhome"));
		//WebElement iframe=driver.findElement(By.xpath(ifxpath.getValue("iframeid")));
		WebElement iframe=driver.findElement(By.tagName("iframe"));
		driver.switchTo().frame(iframe);
		wait.waitForElementPresent(ifxpath.getValue("slider"));
		WebElement slider=driver.findElement(By.xpath(ifxpath.getValue("slider")));
		Point initialPoint=slider.getLocation();
		System.out.println("initialPoint初始位置："+initialPoint);
		
		Actions dragger=new Actions(driver);
		//拖动元素到目标位置
		dragger.dragAndDropBy(slider, initialPoint.getX()+571, initialPoint.getY()).build().perform();;
		initialPoint=slider.getLocation();
		System.out.println("initialPoint结束位置："+initialPoint);
		wait.waitFor(5000);
		
		//切换到初始的ifram
		driver.switchTo().defaultContent();
		WebElement draggable=driver.findElement(By.xpath(ifxpath.getValue("draggable")));
		draggable.click();
		wait.waitFor(5000);
	}
	
	//下拉列表
	@Test
	public void selectItemFromDropDownList(){
		driver.get(ifdata.getValue("urljd"));
		wait.waitFor(5000);
		System.out.println("1111111111111");
	}
	
	@AfterClass
	public void quit(){
		driver.quit();
	}
}


