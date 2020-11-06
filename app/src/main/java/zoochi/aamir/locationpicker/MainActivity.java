package zoochi.aamir.locationpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    Button button;
    TextView textView1,textView2;

    private static final int ADDRESS_PICKER_REQUEST = 1020;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);
        textView1=findViewById(R.id.textView1);
        textView2=findViewById(R.id.textView2);

        MapUtility.apiKey = getResources().getString(R.string.api_key);
        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Intent intent = new Intent(MainActivity.this, LocationPickerActivity.class);
                startActivityForResult(intent, ADDRESS_PICKER_REQUEST);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADDRESS_PICKER_REQUEST) {
            try {
                if (data != null && data.getStringExtra(MapUtility.ADDRESS) != null) {
                    // String address = data.getStringExtra(MapUtility.ADDRESS);
                    double currentLatitude = data.getDoubleExtra(MapUtility.LATITUDE, 0.0);
                    double currentLongitude = data.getDoubleExtra(MapUtility.LONGITUDE, 0.0);
                    Bundle completeAddress =data.getBundleExtra("fullAddress");

                    /* data in completeAddress bundle
                    "fulladdress"
                    "city"
                    "state"
                    "postalcode"
                    "country"
                    "addressline1"
                    "addressline2"
                     */

//                    txtAddress.setText(new StringBuilder().append("addressline2: ").append
//                            (completeAddress.getString("addressline2")).append("\ncity: ").append
//                            (completeAddress.getString("city")).append("\npostalcode: ").append
//                            (completeAddress.getString("postalcode")).append("\nstate: ").append
//                            (completeAddress.getString("state")).toString());
//
//                    txtLatLong.setText(new StringBuilder().append("Lat:").append(currentLatitude).append
//                            ("  Long:").append(currentLongitude).toString());



                    textView1.setText(completeAddress.getString("addressline2").toString());

                    textView2.setText(new StringBuilder().append("Lat:").append(currentLatitude).append
                            ("  Long:").append(currentLongitude).toString());


                    Log.i("mytag", "onActivityResult: address: "+new StringBuilder().append("addressline2: ").append
                            (completeAddress.getString("addressline2")).append("\ncity: ").append
                            (completeAddress.getString("city")).append("\npostalcode: ").append
                            (completeAddress.getString("postalcode")).append("\nstate: ").append
                            (completeAddress.getString("state")).toString());



                    Log.i("mytag", "onActivityResult: latlong:  " +new StringBuilder().append("Lat:").append(currentLatitude).append
                            ("  Long:").append(currentLongitude).toString());




                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}