package parameter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.Browsers;
import browsers.BrowsersType;

public class ParameterInstance {
	public static WebDriver driver;
	public String projectpath=System.getProperty("user.dir");
	private ParseProperties data=null;
	private ParseProperties locator=null;
	private Wait wait;
	
	@Parameters({"data","locator"})
	@BeforeClass
	public void startLanuch(@Optional("data") String td,@Optional("locator") String loc){
		data=new ParseProperties(projectpath+td);
		locator=new ParseProperties(projectpath+loc);
		Browsers browser=new Browsers(BrowsersType.firefox);
		driver=browser.driver;
		driver.manage().window().maximize();
		wait=new Wait(driver);
	}
	
	@Test
	//邮箱登录
	public void accessMail(){
		driver.get(data.getValue("url"));
		try {
			WebElement iframe=driver.findElement(By.tagName("iframe"));
			driver.switchTo().frame(iframe);
			driver.findElement(By.xpath(locator.getValue("username"))).clear();
			driver.findElement(By.xpath(locator.getValue("username"))).sendKeys(data.getValue("username"));
			driver.findElement(By.xpath(locator.getValue("password"))).sendKeys(data.getValue("password"));
			driver.findElement(By.xpath(locator.getValue("submit"))).click();
			wait.waitForElementPresent(locator.getValue("homepage"));
			Assert.assertEquals(driver.findElement(By.xpath(locator.getValue("sendbox"))).isDisplayed(), true);
			wait.waitFor(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//收件箱未读邮件数
	@Test
	public void restriveMainIsIn(){
		int actualunreadMailsNum=0;
		int totalPagesNum=0;
		String strNum=driver.findElement(By.xpath(locator.getValue("inboxMailsNum"))).getText();
		int inboxMailNum=Integer.valueOf(strNum.substring(1, strNum.length()-1));
		driver.findElement(By.xpath(locator.getValue("inbox"))).click();
		wait.waitForElementPresent(locator.getValue("unreadLink"));
		driver.findElement(By.xpath(locator.getValue("unreadLink"))).click();
		wait.waitForElementPresent(locator.getValue("totalPages"));
		wait.waitFor(20000);
		String pageNum=driver.findElement(By.xpath(locator.getValue("totalPages"))).getText();
		totalPagesNum=Integer.valueOf(pageNum.split("/")[1]);
		while(totalPagesNum>=1){
			int unreadMailsNum=driver.findElements(By.xpath(locator.getValue("unreadMails"))).size();
			actualunreadMailsNum+=unreadMailsNum;
			try {
				driver.findElement(By.xpath(locator.getValue("nextBtn"))).click();
			} catch (Exception e) {
				break;
			}
			totalPagesNum--;
		}
		System.out.println("actualunreadMailsNum="+actualunreadMailsNum);
		System.out.println("inboxMailNum="+inboxMailNum);
		Assert.assertEquals(actualunreadMailsNum, inboxMailNum);
	}
	
	//订阅邮件未读邮件数
	@Test
	public void subMailsUnRead(){
		int actualUnReadMailNums=0;
		int totalPageNums=0;
		String strSubMail=driver.findElement(By.xpath(locator.getValue("strSubMail"))).getText();
		String strSubMailNum=strSubMail.substring(1, strSubMail.length()-1);
		driver.findElement(By.xpath(locator.getValue("subMail"))).click();
		wait.waitForElementPresent(locator.getValue("unreadLink"));
		driver.findElement(By.xpath(locator.getValue("unreadLink"))).click();
		wait.waitFor(20000);
		String strPageNum=driver.findElement(By.xpath(locator.getValue("totalPages"))).getText();
		totalPageNums=Integer.valueOf(strPageNum.split("/")[1]);
		System.out.println("totalPageNums="+totalPageNums);
		if(totalPageNums==1){
			int unReadMailsNums=driver.findElements(By.xpath(locator.getValue("unreadMails"))).size();
			actualUnReadMailNums=unReadMailsNums;
		}else if(totalPageNums>1){
			for(int i=0;i<totalPageNums;){
				int unReadMailsNums=driver.findElements(By.xpath(locator.getValue("unreadMails"))).size();
				actualUnReadMailNums+=unReadMailsNums;
				driver.findElement(By.xpath(locator.getValue("nextBtn"))).click();
				totalPageNums--;
			}
		}
		System.out.println("strSubMailNum="+strSubMailNum);
		System.out.println("actualUnReadMailNums="+actualUnReadMailNums);
	}
	
	@AfterClass
	public void release(){
		driver.quit();
	}
	
	
	
	
}
