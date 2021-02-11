package com.ylx.ui.controller;

import com.ylx.ui.common.DeviceInfo;
import com.ylx.ui.common.ImgPantograph;
import com.ylx.ui.common.Resource;
import com.ylx.ui.common.ResourcesManage;
import com.ylx.ui.view.frame.AppFrame;
import com.ylx.ui.view.panel.LaunchPanel;

/**
 * Created by ylx on 2017/1/29.
 */
public class LaunchPanelController {

    private LaunchPanel panel = null;
    private AppFrame appFrame = null;

    public LaunchPanelController(AppFrame frame){
        appFrame = frame;
        init();
    }

    private void init(){
        try {
            panel = new LaunchPanel(ImgPantograph.getImgPantograph(ImgPantograph.BigImg).scaleImageWithParams(ResourcesManage.getFileByFileNameWithType(Resource.image.Launch,ResourcesManage.IMAGE), DeviceInfo.SCREEN_WIDTH,DeviceInfo.SCREEN_HEIGHT,true));
            panel.setBounds(0,0,DeviceInfo.SCREEN_WIDTH,DeviceInfo.SCREEN_HEIGHT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void showLaunch(){
        appFrame.add(panel);
        appFrame.validate();
        appFrame.repaint();
    }

}
