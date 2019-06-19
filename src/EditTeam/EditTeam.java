/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditTeam;

import java.util.Date;

/**
 *
 * @author Viko
 */
public class EditTeam {
    private int id;
    private String name;
    private String City;
    private String Address;
    private String MobilePhone;
    private byte[]picture;
    private Date DateEstablishment;
    
    public EditTeam(int pid, String pname,String pCity,String pAddress,String pMobilePhone ,Date pDateEstablishment,byte[]ppicture){
      this.id = pid;  
      this.name = pname;
      this.picture = ppicture;
      this.City = pCity;
      this.Address = pAddress;
      this.MobilePhone = pMobilePhone;
      this.DateEstablishment = pDateEstablishment;
    }

    public int getID(){
      return this.id;
    }
    
    public String getName(){
    
        return this.name;
    }
    
    public byte [] getPicture(){
    
        return this.picture;
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
    
    public Date getDateEstablishment(){
        return this.DateEstablishment;
    }
}
