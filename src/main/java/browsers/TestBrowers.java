package browsers;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestBrowers {
	public WebDriver driver;
	@BeforeClass
	public void startBrowsers(){
		Browsers browser=new Browsers(BrowsersType.chrome);
		driver=browser.driver;
		driver.manage().window().maximize();
	}
	@Test
	public void start(){
		driver.get("http://www.baidu.com");
		try {
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@AfterClass
	public void releaseBrowser(){
		driver.quit();
	}
}




