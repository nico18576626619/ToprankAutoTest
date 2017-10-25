package com.demo;

import java.io.File;
import java.util.List;

import com.common.Utils;

public class testxslx {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File xlsfile = new File("E:/TR/SeleniumWebRegression/data/FMLogin.xlsx");
		List<String[]> data = Utils.getDataFormxlsxByCaseName(xlsfile, "login", "login");
		System.out.println(data);
	}

}
