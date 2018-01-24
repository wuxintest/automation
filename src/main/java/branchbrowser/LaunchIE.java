package branchbrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LaunchIE {
	private static WebDriver iewb=null;
	private static DesiredCapabilities caps=null;
	private String projectpath=System.getProperty("user.dir");
	
	@BeforeClass
	public void startIE(){
		System.setProperty("webdriver.ie.driver", projectpath+"/tool/IEDriverServer64.exe");
		caps=DesiredCapabilities.internetExplorer();
		//安全等级
		caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		caps.setCapability(InternetExplorerDriver.IE_SWITCHES,"-private");//不进行缓存
		//忽略认证证书
		caps.setCapability("ignoreZoomSetting", true);
		iewb=new InternetExplorerDriver(caps);	
	}
	
	@Test
	public void searchOnBaidu(){
		iewb.manage().window().maximize();
		iewb.get("http://www.baidu.com");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void releaseIEDriver(){
		iewb.close();
		iewb.quit();
	}
}










