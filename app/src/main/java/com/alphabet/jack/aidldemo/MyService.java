package com.alphabet.jack.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Jack on 2016/4/22.
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    MyBinder myBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        myBinder = new MyBinder();

    }
    class MyBinder extends IMyAidlInterface.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String getName() throws RemoteException {

            return "my name is jack";

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(MainActivity.TAG,"onDestroy()===");
    }
}
