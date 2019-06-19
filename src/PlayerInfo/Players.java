package PlayerInfo;

import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Viko
 */
public class Players {
    private int id;
    private String FirstName;
    private String LastName;
    private String City;
    private String Gender;
    private String Team;
    private String age;
    private String Address;
    private Date BirthdayDate;
    private byte[]picture;
    private String MobilePhone;
    private String FirstPlayer;
    private String SecondPlayer;
    private String Result;
    
    public Players(int pid, String pfname, String plname, String pcity, String pAddress, Date pBirthdayDate,
                   String page, String pgender, String pMobilePhone, byte [] ppicture, String pteam 
                   ){
      this.id = pid;
      this.FirstName = pfname;
      this.LastName = plname;
      this.age = page;
      this.Gender = pgender;
      this.City = pcity;
      this.Team = pteam;
      this.picture = ppicture;
      this.BirthdayDate = pBirthdayDate;
      this.Address = pAddress;
      this.MobilePhone = pMobilePhone;
    }

    public Players(int pid, String pfname, String plname, String pCity, String pAddress, String page, String pgender, 
                   String pteam, Date pBirthdayDate, byte [] ppicture){
      this.id = pid;
      this.FirstName = pfname;
      this.LastName = plname;
      this.City = pCity;
      this.Address = pAddress;
      this.age = page;
      this.Gender = pgender;
      this.Team = pteam;
      this.picture = ppicture;
      this.BirthdayDate = pBirthdayDate;
    }
    
    public Players(String pFirstPlayer, String pSecondPlayer, String pResult){
      this.FirstPlayer = pFirstPlayer;
      this.SecondPlayer = pSecondPlayer;
      this.Result = pResult;
    }
    
    public int getId(){
        
        return this.id;
      
    }
    
    public String getFirstName(){
    
        return this.FirstName;
    }
    public String getLastName(){
    
        return this.LastName;
    }
    
    public String getGender(){
    
        return this.Gender;
    }
    
    public String getCity(){
    
        return this.City;
    
    }
    public String getTeam(){
    
        return this.Team;
    }
    public String getAge(){
    
        return this.age;
    }
    public byte [] getPicture(){
    
        return this.picture;
    }
    public String getAddress(){
        return this.Address;
    }
    public Date getBirthdayDate(){
        return this.BirthdayDate;
    }
    
    public String getMobilePhone(){
       return this.MobilePhone;
    }
    
    public String getFirstPlayer(){
       return this.FirstPlayer;
    }
    
    public String getSecondPlayer(){
       return this.SecondPlayer;
    }
    
    public String getResult(){
       return this.Result;
    }
}
