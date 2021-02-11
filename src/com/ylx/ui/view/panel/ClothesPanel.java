package com.ylx.ui.view.panel;

import com.ylx.ui.common.Resource;

import javax.swing.*;

/**
 * Created by ylx on 2017/2/17.
 */
public class ClothesPanel extends MyPanel {


    private JLabel outline = null, suit = null,shirt = null,pants = null;

    public ClothesPanel(int width,int height){
        init(width,height);
    }

    private void init(int width,int height){
        this.setOpaque(false);
        outline = new JLabel();
        outline.setOpaque(false);
        outline.setBounds(0,0,width,height);
        outline.setVisible(false);
        this.add(outline,DEFAULT_LAYER);
        shirt = new JLabel();
        shirt.setOpaque(false);
        shirt.setBounds(0,0,width,height);
        shirt.setVisible(false);
        this.add(shirt,DEFAULT_LAYER);
        suit = new JLabel();
        suit.setOpaque(false);
        suit.setBounds(0,0,width,height);
        suit.setVisible(false);
        this.add(suit,MODAL_LAYER);
        pants = new JLabel();
        pants.setOpaque(false);
        pants.setBounds(0,0,width,height);
        pants.setVisible(false);
        this.add(pants,MODAL_LAYER);
    }

    public void showOutline(Icon outlineImg){
        outline.setIcon(outlineImg);
        outline.setVisible(true);
        outline.repaint();
        this.repaint();
    }

    public void changeClothes(Icon cloth,String imgType){
        if (cloth == null) return;

        if (outline.isVisible()){
            outline.setVisible(false);
        }

        switch (imgType){
            case Resource.options.ShirtChosePanel:{
                if (!shirt.isVisible()){
                    shirt.setVisible(true);
                }
                shirt.setIcon(cloth);
                shirt.repaint();
            }

            case Resource.options.SuitChosePanel:{
                if (!suit.isVisible()){
                    suit.setVisible(true);
                }
                suit.setIcon(cloth);
                suit.repaint();
                break;
            }

            case Resource.options.PantsChosePanel:{
                if (!pants.isVisible()){
                    pants.setVisible(true);
                }
                pants.setIcon(cloth);
                pants.repaint();
                break;
            }

        }
        this.repaint();
    }



}
