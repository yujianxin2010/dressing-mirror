package com.ylx.ui.common;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;

/**
 * Created by ylx on 2017/3/29.
 */
public class ClothLoader extends Thread{

    private final static String Shirt = "Shirt/",Pants = "Pants/",Suit = "Suit/",Man = "Man/",Woman = "Woman/",ChoseImg = "ChoseImg",ShowImg = "ShowImg";

    private String root = null;
    private File[] M_shirt = null,W_shirt = null,M_pants = null,W_pants = null,M_suit = null,W_suit = null,
            MC_shirt = null,WC_shirt = null,MC_pants = null,WC_pants = null,MC_suit = null,WC_suit = null;
    private volatile boolean LoadFinish = false;
    private HashMap<String,Integer> name_id = null;

    private static ClothLoader loader = null;

    @Nullable
    private File[] getFiles(String path,boolean makeIndex){
        File dir = new File(path);
        File[] img = null;
        if (dir.exists() && dir.isDirectory()){
            img = dir.listFiles();
        }
        if (img == null || img.length == 0){
            return null;
        }
        if (makeIndex) {
            for (int i = 0; i < img.length; i++) {
                if (!img[i].isHidden())
                    name_id.put(img[i].getName(), i);
            }
        }
        return img;
    }

    private ClothLoader(String path){
        this.root = path;
        name_id = new HashMap<>();
        start();
    }

    /**
     * 加载衣服图片资源
     * @param path 资源文件夹根目录,以'/'结尾
     * @return 对象
     */
    public static ClothLoader loader(String path){
        return path == null ? loader : (loader = new ClothLoader(path));
    }

    private void loadFile(){
        if (root != null){
            M_shirt = getFiles(root+Shirt+Man+ShowImg,true);
            MC_shirt = getFiles(root+Shirt+Man+ChoseImg,false);
            W_shirt = getFiles(root+Shirt+Woman+ShowImg,true);
            WC_shirt = getFiles(root+Shirt+Woman+ChoseImg,false);
            M_pants = getFiles(root+Pants+Man+ShowImg,true);
            MC_pants = getFiles(root+Pants+Man+ChoseImg,false);
            W_pants = getFiles(root+Pants+Woman+ShowImg,true);
            WC_pants = getFiles(root+Pants+Woman+ChoseImg,false);
            M_suit = getFiles(root+Suit+Man+ShowImg,true);
            MC_suit = getFiles(root+Suit+Man+ChoseImg,false);
            W_suit = getFiles(root+Suit+Woman+ShowImg,true);
            WC_suit = getFiles(root+Suit+Woman+ChoseImg,false);
        }
    }

    public File[] getChoseImg(String type, boolean Man){
        if (LoadFinish) {
            switch (type) {
                case Resource.options.SuitChosePanel:
                    return Man ? MC_suit : WC_suit;
                case Resource.options.PantsChosePanel:
                    return Man ? MC_pants : WC_pants;
                case Resource.options.ShirtChosePanel:
                    return Man ? MC_shirt : WC_shirt;
            }
        }
        return null;
    }

    public File getImgFileByFileName(String fileName,String type,boolean Man){
        int index = name_id.get(fileName);
        switch (type){
            case Resource.options.SuitChosePanel:
                return Man ? M_suit[index] : W_suit[index];
            case Resource.options.PantsChosePanel:
                return Man ? M_pants[index] : W_pants[index];
            case Resource.options.ShirtChosePanel:
                return Man ? M_shirt[index] : W_shirt[index];
        }
        return null;
    }

    @Override
    public void run() {
        loadFile();
        LoadFinish = true;
    }
}
