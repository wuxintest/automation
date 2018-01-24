package browsers;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Browsers{
	public WebDriver driver=null;
	private FirefoxProfile firefoxProfile=null;
	private String projectpath=System.getProperty("user.dir");
	private DesiredCapabilities caps=null;
	
	public Browsers(BrowsersType browsersType){
		switch (browsersType) {
		case firefox:
			System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
			File firebug=new File(projectpath+"/tool/firebug@software.joehewitt.com.xpi");
			File firepath=new File(projectpath+"/tool/FireXPath@pierre.tholence.com.xpi");
			firefoxProfile=new FirefoxProfile();
			try {
				firefoxProfile.addExtension(firebug);
				firefoxProfile.addExtension(firepath);
				firefoxProfile.setPreference("webdriver.accept.untrusted.certs", "true");
				firefoxProfile.setPreference("extensions.firebug.currentVersion", "2.0.19");
				firefoxProfile.setPreference("network.proxy.type", 0);
				firefoxProfile.setPreference("network.proxy.http", "10.1.1.0");
				firefoxProfile.setPreference("networl.proxy.http_port", 3128);
			} catch (Exception e) {
				e.printStackTrace();
			}
			driver=new FirefoxDriver(firefoxProfile);
			break;
		case ie:
			System.setProperty("webdriver.ie.driver", projectpath+"/tool/IEDriverServer64.exe");
			caps=DesiredCapabilities.internetExplorer();
			//安全等级
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			caps.setCapability(InternetExplorerDriver.IE_SWITCHES,"-private");//不进行缓存
			//忽略认证证书
			caps.setCapability("ignoreZoomSetting", true);
			driver=new InternetExplorerDriver(caps);		
			break;
		case chrome:
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
			driver=new ChromeDriver(caps);
			break;

		}
	}
}
