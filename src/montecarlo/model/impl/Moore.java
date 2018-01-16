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
public class Moore implements INeighborhood{

    @Override
    public int getType() { 
       return Moore.MOORE;
    }
  
    @Override
    public Field[][] getBoundary(Field[][] data, int i, int j, boolean b) { 
        Field[][] bound = new Field[3][3];
        int size = data.length;
        for(int ii = 0 ; ii < bound.length ; ii++)
            for(int jj = 0 ; jj < bound.length ; jj++)
                bound[ii][jj] = null;
        
        if(i==0){
            if(b){
                bound[0][1]=data[size-1][j];

                if(j==0){
                    if(b){
                        bound[0][0]=data[size-1][size-1];
                    }
                }else{
                    bound[0][0]=data[size-1][j-1];
                }

                if(j==size-1){
                    if(b){
                        bound[0][2]=data[size-1][0];
                    }
                }else{
                    bound[0][2]=data[size-1][j+1];
                } 
            }
        }else{
            bound[0][1]=data[i-1][j];

            if(j==0){
                if(b){
                    bound[0][0]=data[i-1][size-1];
                }
            }else{
                bound[0][0]=data[i-1][j-1];
            }

            if(j==size-1){
                if(b){
                    bound[0][2]=data[i-1][0];
                }
            }else{
                bound[0][2]=data[i-1][j+1];
            }  
        }

        if(j==0){
            if(b){
                bound[1][0]=data[i][size-1];
            }
        }else{
            bound[1][0]=data[i][j-1];
        }

        if(j==size-1){
            if(b){
                bound[1][2]=data[i][0];
            }
        }else{
            bound[1][2]=data[i][j+1];
        }  


        if(i==size-1){
            if(b){
                bound[2][1]=data[0][j];

                if(j==0){
                    if(b){
                        bound[2][0]=data[0][size-1];
                    }
                }else{
                    bound[2][0]=data[0][j-1];
                }

                if(j==size-1){
                    if(b){
                        bound[2][2]=data[0][0];
                    }
                }else{
                    bound[2][2]=data[0][j+1];
                } 
            }
        }else{
            bound[2][1]=data[i+1][j];

            if(j==0){
                if(b){
                    bound[2][0]=data[i+1][size-1];
                }
            }else{
                bound[2][0]=data[i+1][j-1];
            }

            if(j==size-1){
                if(b){
                    bound[2][2]=data[i+1][0];
                }
            }else{
                bound[2][2]=data[i+1][j+1];
            }  
        }      
        return bound;
    }
        
    
}
