package com.demo;

import java.sql.Time;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String ChDriverServer = "e:/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", ChDriverServer);
		
		WebDriver driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		
		driver.get("http://192.168.1.125/login");
		
		WebElement txtbox=driver.findElement(By.xpath("//*[@id='login-container']/div[2]/div[2]/div/form/div[2]/div/div/input")); 
		txtbox.sendKeys("178440M"); 
	
		WebElement txtbox1=driver.findElement(By.xpath("//*[@id='login-container']/div[2]/div[2]/div/form/div[3]/div/div/input")); 
		txtbox1.sendKeys("888");
		
		WebElement txtbox2=driver.findElement(By.xpath("//*[@id='login-container']/div[2]/div[2]/div/form/div[4]/div/div/input")); 
		txtbox2.sendKeys("123456");
		
		//*[@id="sub#menu_base_0$Menu"]/li[1]
		
//		Thread.sleep(1000);
		
		WebElement but=driver.findElement(By.xpath("//*[@id='login-container']/div[2]/div[2]/div/form/button"));
		but.click();
		Thread.sleep(2000);
		WebElement but1=driver.findElement(By.xpath("//*[@id='sub#menu_base_0$Menu']/li[2]"));
		but1.click();
		
		Thread.sleep(10000);
		driver.close();
	}

}
