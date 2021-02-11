package com.ylx.ui.controller;

import com.ylx.ui.common.DeviceInfo;
import com.ylx.ui.common.ImgPantograph;
import com.ylx.ui.common.Resource;
import com.ylx.ui.common.ResourcesManage;
import com.ylx.ui.view.panel.ClothesPanel;

import javax.swing.*;
import java.io.File;

/**
 * Created by ylx on 2017/2/17.
 */
public class ClothesPanelController {

    private ClothesPanel panel = null;
    private JLayeredPane parentPanel = null;
    private ImgPantograph imgPantograph = null;

    public ClothesPanelController(JLayeredPane layeredPane){
        imgPantograph = ImgPantograph.getImgPantograph(ImgPantograph.SmallImg);
        this.parentPanel = layeredPane;
        init();
    }

    private void init(){
        panel = new ClothesPanel(DeviceInfo.SCREEN_WIDTH,(DeviceInfo.SCREEN_HEIGHT*3/4));
        panel.setBounds(0,(DeviceInfo.SCREEN_HEIGHT/4), DeviceInfo.SCREEN_WIDTH,(DeviceInfo.SCREEN_HEIGHT*3/4));
        parentPanel.add(panel,JLayeredPane.PALETTE_LAYER);
    }

    public void showOutline(boolean Man){
        panel.showOutline(imgPantograph.scaleImageWithParams(ResourcesManage.getFileByFileNameWithType(Man ? Resource.image.Man_Outline : Resource.image.Woman_Outline,ResourcesManage.IMAGE),DeviceInfo.SCREEN_WIDTH,(DeviceInfo.SCREEN_HEIGHT*3/4),true));
    }

    public void changeClothes(File clothFile, String clothType){
        panel.changeClothes(imgPantograph.scaleImageWithParams(clothFile,DeviceInfo.SCREEN_WIDTH,(DeviceInfo.SCREEN_HEIGHT*3/4),true),clothType);
    }

}
