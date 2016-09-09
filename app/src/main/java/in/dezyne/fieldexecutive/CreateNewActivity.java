package in.dezyne.fieldexecutive;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CreateNewActivity extends AppCompatActivity {

    Button sub;
    ImageButton up;
    private static int SELECT_IMAGE = 1;
    DatabaseHandler db;
    static int id;
    EditText name,age,address;
    String image,nam,add,se,ag;

    RadioGroup sex;
    Spinner salary,saving;
    RadioButton rb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_activity_layout);

        db = new DatabaseHandler(this);
        sub = (Button)findViewById(R.id.submitButton);
        up =(ImageButton)findViewById(R.id.imageUpload);

        name=(EditText)findViewById(R.id.nameText);


        age=(EditText)findViewById(R.id.ageText);


        address= (EditText)findViewById(R.id.addressText);


        sex =(RadioGroup)findViewById(R.id.radioSex);




        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Csalary, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Ssalary, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);






        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id=0;
                int selectedId = sex.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                nam =name.getText().toString();
                ag = age.getText().toString();
                add= address.getText().toString();
                rb = (RadioButton) findViewById(selectedId);
                se = rb.getText().toString();

                salary  = (Spinner)findViewById(R.id.spinner);
                String sal = salary.getSelectedItem().toString();
                saving  = (Spinner)findViewById(R.id.spinner2);
                String sav = saving.getSelectedItem().toString();

                Log.d("Insert: ", "Inserting ..");
                db.addContact(new Fields(id,image,nam,se,ag,add,sal,sav));


                // Reading all contacts
                Log.d("Reading: ", "Reading all contacts..");
                List<Fields> contacts = db.getAllContacts();

                for (Fields cn : contacts) {
                    String log = "Id: "+cn.getID()+" ,Image: " + cn.getImagepath()+" ,Name: " + cn.getName() + " ,Sex: " + cn.getSex()+" ,Age: " + cn.getAge()+" ,Address: " + cn.getAddress()+" ,Current Salary: " + cn.getSalary()+" ,Saving: " + cn.getSaving();
                    // Writing Contacts to log
                    Log.d("Name: ", log);
                }
            id++;


            }
        });

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);//
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),SELECT_IMAGE);
            }
        });



    }





    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECT_IMAGE)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                if (data != null)
                {
                    try
                    {

                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                        up.setImageBitmap(bitmap);
                        image= bitmap.toString();

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED)
            {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
