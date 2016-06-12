package funhacks.curry29.morauknow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nifty.cloud.mb.core.FindCallback;
import com.nifty.cloud.mb.core.NCMB;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;
import com.nifty.cloud.mb.core.NCMBQuery;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final LatLng HAKODATE = new LatLng(41.772437, 140.729523);
    private static final LatLng NullLatLng = new LatLng(0, 0);
    private static final String tHAKODATE = "函館市";
    private static List<LatLng> lSS = new ArrayList<LatLng>(32);
    private static List<String> tSS = new ArrayList<String>(32);

    BitmapDescriptor ika;
    String ShopName;
   int AreaId ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        ShopName = intent.getStringExtra("SHOP_NAME");
        Log.d("ShopItem:getExtra", "ShopName="+ShopName);
        NCMB.initialize(this.getApplicationContext(),"fe7bee8bea8475ddbecdbf020d6ec93c2dfb2bb6c857c33f16191eb9ce10ab19","3c50c489b02de8566548de932cadd64f75bbd7127039c1981bfe7652e15c8572");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        AreaId = intent.getIntExtra("AREA_ID",0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ika = BitmapDescriptorFactory.fromResource(R.drawable.ikachan);

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
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(
                        builder.build(), 100));
                mMap.setOnCameraChangeListener(null);

            }
        });
    }
    /*
    //戻るボタンの処理
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            // 戻るボタンの処理
           mMap.clear();
            return super.onKeyDown(keyCode, event);
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

  */
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        load();
        // Add a marker in Sydney and move the camera
        //LatLng hakodate = new LatLng(())
        //mMap.addMarker(new)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAKODATE,14));


        //追加-----------------------------
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
                        if (result.getInt("AreaID") == AreaId) {
                            Log.d("MspdsSctivity:load()", "AreaId" + result.getInt("AreaID"));
                            lSS.add(new LatLng(result.getDouble("Ido"), result.getDouble("Keido")));
                            tSS.add(new String(result.getString("ShopName")));
                        }
                    }
                }

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(HAKODATE,14));
                if(lSS.size()==1) {
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lSS.get(0),16));
                }else{
                    setZoom(lSS);
                }

            }
        });


    }
    private void load(){
        NCMBQuery<NCMBObject> query	=new NCMBQuery<>("LatLng");
        lSS = new ArrayList<LatLng>(32);
        tSS = new ArrayList<String>(32);
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
                        if(AreaId<0){
                            Log.d("Load", "ShopName "+ ShopName);
                            if(result.getString("ShopName").equals(ShopName)){

                                lSS.add(new LatLng(result.getDouble("Ido"), result.getDouble("Keido")));
                                tSS.add(new String(result.getString("ShopName")));
                                MarkerOptions mo = new MarkerOptions();
                                mo.position(lSS.get(count)).title(tSS.get(count)).flat(true).icon(ika);
                                mMap.addMarker(mo);
                                break;
                            }
                        }
                        else if(result.getInt("AreaID") == AreaId) {
                            Log.d("MspdsSctivity:load()", "AreaId" + result.getInt("AreaID"));
                            lSS.add(new LatLng(result.getDouble("Ido"), result.getDouble("Keido")));
                            tSS.add(new String(result.getString("ShopName")));
                            MarkerOptions mo = new MarkerOptions();
                            mo.position(lSS.get(count)).title(tSS.get(count)).flat(true).icon(ika);
                            count++;
                            mMap.addMarker(mo);
                        }
                    }
                    //setZoom(lSS);

                    mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            // TODO Auto-generated method stub
                            // タップされたマーカーのタイトルを取得
                            String name = marker.getTitle().toString();
                            Intent intent = new Intent(getApplication(), EventDtails.class);
                            intent.putExtra("SHOP_NAME", name);
                            intent.putExtra("EventId", -10);
                            startActivity(intent);
                            //mMap.clear();
                        }
                    });

                }
            }
        });
    }

}
