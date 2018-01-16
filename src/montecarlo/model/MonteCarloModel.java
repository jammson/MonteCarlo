/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import montecarlo.data.Field;
import montecarlo.helper.Helper;
import montecarlo.model.abstr.INeighborhood;
import montecarlo.model.abstr.ITransitionRule;
import montecarlo.service.IAnimationModel;
import montecarlo.view.FieldJPanel;

/**
 *
 * @author student
 */
public class MonteCarloModel implements IAnimationModel{

    private FieldJPanel view;
    private int size;
    private Field[][] data;
    private ArrayList<ITransitionRule> rules;
    private INeighborhood neighborhood;
    private boolean periodic = false;
    private int iter;
    private int t;
    
    public MonteCarloModel( FieldJPanel view , int size , INeighborhood neighborhood , int iter , boolean  periodic) {
        this.size = size;
        this.view = view;
        this.rules = new ArrayList<ITransitionRule>(); 
        this.rules.add(new MonteCarloTransitionRule());
        this.data = new Field[size][size];
        this.periodic = periodic;
        this.neighborhood = neighborhood;
        this.iter = iter;
        this.t = 0;
        randomize();
    } 
    
    private void reset(){
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                data[i][j] = new Field(Field.DEAD,Color.WHITE);
            }
        }
    }
    
    public void randomize(){
        reset();
        for(int i = 0 ; i < size ; i++){
            for(int j = 0 ; j < size ; j++){
                data[i][j] = new Field(Field.ALIVE,Helper.gerRandomColor());
            }
        }
        refreshData();
    }
    
    public class Pair{
        public Pair(int i , int j){
            this.i =  i ;
            this.j =  j ;
        }
        public int i;
        public int j;
    }
    
    @Override
    public void step() {
        
        Field[][] newFields = new Field[size][size];
        
        ArrayList<Pair> array = new ArrayList<>();
        for( int i = 0 ; i < data.length ; i++ ){
            for( int j = 0 ; j < data[i].length ; j++ ){
                array.add( new Pair(i,j) );
            }
        }
        
        while (!array.isEmpty()) { // ulepszenie nie mogą się powtarzać         
            Pair p = array.remove( Helper.randInt(0,array.size()-1) );
            int i = p.i;
            int j = p.j; 
             
            Field[][] boundary = neighborhood.getBoundary( data , i  , j , periodic );
            for(ITransitionRule r : rules){
                Field f = r.transition( boundary , neighborhood.getType() , data[i][j] );
                if( f != null ){
                    data[i][j] = f;
                    break;
                }else{
                    data[i][j] = data[i][j];
                }
            }
        }
         
        this.t++;
        //data = newFields;
        refreshData(); 
    }
    
    
    private int randColor(){
        Random rand = new Random();
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        return new Color(r, g, b).getRGB();
    }

    @Override
    public void refreshData() {
        view.onRefresh(data);
    }

    @Override
    public boolean isEnd() {
        return t > iter;
    }
    
}
