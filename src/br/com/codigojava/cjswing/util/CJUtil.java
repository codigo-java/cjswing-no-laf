package br.com.codigojava.cjswing.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CJUtil {
	
    //retorna uma data com adicionando uma qtd de anos
    public static Date setYearDate(Date data, int ano){
    	Calendar c = Calendar.getInstance();
    	c.setTime(data);
    	c.set(Calendar.YEAR, c.get(Calendar.YEAR)+ano);
    	return c.getTime();
    }

    //retorna uma data no formato por extenso
    public static String getDataExtenso(Date data){
    	Calendar c = Calendar.getInstance();
    	int dia = c.get(Calendar.DAY_OF_MONTH);
    	int intMes = c.get(Calendar.MONTH);
    	int ano = c.get(Calendar.YEAR);   	

    	String mes = "";
        switch(intMes){
            case 0: mes = "de janeiro de";break;
            case 1: mes = "de fevereiro de";break;
            case 2: mes = "de março de";break;
            case 3: mes = "de abril de";break;
            case 4: mes = "de maio de";break;
            case 5: mes = "de junho de";break;
            case 6: mes = "de julho de";break;
            case 7: mes = "de agosto de";break;
            case 8: mes = "de setembro de";break;
            case 9: mes = "de outubro de";break;
            case 10: mes = "de novembro de";break;
            case 11: mes = "de dezembro de";break;
        }
    	
    	c.setTime(data);
    	return ""+dia+" "+mes+" "+ano;
    }
    
    public static boolean dataVencida(String data){
    	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  
        Date minhaData;
		try {
			minhaData = formato.parse(data);	          
	        if (minhaData.after(new Date())) {  
	        	// Ainda vai chegar o dia  	        	
	        	return false;
	        } else if (minhaData.before(new Date())) {  
	        	// O dia já aconteceu  
	        	return true;
	        }
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return false;
    }
    
    public static boolean dataVencida(Date data){        
        if (data.after(new Date())) {  
        	// Ainda vai chegar o dia  	        	
        	return false;
        } else if (data.before(new Date())) {  
        	// O dia já aconteceu  
        	return true;
        }
		return false;
    }
    
    public static Date getDataInicio(Date dataInicio){
		try{
			Calendar data = Calendar.getInstance();
			data = GregorianCalendar.getInstance(Locale.getDefault());
			data.setTime(dataInicio);
			data.set(Calendar.HOUR, 0);
			data.set(Calendar.MINUTE, 0);
			data.set(Calendar.SECOND, 0);
			data.set(Calendar.AM_PM, 0);  //0 define que AM
			//System.out.println(new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(data.getTime()));
			return data.getTime();
		} catch (Exception e) {
			//INKJOptionPane.create("Erro", "Verifique o campo da data inicial do período", INKIcons.IMAGE_ERROR);
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date getDataFim(Date dataFim){
		try{
			Calendar data = Calendar.getInstance();
			data.setTime(dataFim);
			data.set(Calendar.HOUR, 11);
			data.set(Calendar.MINUTE, 59);
			data.set(Calendar.SECOND, 59);
			data.set(Calendar.AM_PM, 1);  //1 define que PM
			//System.out.println(new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss").format(data.getTime()));
			return data.getTime();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

    public static Date stringToDate(String data){
    	String pattern = "dd/MM/yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
		try {
          date = format.parse(data);
          System.out.println(date);
        } catch (ParseException e) {
          e.printStackTrace();
        }        
    	return date;
    }
    
    public static String DateToString(Date data){
    	String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(data);          	
    }
    
    public static String DateTimeToStringMySQL(Date data){
    	String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(data);          	
    }    
    public static String DateTimeToStringSQLite(Date data){
    	String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(data);          	
    }
    public static String DateTimeToStringSQLSERVER(Date data){
    	String pattern = "dd/MM/yyyy HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(data);          	
    }
    
    public static Date stringToDateTime(String data){
    	String pattern = "dd/MM/yyyy - HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
		try {
          date = format.parse(data);
          System.out.println(date);
        } catch (ParseException e) {
          e.printStackTrace();
        }        
    	return date;
    }
    
	public static String getBoleano(boolean boo){
		if(boo){
			return "Sim";
		}
		return "Não";
	}
}
