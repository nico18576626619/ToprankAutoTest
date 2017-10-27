package com.demo;

import java.io.File;
import java.io.IOException;

import com.common.Utils;

public class classTest {
	public static void main(String[] args) throws IOException{
		File f=getCaseFile();
		System.out.println(f.getName());
		
	}
	
	private static File getCaseFile(){
		String path="./data/"+getCurClassName()+".xlsx";
		File f=new File(path);
		if (!f.exists()){
			throw new AssertionError("找不到测试用例数据文件"+getCurClassName()+".xlsx");
		}
		return f;
	}
	
	
	private static String getCurClassName() {
		// TODO Auto-generated method stub
		String classname=Thread.currentThread().getStackTrace()[1].getClassName();
		int temp=classname.lastIndexOf(".");
		String sname=classname.substring(temp+1);
		return sname;
	}
	

}
