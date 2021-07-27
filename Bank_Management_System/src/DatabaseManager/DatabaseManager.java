/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseManager;

import BeanClasses.AccountNoBean;
import BeanClasses.AccountTypeBean;
import BeanClasses.CustomerBean;
import BeanClasses.TransactionBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Danish
 */
public class DatabaseManager {
   //bank connection starts
	private static Connection con;
//    private static String fac_id;
	static{
		try{
			init();
		}catch(Exception e){
		}
	}
	private static void init()throws Exception{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con=DriverManager.getConnection("jdbc:odbc:bank");
                System.out.println("Connection succesfull");
	}
     //bank connection ends
        
//**************************************ACCOUNT TYPE METHODS STARTS HERE*************************************        
	public static int addAccountType(String accountTypeName,String remarks)throws Exception{
		String query="INSERT into account_type (acc_type,remarks) values ('"+accountTypeName+"','"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int deleteAccountType(int accountTypeId)throws Exception{
		String query="DELETE from account_type where acc_type_id="+accountTypeId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
        }     
	public static int updateAccountType(int accountTypeId,String accountTypeName,String remarks)throws Exception{
		String query="UPDATE account_type set acc_type='"+accountTypeName+"' , remarks='"+remarks+"' where acc_type_id="+accountTypeId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}                
	public static Vector getAccountType()throws Exception{
		String query="select * from account_type";
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
			while(result.next()){
				AccountTypeBean bean=new AccountTypeBean();	
				bean.setAccountTypeId(result.getInt("acc_type_id") );
				bean.setAccountTypeName(result.getString("acc_type") );
				bean.setRemarks( result.getString("remarks") );			
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}        
//*****************************END ACCOUNT TYPE METHODS****************************************************	

//**************************************CUSTOMER METHODS STARTS HERE*************************************        
	public static int addCustomer(String firstName,String lastName,String city,String state,String zipCode,String address,String country,String email,String fax,String cellNo,String dateOfCreate,String remarks)throws Exception{
                if(dateOfCreate!=null)
                    dateOfCreate="#"+dateOfCreate+"#";
                else
                   dateOfCreate=null; 
		String query="INSERT into customer (first_name,last_name,city,state,zip_code,address,country,email,fax,cell_no,date_of_create,remarks) values ('"+firstName+"','"+lastName+"','"+city+"','"+state+"','"+zipCode+"','"+address+"','"+country+"','"+email+"','"+fax+"','"+cellNo+"',"+dateOfCreate+",'"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int deleteCustomer(int customerId)throws Exception{
		String query="DELETE from customer where customer_id="+customerId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
        }     
	public static int updateCustomer(int customerId,String firstName,String lastName,String city,String state,String zipCode,String address,String country,String email,String fax,String cellNo,String dateOfCreate,String remarks)throws Exception{
                if(dateOfCreate!=null)
                    dateOfCreate="#"+dateOfCreate+"#";
                else
                   dateOfCreate=null;
                String query="UPDATE customer set first_name='"+firstName+"', last_name='"+lastName+"', city='"+city+"',state='"+state+"',zip_code='"+zipCode+"',address='"+address+"',country='"+country+"',email='"+email+"',fax='"+fax+"',cell_no='"+cellNo+"',date_of_create="+dateOfCreate+", remarks='"+remarks+"' where customer_id="+customerId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}                
	public static Vector getCustomer()throws Exception{
		String query="select * from customer";
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
			while(result.next()){
				CustomerBean bean=new CustomerBean();	
				bean.setCustomerId(result.getInt("customer_id") );
				bean.setFirstName(result.getString("first_name") );
                                bean.setLastName(result.getString("last_name"));
                                bean.setCity(result.getString("city"));
                                bean.setState(result.getString("state"));
                                bean.setZipCode(result.getString("zip_code"));
                                bean.setCountry(result.getString("country"));
                                bean.setAddress(result.getString("address"));
                                bean.setEmail(result.getString("email"));
                                bean.setFax(result.getString("fax"));
                                bean.setCellNo(result.getString("cell_no"));
                                bean.setDateOfCreate(result.getDate("date_of_create"));
				bean.setRemarks( result.getString("remarks") );	
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}        
//*****************************END CUSTOMER METHODS****************************************************	        
    
//**************************************ACCOUNT NO METHODS STARTS HERE*************************************        
	public static int addAccountNo(int accTypeId,int customerId,String pinCode,String dateOfCreate,String remarks)throws Exception{
                if(dateOfCreate!=null)
                    dateOfCreate="#"+dateOfCreate+"#";
                else
                   dateOfCreate=null; 
		String query="INSERT into account_no (acc_type_id,customer_id,pin_code,date_of_create,remarks) values ("+accTypeId+","+customerId+",'"+pinCode+"',"+dateOfCreate+",'"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int deleteAccountNo(int accountNoId)throws Exception{
		String query="DELETE from account_no where acc_no_id="+accountNoId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
        }     
	public static int updateAccountNo(int accTypeId,int customerId,int accountNoId,String pinCode,String dateOfCreate,String remarks)throws Exception{
                if(dateOfCreate!=null)
                    dateOfCreate="#"+dateOfCreate+"#";
                else
                   dateOfCreate=null;
                String query="UPDATE account_no set acc_type_id="+accTypeId+", customer_id="+customerId+",pin_code='"+pinCode+"',date_of_create="+dateOfCreate+", remarks='"+remarks+"' where acc_no_id="+accountNoId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}                
	public static Vector getAccountNo(int accountTypeId,int customerId)throws Exception{
		String query="select * from account_no where acc_type_id="+accountTypeId+" and customer_id="+customerId;
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
			while(result.next()){
				AccountNoBean bean=new AccountNoBean();	
				bean.setCustomerId(result.getInt("customer_id") );
                                bean.setAccountTypeId(result.getInt("acc_type_id") );
                                bean.setAccountNoId(result.getInt("acc_no_id") );
                                bean.setPinCode(result.getString("pin_code"));
                                bean.setDateOfCreate(result.getDate("date_of_create"));
				bean.setRemarks( result.getString("remarks") );	
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}        
//*****************************END ACCOUNT NO METHODS****************************************************        
       
//**************************************TRANSACTION METHODS STARTS HERE*************************************        
	public static int addTransaction(int accNoId,String amount,String dateOfTransaction,String transactionType,String remarks)throws Exception{
                if(dateOfTransaction!=null)
                    dateOfTransaction="#"+dateOfTransaction+"#";
                else
                   dateOfTransaction=null; 
		String query="INSERT into transaction (acc_no_id,trans_date,amount,trans_type,remarks) values ("+accNoId+","+dateOfTransaction+",'"+amount+"','"+transactionType+"','"+remarks+"')";
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}
	public static int deleteTransaction(int transactionId)throws Exception{
		String query="DELETE from transaction where trans_id="+transactionId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
        }     
	public static int updateTransaction(int accNoId,int transactionId,String amount,String dateOfTransaction,String transactionType,String remarks)throws Exception{
                if(dateOfTransaction!=null)
                    dateOfTransaction="#"+dateOfTransaction+"#";
                else
                   dateOfTransaction=null;
                String query="UPDATE transaction set acc_no_id="+accNoId+", trans_date="+dateOfTransaction+", amount='"+amount+"',trans_type='"+transactionType+"', remarks='"+remarks+"' where trans_id="+transactionId;
		System.out.println(query);
		
		Statement st=null;
		try{
			st=con.createStatement();
			int rows=st.executeUpdate(query);
			return rows;
		}finally{
			if (st!=null)
			st.close();
		}
	}                
	public static Vector getTransaction(int accountNoId)throws Exception{
		String query="select * from transaction where acc_no_id="+accountNoId;
		System.out.println(query);
		Statement st=null;
		ResultSet result=null;
		try{
			st=con.createStatement();
			result=st.executeQuery(query);
			Vector v=new Vector();
			while(result.next()){
				TransactionBean bean=new TransactionBean();	
				//bean.setCustomerId(result.getInt("customer_id") );
                              //  bean.setAccountTypeId(result.getInt("acc_type_id") );
                                bean.setAccountId(result.getInt("acc_no_id") );
                                bean.setTransactionId(result.getInt("trans_id"));
                                bean.setAmount(result.getString("amount"));
                                bean.setTransactionType(result.getString("trans_type"));
                                bean.setDateOfTransaction(result.getDate("trans_date"));
				bean.setRemarks( result.getString("remarks") );	
				v.addElement(bean);
			}
			return v;
		}finally{
			if(result!=null)result.close();
			if(st!=null)st.close();
		}
	}        
//*****************************END TRANSACTIO METHODS****************************************************         
        
}//end databaseManager class
