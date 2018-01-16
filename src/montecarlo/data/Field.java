/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo.data;

import java.awt.Color;

/**
 *
 * @author student
 */
public class Field {
    
    public static final int NEWBORN = 2;
    public static final int ALIVE = 1;
    public static final int DEAD = 0;
    
    private int type;
    private Color color;
    
    private double p;
    
    public int H; //distributed energy


    public Field(int type, Color color) {
        this.type = type;
        this.color = color;
        this.p = 0;
    }
    
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
     
    public double getP() {
        return p;
    }

    public void setP(double p) {
        this.p = p;
    }
    
    public void incP(double p){
        this.p += p;
    }
    public int getH(){
       return H;
    }
    
}
