package edu.sfsu.cs.orange.ocr.camera;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;

import edu.sfsu.cs.orange.ocr.CaptureActivity;
import edu.sfsu.cs.orange.ocr.R;
import edu.sfsu.cs.orange.ocr.Services.sendService;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends ActionBarActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScannerView;
    String Source_Language;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Activity activity = this;
        view =  activity.getLayoutInflater().inflate(R.layout.activity_main,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Intent intentService = new Intent(this, sendService.class);
        startService(intentService);
        builder.setTitle("اختر نوع الجهاز:");
        final CharSequence[] items = {
                "1", "2"
        };
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("1")) {
                    Source_Language = "eng";
                     } else if (items[item].equals("2")) {
                    Source_Language = "ara";
                }
            }
        });
        AlertDialog alert1 = builder.create();
        alert1.show();
        QrScanner(view);

    }

    @Override
    public void handleResult(final Result rawResult) {
        // Do something with the result here

        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)

        // show the scanner result into dialog box.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this,CaptureActivity.class);
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

        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();         // Start camera
    }

    @Override
    public void onPause() {
        super.onPause();
      mScannerView.stopCamera();   // Stop camera on pause
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        QrScanner(view);
    }
}
