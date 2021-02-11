package com.ylx.gestureanalyse;

/**
 * 手势数据包
 * Created by ylx on 16/11/11.
 */
public class GesturePackage {
    static byte NO_HAND = 0,ONE_HAND = 1,TWO_HAND = 2;

    private float Begin,End;
    private byte HandsNumber = 0;

    void setBegin(float begin) {
        Begin = begin;
    }

    void setEnd(float end) {
        End = end;
    }

    void setHandsNumber(byte handsNumber) {
        HandsNumber = handsNumber;
    }

    byte getHandsNumber() {
        return HandsNumber;
    }

    float getBegin() {
        return Begin;
    }

    float getEnd() {
        return End;
    }
}
