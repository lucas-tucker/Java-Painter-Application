
package com.briansea.paintme;

        import android.graphics.Canvas;
        import android.graphics.Paint;
        import android.graphics.Point;
        import android.graphics.RectF;

public class EllipseStamp extends Stamp{

    public EllipseStamp() {
        super();
    }

    public EllipseStamp ( EllipseStamp other ) {
        super(other);
    }


    public void draw( Canvas canvas, Paint paint ) {
        Point topLeft = getTopLeft();

        RectF rect = new RectF(topLeft.x, topLeft.y,
                topLeft.x+getWidth(), topLeft.y+getHeight() );

        canvas.drawOval(rect, paint);
    }

    @Override

    public Stamp newInstance(){
        return new EllipseStamp();
    }
}
