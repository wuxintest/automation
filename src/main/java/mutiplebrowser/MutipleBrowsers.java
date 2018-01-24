package mutiplebrowser;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.Browsers;
import browsers.BrowsersType;

public class MutipleBrowsers {
	public WebDriver driver;
	@Parameters({"platform"})
	@BeforeMethod(groups="browsers")
	public void inital(String browsertype){
		Browsers browser=null;
		if(browsertype.equals("IE")){
		    browser=new Browsers(BrowsersType.ie);
		}else if(browsertype.equals("Firefox")){
			browser=new Browsers(BrowsersType.firefox);
		}else {
			browser=new Browsers(BrowsersType.chrome);
		}
		driver=browser.driver;
		driver.manage().window().maximize();
	}
	@Test(groups="submodule1")
	public void submodule1(){
		driver.get("http://www.baidu.com");
		System.out.println("--submodule1--");
	}
	@Test(groups="submodule2")
	public void submodule2(){
		driver.get("http://mail.qq.com");
		System.out.println("--submodule2--");
	}
	@Test(groups="submodule3")
	public void submodule3(){
		driver.get("http://www.bing.com");
		System.out.println("--submodule3--");
	}
	@Test(groups="submodule4")
	public void submodule4(){
		driver.get("https://email.163.com/");
		System.out.println("--submodule4--");
	}
	@AfterMethod(groups="browsers")
	public void releaseBrowser(){
		driver.quit();
	}
	
}






