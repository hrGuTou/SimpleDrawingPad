/*
        BY Haoran He
        CSC221
        23528972

        Assignment 5
 */
import java.awt.*;
import java.io.Serializable;

public class MyOval extends MyBoundedShape  implements Serializable {

    public MyOval(){
        super();
        //filled is false
    }

    public MyOval(int x0, int x1, int y0, int y1, Color color, boolean filled){
        super(x0,x1,y0,y1,color,filled);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        if(getFilled() == false){
            g.drawOval(upperLeftX(),upperLeftY(),width(),height());
        }
        else
            g.fillOval(upperLeftX(),upperLeftY(),width(),height());
    }

}
