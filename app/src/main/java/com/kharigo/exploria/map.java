package com.kharigo.exploria;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class map extends AppCompatActivity implements OnMapReadyCallback {
    private final static int PLACE_PICKER_REQUEST = 111;
    private static final String TAG = map.class.getSimpleName();
    SupportMapFragment mapFragment;
    GoogleMap map;
    private TextView txtLatLong;
    private TextView txtAddress;
    ImageButton BTN_GPRS;
    double latitude = 20.0037013;
    double longitude = 73.7916095;
    int my_Accurecy = 4444444;
    Circle my_location_point,kharigo_circle;
    Marker My_addMarker;
    String data="";
    TextView Note;
    int height = 300;
    int width = 280;
    int AD_height = 350;
    int AD_width = 400;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        if(getIntent().getStringExtra("data") != null) {
            data = getIntent().getStringExtra("data");
        }

//        new MainActivity().build_Alert_Message_No_Gps(getApplicationContext(),this);  //check GPRS

        final GPSTracker gps = new GPSTracker(getApplicationContext());
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
        my_Accurecy = (int) gps.getAccurecy();


        BTN_GPRS = findViewById(R.id.IMG_BTN_MY_location);
        Note = findViewById(R.id.Note);
        Note.setText(latitude+" "+longitude+" / "+my_Accurecy);

        // Get the SupportMapFragment and request notification when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        GoogleMapOptions options = new GoogleMapOptions();
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setZoomControlsEnabled(true);




        //   check_distans();

        if (BTN_GPRS.getVisibility()== View.GONE) BTN_GPRS.setVisibility(View.VISIBLE);
        BTN_GPRS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final GPSTracker gps = new GPSTracker(getApplicationContext());
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
                my_Accurecy = (int) gps.getAccurecy();

                float Accurecy = gps.getAccurecy();
                Note.setText(latitude+" "+longitude+" / "+my_Accurecy);

//                sqliteHelper db = new sqliteHelper(getApplicationContext());
//                db.lalo_updateData(String.valueOf(latitude),String.valueOf(longitude));

                float zoom = 20;
                if (Accurecy < 35){
                    zoom = 15;
                }
                if (Accurecy > 35 && Accurecy < 50){
                    zoom = 18;
                }
                if (Accurecy > 50 && Accurecy < 100){
                    zoom = 15;
                }
                if (Accurecy > 100){
                    zoom = 20;
                }

                if(My_addMarker!=null){ My_addMarker.remove(); }
                My_addMarker =  map.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude,longitude))
                        .title("My Location"));


                check_distans();

                map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude)));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), zoom));
                add_shop();


            }
        });






    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void add_shop(){
//Log.i("data",data);

        String x = "20.0034551";
        String y = "73.7761669";
        String area = "30";
        String name = "K.T.H.M. College";

        final LatLng shop = new LatLng(Double.parseDouble(x),Double.parseDouble(y));

        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.kt);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, AD_width, AD_height, false);
        map.addMarker(new MarkerOptions()
                .position(shop)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .title(name));
        map.addCircle(new CircleOptions()
                .center(shop)
                .radius(Integer.parseInt(area))
                .strokeColor(Color.GREEN)
                .fillColor(0x220000FF)
                .strokeWidth(5)
        );

        add_shop1();

        map.moveCamera(CameraUpdateFactory.newLatLng(shop));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(shop, 20));
    }
    private void add_shop1(){
//Log.i("data",data);


        String x = "20.0035010";
        String y = "73.7762010";
        String area = "2";
        String name = "Dilprit";

        final LatLng shop = new LatLng(Double.parseDouble(x),Double.parseDouble(y));

        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.f1);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        map.addMarker(new MarkerOptions()
                .position(shop)
//                .icon(icon)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .title(name));
        map.addCircle(new CircleOptions()
                .center(shop)
                .radius(Integer.parseInt(area))
                .strokeColor(Color.GREEN)
                .fillColor(0x220000FF)
                .strokeWidth(5)
        );

        add_shop2();


    }
    private void add_shop2(){
//Log.i("data",data);


        String x = "20.0034130";
        String y = "73.7761130";

        String area = "2";
        String name = "vishal";

        final LatLng shop = new LatLng(Double.parseDouble(x),Double.parseDouble(y));

        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.f22);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        map.addMarker(new MarkerOptions()
                .position(shop)
//                .icon(icon)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .title(name));
        map.addCircle(new CircleOptions()
                .center(shop)
                .radius(Integer.parseInt(area))
                .strokeColor(Color.GREEN)
                .fillColor(0x220000FF)
                .strokeWidth(5)
        );
        add_shop3();


    }
    private void add_shop3(){
//Log.i("data",data);



        String x = "20.0032010";
        String y = "73.7761010";
        String area = "10";
        String name = "Neha";

        final LatLng shop = new LatLng(Double.parseDouble(x),Double.parseDouble(y));

        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.f3);
      //  BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.user);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        map.addMarker(new MarkerOptions()
                .position(shop)
