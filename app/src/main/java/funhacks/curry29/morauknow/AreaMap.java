package funhacks.curry29.morauknow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AreaMap extends Activity {
    public int AreaID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_map);

        View.OnClickListener station_button_clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AreaID = 0;
                areamap2station();
            }
        };
        findViewById(R.id.button).setOnClickListener(station_button_clickListener);
        findViewById(R.id.button8).setOnClickListener(station_button_clickListener);

        View.OnClickListener center_button_clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AreaID = 1;
                areamap2center();
            }
        };
        findViewById(R.id.button4).setOnClickListener(center_button_clickListener);
        findViewById(R.id.button10).setOnClickListener(center_button_clickListener);

        View.OnClickListener five_button_clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AreaID = 2;
                areamap2five();
            }
        };
        findViewById(R.id.button5).setOnClickListener(five_button_clickListener);
        findViewById(R.id.button11).setOnClickListener(five_button_clickListener);

        View.OnClickListener west_button_clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AreaID = 3;
                areamap2west();
            }
        };
        findViewById(R.id.button2).setOnClickListener(west_button_clickListener);
        findViewById(R.id.button7).setOnClickListener(west_button_clickListener);

        View.OnClickListener east_button_clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AreaID = 4;
                areamap2east();
            }
        };
        findViewById(R.id.button3).setOnClickListener(east_button_clickListener);
        findViewById(R.id.button9).setOnClickListener(east_button_clickListener);

        View.OnClickListener north_button_clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AreaID = 5;
                areamap2north();
            }
        };
        findViewById(R.id.button6).setOnClickListener(north_button_clickListener);
        findViewById(R.id.button12).setOnClickListener(north_button_clickListener);

    }
    private void areamap2station(){
        Intent intent = new Intent(getApplication(), MapsActivity.class);
        intent.putExtra("AREA_ID", AreaID);
        startActivity(intent);
    }

    private void areamap2center(){
        Intent intent = new Intent(getApplication(), MapsActivity.class);
        intent.putExtra("AREA_ID", AreaID);
        startActivity(intent);
    }

    private void areamap2five(){
        Intent intent = new Intent(getApplication(), MapsActivity.class);
        intent.putExtra("AREA_ID", AreaID);
        startActivity(intent);
    }

    private void areamap2west(){
        Intent intent = new Intent(getApplication(), MapsActivity.class);
        intent.putExtra("AREA_ID", AreaID);
        startActivity(intent);
    }

    private void areamap2east(){
        Intent intent = new Intent(getApplication(), MapsActivity.class);
        intent.putExtra("AREA_ID", AreaID);
        startActivity(intent);
    }

    private void areamap2north(){
        Intent intent = new Intent(getApplication(), MapsActivity.class);
        intent.putExtra("AREA_ID", AreaID);
        startActivity(intent);
    }
}
