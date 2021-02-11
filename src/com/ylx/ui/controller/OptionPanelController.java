package com.ylx.ui.controller;

import com.ylx.ui.view.panel.OptionPanel;

import javax.swing.*;

/**
 * Created by ylx on 2017/2/22.
 */
public class OptionPanelController {

    private OptionPanel[] optionPanels = null;
    private int optionAccount = 0;
    private String controllerName = null;

    public OptionPanelController(String controllerName,int optionAccount){
        this.controllerName = controllerName;
        this.optionAccount = optionAccount;
        optionPanels = new OptionPanel[optionAccount];
    }

    public void insertOption(int index,ImageIcon img,String optionName){
        if (index < optionAccount){
            optionPanels[index] = new OptionPanel(img,optionName);
        }
    }

    public String getControllerName() {
        return controllerName;
    }

    public OptionPanel[] getOptionPanels() {
        return optionPanels;
    }
}
