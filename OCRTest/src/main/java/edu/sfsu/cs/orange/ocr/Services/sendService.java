package edu.sfsu.cs.orange.ocr.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by MorcosS on 7/31/16.
 */
public class sendService extends IntentService {
    private static String SOAP_ACTION1 = "http://tempuri.org/GetData";
    private static String NAMESPACE = "http://tempuri.org/";
    private static String METHOD_NAME1 = "GetData";
    private static String METHOD_NAME2 = "GetDataResponse";
    private static String URL = "http://comws.vitalpro.net/webservice2.asmx?op=GetData";

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public sendService(String name) {
        super(name);
    }
    public sendService() {
        super("service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onHandleIntent(intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Calendar c = Calendar.getInstance();
        Date date = c.getTime();
       // DBHelper dbHelper = new DBHelper(this);
        //Cursor cursor = dbHelper.getOrder();
       // if(cursor!=null){
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME1);

            //Use this to add parameters
            request.addProperty("SubscriberID",1);
            request.addProperty("MeterReading",122);
            request.addProperty("ReadingTime","12:00");
        Log.v("hihello","hi");

        //Declare the version of the SOAP request
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            try {
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                //this is the actual part that will call the webservice
                androidHttpTransport.call(SOAP_ACTION1, envelope);

                // Get the SoapResult from the envelope body.
                SoapObject result = (SoapObject)envelope.bodyIn;

                if(result != null)
                {
                    //Get the first property and change the label text
                   Log.v("hihello",result.toString());
                }
                else
                {
                    Log.v("hihello","No");
                    Toast.makeText(getApplicationContext(), "No Response",Toast.LENGTH_LONG).show();

                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.v("hihello","error");

            }
        //}

        }
    }

