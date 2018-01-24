package branchbrowser;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LaunchFF {
	
	private WebDriver ffwb = null;
	private FirefoxProfile firefoxprofile = null;
	private String projectpath = System.getProperty("user.dir");
	
	@BeforeClass
	public void startFirefox(){
		System.setProperty("webdriver.firefox.bin", "c:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"); 
		File firebug = new File(projectpath+"/tool/firebug@software.joehewitt.com.xpi");
	    File firepath = new File(projectpath+"/tool/FireXPath@pierre.tholence.com.xpi");
		firefoxprofile =  new FirefoxProfile();
		try {
			firefoxprofile.addExtension(firebug);
			firefoxprofile.addExtension(firepath);
			firefoxprofile.setPreference("webdriver.accept.untrusted.certs", "true"); 
			firefoxprofile.setPreference("extensions.firebug.currentVersion", "2.0.19");
			firefoxprofile.setPreference("network.proxy.type", 0);
			firefoxprofile.setPreference("network.proxy.http", "10.1.1.0");
			firefoxprofile.setPreference("network.proxy.http_port", 3128);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ffwb = new FirefoxDriver(firefoxprofile);
		ffwb.manage().window().maximize();
	}
	@Test
	public void search(){		
		ffwb.get("https://email.163.com/");
		try {
			Thread.sleep(5000);
			WebElement iframe=ffwb.findElement(By.tagName("iframe"));
			ffwb.switchTo().frame(iframe);
			ffwb.findElement(By.xpath("//input[@name='email']")).clear();
			ffwb.findElement(By.xpath("//input[@name='email']")).sendKeys("wuxintesting");
			ffwb.findElement(By.xpath("//input[@name='password']")).sendKeys("LX511985");
			ffwb.findElement(By.xpath("//a[@id='dologin']")).click();
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void releaseFFDriver(){
		ffwb.quit();
	}
	
}
