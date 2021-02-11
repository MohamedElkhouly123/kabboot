package com.example.kabboot.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.kabboot.R;
import com.example.kabboot.view.fragment.HomeCycle2.HomeServices.CompleteBookingServicesFragment;
import com.example.kabboot.view.fragment.HomeCycle2.onLineStore.MyCartFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.kabboot.utils.HelperMethod.showToast;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback  {

    private static final String TAG = "MapActivity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    @BindView(R.id.map_title_name_tv)
    TextView mapTitleNameTv;
    //    @BindView(R.id.back_btn)
//    ImageButton backBtn;
//    @BindView(R.id.toolbar_title)
//    TextView toolbarTitle;
//    @BindView(R.id.toolbar_sub_view)
//    ConstraintLayout toolbarSubView;
//    private BoomMenuButton bmb;
    //vars
    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private MapView mapView;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private LatLng langlat;
    private  CompleteBookingServicesFragment completeBookingServicesFragment;
    private  MyCartFragment myCartFragment;

    private Bundle extras;
    private String myCartOrMyService;
    private LatLng langlat2;
    private double lang=0;
    private double lat=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        extras = getIntent().getExtras();
        if (extras != null) {
             myCartOrMyService = extras.getString("key");
            //The key argument here must match that used in the other activity
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ButterKnife.bind(this);
        completeBookingServicesFragment=new CompleteBookingServicesFragment();
        myCartFragment=new MyCartFragment();

//        toolbarSubView.setVisibility(View.VISIBLE);
//        toolbarTitle.setText(getString(R.string.title_activity_maps));
//        backBtn.setOnClickListener(onBackPressed());
        getLocationPermission();
        boomShow();
    }

    private void boomShow() {
//        bmb = (BoomMenuButton) findViewById(R.id.bmb);


//        bmb.setButtonEnum(ButtonEnum.Ham);

//        bmb.addBuilder(new SimpleCircleButton.Builder().normalImageRes(R.drawable.ic_add_blue_24dp));

//        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
////            new SimpleCircleButton.Builder().normalImageRes(R.drawable.ic_add_blue_24dp);
//            HamButton.Builder builder = new HamButton.Builder()
//
////                    .normalImageRes(getImageResource())
////                    .normalText("moh")
////                    .subNormalTextRes(R.string.text_outside_circle_button_text_normal)
//                    .normalTextColorRes(R.color.white)
//                    .normalColorRes(R.color.blue)
//                    .highlightedColorRes(R.color.blueLight)
//
////                    .textGravity(Gravity.CENTER_HORIZONTAL)
//                    .textGravity(Gravity.CENTER_VERTICAL)
//                    .textSize(20)
//                    .rotateImage(true)
//                    .buttonCornerRadius(Util.dp2px(5))
////                    .textPadding(new Rect(10, 0, 10, 0))
////                    .unableImageRes(R.drawable.markafhjbaharea)
////                    .imageRect(new Rect(0, 0, Util.dp2px(60), Util.dp2px(60)))
////                    .imagePadding(new Rect(5, 0, 5, 0))
////                    .textRect(new Rect(Util.dp2px(70), Util.dp2px(10), Util.dp2px(280), Util.dp2px(40)))
//                    ;
//
////                    .pieceColor(Color.WHITE)
////                    .shadowEffect(true)
////                    .shadowRadius(Util.dp2px(100))
//            if (i == 0) {
//                builder.normalText("خريطة عادية")
////                builder.normalTextRes(R.string.text_ham_button_text_normal);
//
//                ;
//            }
//            if (i == 1) {
//                builder.normalText("خريطة ساتاليت متقدمة");
//            }
//
//
//
//            builder.listener(new OnBMClickListener() {
//                @Override
//                public void onBoomButtonClick(int index) {
//                    // When the boom-button corresponding this builder is clicked.
//                    Toast.makeText(MapsActivity.this, "Clicked " + index, Toast.LENGTH_SHORT).show();
//                    if(index==0){// do this
//                        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//                        LatLng sydney = new LatLng(-33.852, 151.211);
//                        mMap.addMarker(new MarkerOptions()
//                                .position(sydney)
//                                .title("Marker in Sydney"));
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//                    }
//                            if(index==1){
//                                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//
//                            }
////                            if(index==2){
////
////                            }
////                            if(index==3){}
//
//                }
////                       @Override
////                       public void onBackgroundClick() {
////                           textViewForAnimation.setText("Click background!!!");
////                       }
////                       @Override
////                       public void onBoomWillHide() {
////                           Log.d("BMB", "onBoomWillHide: " + bmb.isBoomed() + " " + bmb.isReBoomed());
////                           textViewForAnimation.setText("Will RE-BOOM!!!");
////                       }
////                       @Override
////                       public void onBoomDidHide() {
////                           Log.d("BMB", "onBoomDidHide: " + bmb.isBoomed() + " " + bmb.isReBoomed());
////                           textViewForAnimation.setText("Did RE-BOOM!!!");
////                       }
////                       @Override
////                       public void onBoomWillShow() {
////                           Log.d("BMB", "onBoomWillShow: " + bmb.isBoomed() + " " + bmb.isReBoomed());
////                           textViewForAnimation.setText("Will BOOM!!!");
////                       }
////                       @Override
////                       public void onBoomDidShow() {
////                           Log.d("BMB", "onBoomDidShow: " + bmb.isBoomed() + " " + bmb.isReBoomed());
////                           textViewForAnimation.setText("Did BOOM!!!");
////                       }
//            });
//            bmb.addBuilder(builder);
//
//        }
    }

    private void initMap() {
        Log.d(TAG, "initMap: initializing map");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionsGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
//    @Override
//    @Overrid
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    //initialize our map
                    initMap();
                }
            }
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        if (mLocationPermissionsGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
                @Override
                public void onCameraChange(CameraPosition cameraPosition) {

                    Log.i("centerLat", String.valueOf(cameraPosition.target.latitude));

                    Log.i("centerLong", String.valueOf(cameraPosition.target.longitude));
                    langlat2 = cameraPosition.target;

                    lang  = langlat2.longitude;
                    lat= langlat2.latitude;
                }
            });
