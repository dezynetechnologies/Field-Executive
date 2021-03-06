package in.dezyne.fieldexecutive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "fieldsManager";

    // Contacts table name
    public static final String TABLE_CONTACTS = "fields";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGEPATH = "imagepath";
    private static final String KEY_NAME = "name";
    private static final String KEY_SEX = "sex";
    private static final String KEY_AGE = "age";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_SALARY = "salary";
    private static final String KEY_SAVING = "saving";
    private static final String KEY_STATUS = "status";
    private static final String KEY_SUBMITDATE = "submitdate";


    // Contacts table name
    private static final String TABLE_LOCATION = "Location";

    private static final String KEY_USERID = "userid";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_LOCATION = "location";
    private static final String TAG = "Database" ;


    Fields fields;
     SQLiteDatabase db;




    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        onCreate(db);
        Log.v("DatabaseHandler", SQLiteDatabase.findEditTable("fields"));  /*SQLiteDatabase is replacement for db here*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS
                + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_IMAGEPATH +" TEXT,"
                + KEY_NAME + " TEXT NOT NULL,"
                + KEY_SEX + " TEXT,"
                + KEY_AGE + " TEXT,"
                + KEY_ADDRESS+" TEXT,"
                + KEY_SALARY+" TEXT,"
                + KEY_SAVING+ " TEXT,"
                + KEY_STATUS+ " TEXT,"
                + KEY_SUBMITDATE+ " TEXT"
                +  ")";
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        Log.v("DatabaseHandler","onCreate Called().");
        db.execSQL(CREATE_CONTACTS_TABLE);




        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_LOCATION
                + "("
                + KEY_USERID + " TEXT ,"
                + KEY_TIMESTAMP +" TEXT PRIMARY KEY,"
                + KEY_LOCATION + " TEXT"
                +  ")";
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        Log.v("DatabaseHandler","onCreate Called().");
        db.execSQL(CREATE_LOCATION_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    void addContact(Fields fields) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID,fields.getID());
        values.put(KEY_IMAGEPATH,fields.getImagepath());
        values.put(KEY_NAME, fields.getName()); // Contact Name
        values.put(KEY_SEX, fields.getSex());
        values.put(KEY_AGE, fields.getAge());
        values.put(KEY_ADDRESS, fields.getAddress());
        values.put(KEY_SALARY, fields.getSalary());
        values.put(KEY_SAVING, fields.getSaving());
        values.put(KEY_STATUS, fields.getStatus());
        values.put(KEY_SUBMITDATE, fields.getSubmitdate());
        // Inserting Row
       db.insert(TABLE_CONTACTS, null, values);
        String sQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(sQuery,null);
        int g=cursor.getCount();
        Log.v("DatabaseHandler:count",Integer.toString(g));
        db.close(); // Closing database connection


    }

    void addLocation(Fields location){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USERID,location.getuserid());
        values.put(KEY_TIMESTAMP,location.gettimestamp());
        values.put(KEY_LOCATION, location.getlocation()); // Contact Name

        db.insert(TABLE_LOCATION, null, values);
        String sQuery = "SELECT  * FROM " + TABLE_LOCATION;
        Cursor cursor = db.rawQuery(sQuery,null);
        int g=cursor.getCount();
        Log.v("DatabaseHandler:count",Integer.toString(g));
        db.close();


    }

    Fields getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_IMAGEPATH,
                        KEY_NAME,
                        KEY_SEX,
                        KEY_AGE,
                        KEY_ADDRESS,
                        KEY_SALARY,
                        KEY_SAVING,
                        KEY_STATUS,
                        KEY_SUBMITDATE },
                        KEY_STATUS + "=?",new String[] { "Pending" }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        int g=cursor.getCount();
        Log.v("DatabaseHandler:count",Integer.toString(g));

         fields = new Fields(Integer.parseInt(cursor.getString(0)),cursor.getString(1), cursor.getString(2),cursor.getString(3),
                cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9));

        String log = "Id: "+fields.getID()+" ,Image: " + fields.getImagepath()+" ,Name: " + fields.getName() + " ,Sex: " + fields.getSex()+" ,Age: " + fields.getAge()+" ,Address: " + fields.getAddress()+" ,Current Salary: " + fields.getSalary()+" ,Saving: " + fields.getSaving()+ " ,Status: " + fields.getStatus()+ " ,SubmitDate: " + fields.getSubmitdate();
        Log.d("Name: ", log);
        cursor.close();
        // return contact
        return fields;
    }

    // Getting All Contacts
    public List<Fields> getAllContacts() {
        List<Fields> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Fields fields = new Fields();
                fields.setID(Integer.parseInt(cursor.getString(0)));
                fields.setImagepath(cursor.getString(1));
                fields.setName(cursor.getString(2));
                fields.setSex(cursor.getString(3));
                fields.setAge(cursor.getString(4));
                fields.setAddress(cursor.getString(5));
                fields.setSalary(cursor.getString(6));
                fields.setSaving(cursor.getString(7));
                fields.setStatus(cursor.getString(8));
                fields.setSubmitdate(cursor.getString(9));
                // Adding contact to list
                contactList.add(fields);
            } while (cursor.moveToNext());
        }

            cursor.close();


        // return contact list
        return contactList;

    }

    // Getting All Contacts
    public List<Fields> getAllLocation()
    {
        List<Fields> locationList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_LOCATION;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Fields location = new Fields();
                location.setuserid((cursor.getString(0)));
                location.settimestamp(cursor.getString(1));
                location.setlocation(cursor.getString(2));
                locationList.add(location);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return locationList;
    }






    // Updating single contact
    public int updateContact(Fields fields) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ID,fields.getID());
        values.put(KEY_IMAGEPATH,fields.getImagepath());
        values.put(KEY_NAME, fields.getName()); // Contact Name
        values.put(KEY_SEX, fields.getSex()); // Contact Sex
        values.put(KEY_AGE, fields.getAge());
        values.put(KEY_ADDRESS, fields.getAddress());
        values.put(KEY_SALARY, fields.getSalary());
        values.put(KEY_SAVING, fields.getSaving());
        values.put(KEY_STATUS, fields.getStatus());
        values.put(KEY_SUBMITDATE, fields.getSubmitdate());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(fields.getID()) });
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }



}
