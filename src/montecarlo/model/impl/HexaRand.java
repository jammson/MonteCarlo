/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo.model.impl;

import java.util.Random; 
import montecarlo.data.Field;
import montecarlo.model.abstr.INeighborhood;

/**
 *
 * @author student
 */
public class HexaRand implements INeighborhood{

    @Override
    public int getType() { 
       return HexaRand.MOORE;
    }
  
    @Override
    public Field[][] getBoundary(Field[][] data, int i, int j, boolean b) { 
        Random r = new Random();
        if(r.nextInt(100)>50){
            return new HexaLeft().getBoundary(data, i, j, b);
        }else{
            return new HexaRight().getBoundary(data, i, j, b);
        } 
    } 
    
}
