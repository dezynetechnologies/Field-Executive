package in.dezyne.fieldexecutive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class SubmittionActivity extends AppCompatActivity {

    ImageButton approve,pending,rejected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_activity_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        approve = (ImageButton)findViewById(R.id.approvedview);
        pending = (ImageButton)findViewById(R.id.pendingview);
        rejected = (ImageButton)findViewById(R.id.rejectedview);


        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubmittionActivity.this,ApprovedActivity.class);
                startActivity(intent);

            }
        });

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SubmittionActivity.this,PendingActivity.class);
                startActivity(intent);
            }
        });


        rejected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SubmittionActivity.this,RejectedActivity.class);
                startActivity(intent);
            }
        });

    }
}
