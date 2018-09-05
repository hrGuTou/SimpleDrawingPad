/*
        BY Haoran He
        CSC221
        23528972

        Assignment 5
 */
import java.awt.*;
import java.io.Serializable;

public class MyLine extends MyShape implements Serializable{

    public MyLine(){
        super();
    }

    public MyLine(int x0, int x1, int y0, int y1, Color color){
        super(x0,x1,y0,y1,color);
    }

   @Override
   public void draw(Graphics g) {
    g.setColor(getColor());
    g.drawLine(getx0(),gety0(),getx1(),gety1());
   }
}
