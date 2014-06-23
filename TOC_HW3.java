import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import jdk.nashorn.internal.parser.JSONParser;
import java.io.*;
import org.omg.CORBA.portable.InputStream;
import org.json.*;

import sun.misc.IOUtils;
import org.apache.commons.io.FileUtils;


public class TOC_HW3 {
	private static BufferedReader bf=null;
	private static char[] buf;
	private static StringBuffer sBuf = new StringBuffer();
	private static String str;
	private static JSONArray ja;
	private static JSONObject myJObject;
	private static String area;
	private static String address;
	private static int year;
	private static int price;
	
	public TOC_HW3() throws Exception{
		System.out.println("Start program");
		/*try {
			FileInputStream fis=new FileInputStream("c:\\house.json");
			BufferedReader bufR =new BufferedReader(new InputStreamReader(fis,"UTF8"));
			
			for(String s;(s=bufR.readLine())!=null;){
				sBuf.append(s);
				sBuf.append("\n");
				}
	

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }*/
		sBuf=download("http://www.datagarage.io/api/5365dee31bc6e9d9463a0057");
		//System.out.println(str);
		
	}
	private static boolean matchChexk(String checkStr,String args){
		Pattern p=Pattern.compile(args);
		Matcher m=p.matcher(checkStr);
		if(m.find()){
			return true;
		}
		
		return false;
		
	}
	private static boolean matchChexk(int checkInt,String args){
		String checkStr;
		Pattern p=Pattern.compile(args);
		checkStr=Integer.toString(checkInt);
		Matcher m=p.matcher(checkStr);
		if(m.find()){
			return true;
		}
		
		return false;
		
	}
	
	private static StringBuffer download(String urlString) throws Exception{
		URL url=new URL(urlString);
		int read;
		buf=new char[1024];
		StringBuffer strBuf=new StringBuffer();		
		try {
			BufferedReader bf =new BufferedReader(new InputStreamReader(url.openStream(),"UTF8"));
			while((read=bf.read(buf))!=-1){
				strBuf.append(buf,0,read);
							
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(bf!=null)
				bf.close();				
		}
		return strBuf;
			
	}
	

	
	

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		TOC_HW3 demo=new TOC_HW3(); 
		String argst[]={"文山區","辛亥路","103"};
		
		int argYear=Integer.parseInt(argst[2]);
		//System.out.println(sBuf);
		JSONArray ja = new JSONArray(new JSONTokener(sBuf.toString()));
		for(int i=0;i<ja.length();i++){
			myJObject=ja.getJSONObject(i);
			area=myJObject.getString("鄉鎮市區");
		    address=myJObject.getString("土地區段位置或建物區門牌");
			year=myJObject.getInt("交易年月");
			price=myJObject.getInt("總價元");
			//matchChexk(area,argst[0]);
			//matchChexk(address,argst[1]);
			//matchChexk(year,argst[2]);
			if(matchChexk(area,argst[0])&&matchChexk(address,argst[1])&&matchChexk(year,argst[2])){
				System.out.println(area+"\t"+address+"\t"+year+"\t"+(int)price);
			}
			
			
		
	}
	

	}
}
