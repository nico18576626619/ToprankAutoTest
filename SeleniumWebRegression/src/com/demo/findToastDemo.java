package com.demo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.common.Utils;

public class findToastDemo {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String ChDriverServer = "e:/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", ChDriverServer);
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("http://192.168.6.185:8080/config");
//		WebDriverWait wd=new WebDriverWait(driver,10000,10000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement w1=driver.findElement(By.xpath(".//*[@id='ipSet']/div[3]/div[2]/div")); 
		w1.click(); 

		WebElement w2=driver.findElement(By.xpath(".//*[@id='ipSet']/div[3]/div[3]/button")); 
		w2.click(); 

		WebElement w3=driver.findElement(By.xpath(".//*[@id='account-num']")); 
		w3.sendKeys("185");
	
		WebElement w4=driver.findElement(By.xpath(".//*[@id='password']")); 
		w4.sendKeys("123456");
	
		WebElement w5=driver.findElement(By.xpath(".//*[@id='num-key']/ul/li[15]")); 
		w5.click();
		WebElement w6=driver.findElement(By.xpath(".//*[@id='btn_free']")); 
		w6.click();
		WebElement w7=driver.findElement(By.xpath(".//*[@id='tableContainer']/div[1]")); 
		w7.click();
		w7.click();
		WebElement w8=driver.findElement(By.xpath(".//*[@id='desk_info_container']/div[4]/div[1]")); 
		w8.click();
		WebElement w9=driver.findElement(By.xpath(".//*[@id='right_block']/div[1]/ul/li[8]")); 
		w9.click();
		WebElement w10=driver.findElement(By.xpath(".//*[@id='desk_info_container']/div[3]/div[2]")); 
		w10.click();
		
		Utils.findToast(driver, "开台成功");
		
		Thread.sleep(5000);
		driver.close();
	}
}
