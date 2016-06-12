package funhacks.curry29.morauknow;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by b1014100 on 2016/05/25.
 */
public class ImageArrayAdapter extends ArrayAdapter<ListItem>{

    private int resouceId;
    private List<ListItem> items;
    private LayoutInflater inflater;
    /*コンストラクタ*/
    public ImageArrayAdapter(Context context, int resouceId, List<ListItem> items){
        super(context,resouceId,items);

        this.resouceId = resouceId;
        this.items = items;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    /*スクロールしたとき用のやつ*/
    @Override
    public View getView(int position, View convertView,ViewGroup parent){
        View view;
        if(convertView  != null) {
            view = convertView;
        }else{
            view = this.inflater.inflate(this.resouceId, null);
        }

        ListItem item = this.items.get(position);

        /*テキストのセット部*/
        ImageView appEventImage = (ImageView)view.findViewById(R.id.Event_Image);
        appEventImage.setImageResource(item.getImageURL());
        TextView appEventName = (TextView) view.findViewById(R.id.Event_Name);
        appEventName.setText(item.getEventName());
        TextView appTime = (TextView) view.findViewById(R.id.Event_Time);
        appTime.setText(item.getTime());
        TextView appAreaName = (TextView) view.findViewById(R.id.Event_Area);
        appAreaName.setText(item.getArea());


        if (item.getAreaId() == 0) {
            view.setBackgroundColor(Color.parseColor("#f8a2a2"));
        } else if(item.getAreaId() == 1){
            view.setBackgroundColor(Color.parseColor("#5cd632"));
        }else if(item.getAreaId() == 2){
            view.setBackgroundColor(Color.parseColor("#399bdd"));
        }else if(item.getAreaId() == 3){
            view.setBackgroundColor(Color.parseColor("#f8e408"));
        }else if(item.getAreaId() == 4){
            view.setBackgroundColor(Color.parseColor("#bf7831"));
        }else{
            view.setBackgroundColor(Color.parseColor("#a933d8"));
        }
        return view;
    }

}
