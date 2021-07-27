/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnDeCoder;

import java.text.SimpleDateFormat;

/**
 *
 * @author Danish
 */
public class Decoder {
	public static String transactionTypeDecode(String transactionType){
		if(transactionType.equals("CA"))return "CASH";
		if(transactionType.equals("CH"))return "CHECK";
		if(transactionType.equals("O"))return "OTHER";

		return transactionType;	
	}         
        public static String getDateFormat(java.util.Date date)
        {
        SimpleDateFormat f = new SimpleDateFormat("dd-MMM-YYYY");
            String d=f.format(date);
            return d;
        }
    
}
