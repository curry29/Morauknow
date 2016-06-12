package funhacks.curry29.morauknow;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nifty.cloud.mb.core.FindCallback;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBObject;
import com.nifty.cloud.mb.core.NCMBQuery;

import java.util.List;

public class EventDtails extends AppCompatActivity {
private ShopItem item = new ShopItem();
   int EventId;
    String ShopName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_dtails);
    /*インテントからイベントIDの取得*/
       /*インテントの取得*/
        Intent intent = getIntent();
        EventId = intent.getIntExtra("EventId",0);
        Log.d("ShopItem:load()", "EventId="+EventId);
        ShopName = intent.getStringExtra("SHOP_NAME");

        loadShopItem();

        //アクションバーに名前表示
        getSupportActionBar().setTitle("イベントの詳細");

        View.OnClickListener dtail_to_map_clicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dtail2map();
            }
        };
        findViewById(R.id.dtail_to_map).setOnClickListener(dtail_to_map_clicklistener);

    }

    private void dtail2map(){
        Intent intent = new Intent(getApplication(), MapsActivity.class);
        intent.putExtra("SHOP_NAME",item.getShopName());
        intent.putExtra("AREA_ID", -10);
        startActivity(intent);
    }

    private void loadShopItem(){
        NCMBQuery<NCMBObject> query	=new NCMBQuery<>("ShopItem");
        //データストアからデータを検索
        query.findInBackground(new	FindCallback<NCMBObject>(){
            @Override
            public	void done(List<NCMBObject> results, NCMBException e)	{
                if(e != null){
                    Log.d("ShopItem:load()", "NG");
                    //load();
                }else {
                    int i = 0;
                    if(EventId<0){
                        Log.d("Load", "ShopName "+ShopName);
                        for (NCMBObject result : results) {
                            Log.d("Load", "ShopName "+result.getString("ShopName"));
                            if(result.getString("ShopName").equals(ShopName)) {
                                EventId = i;
                                break;
                            }
                            i++;
                        }
                    }

                    View LinerView =  findViewById(R.id.change_backgorund);
                    item.setAreaId(results.get(EventId).getInt("AreaId"));

                    if (item.getAreaId() == 0) {
                        LinerView.setBackgroundColor(Color.parseColor("#f8a2a2"));
                    } else if(item.getAreaId() == 1){
                        LinerView.setBackgroundColor(Color.parseColor("#5cd632"));
                    }else if(item.getAreaId() == 2){
                        LinerView.setBackgroundColor(Color.parseColor("#399bdd"));
                    }else if(item.getAreaId() == 3){
                        LinerView.setBackgroundColor(Color.parseColor("#f8e408"));
                    }else if(item.getAreaId() == 4){
                        LinerView.setBackgroundColor(Color.parseColor("#bf7831"));
                    }else{
                        LinerView.setBackgroundColor(Color.parseColor("#a933d8"));
                    }
                    int imagename = getResources().getIdentifier(results.get(EventId).getString("ImageURL"),"drawable",getPackageName());
                   // int imagename = R.drawable.sample1;

                    item.setShopName(results.get(EventId).getString("ShopName"));
                    TextView appShopName = (TextView) findViewById(R.id.Detail_ShopName);
                    appShopName.setText(item.getShopName());

                    item.setConnection(results.get(EventId).getString("Connection"));
                    TextView appConnection = (TextView) findViewById(R.id.Detail_Connection);
                    appConnection.setText(item.getConnection().replaceAll("改行","\n"));

                    item.setEventName(results.get(EventId).getString("EventName"));
                    TextView appEventName = (TextView) findViewById(R.id.Detail_EventName);
                    appEventName.setText(item.getEventName());

                    item.setEventDetail(results.get(EventId).getString("EventDetail"));
                    TextView appEventDetail = (TextView) findViewById(R.id.Detail_EventDetail);
                    appEventDetail.setText(item.getEventDetail().replaceAll("改行","\n"));

                    item.setImageURL(imagename);
                    ImageView appEventImage = (ImageView) findViewById(R.id.Detail_imageView);
                    appEventImage.setImageResource(item.getImageURL());
                }

            }
        });
    }
}
