package com.madebyasshad.runtime_permission;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int storagepermissioncode = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.btnn);


        final String androidID = android.provider.Settings.System.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);        button.setOnClickListener(new View.OnClickListener() {


            //TODO get android idfrom here
            @Override
            public void onClick(View v) {
                //if

                Log.i("android  id","is "+androidID);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //use checkSelfPermission()
                    if (ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "You have already granted this permission!",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        requeststoragepermisssion();
                    }


                    TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                    try
                    {
                        String str=telephonyManager.getDeviceId();
                        String phoneno=telephonyManager.getLine1Number();
                        String operatorName=telephonyManager.getNetworkOperatorName();
                        String simno=telephonyManager.getSimSerialNumber();
                        int phoneCount=telephonyManager.getPhoneCount();


                        Log.i("errrror ","not getdeviceid"+str);
                        Log.i("errrror ","not phoneno"+phoneno);
                        Log.i("errrror ","not operatorname"+operatorName);
                        Log.i("errrror ","not simno"+simno);
                        Log.i("errrror ","not honecount"+phoneCount);
                        Log.i("errrror ","not camed1"+str);


                    }
                    catch (Exception e)
                    {
                        Log.i("errrror ","camed");
                    }


                    //TODO do the same thing here

                }
                else {
                    //simply use the required feature
                    //as the user has already granted permission to them during installation



                    TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
                    try
                    {

                        String str=telephonyManager.getDeviceId();
                        Log.i("errrror ","not camed2"+str);
                    }
                    catch (Exception e)
                    {
                        Log.i("error ","camed");
                    }

                    //TODO do everything here

                }
            }
        });
    }



    public void sshowdialog()
    {
        new AlertDialog.Builder(this)
                .setTitle("Permission needed")
                .setMessage("This permission is needed to reduce the spam unless this you cannot register complain" +
                        "and we do not use your data in wrong purpose")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"turn ON permission for uor app",Toast.LENGTH_LONG).show();
                        //TODO change to our name of app
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivityForResult(intent,storagepermissioncode);
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();
    }


    public void requeststoragepermisssion()
    {

        //here i am requesting permission
        ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_PHONE_STATE},storagepermissioncode);


    }


    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */

    //here doing work after the useer do something
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == storagepermissioncode)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
                sshowdialog();
            }
        }
    }

}
