package edu.sfsu.cs.orange.ocr.camera;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import edu.sfsu.cs.orange.ocr.R;
import edu.sfsu.cs.orange.ocr.qrRead;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends ActionBarActivity {
    private ZXingScannerView mScannerView;
    String Source_Language;
    View view;
    qrRead QrRead;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QrRead = new qrRead();
      //  getSupportFragmentManager().beginTransaction().add(android.R.id.content, QrRead).commit();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
      }

}
