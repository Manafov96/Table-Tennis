/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditPlayer;

/**
 *
 * @author Viko
 */
public class EditPlayer {
    private int ID;
    private String FirstName;
    private String LastName;
    private Object Team;
    private String MobilePhone;
    private byte[]picture;
    
    public EditPlayer(int pId,String pFirstName,String pLastName,Object pTeam,String pMobilePhone,byte[]ppicture){
      this.ID = pId;
      this.FirstName = pFirstName;
      this.LastName = pLastName;
      this.Team = pTeam;
      this.picture = ppicture;
      this.MobilePhone = pMobilePhone;
    }
    
    public int getId(){
        
        return this.ID;
      
    }
    
    public String getFirstName(){
    
        return this.FirstName;
    }
    
    public String getLastName(){
    
        return this.LastName;
    }
    
    
    public Object getTeam(){
        return this.Team;
    }

    public byte [] getPicture(){
    
        return this.picture;
    }
    
    public String getMobilePhone(){
        return this.MobilePhone;
    }
}
