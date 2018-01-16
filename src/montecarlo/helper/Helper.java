/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo.helper;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author student
 */
public class Helper {
    
    private static Random rand;
    
    public static Color gerRandomColor(){
        if(rand==null){
            rand = new Random();
        } 
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b);

    } 
    
    public static int randInt(int min, int max) {
        if(rand==null){
            rand = new Random();
        } 
        int randomNum = rand.nextInt((max - min) + 1) + min; 
        return randomNum;
    }
    
}
