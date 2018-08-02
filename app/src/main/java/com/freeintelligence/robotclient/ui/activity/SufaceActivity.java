package com.freeintelligence.robotclient.ui.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import com.freeintelligence.robotclient.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SufaceActivity extends Activity {
    private SurfaceHolder myHolder;
    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suface);
        // 无title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final SurfaceView mySurfaceView = findViewById(R.id.camera_surfaceview);
        mCamera = getFacingFrontCamera();

        new Thread(){
           @Override
           public void run() {
               try {
                   sleep(2000);
                   myHolder = mySurfaceView.getHolder();
                   myHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
                   mCamera.setPreviewDisplay(myHolder);
                   mCamera.startPreview();
                    //自动对焦
                   mCamera.autoFocus(myAutoFocus);
                    //对焦后拍照
                   mCamera.takePicture(null, null, myPicCallback);
               } catch (Exception e) {
                   e.printStackTrace();
               }

           }
       }.start();
    }
    //自动对焦回调函数(空实现)
    private Camera.AutoFocusCallback myAutoFocus = new Camera.AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {

        }
    };
    //拍照成功回调函数
    private Camera.PictureCallback myPicCallback = new Camera.PictureCallback() {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File tempFile = new File("/sdcard/" + System.currentTimeMillis() + ".png");
            try {
                FileOutputStream fos = new FileOutputStream(tempFile);
                fos.write(data);
                fos.close();

                Bitmap bm = BitmapFactory.decodeByteArray(data, 0, data.length);

               Matrix matrix = new Matrix();
               /*  if (cameraPosition == 1) {
                    matrix.postRotate(90);
                } else {
                    matrix.postRotate(270);
                }*/
                Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
                String picUrl = saveImage(bitmap).getAbsolutePath();
                //saveImage(bitmap);
                stopActivity();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    };
    private Camera getFacingFrontCamera() {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                try {
                    return Camera.open(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    /**
     * 保存本地照片
     *
     * @param bmp
     * @return
     */
    public static File saveImage(Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "images");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".png";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private  void stopActivity() {
        SufaceActivity.this.finish();
        mCamera.stopPreview();
        mCamera.release();
        mCamera = null;
    }
}
