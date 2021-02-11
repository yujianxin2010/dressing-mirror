package com.ylx.thread;

import com.sun.tools.corba.se.idl.constExpr.Times;
import com.ylx.ui.common.DeviceInfo;
import com.ylx.ui.controller.CameraPanelController;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

import java.util.Date;

/**
 * Created by ylx on 2017/1/30.
 */
public class CameraCatchThread extends Thread {

    private OpenCVFrameGrabber CameraGrabber = null;
    private volatile boolean close = false;
    private CameraPanelController controller = null;

    public CameraCatchThread(CameraPanelController controller){
        try {
            this.controller = controller;
            CameraGrabber = OpenCVFrameGrabber.createDefault(DeviceInfo.CAMERA_DEVICE_NUMBER);
        } catch (FrameGrabber.Exception e) {
            System.out.println("摄像头设备错误");
            e.printStackTrace();
        }
    }

    public void closeThread(){ close = true; }

    @Override
    public void run() {
        if (CameraGrabber != null && controller != null){
            try {
                CameraGrabber.start();
            } catch (FrameGrabber.Exception e) {
                System.out.println("摄像头开启错误");
                e.printStackTrace();
            }
            try {
                while (!close) {
                    controller.refresh(CameraGrabber.grab());
                    sleep((1000/DeviceInfo.FPS));
                }
            } catch (FrameGrabber.Exception | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
