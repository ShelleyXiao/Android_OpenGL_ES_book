package com.zx.openglstudy.objects;

import android.opengl.GLES20;

import com.zx.openglstudy.Constants;
import com.zx.openglstudy.data.VertexArray;
import com.zx.openglstudy.programe.TextureShaderProgram;
import com.zx.openglstudy.utils.TextureHelper;

public class Table {

    private static final int POSITION_COMPONENT_COUNT = 2;
    private static final int TEXTURE_COORDINATES_COMPONENT_COUNT = 2;
    private static final int STRIDE =
            (POSITION_COMPONENT_COUNT + TEXTURE_COORDINATES_COMPONENT_COUNT) * Constants.BYTES_PER_FLOAT;

    private static final float[] VERTEX_DATA = {

            // x y s t
            0f, 0f, 0.5f, 0.5f,
            -0.5f, -0.8f, 0f, 0.9f,
            0.5f, -0.8f, 1f, 0.9f,
            0.5f, 0.8f, 1f, 0.1f,
            -0.5f, 0.8f, 0f, 0.1f,
            -0.5f, -0.8f, 0f, 0.9f
    };

    private final VertexArray vertexArray;

    public Table() {
        vertexArray = new VertexArray(VERTEX_DATA);
    }

    public void bindData(TextureShaderProgram program) {
        vertexArray.setVertexAttribPointer(0, program.getaPositionLocation(),
                POSITION_COMPONENT_COUNT, STRIDE);
        vertexArray.setVertexAttribPointer(POSITION_COMPONENT_COUNT, program.getaTextureCoordinateLocation(), TEXTURE_COORDINATES_COMPONENT_COUNT, STRIDE);

    }

    public void draw() {
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, 6);
    }
}
