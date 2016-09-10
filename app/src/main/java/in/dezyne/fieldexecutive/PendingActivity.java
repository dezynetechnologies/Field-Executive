package in.dezyne.fieldexecutive;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dezyne 2 on 9/10/2016.
 */
public class PendingActivity extends Activity {

    DatabaseHandler db;
    ArrayList<Fields> stringArrayList;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_layout);

        db = new DatabaseHandler(this);

        db.getContact(1);






        }
}
