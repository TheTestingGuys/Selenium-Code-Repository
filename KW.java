import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class KW {

	
	public static void enterText(WebDriver driver, String xPath, String data){
		//		Purpose: Enter Text into a edit field
		//		I/P:driver, xPath, Data
		//		O/P:Nil
		//		CreatedBy:Steve Paulson
		//		When:5/24/13
		//		EditedBy:
		//		When:
		driver.findElement(By.xpath(xPath)).clear();
		driver.findElement(By.xpath(xPath)).sendKeys(data);
	}
	
	public static String readText(WebDriver driver, String xPath){
		//		Purpose: Read Text from a edit field
		//		I/P:driver, xPath
		//		O/P:Data
		//		CreatedBy:Steve Paulson
		//		When:5/24/13
		//		EditedBy:
		//		When:
		return driver.findElement(By.xpath(xPath)).getText();
	}
	
	public static String verifyText(WebDriver driver, String xPath, String sText){
		//		Purpose: Verify Text from a edit field
		//		I/P:driver, xPath, Data
		//		O/P:Data
		//		CreatedBy:Steve Paulson
		//		When:5/24/13
		//		EditedBy:
		//		When:
		if (sText.equalsIgnoreCase(driver.findElement(By.xpath(xPath)).getText())){
			return "Pass";
		}else {
			return "Fail";
		}
		
	}
	
	public static void clickElement(WebDriver driver, String xPath){
		//		Purpose: Click any element
		//		I/P:driver, xPath
		//		O/P:-
		//		CreatedBy:Steve Paulson
		//		When:5/24/13
		//		EditedBy:
		//		When:
		//driver.findElement(By.xpath(xPath)).click();  // this way does a click
		driver.findElement(By.xpath(xPath)).sendKeys("\n"); // this way sends the enter key, usually works better.
	}
	
	public static void selectList(WebDriver driver, String xPath, String data){
		//		Purpose: Select from a dropdown list
		//		I/P:driver, xPath, data
		//		O/P:-
		//		CreatedBy:Steve Paulson
		//		When:5/24/13
		//		EditedBy:
		//		When:
		Select myDD = new Select(driver.findElement(By.xpath(xPath)));
		myDD.selectByVisibleText(data);
		myDD = null;
	}
	
	public static void checkBox(WebDriver driver, String xPath){
		//		Purpose: Check a checkbox
		//		I/P:driver, xPath, data
		//		O/P:-
		//		CreatedBy:Steve Paulson
		//		When:5/24/13
		//		EditedBy:
		//		When:
		// Is it checked already?
		if(driver.findElement(By.xpath(xPath)).getAttribute("checked").equalsIgnoreCase("checked")){
			//Then don't do anything
		} else {
			driver.findElement(By.xpath(xPath)).click(); //check the box
		}
	}

	public static void navigateBrowser(WebDriver driver, String data){
		//		Purpose: Navigate a browser to a URL
		//		I/P:driver, data
		//		O/P:-
		//		CreatedBy:Steve Paulson
		//		When:5/24/13
		//		EditedBy:
		//		When:
		driver.navigate().to(data);
	}
	
	public static void waitTime(long lTime) throws Exception{
		//		Purpose: Waits for a certain amount of time
		//		I/P: Data
		//		O/P:-
		//		CreatedBy:Steve Paulson
		//		When:5/24/13
		//		EditedBy:
		//		When:
		Thread.sleep(lTime);
	}
	
	public static String waitForElement(WebDriver driver, String xPath) throws Exception{
//		Purpose: Waits for a certain amount of time
		//		I/P:driver, xpath
		//		O/P:-
		//		CreatedBy:Steve Paulson
		//		When:5/24/13
		//		EditedBy:
		//		When:
		for (int second = 0;; second++) {
			if (second < 20){
				if (isElementPresent(driver, By.xpath(xPath))){
					return "Pass"; 
				}
			} else {
				return "Fail";
			}
			Thread.sleep(1000);
		}
	}
	
	static boolean isElementPresent(WebDriver driver, String xPath) {
//		Purpose: Waits for a certain amount of time
		//		I/P: Time in milli seconds
		//		O/P:Nil
		//		CreatedBy:SelA16 Batch
		//		When:9/11/12
		//		EditedBy:Karthik
		//		When:9/11/12
		try {
			driver.findElement(By.xpath(xPath));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
