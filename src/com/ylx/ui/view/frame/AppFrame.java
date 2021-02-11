package com.ylx.ui.view.frame;

import com.ylx.ui.common.DeviceInfo;

import javax.swing.*;

/**
 * Created by ylx on 16/11/26.
 */
public class AppFrame extends JFrame{

//
//    public AppFrame() throws InterruptedException {
//        //this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
//        this.setSize(DeviceInfo.getScreenWidth(),DeviceInfo.getScreenHeight());
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.setLayout(null);
//        this.setUndecorated(true);
//        Test();
//    }
//
//    public void Test(){
//        JLabel jLabel = new JLabel();
//        ImgPantograph imgPantograph = ImgPantograph.getImgPantograph(ImgPantograph.BigImg);
//        jLabel.setIcon(imgPantograph.scaleImageWithParams(ResourcesManage.getFileByFileNameWithType(Resource.image.Launch,ResourcesManage.IMAGE),DeviceInfo.getScreenWidth(),DeviceInfo.getScreenHeight(),false));
//        jLabel.setPreferredSize(new Dimension(DeviceInfo.getScreenWidth(),DeviceInfo.getScreenHeight()));
//        jLabel.setBounds(0,0,DeviceInfo.getScreenWidth(),DeviceInfo.getScreenHeight());
//
//        this.add(jLabel);
//        this.setVisible(true);
//    }


    private static AppFrame appFrameObject = null;
    private boolean isFullScreen = false;


    public static AppFrame getAppFrameInstance(boolean isFullScreen){
        return appFrameObject == null ? (appFrameObject = new AppFrame(isFullScreen)) : appFrameObject ;
    }

    private AppFrame(boolean isFullScreen){
        if (isFullScreen){
            //全屏显示
            this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        }else{
            this.setSize(DeviceInfo.SCREEN_WIDTH,DeviceInfo.SCREEN_HEIGHT);
            this.setUndecorated(true);
        }
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
        this.isFullScreen = isFullScreen;
    }

    public boolean isFullScreen() {
        return isFullScreen;
    }
}
