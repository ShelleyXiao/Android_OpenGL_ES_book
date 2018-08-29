package com.zx.openglstudy.gl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.zx.openglstudy.R;
import com.zx.openglstudy.objects.Mallet;
import com.zx.openglstudy.objects.Table;
import com.zx.openglstudy.programe.ColorShaderProgram;
import com.zx.openglstudy.programe.TextureShaderProgram;
import com.zx.openglstudy.utils.MatrixHelper;
import com.zx.openglstudy.utils.ShaderHelper;
import com.zx.openglstudy.utils.TextResourceReader;
import com.zx.openglstudy.utils.TextureHelper;

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

public class AirHockeyRenderer implements GLSurfaceView.Renderer {
    private Context mContext;

    private final float[] projectionMatrix = new float[16];
    private final float[] modelMatrix = new float[16];

    private Table table;
    private Mallet mallet;

    private TextureShaderProgram textureProgram;
    private ColorShaderProgram colorProgram;

    private int texture;

    public AirHockeyRenderer(Context context) {
        this.mContext = context;

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        table = new Table();
        mallet = new Mallet();

        textureProgram = new TextureShaderProgram(mContext);
        colorProgram = new ColorShaderProgram(mContext);

        texture = TextureHelper.loadTexture(mContext, R.drawable.air_hockey_surface);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
//        final float aspectRation = width > height ?
//                (float)width / height : (float)height / width;
//        if(width > height) {
//            //landscape
//            Matrix.orthoM(projectionMatrix,0, -aspectRation, aspectRation, -1f, 1f, -1f, 1f);
//        } else  {
//            Matrix.orthoM(projectionMatrix, 0, -1f, 1f, -aspectRation, aspectRation, -1f, 1f);
//        }

        MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width / (float) height, 1.0f, 10f);
        final float[] temp = new float[16];
        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, 0f, 0f, -2.5f);
        Matrix.rotateM(modelMatrix, 0, -60f, 1f, 0f, 0f);
        Matrix.multiplyMM(temp, 0, projectionMatrix, 0, modelMatrix, 0);
        System.arraycopy(temp, 0, projectionMatrix, 0, temp.length);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        textureProgram.useProgram();
        textureProgram.setUniforms(projectionMatrix, texture);
        table.bindData(textureProgram);
        table.draw();

        colorProgram.useProgram();
        colorProgram.setUiforms(projectionMatrix);
        mallet.bindData(colorProgram);
        mallet.draw();
    }
}
