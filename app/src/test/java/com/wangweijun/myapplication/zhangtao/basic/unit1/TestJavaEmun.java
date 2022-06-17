package com.wangweijun.myapplication.zhangtao.basic.unit1;

import org.junit.Test;

/**
 * author : user
 * email : xxx@xx.com
 * time   : 2022/06/16 16:13
 * version: 1.0
 * desc   :
 */
public class TestJavaEmun {

    @Test
    public void testJavaEnum() {
        FaceQualityErrorType type = FaceQualityErrorType.FRAME_NEED_HOLDING;
        System.out.println(type.name());
    }

    public static enum FaceQualityErrorType {
        NONE,
        FRAME_NEED_HOLDING,
        FACE_NOT_FOUND,
        FACE_POS_DEVIATED,
        FACE_NONINTEGRITY,
        FACE_TOO_DARK,
        FACE_TOO_BRIGHT,
        FACE_TOO_SMALL,
        FACE_TOO_LARGE,
        FACE_TOO_BLURRY,
        FACE_OUT_OF_RECT,
        FACE_EYE_OCCLUSIVE,
        FACE_MOUTH_OCCLUSIVE;

        private FaceQualityErrorType() {
        }
    }
}
