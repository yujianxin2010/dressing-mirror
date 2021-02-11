package com.ylx.ui.common;

import javax.swing.*;

/**
 * Created by ylx on 2017/2/22.
 */
public class OptionBean {

    private ImageIcon img = null;
    private String ImgFileName = null;

    public OptionBean(ImageIcon img,String ImgFileName){
        this.img = img;
        this.ImgFileName = ImgFileName;
    }

    public ImageIcon getImg() {
        return img;
    }

    public String getImgFileName() {
        return ImgFileName;
    }
}
