package com.suyan.cloud.utils;

import android.graphics.Bitmap;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class ImageConvertUtils {

    public static String Img2Base64(byte[] imgData) {
        String result = null;
        result = Base64.encodeToString(imgData, Base64.DEFAULT);
        return result;
    }
    public static byte[] Base64ToImg(String base64) {
        return Base64.decode(base64.getBytes(), Base64.DEFAULT);
    }


    public static String Img2Base64WithHead(byte[] imgData) {
        String result = null;
        result = Base64.encodeToString(imgData, Base64.DEFAULT);
        return "data:image/jpeg;base64," + result;
    }

    public static byte[] Base64ToImgWithHead(String base64) {
        return Base64.decode(base64.split(",")[1].getBytes(), Base64.DEFAULT);
    }

    //将nv21格式的图片转换成jpg
    public static byte[] Nv21toJpg(byte[] imgData, int width, int height) {
        YuvImage img = new YuvImage(imgData, ImageFormat.NV21, width, height, null);
        Rect rect = new Rect(0, 0, width, height);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        img.compressToJpeg(rect, 95, os);
        return  os.toByteArray();
    }

    //将nv21格式的图片转换成base64,这个方法会先把格式转换为jpg,然后转为base64
    public static String Nv21toBase64(byte[] imgData, int width, int height) {
        YuvImage img = new YuvImage(imgData, ImageFormat.NV21, width, height, null);
        Rect rect = new Rect(0, 0, width, height);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        img.compressToJpeg(rect, 95, os);
        byte[] tmp = os.toByteArray();
        String result = Img2Base64WithHead(tmp);
        Log.i("Nv21toBase64", "result length = " + result.length() + " result = " + result);
        return result;
    }


    //bitmap 转nv21
    public static byte[] getNV21(int inputWidth, int inputHeight, Bitmap srcBitmap) {
        int[] argb = new int[inputWidth * inputHeight];
        if (null != srcBitmap) {
            try {
                srcBitmap.getPixels(argb, 0, inputWidth, 0, 0, inputWidth, inputHeight);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            // byte[] yuv = new byte[inputWidth * inputHeight * 3 / 2];
            // encodeYUV420SP(yuv, argb, inputWidth, inputHeight);
            if (null != srcBitmap && !srcBitmap.isRecycled()) {
               // srcBitmap.recycle();
                //srcBitmap = null;
            }
            return colorconvertRGB_IYUV_I420(argb, inputWidth, inputHeight);
        } else return null;
    }

    private static void encodeYUV420SP(byte[] yuv420sp, int[] argb, int width, int height) {
        final int frameSize = width * height;
        int yIndex = 0;
        int uvIndex = frameSize;

        int a, R, G, B, Y, U, V;
        int index = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {

                a = (argb[index] & 0xff000000) >> 24; // a is not used obviously
                R = (argb[index] & 0xff0000) >> 16;
                G = (argb[index] & 0xff00) >> 8;
                B = (argb[index] & 0xff) >> 0;

                // well known RGB to YUV algorithm
                Y = ((66 * R + 129 * G + 25 * B + 128) >> 8) + 16;
                U = ((-38 * R - 74 * G + 112 * B + 128) >> 8) + 128;
                V = ((112 * R - 94 * G - 18 * B + 128) >> 8) + 128;

                /* NV21 has a plane of Y and interleaved planes of VU each sampled by a factor of 2 				meaning for every 4 Y pixels there are 1 V and 1 U.  Note the sampling is 					every otherpixel AND every other scanline.*/
                yuv420sp[yIndex++] = (byte) ((Y < 0) ? 0 : ((Y > 255) ? 255 : Y));
                if (j % 2 == 0 && index % 2 == 0) {
                    yuv420sp[uvIndex++] = (byte) ((V < 0) ? 0 : ((V > 255) ? 255 : V));
                    yuv420sp[uvIndex++] = (byte) ((U < 0) ? 0 : ((U > 255) ? 255 : U));
                }
                index++;
            }
        }
    }

    public static byte[] colorconvertRGB_IYUV_I420(int[] aRGB, int width, int height) {
        final int frameSize = width * height;
        final int chromasize = frameSize / 4;

        int yIndex = 0;
        int uIndex = frameSize;
        int vIndex = frameSize + chromasize;
        byte[] yuv = new byte[width * height * 3 / 2];

        int a, R, G, B, Y, U, V;
        int index = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                //a = (aRGB[index] & 0xff000000) >> 24; //not using it right now
                R = (aRGB[index] & 0xff0000) >> 16;
                G = (aRGB[index] & 0xff00) >> 8;
                B = (aRGB[index] & 0xff) >> 0;

                Y = ((66 * R + 129 * G + 25 * B + 128) >> 8) + 16;
                U = ((-38 * R - 74 * G + 112 * B + 128) >> 8) + 128;
                V = ((112 * R - 94 * G - 18 * B + 128) >> 8) + 128;

                yuv[yIndex++] = (byte) ((Y < 0) ? 0 : ((Y > 255) ? 255 : Y));

                if (j % 2 == 0 && index % 2 == 0) {
                    yuv[vIndex++] = (byte) ((U < 0) ? 0 : ((U > 255) ? 255 : U));
                    yuv[uIndex++] = (byte) ((V < 0) ? 0 : ((V > 255) ? 255 : V));
                }
                index++;
            }
        }
        return yuv;
    }
}
