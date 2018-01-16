/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo.service;
 
import java.awt.EventQueue; 
 

/**
 *
 * @author student
 */
public class WorkerThread extends Thread{
    
    private boolean isRunning = true;
    private boolean isPaused = false;
    
    private int milis = 50;
    private Runnable endAction;
    private IAnimationModel model;
    
    public WorkerThread( IAnimationModel model , Runnable endAction ) {
        this.model = model;
        this.endAction = endAction;
    }
    
    public void stopAnim(){
        isRunning = false;
    }
    
    public void pauseAnim(){
        isPaused = true;
    }
     
    public void resumeAnim(){
        isPaused = false;
    }

    @Override
    public void run() {
        while (isRunning) {   
            
            if(!isPaused){
                model.step(); 
            }
            try{
                Thread.sleep(milis);
            }catch(Exception ex){
                ex.printStackTrace();
            }
            if( model.isEnd() ){
                isRunning = false;
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        endAction.run();
                    }
                }); 
            }
        } 
    } 
}
