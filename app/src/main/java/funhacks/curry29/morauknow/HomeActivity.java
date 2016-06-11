package funhacks.curry29.morauknow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        View.OnClickListener eventlist_button_clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home2eventlist();
            }
        };
        findViewById(R.id.eventlist_button).setOnClickListener(eventlist_button_clickListener);
        /*
        View.OnClickListener areamap_button_clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home2areamap();
            }
        };
        findViewById(R.id.areamap_button).setOnClickListener(areamap_button_clickListener);
        */


    }
    private void home2eventlist(){
        Intent intent = new Intent(getApplication(), EventList.class);
        startActivity(intent);
    }
/*
    private void home2areamap(){
        Intent intent = new Intent(getApplication(), EventList.class);
        startActivity(intent);
    }
    */
}
