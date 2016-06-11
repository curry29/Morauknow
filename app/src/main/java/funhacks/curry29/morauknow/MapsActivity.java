package funhacks.curry29.morauknow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nifty.cloud.mb.core.FindCallback;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;
import com.nifty.cloud.mb.core.NCMBQuery;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final LatLng HAKODATE = new LatLng(41.786900, 140.736987);
    private static final String tHAKODATE = "函館市";

    private static List<LatLng> lSS = new ArrayList<LatLng>(32);
    private static List<String> tSS = new ArrayList<String>(32);
    int AreaId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        AreaId = intent.getIntExtra("AREA_ID",0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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

    public void setZoom(List<LatLng> latLngList) {
        if (mMap == null || latLngList.size() == 0)
            return;

        final LatLngBounds.Builder builder = LatLngBounds.builder();
        for (LatLng latLng : latLngList) {
            builder.include(latLng);
        }

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition arg0) {
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(
                        builder.build(), 40));
                mMap.setOnCameraChangeListener(null);
            }
        });
    }

    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        setZoom(lSS);
        load();
        // Add a marker in Sydney and move the camera
       // LatLng hakodate = new LatLng(())
     //   mMap.addMarker(new)
    //    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAKODATE,14));


    }
    private void load(){
        NCMBQuery<NCMBObject> query	=new NCMBQuery<>("LatLng");
        //データストアからデータを検索
        query.findInBackground(new	FindCallback<NCMBObject>(){
            @Override
            public	void done(List<NCMBObject> results, NCMBException e)	{
                if(e != null){
                    Log.d("EventList:load()", "NG");
                    //load();
                }else {
                    int count = 0;
                    for (NCMBObject result : results) {
                        if(result.getInt("AreaId") == AreaId) {
                            lSS.add(new LatLng(result.getDouble("Ido"), result.getDouble("Keido")));
                            tSS.add(new String(result.getString("ShopName")));
                            // setZoom(lSS);
                            MarkerOptions mo = new MarkerOptions();
                            mo.position(lSS.get(count)).title(tSS.get(count)).flat(true);
                            count++;
                            Marker mHAKODATE = mMap.addMarker(mo);
                        }
                    }
                    setZoom(lSS);
                    // マーカーがクリックされた時の処理
                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            // タップされたマーカーのタイトルを取得
                            String name = marker.getTitle().toString();

                            
                            return false;
                        }
                    });
                }
            }
        });
    }

}
