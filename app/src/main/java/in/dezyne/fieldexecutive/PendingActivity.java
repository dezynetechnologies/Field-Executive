package in.dezyne.fieldexecutive;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

/**
 * Created by Dezyne 2 on 9/10/2016.
 */
public class PendingActivity extends Activity {

    DatabaseHandler db;
    Fields f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_layout);


        f = db.getContact(1);
        f.getID();




        }
}
