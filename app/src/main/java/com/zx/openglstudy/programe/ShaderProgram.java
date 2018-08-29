package com.zx.openglstudy.programe;

import android.content.Context;
import android.opengl.GLES20;

import com.zx.openglstudy.utils.ShaderHelper;
import com.zx.openglstudy.utils.TextResourceReader;

public class ShaderProgram {

    public static final String U_TEXTURE_UINT = "u_TextureUint";
    public static final String U_MATRIX = "u_Matrix";


    public static final String A_POSITION = "a_Position";
    public static final String A_COLOR = "a_Color";

    public static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

    protected final int program;

    protected ShaderProgram(Context context, int vertexShaderResouceId,
                            int fragmentShaderResourceID) {
        program = ShaderHelper.buildProgram(TextResourceReader.readTextFileFromResource(context, vertexShaderResouceId)
                , TextResourceReader.readTextFileFromResource(context, fragmentShaderResourceID));


    }

    public void useProgram() {
        GLES20.glUseProgram(program);
    }


}
