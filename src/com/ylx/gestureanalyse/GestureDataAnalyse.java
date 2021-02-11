package com.ylx.gestureanalyse;

import com.ylx.servicelogic.api.GestureController;

/**
 * 手势数据分析
 * Created by ylx on 16/11/11.
 */
class GestureDataAnalyse {

    private GestureController gestureController = null;

    void gestureDataSubmit(GesturePackage gesturePackage){
        if (gesturePackage.getHandsNumber() == GesturePackage.ONE_HAND){
            oneHand(gesturePackage.getBegin() - gesturePackage.getEnd());
        }else if (gesturePackage.getHandsNumber() == GesturePackage.TWO_HAND){
            twoHand();
        }
    }

    GestureDataAnalyse(GestureController gestureController){
        if (gestureController != null){
            this.gestureController = gestureController;
        }
    }

    void determine(){
        gestureController.determine();
    }

    private void oneHand(float DeltaX){
        if (DeltaX > 0){
            gestureController.next();
        }else{
            gestureController.previous();
        }
    }

    private void twoHand(){}

}
