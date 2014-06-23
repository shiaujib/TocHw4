import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;




public class TOC_HW4 {

	/**
	 * @param args
	 */
	
	private static char[] buf;
	private static BufferedReader bf=null;
	private static StringBuffer sBuf = new StringBuffer();
	private static int[] monthNum=new int[20];
	private static JSONObject myJObject;
	private static String [] fAddress=new String[100000];
	private static int addressIndex=0;
	private static int priceIndex=0;
	private static int [] monthList=new int [1000000];
	private static int monthListIndex=0;
	private static int [] addressDifMonth=new int [100000];
	private static int [] priceList=new int [100];
	private static int listIndex; //紀錄相同大小結果
	private static int flag;
	private static int num;
	
	
	public TOC_HW4() throws Exception{
		sBuf=download("http://www.datagarage.io/api/538447a07122e8a77dfe2d86");
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
	public void distributeMonth(int month){
		switch(month){
		case 10101:
			monthNum[0]++;
			break;
		case 10202:
			monthNum[1]++;
			break;
		case 10203:
			monthNum[2]++;
			break;
		case 10204:
			monthNum[3]++;
			break;
		case 10205:
			monthNum[4]++;
			break;
		case 10206:
			monthNum[5]++;
			break;
		case 10207:
			monthNum[6]++;
			break;
		case 10208:
			monthNum[7]++;
			break;
		case 10209:
			monthNum[8]++;
			break;
		case 10210:
			monthNum[9]++;
			break;
		case 10211:
			monthNum[10]++;
			break;
		case 10212:
			monthNum[11]++;
			break;
		
	
		}
		
		
	}
	public static void distributCount(int month,int index){
		monthList[monthListIndex++]=month;
		for(int i=0;i<monthListIndex-1;i++){
			if(monthList[monthListIndex-1]==(monthList[i])){ //查看是否相同
				monthListIndex--;
				//complexFlag=1;
				break;
			}
			
				
	}
		/*for(int i=0;i<monthListIndex;i++){
			if(monthList[i]>10300){
				num++;
				
			}
			//System.out.println(num);
			System.out.println("////"+monthList[i]);
			
		}
		//System.out.println("////////////");
		System.out.println("        "+monthListIndex);
		*/
		
		
	}
	public static void distributeRoadName(String address){
		String roadName;
		Pattern p=Pattern.compile("區.*路");
		Matcher m=p.matcher(address);
		if(m.find()){
			roadName=m.group().substring(1, m.group().length());
			//System.out.println(roadName);
			setAddress(roadName,flag);
		}
		
	}
    /*public static void display(){
    	
    		for(int i=0;i<15;i++){
    			System.out.println(fAddress);
    		}
    	
    }*/
	public static void setAddress(String ad,int flag){

			fAddress[addressIndex++]=ad;
			for(int i=0;i<addressIndex-1;i++){
				if(fAddress[addressIndex-1].equals(fAddress[i])){ //查看是否相同
					addressIndex--;
					//complexFlag=1;
					//break;
				}
				
		}
	
		
	}
	public static int findMin(int array[],int index){
		int min=array[0];
		for(int i=0;i<index;i++){
			if(array[i]<min)
				min=array[i];
		}
		return min;
		
	}
	public static int findMaxPrice(int array[],int index){
		int max=array[0];
		for(int i=0;i<index;i++){
			if(array[i]>max)
				max=array[i];
		}
		return max;
		
	}
	public static int[] findMax(int addressDifMonth[],int flag,int n){ //flag==0 return index flag=1 return value
		int max=0;
		int index=0;
		listIndex=0;
		int []indexList=new int [5];
		for(int i=0;i<n;i++){
			if(addressDifMonth[i]>max){
				max=addressDifMonth[i];
				
			}
			
		}
		
		for(int i=0;i<n;i++){
			if(addressDifMonth[i]==max){
				indexList[listIndex++]=i;
			}
			
		}
		return indexList;
		

	}
	/*public int getPrice(String str,JSONArray ja){
		JSONObject jo;
		Pattern p=Pattern.compile(".*"+str+".*");
		for(int i=0;i<ja.length();i++){
		Matcher m=p.matcher(json);
		}
		return 0;
		
	}*/
	public static int distributeYear(int array[],int index){
		int [] a=new int [10];
		int max=0;
		for(int i=0;i<index;i++){
			if(array[i]<10200&&array[i]>10100){
				a[0]++;
				
			}
			else if(array[i]<10300&&array[i]>10200){
				a[1]++;
			}
			else if(array[i]<10400&&array[i]>10300){
				a[2]++;
				
			}
		}
		for(int i=0;i<3;i++){
			if(a[i]>=max)
				max=a[i];
		}
		
		return max;
		
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int month;
		String address;
		String finalAddress = null;
		int index=0;
		Pattern pAddress;
		Pattern pPrice;
		//Pattern date=Pattern.compile()
		Matcher mAdd;
		Matcher mPrice;
		TOC_HW4 demo=new TOC_HW4();
		JSONArray ja = new JSONArray(new JSONTokener(sBuf.toString()));
		for(int i=0;i<ja.length();i++){
			myJObject=ja.getJSONObject(i);
			month=myJObject.getInt("交易年月");
			address=myJObject.getString("土地區段位置或建物區門牌");
			//System.out.println(month);
			//System.out.println(address);
			distributeRoadName(address);
			demo.distributeMonth(month);
			
		}
		//pAddress=Pattern.compile(".*"+fAddress[index]+".*");
		for(int n=0;n<addressIndex;n++){
			pAddress=Pattern.compile(".*區"+fAddress[n]+".*");
			//System.out.println(fAddress[n]+"//////");
			for(int i=0;i<ja.length();i++){
			myJObject=ja.getJSONObject(i);
			address=myJObject.getString("土地區段位置或建物區門牌");
			//System.out.println(address);
			if(pAddress.matcher(address).find()){
				month=myJObject.getInt("交易年月");
				distributCount(month,num);
				
				//System.out.println(month);
				
			}
		}
			
			//System.out.println("monthListindex "+monthListIndex);
			//System.out.println("    total 103 "+num);	
			//System.out.println("max diff year month "+distributeYear(monthList,monthListIndex));
			addressDifMonth[n]=monthListIndex;
			//addressDifMonth[n]=distributeYear(monthList,monthListIndex);
			monthListIndex=0;
			num=0;
	}
		
			pPrice=Pattern.compile(".*"+fAddress[findMax(addressDifMonth,0,addressIndex+1)[0]]);
		for(int i=0;i<ja.length();i++){
			myJObject=ja.getJSONObject(i);
			address=myJObject.getString("土地區段位置或建物區門牌");
			if(pPrice.matcher(address).find()){
				finalAddress=myJObject.getString("土地區段位置或建物區門牌");
				priceList[priceIndex++]=myJObject.getInt("總價元");
					}
			
		}
		finalAddress=finalAddress.substring(0,finalAddress.indexOf("路")+1);
		System.out.println(finalAddress+", 最高成交價: "+findMaxPrice(priceList,priceIndex)+",最低成交價: "+findMin(priceList,priceIndex));
		if(listIndex>1){
			pPrice=Pattern.compile(".*"+fAddress[findMax(addressDifMonth,0,addressIndex+1)[listIndex-1]]);
			for(int i=0;i<ja.length();i++){
				myJObject=ja.getJSONObject(i);
				address=myJObject.getString("土地區段位置或建物區門牌");
				if(pPrice.matcher(address).find()){
					finalAddress=myJObject.getString("土地區段位置或建物區門牌");
					priceList[priceIndex++]=myJObject.getInt("總價元");
						}
				
			}
			finalAddress=finalAddress.substring(0,finalAddress.indexOf("路")+1);
			System.out.println(finalAddress+", 最高成交價: "+findMaxPrice(priceList,priceIndex)+",最低成交價: "+findMin(priceList,priceIndex));
			
		}
		

	
	}

}
