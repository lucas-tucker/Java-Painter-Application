package com.briansea.paintme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private LinearLayout toolbox;
    private DrawingPane drawingArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbox = findViewById(R.id.toolbox);
        drawingArea = findViewById( R.id.drawingarea );


        Stamp[] tools = { new RectStamp(), new TriStamp(), new EllipseStamp()};
        View.OnClickListener listener = new ToolSelector();

        for( Stamp tool : tools ) {
            Tool t = new Tool(toolbox.getContext(), null);
            t.setMinimumHeight(200);
            t.setStamp(tool);
            t.setOnClickListener( listener );
            toolbox.addView(t);
        }
    }

    public class ToolSelector implements View.OnClickListener {
        public void onClick( View view ) {
            Tool selectedTool = (Tool) view;
            drawingArea.setStamp(selectedTool.getStamp().newInstance());
        }
    }
}
