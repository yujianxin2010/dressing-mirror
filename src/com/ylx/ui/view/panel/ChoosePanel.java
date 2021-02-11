package com.ylx.ui.view.panel;


import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

/**
 * Created by ylx on 2017/2/22.
 */
public class ChoosePanel extends MyPanel{
    private OptionPanel[] optionPanels = null;
    private int optionWidth,optionHeight,optionCount,currentBeenChoseNumber = 0;

    public static final byte NEXT_OPTION = 0,PREVIOUS_OPTION = 1;

    public ChoosePanel(int optionWidth, int optionHeight){
        this.optionWidth = optionWidth;
        this.optionHeight = optionHeight;
        init();
    }

    private void init(){
        this.setOpaque(false);
    }

    private void updateOptions(){
        this.removeAll();
        if (optionPanels != null){
            for (int i = 0; i < optionCount; i++) {
                if (i > 2){
                    break;
                }
                displayOption(i+1,i);
            }
            choseOption(0);
        }
        this.validate();
        this.repaint();
    }

    private void displayOption(int optionLocationIndex,int optionIndex){
        OptionPanel optionPanel = optionPanels[optionIndex];
        if (optionLocationIndex <= 3){
            optionPanel.setBounds((optionLocationIndex-1)*optionWidth,0,optionWidth,optionHeight);

            System.out.println(optionLocationIndex+"'s bounds = "+optionPanel.getBounds().toString());
            this.add(optionPanel,MODAL_LAYER);
        }
    }

    public void changeOptions(OptionPanel[] optionPanels){
        this.optionPanels = optionPanels;
        this.optionCount = optionPanels.length;
        updateOptions();
    }

    void choseOption(int optionNumber){
        if (optionNumber < optionCount && optionNumber >= 0) {
            currentBeenChoseNumber = optionNumber;
            for (int i = 0; i < optionCount; i++) {
                optionPanels[i].isBeenChoose(i == optionNumber);
            }
        }
    }

    public String chosenName(){
        return optionPanels[currentBeenChoseNumber].getOptionImgFileName();
    }

    public void moveOption(byte moveType){
        switch (moveType){
            case NEXT_OPTION:{
                if (currentBeenChoseNumber != optionCount - 1){
                    choseOption(currentBeenChoseNumber+1);
                }
                break;
            }
            case PREVIOUS_OPTION:{
                if (currentBeenChoseNumber != 0){
                    choseOption(currentBeenChoseNumber-1);
                }
                break;
            }
        }
    }
}
