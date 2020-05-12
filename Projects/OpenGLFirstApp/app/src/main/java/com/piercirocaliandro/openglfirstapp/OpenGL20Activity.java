package com.piercirocaliandro.openglfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

public class OpenGL20Activity extends AppCompatActivity {
    private GLSurfaceView gLView; //the class in which OpenGL elements are drawn
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float previousX;
    private float previousY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gLView = new MyGLSurfaceView(this);
        setContentView(gLView);
    }

    class MyGLSurfaceView extends GLSurfaceView {
        private final MyGLRenderer glRenderer;

        public MyGLSurfaceView(Context context) {
            super(context);
            setEGLContextClientVersion(2); //creates an OpenGL2.0 context
            glRenderer = new MyGLRenderer();
            setRenderer(glRenderer);
            setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY); //this line needs comment to have the roation to work
        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            float x = e.getX();
            float y = e.getY();

            switch (e.getAction()) {
                case MotionEvent.ACTION_MOVE:

                    float dx = x - previousX;
                    float dy = y - previousY;

                    // reverse direction of rotation above the mid-line
                    if (y > getHeight() / 2) {
                        dx = dx * -1;
                    }

                    // reverse direction of rotation to left of the mid-line
                    if (x < getWidth() / 2) {
                        dy = dy * -1;
                    }

                    glRenderer.setAngle(
                        glRenderer.getAngle() +
                                ((dx + dy) * TOUCH_SCALE_FACTOR));
                    requestRender();
            }

            previousX = x;
            previousY = y;
            return true;
        }
    }
}
