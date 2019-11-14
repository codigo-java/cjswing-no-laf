package br.com.codigojava.cjswing;

public class CJTextColor {
	
	public static String Red(String texto){
		return "<html><font color='#FF0000'>"+texto+"</font></html>";
	}
	public static String RedBold(String texto){
		return "<html><font color='#FF0000'><b>"+texto+"</b></font></html>";
	}
	
	public static String Green(String texto){
		return "<html><font color='#008B00'>"+texto+"</font></html>";
	}
	public static String GreenBold(String texto){
		return "<html><font color='#008B00'><b>"+texto+"</b></font></html>";
	}

	public static String Blue(String texto){
		return "<html><font color='#0000FF'>"+texto+"</font></html>";
	}
	public static String BlueBold(String texto){
		return "<html><font color='#0000FF'><b>"+texto+"</b></font></html>";
	}
	
	public static String Yellow(String texto){
		return "<html><font color='#FFFF00'>"+texto+"</font></html>";
	}
	public static String YellowBold(String texto){
		return "<html><font color='#FFFF00'><b>"+texto+"</b></font></html>";
	}
	
	public static String White(String texto){
		return "<html><font color='#FFFFFF'>"+texto+"</font></html>";
	}
	public static String WhiteBold(String texto){
		return "<html><font color='#FFFFFF'><b>"+texto+"<b></font></html>";
	}
	
	public static String Black(String texto){
		return "<html><font color='#000000'>"+texto+"</font></html>";
	}
	public static String BlackBold(String texto){
		return "<html><font color='#000000'><b>"+texto+"</b></font></html>";
	}

	public static String Custom(String texto, String rgbHexCode){
		return "<html><font color='"+rgbHexCode+"'>"+texto+"</font></html>";
	}
	public static String CustomBold(String texto, String rgbHexCode){
		return "<html><font color='"+rgbHexCode+"'><b>"+texto+"</b></font></html>";
	}

}
