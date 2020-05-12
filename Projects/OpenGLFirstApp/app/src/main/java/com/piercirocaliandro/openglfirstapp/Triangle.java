package com.piercirocaliandro.openglfirstapp;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {
    //the vertex shader
    private final String vertexShaderCode =
            // This matrix member variable provides a hook to manipulate
            // the coordinates of the objects that use this vertex shader
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    // the matrix must be included as a modifier of gl_Position
                    // Note that the uMVPMatrix factor *must be first* in order
                    // for the matrix multiplication product to be correct.
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    //the fragment shader
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";
    private final int mProgram;

    // Use to access and set the view transformation
    private int vPMatrixHandle;

    private FloatBuffer vertexArray;
    static final int coordsPerVertex = 3; //the number of coordinates in this array
    static float triangleCoords[] = {0.0f, 0.622008459f, 0.0f, //top
            -0.5f, -0.311004243f, 0.0f, //bottom-left
            0.5f, -0.311004243f, 0.0f //bottom-right
    }; //coordinates are given in counterclockwise order
    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };
    private int positionHandle;
    private int colorHandle;

    private final int vertexCount = triangleCoords.length / coordsPerVertex;
    private final int vertexStride = coordsPerVertex * 4; // 4 bytes per vertex

    public Triangle(){
        //initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(triangleCoords.length*4);//number of coordinate values * 4 bytes per float
        bb.order(ByteOrder.nativeOrder()); //use the device native byte order
        vertexArray = bb.asFloatBuffer(); //creates a float buffer from the byte ones
        vertexArray.put(triangleCoords);
        vertexArray.position(0); //sets the buffer yo read the first coordinate
        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode);

        // create empty OpenGL ES Program
        mProgram = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(mProgram, vertexShader);

        // add the fragment shader to program
        GLES20.glAttachShader(mProgram, fragmentShader);

        // creates OpenGL ES program executables
        GLES20.glLinkProgram(mProgram);
    }

    //this function is needed to draw the object in the GLSurfaceView
    public void draw(float[] mvpMatrix){
        //add program to the OpenGL ES environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(positionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(positionHandle, coordsPerVertex,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexArray);

        // get handle to fragment shader's vColor member
        colorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(colorHandle, 1, color, 0);

        // get handle to shape's transformation matrix
        vPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, mvpMatrix, 0);

        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(positionHandle);

    }
}
