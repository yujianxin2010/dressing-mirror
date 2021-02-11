package com.ylx.ui.controller;


import com.ylx.thread.CameraCatchThread;
import com.ylx.ui.common.DeviceInfo;
import com.ylx.ui.view.frame.AppFrame;
import com.ylx.ui.view.panel.CameraPanel;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ylx on 2017/1/30.
 */
public class CameraPanelController {

    private CameraPanel cameraPanel = null;
    private OpenCVFrameConverter.ToIplImage toIplImage = null;
    private opencv_core.Mat finalMat = null,tempMat = null;
    private Java2DFrameConverter converter = null;
    private CameraCatchThread thread = null;
    private JLayeredPane layeredPane = null;

    public CameraPanelController(JLayeredPane layeredPane){
        this.layeredPane = layeredPane;
        cameraPanel = new CameraPanel();
        converter = new Java2DFrameConverter();
        tempMat = new opencv_core.Mat();
        finalMat = new opencv_core.Mat();
        toIplImage = new OpenCVFrameConverter.ToIplImage();
    }

    public void startCatchVideo(){
        cameraPanel.setBounds(0,0, DeviceInfo.SCREEN_WIDTH,DeviceInfo.SCREEN_HEIGHT);
        thread = new CameraCatchThread(this);
        layeredPane.add(cameraPanel,JLayeredPane.DEFAULT_LAYER);
        thread.start();
    }

    public void stopCatchCamera(){
        thread.closeThread();
    }

    public void refresh(Frame cameraFrame){
        cameraPanel.refreshImage(handleImage(cameraFrame));
    }

    private Image handleImage(Frame cameraFrame){
        opencv_core.flip(toIplImage.convertToMat(cameraFrame),tempMat,1);
        opencv_core.transpose(tempMat,finalMat);
        opencv_core.flip(finalMat,tempMat,1);
        return converter.getBufferedImage(toIplImage.convert(tempMat));
    }


}
