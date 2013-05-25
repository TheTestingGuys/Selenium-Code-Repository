/*
//Defines the list of JAR libraries that we will use in this program.
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
//import org.junit.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

public class keywordTest {
	@Test
	public void myDriver() throws Exception{
		String[][] xTC, xTS;
		String vKW, vXPath, vTestData;
		long vWaitTime = 2000; // Set the Wait Time Variable
		
		WebDriver myD = new FirefoxDriver(); // Create the Webdriver
		
		xTC = xl.readXL("//Users//Steve//Documents//Seleniumworkspace//KDFAutomation.xls", "Test Cases - Table 1"); // Read in the Test Cases
		xTS = xl.readXL("//Users//Steve//Documents//Seleniumworkspace//KDFAutomation.xls", "Test Steps - Table 1"); // Read in the Test Steps
		int vRows = xTC.length;
		int vCols = xTC[0].length;
		for (int i=1; i<xTC.length ; i++) { // Go to each Row in Test Cases
			if (xTC[i][3].equalsIgnoreCase("Y")) { // Identify which Test Cases to execute
				for (int k=1;k<xTS.length; k++){ // Go through the Test Steps			 
					if (xTC[i][0].equalsIgnoreCase(xTS[k][1])) { // see if Test Case ID's match
						
						vKW = xTS[k][4];
						vXPath = xTS[k][5];
						vTestData = xTS[k][6];
						String vError = "Pass";
						try {
							// Call the corresponding Keyword Function
							if (vKW.equalsIgnoreCase("enterText")) {
								KW.enterText(myD, vXPath, vTestData);
							}
							if (vKW.equalsIgnoreCase("navigateBrowser")) {
								KW.navigateBrowser(myD,vTestData);
							}
							if (vKW.equalsIgnoreCase("clickElement")) {
								KW.clickElement(myD,vXPath);
							}
							if (vKW.equalsIgnoreCase("readText")) {
								vError = KW.readText(myD,vXPath);
								System.out.println("Text here is " + vError);
							}
							if (vKW.equalsIgnoreCase("verifyText")) {
								vError = KW.verifyText(myD,vXPath, vTestData);
								System.out.println("Test Step is a " + vError);
							}
							if (vKW.equalsIgnoreCase("waitTime")) {
								KW.waitTime(vWaitTime);
							}
							if (vKW.equalsIgnoreCase("waitForElement")) {
								KW.waitForElement(myD,vXPath);
							}
							if (vKW.equalsIgnoreCase("isElementPresent")) {
								KW.isElementPresent(myD,vXPath);
							}
							if (vError.equalsIgnoreCase("Pass")) {
								System.out.println("Pass: " + xTC[i][0] + "_" + vKW);
								xTS[k][7] = "Pass";
							} else {
								System.out.println("Fail: " + xTC[i][0] + "_" + vKW);
								xTS[k][7] = "Fail";
								xTS[k][8] = "Function returned Fail";
							}
							
						} catch(Exception selError) {
							System.out.println("Fail: " + xTC[i][0] + "_" + vKW);
							xTS[k][7] = "Fail";
							System.out.println("Error occurred: " + selError);
							xTS[k][8] = "Error occurred: " + selError;
							
						}

						
					}
				}
			}
		}
		myD.quit();
		// Write back the Test Steps with the error details into an excel
		xl.writeXL("//Users//Steve//Documents//Seleniumworkspace//KDFAutomation-Result.xls", "TestSteps Result", xTS);
	}

}
