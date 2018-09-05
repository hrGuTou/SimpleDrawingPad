/*
        BY Haoran He
        CSC221
        23528972

        Assignment 5
 */
import java.awt.*;
import java.io.Serializable;

public class MyRectangle extends MyBoundedShape implements Serializable {
    public MyRectangle(){
        super();
        //filled is false
    }

    public MyRectangle(int x0, int x1, int y0, int y1, Color color, boolean filled){
        super(x0,x1,y0,y1,color,filled);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        if(getFilled() == false){
            g.drawRect(upperLeftX(),upperLeftY(),width(),height());
        }
        else
            g.fillRect(upperLeftX(),upperLeftY(),width(),height());
    }
    
}
