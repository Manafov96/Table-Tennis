/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreateUser;

/**
 *
 * @author Viko
 */
public class Users {
   private int ID;
   private String fName;
   private String lName;
   private Object City;
   private String Address;
   private String mPhone;
   private int UserID;
   private String User;
   private String Password;
    
  public Users(int pId, String pfName, String plName, Object pCity, String pAddress, String pmPhone, String pUser, String pPassword, int pUserID){
    this.ID = pId;
    this.fName = pfName;
    this.lName = plName;
    this.City = pCity; 
    this.Address = pAddress;
    this.mPhone = pmPhone;
    this.User = pUser;
    this.Password = pPassword;
    this.UserID = pUserID;
  } 
  
  public Users(int pId, String pfName, String plName, Object pCity, String pAddress, String pmPhone){
     this.ID = pId;
     this.fName = pfName;
     this.lName = plName;
     this.City = pCity; 
     this.Address = pAddress;
     this.mPhone = pmPhone;
  }
  
  public Users(int pUserID, String pUser, String pPassword){
    this.User = pUser;
    this.Password = pPassword;
    this.UserID = pUserID;
  }
   
  public int getID(){
    return this.ID;
  }
  
  public String getFName(){
    return this.fName;
  }
  
  public String getLName(){
    return this.lName;
  }
  
  public Object getCity(){
    return this.City;
  }
  
  public String getAddress(){
    return this.Address;
  }
  
  public String getPhone(){
    return this.mPhone;
  }
  
  public String getUser(){
    return this.User;
  }
  
  public String getPassword(){
    return this.Password;
  }
  
  public int getUserID(){
    return this.UserID;
  }
}
