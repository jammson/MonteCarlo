/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montecarlo.view;
 
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random; 
import montecarlo.data.Field;
import montecarlo.helper.Helper;
import montecarlo.view.listeners.IModelInteractionListener;
import montecarlo.view.listeners.OnClickField;

/**
 *
 * @author student
 */
public class FieldJPanel extends javax.swing.JPanel implements IModelInteractionListener{

    public static interface InteractionInterface{
        public void onFielfPlaced(int toplace);
    }
    
    private int size = 50;
    private int boxSize;
    private int padding = 0;
    
    private Field[][] data;
    
    private OnClickField listener;
    private InteractionInterface interactionInterface;

    public void setInteractionInterface(InteractionInterface interactionInterface) {
        this.interactionInterface = interactionInterface;
    }
    
    private int fieldsToPlace = 0;
    
    public void setListener(OnClickField listener){
        this.listener =listener;
    }
    
    
    @Override
    public Field[][] getData() { 
        return data;
    }
    /**
     * Creates new form FieldJPanel
     */
    public FieldJPanel() {
        initComponents(); 
        setPreferredSize(new Dimension(600, 600));
        setBoardSize(size); 
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent me) {
                int i = (me.getX()-padding)/((int)(boxSize+padding/2.0));
                int j = (me.getY()-padding)/((int)(boxSize+padding/2.0));
                if(i < 0 || i >= size ||j < 0 || j >= size ){
                    return;
                }
                if(listener!=null){
                    listener.onMove(i, j);
                }
            } 
            public void mouseClicked(MouseEvent me) {
                int i = (me.getX()-padding)/((int)(boxSize+padding/2.0));
                int j = (me.getY()-padding)/((int)(boxSize+padding/2.0));
                if(i < 0 || i >= size ||j < 0 || j >= size ){
                    return;
                }
                
                if( fieldsToPlace != 0 ){
                    if( data[i][j].getType() == Field.DEAD ){
                        data[i][j] = new Field(Field.ALIVE, Helper.gerRandomColor() );
                        fieldsToPlace--;
                        if(interactionInterface!=null){
                            interactionInterface.onFielfPlaced(fieldsToPlace);
                        }
                    }
                    repaint();
                }
                
                if(listener!=null){
                    listener.onClick(i, j);
                }
            }
            
            
            
        });
        
    } 
    
    @Override
    public void onRefresh(Field[][] data) {
        this.data = data; 
        size = data.length;
        boxSize = (int)(600.0f/(float)size);
        repaint();
    }
     
    public final void setBoardSize(int size){
        this.size = size; 
        boxSize = (int)(600.0f/(float)size); 
        clear();
        repaint();
    }   
    
    public final void  randomize(int c){
        clear();
        int n = 0;
        while(n<c){
            int x = Helper.randInt(0, size-1);
            int y = Helper.randInt(0, size-1);
            if( data[x][y].getType() == Field.DEAD ){
                data[x][y] = new Field(Field.ALIVE, Helper.gerRandomColor() );
                n++;
            }
        }
        repaint();
    }
    
    public void placeFields( int num ){
        clear();
        fieldsToPlace = num;
    }
    
    public void clear(){
        data = new Field[size][size];
        for( int i = 0 ; i < data.length ; i++ ){
            for( int j = 0 ; j < data.length ; j++ ){
                data[i][j] = new Field(Field.DEAD, Color.WHITE );
            }
        }
        repaint();
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.RED);
        g.fillRect( 0 , 0 , (int)(size*(boxSize+padding/2.0))+2*padding, (int)(size*(boxSize+padding/2.0))+2*padding);
        
        for(int i = 0 ; i < data.length ; i++){
            for(int j = 0 ; j < data[i].length ; j++){
                if(data[i][j]!=null){
                    g.setColor(data[i][j].getColor());
                    g.fillRect( (int)(padding+i*(boxSize+padding/2.0)) , (int)(padding+j*(boxSize+padding/2.0)), boxSize, boxSize);
                }
            }
        } 
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
