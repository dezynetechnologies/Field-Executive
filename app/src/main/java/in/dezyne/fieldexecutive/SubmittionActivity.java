package in.dezyne.fieldexecutive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;


public class SubmittionActivity extends AppCompatActivity {

    ImageButton approve,pending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submit_activity_layout);

        approve = (ImageButton)findViewById(R.id.approvedview);
        pending = (ImageButton)findViewById(R.id.pendingview);

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


    }
}
