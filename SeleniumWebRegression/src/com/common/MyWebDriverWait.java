package com.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyWebDriverWait extends WebDriverWait {

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}

	private String eid;
	
	public MyWebDriverWait(WebDriver driver, long timeOutInSeconds,long sleepInMillis) {
		super(driver, timeOutInSeconds, sleepInMillis);
	}
}
