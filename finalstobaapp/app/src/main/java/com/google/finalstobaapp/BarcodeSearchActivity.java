package com.google.finalstobaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class BarcodeSearchActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    int MY_PERMISSION_REQUEST_CAMERA = 0;
    ZXingScannerView Scanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Scanner = new ZXingScannerView(this);
        setContentView(Scanner);
    }

    @Override
    public void handleResult(Result result) {
        EditActivity.resultsearcheview.setText(result.getText());

        onBackPressed();

    }


    @Override
    protected void onPause() {
        super.onPause();
        Scanner.stopCamera();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSION_REQUEST_CAMERA);
        }
        Scanner.setResultHandler(this);
        Scanner.startCamera();
    }
}