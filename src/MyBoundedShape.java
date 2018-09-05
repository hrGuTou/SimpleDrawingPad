/*
        BY Haoran He
        CSC221
        23528972

        Assignment 5
 */
import java.awt.*;
import java.io.Serializable;

public abstract class MyBoundedShape extends MyShape implements Serializable{
    private  boolean filled;

    public MyBoundedShape(){
        super();
        filled = false;
    }

    public MyBoundedShape(int x0, int x1, int y0, int y1, Color color, boolean filled){
        super(x0,x1,y0,y1,color);
        this.filled = filled;
    }

    public void setFilled(boolean filled){
        this.filled = filled;
    }

    public boolean getFilled(){return filled;}

    public int upperLeftX(){
        if(getx0()>getx1())
            return getx1();
        else
            return getx0();
    }

    public int upperLeftY(){
        if(gety0()>gety1())
            return gety1();
        else
            return gety0();
    }

    public int width(){
        if(getx0()>getx1())
            return getx0()-getx1();
        else
            return getx1()-getx0();
    }

    public int height(){
        if(gety0()>gety1())
            return (gety0()-gety1());
        else
            return (gety1()-gety0());
    }

}
