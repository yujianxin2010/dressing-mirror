package com.ylx.setup;


import com.leapmotion.leap.*;
import com.ylx.servicelogic.api.GestureController;
import com.ylx.ui.common.ClothLoader;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.IOException;
import java.util.Scanner;


/**
 * 程序启动主类
 * Created by ylx on 16/11/11.
 */
public class Main{
    public static String root = System.getProperty("user.dir") + "/image/";

    public static void main(String[] args) throws InterruptedException {
        System.out.println(root);
        ClothLoader.loader(root);
        new Setup().start();
//        while (true){
//            Thread.sleep(1000);
//        }

    }

}