package com.ylx.gestureanalyse;

import com.leapmotion.leap.Controller;
import com.ylx.servicelogic.api.GestureController;

import java.math.BigDecimal;

/**
 * 手势数据打包
 * Created by ylx on 16/11/11.
 */
class GestureDataPack implements Runnable {

    private final int TIMES = 10;
    private volatile boolean END = false,BEGIN = true,DETERMINE = false;
    private volatile float BeginX = 0f, EndX = 0f;
    private volatile byte HandNumber = GesturePackage.NO_HAND;
    private volatile int NoHandTimes = 0;
    private int CatchingTimes = 0,BeginDelta = 0;
    private GestureDataAnalyse gestureDataAnalyse = null;

    GestureDataPack(GestureController gestureController){
        gestureDataAnalyse = new GestureDataAnalyse(gestureController);

    }

    void setEND(boolean END) {
        this.END = END;
    }

    void setNoHand(){ NoHandTimes++; }

    /**
     * 单手势坐标提交
     * @param XPoint 手臂X坐标
     */
    void OneHandGesture(float XPoint){
        if (HandNumber == GesturePackage.TWO_HAND){

        }
        if (BEGIN && !END){
            BeginX = XPoint;
            BEGIN = false;
            HandNumber = GesturePackage.ONE_HAND;
        }else if (!END){
            EndX = XPoint;
        }
    }

    /**
     * 双手手势识别
     * @param leftX 左手X坐标
     * @param rightX 右手X坐标
     */
    void TwoHandsGesture(float leftX, float rightX){
        int left = (int)leftX,right = (int)rightX;
        if (BEGIN && !END){
            BeginDelta = right - left;
            BEGIN = false;
            HandNumber = GesturePackage.TWO_HAND;
        }else if (!BEGIN && !END){
            int Delta = right - left;
            //float times = new BigDecimal((Delta/BeginDelta)).setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
        }
    }

    @Override
    public void run() {
        while (true){
            if (END || (END && CatchingTimes > TIMES) ){
                if (CatchingTimes > TIMES){
                    DETERMINE = true;
                    gestureDataAnalyse.determine();
                }else if (!DETERMINE){
                    GesturePackage gesturePackage = new GesturePackage();
                    gesturePackage.setBegin(BeginX);
                    gesturePackage.setEnd(EndX);
                    gesturePackage.setHandsNumber(HandNumber);
                    gestureDataAnalyse.gestureDataSubmit(gesturePackage);
                }else{
                    DETERMINE = false;
                }
                BeginX = 0f;
                EndX = 0f;
                CatchingTimes = 0;
                BEGIN = true;
                END = false;
            }else if (!BEGIN && !END){
                CatchingTimes += 1;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
