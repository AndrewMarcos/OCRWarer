package edu.sfsu.cs.orange.ocr;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by MorcosS on 8/1/16.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class qrRead extends Fragment implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    String Source_Language;
    View view;

    public qrRead() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.activity_main,null);

        mScannerView = new ZXingScannerView(view.getContext());   // Programmatically initialize the scanner view
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
        return mScannerView;
    }


    @Override
    public void handleResult(final Result rawResult) {
        // Do something with the result here

        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        // show the scanner result into dialog box.
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(view.getContext(),CaptureActivity.class);
                intent.putExtra("Source_Language",Source_Language);
                intent.putExtra("Customer_ID",rawResult.getText().toString());
                startActivity(intent);
            }
        });
        AlertDialog alert1 = builder.create();

        alert1.show();
         // If you would like to resume scanning, call this method below:
        // mScannerView.resumeCameraPreview(this);
    }
    public void QrScanner(View view){

            // Start camera
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();   // Stop camera on pause
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);


    }
}
