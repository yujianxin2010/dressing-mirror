package com.ylx.ui.view.panel;

import com.ylx.ui.common.DeviceInfo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ylx on 2017/1/29.
 */
public class LaunchPanel extends MyPanel {
    private ImageIcon launchImage = null;

    public LaunchPanel(ImageIcon launchImage) throws Exception {
        this.launchImage = launchImage;
        init();
    }

    private void init() throws Exception {
        if (launchImage == null){
            throw (new Exception("启动页面图片为空"));
        }
        JLabel launch = new JLabel();
        launch.setIcon(launchImage);
        launch.setBounds(0,0,launchImage.getIconWidth(),launchImage.getIconHeight());
        this.add(launch);
    }

}
