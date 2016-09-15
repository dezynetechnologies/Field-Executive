package in.dezyne.fieldexecutive;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Dezyne 2 on 9/8/2016.
 */
public class MainActivity extends AppCompatActivity{

    Button submit,logout;
    FloatingActionButton fab;
    boolean isInFront;
    ImageButton maps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);


        submit = (Button)findViewById(R.id.submittions);
        logout = (Button)findViewById(R.id.logout);
        fab = (FloatingActionButton)findViewById(R.id.fabadd);
        maps =(ImageButton)findViewById(R.id.maps);

        startService(new Intent(this, MyService.class));

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SubmittionActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CreateNewActivity.class);
                startActivity(intent);


            }
        });

        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Maps.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        isInFront = true;

    }

    @Override
    public void onPause() {
        super.onPause();
        isInFront = false;
    }



}
