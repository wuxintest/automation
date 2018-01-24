package changewindow;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class Switch {
	private WebDriver driver;
	private String currentwindow;
	
	public Switch(WebDriver driver){
		this.driver=driver;
		currentwindow=driver.getWindowHandle();
	}
	
	public void toSpecificWindow(String partialTitleName){
		Set<String> handles=driver.getWindowHandles();
		String titlename;
		for(String handle:handles){
			titlename=driver.switchTo().window(handle).getTitle();
			if(titlename.contains(partialTitleName)){
				break;
			}
		}
	}
	
	public void backToCurrentWindow(){
		driver.switchTo().window(currentwindow);
	}
}













