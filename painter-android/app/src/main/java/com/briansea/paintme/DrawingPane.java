package com.briansea.paintme;

import java.util.Random;

import android.content.Context;

import android.view.MotionEvent;
import android.view.View;

import android.util.AttributeSet;
import android.util.Log;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Color;

import java.util.ArrayList;



public class DrawingPane extends View {


    private Paint drawingStyle;
    private ArrayList<Stamp> stamps;

    private Random rand;

    private Stamp selectedStamp;
    private Stamp drawing;

    public DrawingPane(Context context, AttributeSet attrs ){
        super( context, attrs );
        setFocusable(true);
        setFocusableInTouchMode(true);
        drawingStyle = setupPaint(Color.BLACK);

        this.setOnTouchListener( new DrawStamp() );

            stamps = new ArrayList<Stamp>();
             rand = new Random();
    }

    private Paint setupPaint( int strokeColor ){
        Paint style = new Paint();
        style.setColor( strokeColor );
        style.setAntiAlias( true );
        style.setStrokeWidth( 5 );
        style.setStyle( Paint.Style.FILL_AND_STROKE );
        style.setStrokeJoin( Paint.Join.ROUND );
        style.setStrokeCap( Paint.Cap.ROUND );
        return style;
    }

    @Override

    protected void onDraw( Canvas canvas ){

        // TODO: Draw the stamps on the screen
        for(Stamp stamp: stamps){
            stamp.draw(canvas,stamp.getStyle());
        }


        if( drawing != null  ){
            drawing.draw(canvas, drawing.getStyle());
        }

    }


    public void setStamp( Stamp stamp ) {
        this.selectedStamp = stamp;
    }


    private class DrawStamp implements View.OnTouchListener {

       private Point downPoint;

       public boolean onTouch( View view, MotionEvent event ) {


            if( event.getAction() == MotionEvent.ACTION_DOWN && selectedStamp != null ){

                drawing = selectedStamp.newInstance();



                int r = (int)(Math.random()*255);
                int g = (int)(Math.random()*255);
                int b = (int)(Math.random()*255);

                drawingStyle.setARGB(255, r, g, b);

                drawing.setStyle( new Paint(drawingStyle));

                downPoint = new Point( (int)event.getX() , (int)event.getY() );

            }

            if( selectedStamp == null ){
                return true;
            }

            if( event.getAction() == MotionEvent.ACTION_MOVE ){
                Point movePoint = new Point( (int)event.getX(), (int)event.getY() );



                Point topleft = new Point(downPoint.x, downPoint.y);

                if( movePoint.y < downPoint.y ){
                    topleft.y = movePoint.y;
                }

                if(movePoint.x < downPoint.x) {
                    topleft.x = movePoint.x;
                }



                int width = downPoint.x - movePoint.x;
                int height = downPoint.y - movePoint.y;

                drawing.setTopLeft(topleft);
                drawing.setDimensions(width, height);


                invalidate();
            }

            if( event.getAction() == MotionEvent.ACTION_UP ){
                Point upPoint = new Point( (int) event.getX(), (int) event.getY() );


                stamps.add(drawing);
                invalidate();
            }

            return true;
       }
    }
}
