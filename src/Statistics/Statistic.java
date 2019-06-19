/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;

/**
 *
 * @author Viko
 */
public class Statistic {
  private String FirstPlayer;
  private String SecondPlayer;
  private String FullName;
  private String Result;
  private int CountWin;

public Statistic(String pFirstPlayer, String pSecondPlayer, String pFullName, String pResult, int pCountWin){
  this.FirstPlayer = pFirstPlayer;
  this.SecondPlayer = pSecondPlayer;
  this.FullName = pFullName;
  this.Result = pResult;
  this.CountWin = pCountWin;
}  

public Statistic(String pFullName, int pCountWin) {
       this.FullName = pFullName;
       this.CountWin = pCountWin;
    }
public Statistic(String pFirstPlayer, String pSecondPlayer, String pResult) {
       this.FirstPlayer = pFirstPlayer;
       this.SecondPlayer = pSecondPlayer;
       this.Result = pResult;
    }

public String getFirstPlayer(){
  return this.FirstPlayer;
}

public String getSecondPlayer(){
  return this.SecondPlayer;
}

public String getFullName(){
  return this.FullName;
}

public String getResult(){
  return this.Result;
}

public int getCountWin(){
  return this.CountWin;
}

}
