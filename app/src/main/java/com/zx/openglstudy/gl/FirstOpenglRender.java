package com.zx.openglstudy.gl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.zx.openglstudy.R;
import com.zx.openglstudy.utils.ShaderHelper;
import com.zx.openglstudy.utils.TextResourceReader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * User: ShaudXiao
 * Date: 2018-08-21
 * Time: 14:20
 * Company: zx
 * Description:
 * FIXME
 */

public class FirstOpenglRender implements GLSurfaceView.Renderer {

    private static final int POSITION_COMPOENT_COUNT = 2;
    private static final int COLOR_COMPOENT_COUNT = 3;

    private static final int BYTES_PER_FLOAT = 4;

    private static final String U_COLOR = "u_Color";
    private static final String A_POSITION = "a_Position";
    private static final String A_COLOR = "a_Color";
    private static final int STRIDE = (POSITION_COMPOENT_COUNT + COLOR_COMPOENT_COUNT) * BYTES_PER_FLOAT;

    private int uColorLocation;
    private int aPositionLocation;
    private int aColorLocation;

    private final FloatBuffer vertexData;

    private final Context mContext;

    private float[] tableVertexs = new float[]{
            0f, 0f, 0f, 14f, 9f, 14f, 9f, 0f
    };

    float[] tableVerticesWithTriangles = {
            // Order of coordinates: X, Y,  R, G, B

            // Triangle Fan
            0f, 0f, 1f, 1f, 1f,
            -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
            0.5f, -0.5f, 0.7f, 0.7f, 0.7f,
            0.5f, 0.5f, 0.7f, 0.7f, 0.7f,
            -0.5f, 0.5f, 0.7f, 0.7f, 0.7f,
            -0.5f, -0.5f, 0.7f, 0.7f, 0.7f,

            // Line 1
            -0.5f, 0f, 1f, 0f, 0f,
            0.5f, 0f, 1f, 0f, 0f,

            // Mallets
            0f, -0.25f, 0f, 0f, 1f,
            0f, 0.25f, 1f, 0f, 0f
    };

    private int program;

    public FirstOpenglRender(Context context) {
        this.mContext = context;
        vertexData = ByteBuffer
                .allocateDirect(tableVerticesWithTriangles.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        vertexData.put(tableVerticesWithTriangles);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        String vertexShaderCode = TextResourceReader.readTextFileFromResource(mContext, R.raw.simple_vertex_shader);
        String fragmentShaderCode = TextResourceReader.readTextFileFromResource(mContext, R.raw.simple_fragment_shader);

        int vertexShader = ShaderHelper.compileVertexShader(vertexShaderCode);
        int fragmentShader = ShaderHelper.compileFragmentShader(fragmentShaderCode);

        program = ShaderHelper.linkProgram(vertexShader, fragmentShader);
        ShaderHelper.validateProgram(program);

        GLES20.glUseProgram(program);

//        uColorLocation = GLES20.glGetUniformLocation(program, U_COLOR);
        aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);
        aColorLocation = GLES20.glGetAttribLocation(program, A_COLOR);

        vertexData.position(0);
        GLES20.glVertexAttribPointer(aPositionLocation, POSITION_COMPOENT_COUNT, GLES20.GL_FLOAT,
                false, STRIDE, vertexData);
        GLES20.glEnableVertexAttribArray(aPositionLocation);


        vertexData.position(POSITION_COMPOENT_COUNT);
        GLES20.glVertexAttribPointer(aColorLocation,COLOR_COMPOENT_COUNT,GLES20.GL_FLOAT,false,STRIDE,vertexData);
        GLES20.glEnableVertexAttribArray(aColorLocation);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);

        GLES20.glDrawArrays(GLES20.GL_LINES, 6, 2);

        GLES20.glDrawArrays(GLES20.GL_POINTS, 8, 1);

        GLES20.glDrawArrays(GLES20.GL_POINTS, 9, 1);
    }
}
