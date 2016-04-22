package com.alphabet.jack.childapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alphabet.jack.aidldemo.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    IMyAidlInterface myAidlInterface;
    private final String ACTION = "com.alphabet.jack.aidldemo.MyService";
    private final String PACKAGE = "com.alphabet.jack.aidldemo";
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text =  (TextView)findViewById(R.id.text);
        Intent intent = new Intent(ACTION);//服务端Service注册的Action
        intent.setPackage(PACKAGE);//服务端包名
        bindService(intent,conn,BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                text.setText(myAidlInterface.getName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myAidlInterface = null;
        }
    };
}
