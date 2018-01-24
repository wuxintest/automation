package branchbrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LaunchChrome {

	private static WebDriver chromewb=null;
	private DesiredCapabilities caps=null;
	private String projectpath=System.getProperty("user.dir");
	@BeforeClass
	public void startChrome(){
		System.setProperty("webdriver.chrome.driver", projectpath+"/tool/chromedriver_X64.exe");
		caps=DesiredCapabilities.chrome();
		//设置连接超时
		System.setProperty("sun.net.client.defaultConnectTimeout", "95000");
		//读取数据超时
		System.setProperty("sun.net.client.defaultReadTimeout", "95000");
		//设置浏览器最大化
		//caps.setCapability("chrsome.switches", Arrays.asList("--start-maximized"));
		//设置代理
		//caps.setCapability("chrsome.switches",Arrays.asList("--proxy-server=http://your-proxy-domain:4443"));
		chromewb=new ChromeDriver(caps);
		System.out.println("111111112");
	}
	@Test
	public void searchOnBaidu(){
		chromewb.get("http://www.baidu.com");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void releaseChromeDriver(){
		chromewb.quit();
	}
}






