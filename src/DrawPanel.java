/*
        BY Haoran He
        CSC221
        23528972

        Assignment 5
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.ArrayList;


public class DrawPanel extends JPanel implements Serializable {
    private ArrayList<MyShape> shape;
    private ArrayList<MyShape> backup = new ArrayList<MyShape>(); //for redo function
    private static int shapeCount;
    private int shapeType;
    private MyShape currentShape;
    private Color currentColor;
    private boolean filledFalse;
    private JLabel statusLabel;

    public ArrayList<MyShape> getShapes(){return shape;}
    public void loadFileComplete(ArrayList<MyShape> load){clearDrawing(); shape = load;repaint();}    //clear the whole screen then load
    public void loadFile(ArrayList<MyShape> load){  //add the shape to current
        shape.addAll(load);
        repaint();
    }


   public void setfilledFalse(boolean filledFalse){
        this.filledFalse = filledFalse;
    }

    public void setCurrentColor(Color currentColor){
        this.currentColor = currentColor;
    }

   public  void setShapeType(int shapeType){
        this.shapeType = shapeType;
    }

    //delete last element, return 0 indicates delete successfully
    public void clearLastShape(){   //add the target to backup arraylist, then delete
        if(shape.size()!=0){
            backup.add(shape.get(shape.size()-1));
            shape.remove(shape.size()-1);
            shapeCount = shape.size();
            repaint();
            currentShape = null;
        }
    }

    public void redoDrawing(){     //get the last deleted target from backup, add back to original, then delete that in backup arraylist.
        if(backup.size()!=0){
            shape.add(backup.get(backup.size()-1));
            backup.remove(backup.size()-1);
            repaint();
        }
    }

    //clear all shape
    public void clearDrawing(){     // add all targets to backup, then delete
        backup = (ArrayList<MyShape>)shape.clone();
        shape.clear();
        shapeCount = shape.size();
        repaint();
        currentShape = null;
    }

    public DrawPanel(JLabel label){
        shape = new ArrayList<MyShape>();
        shape = new ArrayList<MyShape>();
        shapeCount = 0;
        shapeType = 1; // LINE
        currentShape = null;
        currentColor = Color.BLACK;
        setBackground(Color.white);

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        statusLabel = label;
    }

    @Override
    public void paintComponent(Graphics g){
       super.paintComponent(g);
       if(currentShape!=null) currentShape.draw(g);
       for(MyShape s : shape){ s.draw(g); }
        repaint();
    }

    public class Mouse extends MouseAdapter implements  MouseMotionListener{
        @Override
        public void mousePressed(MouseEvent e) {
            //1 for Line, 2 for Oval, 3 for rectangle


            if(shapeType == 1) {
                currentShape = new MyLine(e.getX(), e.getX(), e.getY(), e.getY(), currentColor);
            }

            else if(shapeType == 2){
                currentShape = new MyOval(e.getX(),e.getX(),e.getY(),e.getY(),currentColor, filledFalse);
            }

            else{
                currentShape = new MyRectangle(e.getX(),e.getX(),e.getY(),e.getY(),currentColor, filledFalse);
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {

            currentShape.setx1(e.getX());
            currentShape.sety1(e.getY());
            shape.add(currentShape);
           
            currentShape = null;
            repaint();
            shapeCount = shape.size(); //increase by 1
            //System.out.println(shape.size());
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            currentShape.setx1(e.getX());
            currentShape.sety1(e.getY());

            repaint();

            statusLabel.setText("(" +e.getX()+", "+e.getY()+ ")");

        }

        @Override
        public void mouseMoved(MouseEvent e){
            statusLabel.setText("(" +e.getX()+", "+e.getY()+ ")");
        }
    }
}
