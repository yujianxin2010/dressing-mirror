package com.ylx.ui.common;

import org.jetbrains.annotations.Contract;

import java.io.File;

/**
 * Created by ylx on 2017/1/26.
 */
public class ResourcesManage {

    public static final String IMAGE = "img";

    @Contract("_, _ -> !null")
    public static File getFileByFileNameWithType(String FileName, String FileType){
        String url = "res/"+FileType+"/"+FileName;
        return new File(url);
    }

}
