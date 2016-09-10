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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_layout);


        List<Fields> contacts = db.Pending();

        for (Fields cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Image: " + cn.getImagepath() + " ,Name: " + cn.getName() + " ,Sex: " + cn.getSex() + " ,Age: " + cn.getAge() + " ,Address: " + cn.getAddress() + " ,Current Salary: " + cn.getSalary() + " ,Saving: " + cn.getSaving() + " ,Status: " + cn.getStatus();
            // Writing Contacts to log
            Log.d("Name: ", log);

        }

        }
}
