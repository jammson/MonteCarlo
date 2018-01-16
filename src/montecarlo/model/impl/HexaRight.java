/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo.model.impl;
 
import montecarlo.data.Field;
import montecarlo.model.abstr.INeighborhood;


/**
 *
 * @author student
 */
public class HexaRight implements INeighborhood{

    @Override
    public int getType() { 
       return HexaRight.HEXA;
    }
  
    @Override
    public Field[][] getBoundary(Field[][] data, int i, int j, boolean b) { 
        Moore more = new Moore();
        Field[][] bound = more.getBoundary(data, i, j, b); 
        bound[0][0] = null;
        bound[2][2] = null;
        return bound;
    }
        
    
}
