package com.testng;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.common.Utils;
@SuppressWarnings("unused")
public class SettleAccounts {
	
	WebDriver driver;
	int waitTime = 60;
	String BrowserFlag="";
	Properties props;
	
	@Parameters({"config"})
	@Test
	public void Start(String config)
	{
		try {
			URL fileurl = ClassLoader.getSystemClassLoader().getResource(config);
			FileInputStream fin = new FileInputStream(fileurl.getFile());
			props = new Properties();
			props.load(fin);
			//测试用例文件名
		    String datafile = props.getProperty(getCurClassName());
		    System.out.println(datafile);
		    
			File xlsfile = new File(datafile);
			  try
				 {
				  	CreateChromeDriver();
				  	ServiceLogin(xlsfile,"login","login");
				  	TestExec(xlsfile,"opentable","opentable");
//				  	Utils.findToast(driver, "开台成功!");
				  	TestExec(xlsfile,"orderdishes","orderdishes");
				  	TestExec(xlsfile,"cleartable","cleartable");
				
				 }catch(Exception e)
				 {
					 MyTakesScreenshot();
					 Assert.fail("异常"+e.getMessage());
				 }
		} catch (Exception e) {
			System.out.println("load configrations fail...");
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	
	private void ServiceLogin(File xlsfile,String sheet,String caseName) throws Exception
	{
		String url = props.getProperty("BYD.url");
		Utils.SysLogin(driver, url, BrowserFlag, xlsfile,sheet,caseName);
	}
	
	private void TestExec(File testdata,String sheetname,String caseName) throws Exception {
		List<String[]> dataList = Utils.getDataFormxlsxByCaseName(testdata, sheetname, caseName);
		Utils.SetElementValue(driver, dataList);	
	}
	
	
	public  void MyTakesScreenshot() {
		String logFolder = props.getProperty("logfolder");
		if(logFolder.equals("")) 
			logFolder ="d:/logs";
	      if (!(new File(logFolder).isDirectory())) {
	         new File(logFolder).mkdir();  
	      }
	     
	      SimpleDateFormat simDate = new SimpleDateFormat("yyyy-MM-dd");
	      String dateFolder =logFolder+File.separator+simDate.format(new Date());
	      if (!(new File(dateFolder).isDirectory())) {
		         new File(dateFolder).mkdir();
		      }

		  SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-ss");
	      String time = sdf.format(new Date()); 
	      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
	      try { 
	             FileUtils.copyFile(scrFile, new File(dateFolder + File.separator + this.getClass().getName()+"_"+time + ".jpg")); 
	          } catch (IOException e) { 
	               e.printStackTrace(); 
	           } 
	    }
	
	private void CreateChromeDriver()
	{
		String ChDriverServer = props.getProperty("ch.driver");
		System.setProperty("webdriver.chrome.driver", ChDriverServer);
		driver=new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	private void CreateHtmlUnitDriver(){
		driver = new HtmlUnitDriver();
		driver.manage().window().maximize();
	}
	
	private static String getCurClassName() {
		// TODO Auto-generated method stub
		String classname=Thread.currentThread().getStackTrace()[1].getClassName();
		int temp=classname.lastIndexOf(".");
		String sname=classname.substring(temp+1);
		return sname;
	}
	
	
}