//                .icon(icon)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                .title(name));
        map.addCircle(new CircleOptions()
                .center(shop)
                .radius(Integer.parseInt(area))
                .strokeColor(Color.GREEN)
                .fillColor(0x220000FF)
                .strokeWidth(5)
        );


    }





    private void check_distans() {

        final GPSTracker gps = new GPSTracker(getApplicationContext());
        latitude = gps.getLatitude();
        longitude = gps.getLongitude();
        my_Accurecy = (int) gps.getAccurecy();

        if(my_location_point!=null){ my_location_point.remove(); }
        my_location_point =  map.addCircle(new CircleOptions()
                .center(new LatLng(latitude,longitude))
                .radius(my_Accurecy)
                .strokeColor(Color.RED)
                .fillColor(0x220000FF)
                .strokeWidth(5));

        Note.setText(latitude+" "+longitude+" / "+my_Accurecy);


        if (data.length() > 6) {
            try {
                JSONArray array = null;
                array = new JSONArray(data);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject product = null;
                    product = array.getJSONObject(i);

                    String x = product.getString("x");
                    String y = product.getString("y");
                    String area = product.getString("area");
                    String name = product.getString("name");



                    float[] results = new float[array.length()];
                    Location.distanceBetween(latitude, longitude, Double.parseDouble(x), Double.parseDouble(y), results);
                    double check = Math.round(results[0]) - Double.parseDouble(area);
                    //   Log.i("check",i+" )  A: "+check+" B: "+Math.round(results[0])+" C: "+Double.parseDouble(area));

                    if (check < 0){
                        String aold = Note.getText().toString();
                        Note.setText(aold+"\n you are in the area of "+name+", \nyou will get all the service through "+name+".");
                        // Log.i("ok",i+" )  A: "+check+" = B: "+Math.round(results[0])+" - C: "+Double.parseDouble(area));
                    }else{

//
//                  Polyline  polyline_all = map.addPolyline(new PolylineOptions()
//                            .width(5)
//                            .color(Color.BLUE)
//                            .add(
//                                    new LatLng(latitude,longitude),
//                                    new LatLng(Double.parseDouble(x),Double.parseDouble(y))
//                            ));
//
//
//
//


                        String aold = Note.getText().toString();
                        Note.setText(aold+"\n"+name+" = "+check+" meters away.");
                    }



                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


            map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude)));
            // map.animateCamera(CameraUpdateFactory.zoomTo(40));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude), 15));

        }

    }



    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
        try {
            List<Address> addresses =geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getPostalCode();
            add = add + "\n" + obj.getSubAdminArea();
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getSubThoroughfare();

            Log.v("IGA", "Address" + add);
            Toast.makeText(this, "Address=>" + add,
                    Toast.LENGTH_SHORT).show();

            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
