package cn.com.ntesec.uml.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	public static void writeJson(String name ,String data){
		try{
		      File file =new File(name);
		      //if file doesnt exists, then create it
		      if(!file.exists()){
		       file.createNewFile();
		      }
		      //true = append file
		      FileWriter fileWritter = new FileWriter(file.getName(),false);
		             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
		             bufferWritter.write(data);
		             bufferWritter.close();
	
	     }catch(IOException e){
		      e.printStackTrace();
	     }
    }
}
