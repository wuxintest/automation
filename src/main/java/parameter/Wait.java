package parameter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

	private WebDriver driver;
	private int timeout=60;
	private WebDriverWait wait;
	public Wait(WebDriver driver){
		this.driver=driver;
	}
	//将查找元素的最大等待时间设置为10s
	//ExpectedConditions.presenceOfElementLocated：判断页面上的元素是否存在
	public void waitForElementPresent(String loctor){
		wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(loctor)));
	}
	
	//ExpectedConditions.elementToBeClickable：判断页面上的元素是否可点击
	public void waitForElementIsEnable(String loctor){
		wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(loctor)));
	}
	
	//ExpectedConditions.elementToBeSelected：判断页面上的元素是否被选中
	public void waitForElementIsSelected(String loctor){
		wait=new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeSelected(By.xpath(loctor)));
	}
	
	public void waitFor(long timeout){
		try {
			Thread.sleep(timeout);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}










