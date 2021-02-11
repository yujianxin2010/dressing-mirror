package com.ylx.servicelogic.api;

/**
 * 手势控制器接口
 * Created by ylx on 16/11/11.
 */
public interface GestureController {
    /**
     * 选择下一个
     */
    void next();

    /**
     * 选择上一个
     */
    void previous();

    /**
     * 确定
     */
    void determine();

    /**
     * 放大
     *
     * @param Magnification 放大倍率(1-2,步长0.1)
     */
    void enlarge(int Magnification);

    /**
     * 缩小
     *
     * @param Magnification 缩小比例(1-0.5,步长0.1)
     */
    void narrow(int Magnification);
}
