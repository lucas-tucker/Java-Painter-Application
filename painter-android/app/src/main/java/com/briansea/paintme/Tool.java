package com.briansea.paintme;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.graphics.Point;

import android.graphics.Canvas;


public class Tool extends ImageButton{

    private Stamp stamp;

    public Tool( Context context, AttributeSet attrs ) {
        super( context, attrs );
    }

    public void setStamp( Stamp stamp ) {
        this.stamp = stamp;
    }


    public Stamp getStamp() {
        return stamp;
    }

    @Override

    public void onDraw( Canvas canvas ) {
        super.onDraw(canvas);

        if( stamp != null ){

            stamp.setTopLeft(new Point(getWidth()/4, getHeight()/4 ));
            stamp.resize(getWidth()/2,getHeight()/2);

            stamp.draw(canvas, stamp.getStyle());
        }
    }

}
