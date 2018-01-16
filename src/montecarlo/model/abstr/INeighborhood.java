/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo.model.abstr;
 
import montecarlo.data.Field;


/**
 *
 * @author student
 */
public interface INeighborhood{
    public static final int MOORE = 1;
    public static final int HEXA = 2;
     
    public int getType(); 
    public Field[][] getBoundary( Field[][] fields , int x , int y , boolean periodic );
}
