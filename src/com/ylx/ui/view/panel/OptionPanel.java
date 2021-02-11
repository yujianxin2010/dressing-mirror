package com.ylx.ui.view.panel;

import com.ylx.ui.common.DeviceInfo;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ylx on 2017/2/22.
 */
public class OptionPanel extends MyPanel {

    private ImageIcon displayImg = null;
    private String optionImgFileName = null;
    private JLabel imgLabel = null;

    public OptionPanel(ImageIcon displayImg,String optionImgFileName){
        this.displayImg = displayImg;
        this.optionImgFileName = optionImgFileName;
        init();
    }

    private void init(){
        this.setBackground(new Color(DeviceInfo.CHOSE_PANEL_COLOR_R, DeviceInfo.CHOSE_PANEL_COLOR_G, DeviceInfo.CHOSE_PANEL_COLOR_B));
        this.setOpaque(false);
        imgLabel = new JLabel(displayImg);
        imgLabel.setBounds(0,0,displayImg.getIconWidth(),displayImg.getIconHeight());
        imgLabel.setVisible(true);
        this.add(imgLabel);
    }

    void isBeenChoose(boolean choose){
        this.setOpaque(choose);
        this.repaint();
    }

    public String getOptionImgFileName() {
        return optionImgFileName;
    }
}
