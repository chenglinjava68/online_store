package com.yuan.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	/**
	 * 如果str为null，返回“”,否则返回str
	 * 
	 * @param str
	 * @return
	 */
	public static String isNull(String str) {
		if (str == null) {
			return "";
		}
		return str;
	}

	public static String isNull(Object o) {
		if (o == null) {
			return "";
		}
		String str="";
		if(o instanceof String){
			str=(String)o;
		}else{
			str=o.toString();
		}
		return str;
	}

	/**
	 * 检查email是否是邮箱格式，返回true表示是，反之为否
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		email = isNull(email);
		Pattern regex = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();
		return isMatched;
	}
	/**
	 * 检验手机号码格式
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobile(String mobiles){     
		Pattern p = Pattern.compile("^1[3|4|7|5|8][0-9]{9}$");     
		Matcher m = p.matcher(mobiles);      
		return m.matches();     
	} 

	/**
	 * 检查身份证的格式，返回true表示是，反之为否
	 * 
	 * @return
	 */
	public static boolean isCard(String cardId) {
		cardId = isNull(cardId);
		//身份证正则表达式(15位) 
		Pattern isIDCard1=Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"); 
		//身份证正则表达式(18位) 
		Pattern isIDCard2=Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$"); 
		Matcher matcher1= isIDCard1.matcher(cardId);
		Matcher matcher2= isIDCard2.matcher(cardId);
		boolean isMatched = matcher1.matches()||matcher2.matches();
		return isMatched;
	}

	/**
	 * 判断字符串是否为整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		if (isEmpty(str)) {
			return false;
		}
		Pattern regex = Pattern.compile("\\d*");
		Matcher matcher = regex.matcher(str);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (isEmpty(str)) {
			return false;
		}

		Pattern regex = Pattern.compile("\\d*(.\\d*)?");
		Matcher matcher = regex.matcher(str);
		boolean isMatched = matcher.matches();
		return isMatched;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String firstCharUpperCase(String s) {
		StringBuffer sb = new StringBuffer(s.substring(0, 1).toUpperCase());
		sb.append(s.substring(1, s.length()));
		return sb.toString();
	}

	public static String hideChar(String str,int len){
		if(str==null) return null;
		char[] chars=str.toCharArray();
		for(int i=1;i>chars.length-1;i++){
			if(i<len){
				chars[i]='*';
			}
		}
		str=new String(chars);
		return str;
	}

	public static String hideLastChar(String str,int len){
		if(str==null) return null;
		char[] chars=str.toCharArray();
		if(str.length()<=len){
			for(int i=0;i<chars.length;i++){
				chars[i]='*';
			}
		}else{
			for(int i=chars.length-1;i>chars.length-len-1;i--){
				chars[i]='*';
			}
		}
		str=new String(chars);
		return str;
	}

	public static String hideLastChar(String str){
		if(str==null) return null;
		//		String a = str.substring(0, 2);
		//		String b = str.substring(str.length()-1,str.length());
		//		String c = a+"***"+b;
		String a = str.substring(0, 3);
		String b = str.substring(str.length()-4,str.length());
		String c = a+"***"+b;

		return c;
	}
	/**
	 * 
	 * @return
	 */
	public static String format(String str,int len){
		if(str==null) return "-";
		if(str.length()<=len){
			int pushlen=len-str.length();
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<pushlen;i++){
				sb.append("0");
			}
			sb.append(str);
			str=sb.toString();
		}else{
			String newStr=str.substring(0, len);
			str=newStr;
		}
		return str;
	}

	public static String contact(Object[] args){
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<args.length;i++){
			sb.append(args[i]);
			if(i<args.length-1){
				sb.append(",");
			}
		}
		return sb.toString();
	}

	/**
	 * 是否包含在以“，”隔开字符串内
	 * @param s
	 * @param type
	 * @return
	 */
	public static boolean isInSplit(String s,String type){
		if(isNull(s).equals("")){
			return false;
		}
		List<String> list=Arrays.asList(s.split(","));
		if(list.contains(type)){
			return true;
		}
		return false;
	}

	public static boolean isBlank(String str){
		return StringUtils.isNull(str).equals("");
	}

	public static String generateTradeNO(long userid,String type){
		String s;
		s = type + userid + getFullTimeStr();
		return s;
	}

	public static String generatePnrTradeNO(long userid,String type){
		String s;
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss");
		String str = format.format(Calendar.getInstance().getTime());
		long r=NumberUtils.getRandom(4);
		StringUtils.format(r+"", 4);
		s = type + userid + str+StringUtils.format(r+"", 4);
		return s;
	}

	public  synchronized static String getYJFTradeNO(){
		String s;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String str = format.format(Calendar.getInstance().getTime());
		long r=NumberUtils.getRandom(8);
		StringUtils.format(r+"", 8);
		s = str+StringUtils.format(r+"",8);
		return s;
	}

	public static String getFullTimeStr(){
		String s=DateUtils.dateStr3(Calendar.getInstance().getTime());
		return s;
	}

	public static String array2Str(Object[] arr){
		StringBuffer s=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			s.append(arr[i]);
			if(i<arr.length-1){
				s.append(",");
			}
		}
		return s.toString();
	}

	public static String array2Str(int[] arr){
		StringBuffer s=new StringBuffer();
		for(int i=0;i<arr.length;i++){
			s.append(arr[i]);
			if(i<arr.length-1){
				s.append(",");
			}
		}
		return s.toString();
	}

	/**
	 * 检验用户名
	 * 规则：数字与字母组合，字母，汉字，4-16位(?![a-zA-Z]+$)
	 * @return
	 */
	public static boolean checkUsername(String username){
		Pattern p = Pattern.compile("^(?![0-9]+$)[0-9A-Za-z_\u0391-\uFFE5]{2,15}$");
		Matcher m = p.matcher(username);
		return m.matches();
	}

	public static void main(String[] args) throws IOException {
		/*	String ss = "周学成sss";
		//System.out.println(checkUsername(ss));
		System.out.println(hideChar(ss, 7));
		System.out.println(hideLastChar(ss, 2));
		//System.out.println(isBlank(null));
		 */		
		/*double a = 5%3;
		System.out.println(a);*/

		/*	
		BASE64Encoder  de = new BASE64Encoder();
		System.out.println(de.encode("1111111,我哎我加".getBytes()));
		System.out.println(Base64.encode("1111111,我哎我加".getBytes()));
		String str = new String(Base64.encode("1111111,我哎我加".getBytes()));
		BASE64Decoder dec = new BASE64Decoder();
		String s = dec.decodeStr(str);
		System.out.println(s);*/

		String remark = "[<a href='http://yjf.wdaics.com/invest/detail.html?borrowid=1' target=_blank>测试流转标</a>]投资成功，冻结投资者的投标资金300.0";
		String s = "";
		int firstBeginIndex = remark.indexOf("<");
		if(firstBeginIndex != -1){

			s =  remark.substring(0,firstBeginIndex)+remark.substring(remark.indexOf(">")+1, remark.lastIndexOf("<"))+remark.substring(remark.lastIndexOf(">")+1);
		}
		System.out.println(s);

	}

	/**
	 * 密码必须为数字+字母8位以上
	 * @param pwd
	 * @return
	 */
	public static boolean checkwd(String pwd){
		String regEx = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z~!@#$%^&*()]{8,16}$"; 
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(pwd);
		boolean rs = mat.find(); 
		return rs;
	}

	/**
	 * 判断密码有无字母
	 * @param pwd
	 * @return
	 */
	public static boolean pwdContainStr(String pwd){
		Pattern p = Pattern.compile("[a-z|A-Z]");
		Matcher m = p.matcher(pwd);
		return m.find();
	}

	/**
	 * 判断密码有无数字
	 * @param pwd
	 * @return
	 */
	public static boolean pwdContainNum(String pwd){
		Pattern p = Pattern.compile("[0-9]");
		Matcher m = p.matcher(pwd);
		return m.find();
	}
	/**
	 * 校验后台管理员密码，必须是字母+数字+字符格式10位以上
	 * @param pwd
	 * @return
	 */
	public static boolean checaAdminPwd(String pwd){
		String regEx = "^(?![0-9A-Za-z]+$)[0-9A-Za-z~!@#$%^&*()]{10,}$";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(pwd);
		boolean rs = mat.find(); 
		return rs;

	}

	public static String gbk2Utf(String gbk) throws UnsupportedEncodingException {  
		char[] c = gbk.toCharArray();  
		byte[] fullByte = new byte[3*c.length];  
		for (int i=0; i<c.length; i++) {  
			String binary = Integer.toBinaryString(c[i]);  
			StringBuffer sb = new StringBuffer();  
			int len = 16 - binary.length();  
			//前面补零  
			for(int j=0; j<len; j++){  
				sb.append("0");  
			}  
			sb.append(binary);  
			//增加位，达到到24位3个字节  
			sb.insert(0, "1110");  
			sb.insert(8, "10");  
			sb.insert(16, "10");  
			fullByte[i*3] = Integer.valueOf(sb.substring(0, 8), 2).byteValue();//二进制字符串创建整型  
			fullByte[i*3+1] = Integer.valueOf(sb.substring(8, 16), 2).byteValue();  
			fullByte[i*3+2] = Integer.valueOf(sb.substring(16, 24), 2).byteValue();  
		}  
		//模拟UTF-8编码的网站显示  
		return new String(fullByte,"UTF-8");
	} 

	public static boolean checkDateString(String dateStr){
		String eL= "[1-9]{1}[0-9]{3}-[0-9]{2}-[0-9]{2}";   
		Pattern p = Pattern.compile(eL);    
		Matcher m = p.matcher(dateStr);    
		return m.matches();
	}

	public static String getGbk(String str) throws UnsupportedEncodingException{
		return new String(str.getBytes("UTF-8"),"GB2312");
	}


	public static String getEmailLoginUrl(String email){
		String url = email.split("@")[1]; 
		return "http://mail."+url;
	}

	public static String[] StringSort(String[] str) {
		MyString mySs[] = new MyString[str.length];// 创建自定义排序的数组
		for (int i = 0; i < str.length; i++) {
			mySs[i] = new MyString(str[i]);
		}
		Arrays.sort(mySs);// 排序
		String[] str2 = new String[mySs.length];
		for (int i = 0; i < mySs.length; i++) {
			str2[i] = mySs[i].s;
		}
		return str2;
	}


	/**
	 * 产生协议的单号，借款单号，投标单号
	 * @param type ：单号类型
	 * @param numb ：当前数
	 * @param endNum:随机两位数
	 * 协议编号：如：HT+20130927（时间）+0013(当前订单的顺序)+09（随机数）
	 * 合同编号:HT20130927001391,投资申请号TZ130927010919,借款申请号JK130927001391
	 * @return
	 */
	public static String madeAgreementNo(String type,String date,int numb,int endNum){
		//int endNum=(int)(Math.random()*90)+10;
		if (numb<10) {
			return type+date+"000"+numb+endNum;
		}else if(numb<100){
			return type+date+"00"+numb+endNum;
		}else if (numb<1000) {
			return type+date+"0"+numb+endNum;
		}else{
			return type+date+numb+endNum;
		}
	}

	public static String[] splitArray(String[]  array){

		for (int i = 0; i < array.length; i++) {
			/*String[] newArray = null;
			if(i/50 == 0){ 
				newArray = new String[]{}; 
			}else{

			}*/
		}
		return null;
	}

	public static String[] getArrayByIndex(String[][] ary,int index){
		String[] new_ary = new String[ary[index].length];
		for (int i = 0; i < ary[index].length; i++) {
			new_ary[i] = ary[index][i];
		}
		return new_ary;
	}

	/**
	 * 环迅解析xml使用
	 * @param xml
	 * @param nodeName
	 * @return
	 */
	public static String getXmlNodeValue(String xml ,String nodeName){
		String temp1[] = xml.split("<"+nodeName+">");
		String temp2[] = temp1[1].split("</"+nodeName+">");
		String resultXml = temp2[0];
		return resultXml;
	}

	/**
	 * 字符串解码
	 * @param sStr
	 * @return String
	 */
	public static String UrlDecoder(String sStr){
		String sReturnCode = sStr;
		try {
			sReturnCode = URLDecoder.decode(sStr, "utf-8");
		} catch (Exception e) {
		}
		return sReturnCode;
	}

	/**
	 * 字符串编码
	 * @param sStr
	 * @param sEnc
	 * @return String
	 */
	public final static String UrlEncoder(String sStr, String sEnc){
		String sReturnCode = "";
		try{
			sReturnCode = URLEncoder.encode(sStr, sEnc);
		}catch (Exception ex){
		}
		return sReturnCode;
	}
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}

}

