package com.zx.openglstudy.programe;

import android.content.Context;
import android.opengl.GLES20;

import com.zx.openglstudy.R;

public class ColorShaderProgram extends ShaderProgram {

    private int aPositionLocation;
    private int aColorLocation;
    private int uMatrixLocation;

    public ColorShaderProgram(Context context) {
        super(context, R.raw.simple_vertex_shader,
                R.raw.simple_fragment_shader);

        uMatrixLocation = GLES20.glGetUniformLocation(program, U_MATRIX);
        aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);
        aColorLocation = GLES20.glGetAttribLocation(program, A_COLOR);

    }

    public void setUiforms(float[] matrix) {
        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
    }

    public int getaPositionLocation() {
        return aPositionLocation;
    }

    public int getaColorLocation() {
        return aColorLocation;
    }
}