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
public interface ITransitionRule{
    public Field transition( Field[][] boundary , int type , Field input );
}