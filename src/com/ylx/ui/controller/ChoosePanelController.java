package com.ylx.ui.controller;

import com.sun.jmx.remote.internal.ArrayQueue;
import com.ylx.servicelogic.api.GestureController;
import com.ylx.ui.common.*;
import com.ylx.ui.view.panel.ChoosePanel;

import javax.swing.*;
import java.util.LinkedList;

/**
 * Created by ylx on 2017/2/22.
 */
public class ChoosePanelController implements GestureController{

    private ChoosePanel chosePanel = null;
    private JLayeredPane parentPanel = null;
    private String currentChoseTypeName = null;
    private ClothesPanelController clothesPanelController = null;
    private OptionPanelController mainOption = null;
    private ClothLoader loader = null;
    private boolean Man = true;

    private LinkedList<OptionPanelController> optionPanelControllerList = null;


    public ChoosePanelController(JLayeredPane parentPanel,ClothesPanelController clothesPanelController,boolean Man){
        this.loader = ClothLoader.loader(null);
        this.parentPanel = parentPanel;
        this.clothesPanelController = clothesPanelController;
        this.Man = Man;
        init();
    }

    public void addOptions(String optionSetName, ArrayQueue<OptionBean> options){
        int account = options.size();
        OptionPanelController controller = new OptionPanelController(optionSetName,account);
        for (int i = 0;i < account;i++){
            OptionBean bean = options.get(i);
            controller.insertOption(i,bean.getImg(),bean.getImgFileName());
        }
        if (mainOption == null && optionSetName.equals(Resource.options.MainChosePanel)) mainOption = controller;
        optionPanelControllerList.add(controller);
    }

    public void choseOptions(String optionsName){
        for (OptionPanelController options :
                optionPanelControllerList) {
            if (options.getControllerName().equals(optionsName)){
                currentChoseTypeName = optionsName;
                chosePanel.changeOptions(options.getOptionPanels());
                break;
            }
        }
    }



    private void init(){
        optionPanelControllerList = new LinkedList<>();
        chosePanel = new ChoosePanel((DeviceInfo.SCREEN_WIDTH/3),(DeviceInfo.SCREEN_HEIGHT/4));
        chosePanel.setBounds(0,0,DeviceInfo.SCREEN_WIDTH,(DeviceInfo.SCREEN_HEIGHT/4));
        parentPanel.add(chosePanel,JLayeredPane.PALETTE_LAYER);
    }

    public void selectOptions(){
        for (OptionPanelController options :
                optionPanelControllerList) {
            if (options.getControllerName().equals(currentChoseTypeName)){
                System.out.println("type name : "+currentChoseTypeName);
                System.out.println("chosen name : "+chosePanel.chosenName());
                switch (currentChoseTypeName){
                    case Resource.options.MainChosePanel:
                        //主选项组
                        choseOptions(chosePanel.chosenName());
                        clothesPanelController.changeClothes(loader.getImgFileByFileName(chosePanel.chosenName(),currentChoseTypeName,Man),currentChoseTypeName);
                        break;
                    default:
                        clothesPanelController.changeClothes(loader.getImgFileByFileName(chosePanel.chosenName(),currentChoseTypeName,Man),currentChoseTypeName);
                        currentChoseTypeName = mainOption.getControllerName();
                        chosePanel.changeOptions(mainOption.getOptionPanels());
                        break;
                }
                break;
            }
        }
    }

    @Override
    public void next() {
        System.out.println("next");
        chosePanel.moveOption(ChoosePanel.NEXT_OPTION);
    }

    @Override
    public void previous() {
        System.out.println("previous");
        chosePanel.moveOption(ChoosePanel.PREVIOUS_OPTION);
    }

    @Override
    public void determine() {
        System.out.println("determine");
        selectOptions();
    }

    @Override
    public void enlarge(int Magnification) {

    }

    @Override
    public void narrow(int Magnification) {

    }
}
