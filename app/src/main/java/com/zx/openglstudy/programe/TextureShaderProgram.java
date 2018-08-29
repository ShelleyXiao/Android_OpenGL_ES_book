package com.zx.openglstudy.programe;

import android.content.Context;
import android.opengl.GLES20;

import com.zx.openglstudy.R;

public class TextureShaderProgram extends ShaderProgram {

    private final int uMatrixLocation;
    private final int uTextureUnitLocation;

    private final int aPositionLocation;
    private final int aTextureCoordinateLocation;

    public TextureShaderProgram(Context context) {
        super(context, R.raw.texture_vertex_shader, R.raw.texture_fragment_shader);

        aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);

        uMatrixLocation = GLES20.glGetUniformLocation(program, U_MATRIX);
        uTextureUnitLocation = GLES20.glGetUniformLocation(program, U_TEXTURE_UINT);
        aTextureCoordinateLocation = GLES20.glGetAttribLocation(program, A_TEXTURE_COORDINATES);
    }

    public void setUniforms(float[] matrix, int textureId) {
        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);
        GLES20.glActiveTexture(textureId);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);

        GLES20.glUniform1i(uTextureUnitLocation, 0);
    }

    public int getaPositionLocation() {
        return aPositionLocation;
    }

    public int getaTextureCoordinateLocation() {
        return aTextureCoordinateLocation;
    }
}
