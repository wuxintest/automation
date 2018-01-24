package parameter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class ParseProperties {
	private Properties pro=new Properties();//读取配置文件类
	public String value=null;
	public ParseProperties(String propertiespath){
		this.loadProperties(propertiespath);
	}
	
	public void loadProperties(String propertiespath){
		try {
			//InputStream是所有字节输入流的超类，一般使用它的子类：FileInputStream等，它能输出字节流
			InputStream in = new FileInputStream(propertiespath);
			//InputStreamReader是字节流与字符流之间的桥梁，能将字节流输出为字符流，并且能为字节流指定字符集，可输出一个个的字符；
			InputStreamReader inr=new InputStreamReader(in);
			//BufferedReader提供通用的缓冲方式文本读取，readLine读取一个文本行， 从字符输入流中读取文本，缓冲各个字符，从而提供字符、数组和行的高效读取。
			BufferedReader br=new BufferedReader(inr);
			pro.load(br);//传入字符流
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getValue(String keyname){
		value=pro.getProperty(keyname).trim();
		try {
			//设置字符编码集
			value=new String(value.getBytes("UTF-8"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public static void main(String[] args){
		ParseProperties pp=new ParseProperties("D:/WorkSpace/automation/tool/test.properites");
		System.out.println(pp.getValue("url"));
	}
}




