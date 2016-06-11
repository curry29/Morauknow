package funhacks.curry29.morauknow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_dtails);
    /*インテントからイベントIDの取得*/
       /*インテントの取得*/
        Intent intent = getIntent();
        EventId = intent.getIntExtra("EventId",0);

        loadShopItem();


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
