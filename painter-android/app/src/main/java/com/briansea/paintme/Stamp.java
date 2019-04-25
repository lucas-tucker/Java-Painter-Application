package com.briansea.paintme;



import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.Point;
import android.graphics.RectF;

public abstract class Stamp extends RectShape{

    private Paint style;
    private Point topLeft;
    private int width;
    private int height;

    public Stamp() {
        style = new Paint();
        topLeft = new Point(0,0);
    }

    public Stamp( Stamp other ) {
        this.style = new Paint(other.style);
        this.topLeft = new Point( other.topLeft.x, other.topLeft.y);
    }

    public void draw( Canvas canvas, Paint paint ){
        super.draw( canvas, paint );

    }

    public boolean invertedX(){
        return width < 0;
    }


    public boolean invertedY() {
        return height < 0;
    }


    public boolean setTopLeft( Point tl ) {
        boolean rtn = false;
        if( tl != null ){
            topLeft = tl;

            RectF topLeft = this.rect();
            topLeft.left = tl.x;
            topLeft.top = tl.y;
            rtn = true;
        }
        return rtn;
    }


    public Point getTopLeft() {
        return topLeft;
    }

    public boolean setStyle( Paint style ) {
        boolean rtn = false;
        if( style != null ) {
            this.style = style;
            rtn = true;
        }
        return rtn;
    }


    public void setDimensions( int width, int height ){
        this.width = width;
        this.height = height;

        this.resize( width(), height() );
    }


    public int width() {
        return Math.abs(this.width);
    }


    public int height() {
        return Math.abs(this.height);
    }


    public Paint getStyle(){
        return style;
    }


    public abstract Stamp newInstance();

}
