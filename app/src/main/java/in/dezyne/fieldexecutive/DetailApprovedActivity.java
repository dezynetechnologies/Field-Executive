package in.dezyne.fieldexecutive;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Dezyne 2 on 9/13/2016.
 */
public class DetailApprovedActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    Button ok;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_approved_activity_layout);
        ok = (Button) findViewById(R.id.okButton);


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailApprovedActivity.this, PendingActivity.class);
                startActivity(intent);
            }
        });


        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();



        TypedArray placePictures = resources.obtainTypedArray(R.array.imagepath);
        ImageView placePicture = (ImageView) findViewById(R.id.imageUpload);
        placePicture.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));
        placePictures.recycle();

        String[] _name = resources.getStringArray((R.array.name));
        TextView name = (TextView) findViewById(R.id.nameText);
        name.setText(_name[postion % _name.length]);

        String[] _age = resources.getStringArray(R.array.age);
        TextView age = (TextView) findViewById(R.id.ageText);
        age.setText(_age[postion % _age.length]);

        String[] _address = resources.getStringArray(R.array.address);
        TextView address = (TextView) findViewById(R.id.addressText);
        address.setText(_address[postion % _address.length]);

        String[] _csalary = resources.getStringArray(R.array.current_salary);
        TextView csalary = (TextView) findViewById(R.id.spinner);
        csalary.setText(_csalary[postion % _csalary.length]);

        String[] _saving = resources.getStringArray(R.array.saving_target);
        TextView saving = (TextView) findViewById(R.id.spinner2);
        saving.setText(_saving[postion % _saving.length]);

        String[] _sex = resources.getStringArray(R.array.sex);
        RadioGroup sex = (RadioGroup) findViewById(R.id.radioSex);
        if (_sex[postion].equals("Male")) {
            sex.check(R.id.radioButton);
        } else {
            sex.check(R.id.radioButton2);
        }


        String[] _submitdate = resources.getStringArray(R.array.submitdate);
        TextView submitdate = (TextView) findViewById(R.id.submitdate);
        submitdate.setText(_submitdate[postion % _submitdate.length]);

    }
}
