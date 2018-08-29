package com.zx.openglstudy.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;

import static android.opengl.GLUtils.texImage2D;

public class TextureHelper {

    public static int loadTexture(Context context, int resourceId) {
        final int[] texture = new int[1];
        GLES20.glGenTextures(1, texture, 0);
        if(texture[0] == 0) {
            LogUtils.e("can not create texture");
            return 0;
        }

        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inScaled = false;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, op);
        if(null == bitmap) {
            LogUtils.e("Resource id can not decoder");
            GLES20.glDeleteTextures(1, texture, 0);
            return 0;
        }

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture[0]);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR_MIPMAP_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);

        texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);

        return texture[0];
    }
}
