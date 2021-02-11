package com.ylx.gestureanalyse;

import com.leapmotion.leap.Controller;
import com.leapmotion.leap.Frame;
import com.leapmotion.leap.HandList;
import com.leapmotion.leap.Listener;

/**
 * 手势捕捉
 * Created by ylx on 16/11/11.
 */
public class GestureCatch extends Listener {
    private GestureDataPack gestureDataPack = null;
    private boolean isListen = false,twoHandFlag = false;

    public GestureCatch(GestureDataPack gestureDataPack){
        this.gestureDataPack = gestureDataPack;
    }

    @Override
    public void onInit(Controller controller) {
        //初始化
    }

    @Override
    public void onConnect(Controller controller) {
        //设备已经连接上
    }

    @Override
    public void onDisconnect(Controller controller) {
        //设备已经断开连接
    }

    @Override
    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        HandList hands = frame.hands();
        if (!hands.isEmpty()){
            isListen = true;
            int HandsCount = hands.count();
            if (HandsCount == 1){
                //单手手势
                gestureDataPack.OneHandGesture(hands.get(0).arm().center().getX());
            }else if (HandsCount == 2){
                //双手手势
                float XOne = hands.get(0).arm().center().getX();
                float XTwo = hands.get(1).arm().center().getX();
                gestureDataPack.TwoHandsGesture(XOne < XTwo ? XOne : XTwo,XOne > XTwo ? XOne : XTwo);
            }
        }else{
            if (isListen){
                gestureDataPack.setEND(true);
                isListen = false;
            }
        }
    }

}
