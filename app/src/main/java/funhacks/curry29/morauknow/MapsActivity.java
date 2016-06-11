package funhacks.curry29.morauknow;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final LatLng HAKODATE = new LatLng(41.786900, 140.736987);
    private static final String tHAKODATE = "函館市";

    private static List<LatLng> lSS = new ArrayList<LatLng>(32);
    private static List<String> tSS = new ArrayList<String>(32);

    public void listadd() {
        lSS.add(new LatLng(41.786900, 140.736987));
        lSS.add(new LatLng(40.786900, 141.936987));
        lSS.add(new LatLng(41.996900, 141.136987));
        lSS.add(new LatLng(41.386900, 141.536987));
        lSS.add(new LatLng(41.186900, 140.936987));

        tSS.add(new String("函館市1"));
        tSS.add(new String("函館市2"));
        tSS.add(new String("函館市3"));
        tSS.add(new String("函館市4"));
        tSS.add(new String("函館市5"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        listadd();

        for(int i=0;i<5;i++){

            MarkerOptions mo = new MarkerOptions();
            mo.position(lSS.get(i)).title(tSS.get(i)).flat(true);
            Marker mHAKODATE = mMap.addMarker(mo);
        }

        // Add a marker in Sydney and move the camera
       // LatLng hakodate = new LatLng(())
     //   mMap.addMarker(new)
    //    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAKODATE,14));

        setZoom(lSS);
    }
}
