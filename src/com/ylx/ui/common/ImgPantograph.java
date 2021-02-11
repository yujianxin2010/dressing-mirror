package com.ylx.ui.common;

import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

/**
 * Created by ylx on 16/12/3.
 */
public class ImgPantograph {
    public static final String BigImg = "bigimg",SmallImg = "smallimg";

    private static ImgPantograph imgPantograph1,imgPantograph2;

    private ImgPantograph(){}


    @Nullable
    public static ImgPantograph getImgPantograph(String ObjectType){
        switch (ObjectType){
            case BigImg:
                return imgPantograph1 == null ? (imgPantograph1 = new ImgPantograph()) : imgPantograph1;
            case SmallImg:
                return imgPantograph2 == null ? (imgPantograph2 = new ImgPantograph()) : imgPantograph2;
        }
        return null;
    }

    private double getDot2Decimal(int a,int b){
        BigDecimal bigDecimal_1 = new BigDecimal(a);
        BigDecimal bigDecimal_2 = new BigDecimal(b);
        BigDecimal bigDecimal_result = bigDecimal_1.divide(bigDecimal_2,new MathContext(4));
        Double double1 = new Double(bigDecimal_result.toString());
        //System.out.println("相除后的double为："+double1);
        return double1;
    }

    private int parseDoubleToInt(double sourceDouble) {
        int result;
        result = (int) sourceDouble;
        return result;
    }

    private ArrayList<Integer> getAutoWidthAndHeight(BufferedImage bufferedImage,int width_scale,int height_scale){
        ArrayList<Integer> arrayList = new ArrayList<>();
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        double scale_w =getDot2Decimal( width_scale,width);

        //System.out.println("getAutoWidthAndHeight width="+width + "scale_w="+scale_w);
        double scale_h = getDot2Decimal(height_scale,height);
        if (scale_w<scale_h) {
            arrayList.add(parseDoubleToInt(scale_w*width));
            arrayList.add(parseDoubleToInt(scale_w*height));
        }
        else {
            arrayList.add(parseDoubleToInt(scale_h*width));
            arrayList.add(parseDoubleToInt(scale_h*height));
        }
        return arrayList;

    }

    /**
     * 获得缩放后的图片
     * @param file 图片文件对象
     * @param width 需要缩放到的宽度
     * @param height 需要缩放到的高度
     * @param auto 是否保持长宽比
     * @return 得到的对象
     */
    public ImageIcon scaleImageWithParams(File file,int width, int height, boolean auto) {
        if (file == null) return null;
        try {
            BufferedImage bufferedImage;
            bufferedImage = ImageIO.read(file);
            if (auto) {
                ArrayList<Integer> paramsArrayList = getAutoWidthAndHeight(bufferedImage,width,height);
                width = paramsArrayList.get(0);
                height = paramsArrayList.get(1);
                //System.out.println("自动调整比例，width="+width+" height="+height);
            }
            Image image = bufferedImage.getScaledInstance(width, height,
                    Image.SCALE_DEFAULT);
            return new ImageIcon(image);
        } catch (Exception e) {
            //System.out.println("scaleImageWithParams方法压缩图片时出错了");
            e.printStackTrace();
        }
        return null;
    }

}
