package com.example.ahosanhabib.slash_screen;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    public static final int Request_Code= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new TestNet().execute();
    }
    class TestNet extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            String[] permissions = {Manifest.permission.SEND_SMS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION};
            if(hasPermission(SplashActivity.this, permissions)){

                Intent goHome = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(goHome);

            }else{

                ActivityCompat.requestPermissions(SplashActivity.this,permissions, Request_Code);

            }
        }
    }
    public static boolean hasPermission(Context c,String... permissions){
        if (c!=null && permissions!=null){
            for(String p: permissions){
                if(ActivityCompat.checkSelfPermission(c,p)!=PackageManager.PERMISSION_GRANTED){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED && grantResults[2]==PackageManager.PERMISSION_GRANTED){

            Intent goHome = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(goHome);
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
            builder.setMessage("You have to grant all the permission");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
        }

    }
}
