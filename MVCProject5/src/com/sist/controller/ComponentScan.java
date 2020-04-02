package com.sist.controller;

import java.io.*;
import java.util.*;

public class ComponentScan {
	
	// 클래스 이름을 읽는 메소드 - list에 클래스명을 담는다.
	public List<String> getClassName(String pack, String path) 
	{
		//System.out.println("========== 흐름파악: ComponentScan (시작) ========== ");
		List<String> list = new ArrayList<String>();
		
		try
		{
			// String path="C:\\webDev\\webStudy\\MVCProject5\\src"; // MVCProject4까지는 절대경로로 잡아줬었음. 이제부터는 web.xml에서 defaultPath 지정해줘서 이게 path로 들어오게 해놨음 
			path=path+"\\"+pack.replace(".", "\\"); 
			System.out.println("path="+path);
			
			File dir = new File(path);
			File[] files = dir.listFiles();
			for(File f:files)
			{
				String name=f.getName(); //  f.getName(): 파일명 가져옴
				System.out.println("name="+name);
				
				if(name.endsWith("java")) // 파일명이 java로 끝난다 (확장자가 java)
				{
					String s = pack+"."+f.getName().substring(0,name.indexOf("."));
					// name="BoardModel.java"
					// s = "com.sist.model" + "." + "BoardModel"
					System.out.println("s="+s);
					list.add(s);
				}
				System.out.println("list="+list);
				
			}
		}catch (Exception ex) {}
		
		System.out.println("========== 흐름파악: ComponentScan (끝) ========== ");
		return list;
	}
	
}



