package com.madebyasshad.runtime_permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private int storagepermissioncode = 1;

    TelephonyManager telephonyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btnn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //use checkSelfPermission()
                    requeststoragepermisssion();
                    permissingranted();


                } else {
                    //simply use the required feature
                    //as the user has already granted permission to them during installation
                }
            }
        });
    }

    public void permissingranted() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            Log.i("permisson","is granted");




            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        }
        else
        {
            requeststoragepermisssion();
            Log.i("permisson","is  not granted");
        }

    }


    public void requeststoragepermisssion()
    {
        //if
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_PHONE_STATE},storagepermissioncode);


    }
}
