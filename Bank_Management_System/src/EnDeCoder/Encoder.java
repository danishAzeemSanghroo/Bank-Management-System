/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnDeCoder;

/**
 *
 * @author Danish
 */
public class Encoder {
	public static String transactionTypeEncode(String transactionType){
		if(transactionType.equals("CASH"))return "CA";
		if(transactionType.equals("CHECK"))return "CH";
		if(transactionType.equals("OTHER"))return "O";

		return transactionType;	
	}  
    
}
