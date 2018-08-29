package com.zx.openglstudy.objects;

import android.content.Context;
import android.opengl.GLES20;

import com.zx.openglstudy.Constants;
import com.zx.openglstudy.data.VertexArray;
import com.zx.openglstudy.programe.ColorShaderProgram;

public class Mallet {

    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int COLOR_COMPONENT_COUNT = 3;
    private static final int STRIDE =
            (POSITION_COMPONENT_COUNT + COLOR_COMPONENT_COUNT) * Constants.BYTES_PER_FLOAT;

    private static final float[] VERTEX_DATA = {
            0f, -0.4f, 0f, 0f, 1f, 0f, 0.4f, 1f, 0f, 0f
    };

    private final VertexArray vertexArray;

    public Mallet() {
        this.vertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(ColorShaderProgram program) {
        vertexArray.setVertexAttribPointer(0, program.getaPositionLocation(),
                POSITION_COMPONENT_COUNT, STRIDE);
        vertexArray.setVertexAttribPointer(POSITION_COMPONENT_COUNT, program.getaColorLocation()
                , COLOR_COMPONENT_COUNT, STRIDE);
    }

    public void draw() {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);
    }
}
