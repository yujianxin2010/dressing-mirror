package com.ylx.ui.view.panel;

import com.ylx.ui.common.DeviceInfo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ylx on 2017/1/30.
 */
public class CameraPanel extends MyPanel {

    private JLabel camera = null;
    private ImageIcon video = null;

    public CameraPanel(){
        init();
    }

    private void init(){
        camera = new JLabel();
        camera.setBounds(0,0,DeviceInfo.SCREEN_WIDTH,DeviceInfo.SCREEN_HEIGHT);
        video = new ImageIcon();
        camera.setIcon(video);
        this.add(camera);
    }

    public void refreshImage(Image image){
        video.setImage(image);
        camera.repaint();
    }

}
