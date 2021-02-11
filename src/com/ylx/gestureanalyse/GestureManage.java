package com.ylx.gestureanalyse;

import com.leapmotion.leap.Controller;
import com.ylx.servicelogic.api.GestureController;

import java.io.IOException;

/**
 * Created by ylx on 16/11/27.
 */
public class GestureManage {

    private GestureController gestureController = null;
    private GestureCatch gestureCatch = null;
    private Thread gestureDataPackThread = null;
    private Controller controller = null;
    private GestureCatch listener;

    public GestureManage(GestureController gestureController){
        this.gestureController = gestureController;
    }

    public void startCatchingGesture(){
        GestureDataPack pack = new GestureDataPack(gestureController);
        gestureDataPackThread = new Thread(pack);
        gestureDataPackThread.start();
        controller = new Controller();
        listener=new GestureCatch(pack);
        controller.addListener(listener);
    }

    public void destroy(){
        // Keep this process running until Enter is pressed
        System.out.println("Press Enter to quit...");

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Remove the sample listener when done
        controller.removeListener(listener);

    }

}
