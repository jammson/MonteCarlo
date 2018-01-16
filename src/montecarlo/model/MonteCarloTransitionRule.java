/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo.model;

import java.awt.Color;
import java.util.ArrayList;
import montecarlo.data.Field;
import montecarlo.helper.Helper;
import montecarlo.model.abstr.ITransitionRule;

/**
 *
 * @author Seweryn
 */
public class MonteCarloTransitionRule implements ITransitionRule{

    @Override
    public Field transition(Field[][] boundary, int type, Field input) { 
        boundary[1][1] = null;
        
        ArrayList<Color> colors = getColors(boundary);
        if(!isInArray(colors, input.getColor())){
            colors.add(input.getColor());
        }
        
        if(colors.size() > 1){ // ulepszemnnie tylko na granicy
            
            int E = calcEnergy( boundary , input.getColor() ) ;
           
            // ulepszenie losujemy jeden kolor z otoczenia
            Color c = colors.remove( Helper.randInt(0, colors.size()-1) );
            int nE = calcEnergy( boundary , c );

            if( nE <= E ){  
                input.setColor(c);
            }  
           
        } 
        return input;
    }
    
    
    private int calcEnergy( Field[][] boundary , Color c ){
        int colors = 0;
        int bounds = 0;
        for( int i = 0 ; i < boundary.length ; i++ ){
            for( int j = 0 ; j < boundary[i].length ; j++ ){
                if( boundary[i][j] != null ){ 
                    bounds++;
                    if( boundary[i][j].getColor().equals(c)  ){
                        colors++; 
                    }
                }
            }
        }
        return bounds-colors;
    }
    
    private boolean isInArray(ArrayList<Color> colors,Color cc){
        boolean isInArray = false;
        for( Color c : colors  ){
            if(c.equals( cc )){
                isInArray = true;
                break;
            }
        }
        return isInArray;
    }
    
    private ArrayList<Color> getColors( Field[][] boundary){
        ArrayList<Color> colors = new ArrayList<>();
        for( int i = 0 ; i < boundary.length ; i++ ){
            for( int j = 0 ; j < boundary[i].length ; j++ ){
                if( boundary[i][j] != null ){ 
                    if( !isInArray( colors , boundary[i][j].getColor() ) ){
                        colors.add( boundary[i][j].getColor() );
                    }
                }
            }
        }
        return colors;
    }
     
    
}
