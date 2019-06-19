package TeamInfo;

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
public class Team {
    private int id;
    private String TeamName;
    private String FirstName;
    private String LastName;
    private String PlayerCity;
    private String PlayerAddress;
    private String PlayerMobilePhone;
    private String City;
    private String Address;
    private String MobilePhone;
    private Date Establishment;
    private Date DateOfCreated;
    private int NumberOfPlayers;
    private byte[] Image;
    
    public Team(int pid, String Tname, String pCity, String pAddress, String pMobilePhone, 
                Date pEstablishment, Date pDateCreated, byte[]pImage){
      this.id = pid;
      this.TeamName = Tname;
      this.City = pCity;
      this.Address = pAddress;
      this.MobilePhone = pMobilePhone;
      this.Establishment = pEstablishment;
      this.DateOfCreated = pDateCreated;
      //this.NumberOfPlayers = pNumberOfPlayers;
      this.Image = pImage;
    }
     
    public Team(String pFirstName, String pLastName, String ppCity, String ppAddress, String pPlayerMobilePhone){
      this.FirstName = pFirstName;
      this.LastName = pLastName;
      this.PlayerCity = ppCity;
      this.PlayerAddress = ppAddress;
      this.PlayerMobilePhone = pPlayerMobilePhone;
    }
    public int getId(){  
        return this.id;
    }
    
    public String getTeamName(){
        return this.TeamName;
    }
    
    public String getFirstName(){
        return this.FirstName;
    }
    
    public String getLastName(){
        return this.LastName;
    }
    
    public String getPlayerCity(){
        return this.PlayerCity;
    }
    
    public String getPlayerAddress(){
        return this.PlayerAddress;
    }
    
    public String getPlayerPhone(){
        return this.PlayerMobilePhone;
    }
    
    public String getCity(){
        return this.City;
    }
    
    public String getAddress(){
        return this.Address;
    }
    
    public String getMobilePhone(){
        return this.MobilePhone;
    }
    
    public Date getEstablishment(){
        return this.Establishment;
    }
    
    public int getNumberOfPLayers(){
        return this.NumberOfPlayers;
    }
    
    public Date getDateCreated(){
       return this.DateOfCreated;
    }
    
    public byte[] getImage(){
       return this.Image;
    }
}