//            LatLng centerLatLang = mMap.getProjection().getVisibleRegion().latLngBounds.getCenter();

//            mMap.getUiSettings().setMyLocationButtonEnabled(false);
// Setting a click event handler for the map
//            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//
//                @Override
//                public void onMapClick(LatLng latLng) {
//
//                    // Creating a marker
//                    MarkerOptions markerOptions = new MarkerOptions();
//
//                    // Setting the position for the marker
//                    markerOptions.position(latLng);
//
//                    // Setting the title for the marker.
//                    // This will be displayed on taping the marker
//                    markerOptions.title("Success Select "+latLng.latitude + " : " + latLng.longitude);
//                    ToastCreator.onCreateSuccessToast(MapsActivity.this, "Success Select Click Submit to go to next step");
//                    lang = latLng.longitude;
//                    lat = latLng.latitude;
//                    markerOptions.icon(bitmapDescriptorFromVector(MapsActivity.this, R.drawable.ic_baseline_directions_car_24));
//
//                    // Clears the previously touched position
//                    googleMap.clear();
//
//                    // Animating to the touched position
//                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
//
//                    // Placing a marker on the touched position
//                    googleMap.addMarker(markerOptions);
//                }
//            });

        }
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if (mLocationPermissionsGranted) {

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();
                            if (currentLocation != null) {
//                                showToast(MapsActivity.this, " yes ");
                                moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                        DEFAULT_ZOOM);
                            }

                        } else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(MapsActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }
    }

    private void moveCamera(LatLng latLng, float zoom) {
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    @Override
    public void onBackPressed() {
        if(myCartOrMyService.equalsIgnoreCase("myService")){
            completeBookingServicesFragment.mabBack=true;
        }
        if(myCartOrMyService.equalsIgnoreCase("myCard")){
            myCartFragment.mabBack=true;
        }
//        completeBookingServicesFragment.mabBack=true;
        super.onBackPressed();
    }

    @OnClick({R.id.map_back_img, R.id.map_save_location_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.map_back_img:
                onBackPressed();
                break;
            case R.id.map_save_location_btn:
                if(lang!=0&&lat!=0){
//                    showToast(this, lang+"  "+lat);
                    if(myCartOrMyService.equalsIgnoreCase("myService")){
                    completeBookingServicesFragment.myLang=lang;
                    completeBookingServicesFragment.myLat=lat;
                    }
                    if(myCartOrMyService.equalsIgnoreCase("myCard")){
                        myCartFragment.myLang=lang;
                        myCartFragment.myLat=lat;
                    }
                    finish();
                }
//                else {
//                    ToastCreator.onCreateErrorToast(MapsActivity.this, getString(R.string.location_required));
//
//                }
                break;
        }
    }

//    @Override
//    public void onLocationChanged(@NonNull Location location) {
//        lat = location.getLatitude();
//
//        // Getting longitude of the current location
//        lang = location.getLongitude();
//
//        // Creating a LatLng object for the current location
//        LatLng latLng = new LatLng(lat, lang);
//
//        // Showing the current location in Google Map
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//
//        // Zoom in the Google Map
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
//
//        // Setting latitude and longitude in the TextView tv_location
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(@NonNull String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(@NonNull String provider) {
//
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }

//    @Override
//    public void onCameraChange(CameraPosition cameraPosition) {
//        langlat2 = cameraPosition.target;
//
//        lang  = langlat2.longitude;
//        lat= langlat2.latitude;
//        // Getting longitude of the current location
//    }
}


