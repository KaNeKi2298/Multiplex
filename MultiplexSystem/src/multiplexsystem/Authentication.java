/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplexsystem;
import java.sql.*;

/**
 *
 * @author Akhil Agrawal
 */
public class Authentication {
    public static String email;
    public static String pass;
    
    public static int login(String email,String password){
        int p=0;
        Connection con=null;  
        try{  
           
            Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/multicket","root","AKhil@123");  
  
            Statement stmt=con.createStatement(); 
            ResultSet rs=stmt.executeQuery("call yo();");  
        
        while(rs.next()){
          
        if(rs.getString(1).equals(email)&&rs.getString(4).equals(password)){
           
            Authentication.email=rs.getString(1);
             Authentication.pass=rs.getString(4);
             
             p=1;
             break;
     }
 }
             con.close(); 


        }
        catch(Exception e){
            
            System.out.println(e);
        }
      return p;  
    }
    
   public static Connection CustomerConn(){
        int p=0;
        Connection con=null;  
        try{  
           
            Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/multicket","root","AKhil@123");
            
        }
        catch(Exception e){
          System.out.println(e);
        }
    
       return con;
   
   } 
    
}
