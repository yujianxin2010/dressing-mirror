package com.ylx.setup;

import com.sun.jmx.remote.internal.ArrayQueue;
import com.ylx.gestureanalyse.GestureManage;
import com.ylx.servicelogic.api.GestureController;
import com.ylx.ui.common.*;
import com.ylx.ui.controller.*;
import com.ylx.ui.view.frame.AppFrame;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

/**
 * Created by ylx on 2017/1/29.
 */
public class Setup {

    private boolean Man = true;

    private AppFrame appFrame = null;
    private LaunchPanelController launchPanel = null;
    private CameraPanelController cameraPanel = null;
    private ClothesPanelController clothesPanel = null;
    private ChoosePanelController choosePanel = null;
    private JLayeredPane layeredPane = null;
    private GestureManage manager = null;

    public void start(){
        appFrame = AppFrame.getAppFrameInstance(false);
        launchPanel = new LaunchPanelController(appFrame);
        launchPanel.showLaunch();
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0, DeviceInfo.SCREEN_WIDTH,DeviceInfo.SCREEN_HEIGHT);
        cameraPanel = new CameraPanelController(layeredPane);
        clothesPanel = new ClothesPanelController(layeredPane);
        choosePanel = new ChoosePanelController(layeredPane,clothesPanel,Man);
        manager = new GestureManage(choosePanel);
        manager.startCatchingGesture();
        cameraPanel.startCatchVideo();






        clothesPanel.showOutline(Man);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        appFrame.getContentPane().removeAll();
        appFrame.getContentPane().add(layeredPane);
        appFrame.validate();
        appFrame.repaint();


        addMainChosePanel();
        addRemainChose();
        manager.destroy();
        //appFrame.addKeyListener(new Test(choosePanel));

    }

    private void addMainChosePanel(){
        ImgPantograph imgPantograph = ImgPantograph.getImgPantograph(ImgPantograph.SmallImg);
        ArrayQueue<OptionBean> queue = new ArrayQueue<>(3);
        if (Man) {
            queue.add(new OptionBean(imgPantograph.scaleImageWithParams(ResourcesManage.getFileByFileNameWithType(Resource.image.Man_T_Shirt_Chose, ResourcesManage.IMAGE), (DeviceInfo.SCREEN_WIDTH / 3), (DeviceInfo.SCREEN_HEIGHT / 4), true), Resource.options.ShirtChosePanel));
            queue.add(new OptionBean(imgPantograph.scaleImageWithParams(ResourcesManage.getFileByFileNameWithType(Resource.image.Man_Suit_Chose, ResourcesManage.IMAGE), (DeviceInfo.SCREEN_WIDTH / 3), (DeviceInfo.SCREEN_HEIGHT / 4), true), Resource.options.SuitChosePanel));
            queue.add(new OptionBean(imgPantograph.scaleImageWithParams(ResourcesManage.getFileByFileNameWithType(Resource.image.Man_Pants_Chose, ResourcesManage.IMAGE), (DeviceInfo.SCREEN_WIDTH / 3), (DeviceInfo.SCREEN_HEIGHT / 4), true), Resource.options.PantsChosePanel));
        }else {
            queue.add(new OptionBean(imgPantograph.scaleImageWithParams(ResourcesManage.getFileByFileNameWithType(Resource.image.Woman_T_Shirt_Chose, ResourcesManage.IMAGE), (DeviceInfo.SCREEN_WIDTH / 3), (DeviceInfo.SCREEN_HEIGHT / 4), true), Resource.options.ShirtChosePanel));
            queue.add(new OptionBean(imgPantograph.scaleImageWithParams(ResourcesManage.getFileByFileNameWithType(Resource.image.Woman_Suit_Chose, ResourcesManage.IMAGE), (DeviceInfo.SCREEN_WIDTH / 3), (DeviceInfo.SCREEN_HEIGHT / 4), true), Resource.options.SuitChosePanel));
            queue.add(new OptionBean(imgPantograph.scaleImageWithParams(ResourcesManage.getFileByFileNameWithType(Resource.image.Woman_Pants_Chose, ResourcesManage.IMAGE), (DeviceInfo.SCREEN_WIDTH / 3), (DeviceInfo.SCREEN_HEIGHT / 4), true), Resource.options.PantsChosePanel));
        }
        choosePanel.addOptions(Resource.options.MainChosePanel,queue);
        choosePanel.choseOptions(Resource.options.MainChosePanel);
    }

    private void addRemainChose(){
        ImgPantograph imgPantograph = ImgPantograph.getImgPantograph(ImgPantograph.SmallImg);
        ClothLoader loader = ClothLoader.loader(null);
        if (loader != null && imgPantograph != null) {
            addChose(imgPantograph,loader,Resource.options.ShirtChosePanel);
            addChose(imgPantograph,loader,Resource.options.SuitChosePanel);
            addChose(imgPantograph,loader,Resource.options.PantsChosePanel);
        }
    }

    private void addChose(ImgPantograph imgPantograph,ClothLoader loader,String PanelName){
        File[] shirt = loader.getChoseImg(PanelName,Man);
        if (shirt != null) {
            ArrayQueue<OptionBean> queue = new ArrayQueue<>(shirt.length);
            for (File file : shirt){
                if (!file.isHidden())
                    queue.add(new OptionBean(imgPantograph.scaleImageWithParams(file, (DeviceInfo.SCREEN_WIDTH / 3), (DeviceInfo.SCREEN_HEIGHT / 4), true),file.getName()));
            }
            choosePanel.addOptions(PanelName,queue);
        }
    }

}

class Test implements KeyListener{
    GestureController controller;

    public Test(GestureController controller){
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_A:
                controller.previous();
                break;
            case KeyEvent.VK_D:
                controller.next();
                break;
            case KeyEvent.VK_S:
                controller.determine();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}