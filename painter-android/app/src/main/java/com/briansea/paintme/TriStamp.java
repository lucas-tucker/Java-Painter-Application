package com.briansea.paintme;

import android.graphics.Canvas; 
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Path;

public class TriStamp extends Stamp{

    public TriStamp() {
        super();
    }

    public TriStamp( TriStamp other ) {
        super(other);
    }


    public void draw(Canvas canvas, Paint paint ) {
Point topleft = getTopLeft();

Path path = new Path();
path.moveTo(topleft.x, topleft.y + getHeight());

if (invertedY() == true){
    path.moveTo(topleft.x, topleft.y);
    path.lineTo((getWidth() * 1/2) + topleft.x, topleft.y + getHeight());
    path.lineTo(getWidth() + topleft.x, topleft.y);
    path.lineTo(topleft.x, topleft.y);
}

if (invertedY() == false) {
    path.lineTo((getWidth() * 1 / 2) + topleft.x, topleft.y);
    path.lineTo(getWidth() + topleft.x, getHeight() + topleft.y);
    path.lineTo(topleft.x, topleft.y + getHeight());

}
canvas.drawPath(path, paint);
    }

    @Override

    public Stamp newInstance(){
        return new TriStamp();
    }
}
