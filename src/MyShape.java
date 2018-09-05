/*
        BY Haoran He
        CSC221
        23528972

        Assignment 5
 */
import java.awt.*;
import java.io.Serializable;

public abstract class MyShape implements Serializable {
    private int x0, x1, y0, y1;
    private Color color;

    public MyShape(){
        x0=0;
        x1=0;
        y0=0;
        y1=0;
        color = Color.black;
    }

    public MyShape(int x0, int x1, int y0, int y1, Color color){
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
        this.color = color;
    }

    public void setx0(int x0){
        this.x0=x0;
    }
    public void setx1(int x1){
        this.x1=x1;
    }
    public void sety0(int y0){
        this.y0=y0;
    }
    public void sety1(int y1){
        this.y1=y1;
    }
    public void setColor(Color color){
        this.color = color;
    }

    public int getx0(){return x0;}
    public int getx1(){return x1;}
    public int gety0(){return y0;}
    public int gety1(){return y1;}
    public Color getColor(){return color;}

    public abstract void draw(Graphics g);

}

