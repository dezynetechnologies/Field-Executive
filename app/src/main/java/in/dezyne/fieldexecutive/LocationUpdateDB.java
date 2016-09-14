package in.dezyne.fieldexecutive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class LocationUpdateDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "LocationManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "Location";

    private static final String KEY_LOCATION = "location";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_USERID = "userid";

    LocationGetSet lgs;
    SQLiteDatabase db1;

    public LocationUpdateDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db1 = getWritableDatabase();
        db1.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db1);
        Log.v("DatabaseHandler",db1.findEditTable("Location"));
    }

    @Override
    public void onCreate(SQLiteDatabase db1) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS
                + "("
                + KEY_USERID +" TEXT,"
                + KEY_TIMESTAMP + " TEXT NOT NULL,"
                + KEY_LOCATION + "TEXT "
                +  ")";
        db1.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        Log.v("DatabaseHandler","onCreate Called().");
        db1.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db1);
    }

    // Adding new contact
    void addContact(LocationGetSet Location) {

        SQLiteDatabase db1 = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_USERID,Location.getuserid());
        values.put(KEY_TIMESTAMP,Location.gettimestamp());
        values.put(KEY_LOCATION, Location.getlocation()); // location

        // Inserting Row
        db1.insert(TABLE_CONTACTS, null, values);
        String sQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        Cursor cursor = db1.rawQuery(sQuery,null);
        int g=cursor.getCount();
        Log.v("LocationDB:count",Integer.toString(g));
        db1.close(); // Closing database connection

    }

    // Getting All Contacts
    public List<LocationGetSet> getAllContacts() {
        List<LocationGetSet> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db1 = this.getWritableDatabase();
        Cursor cursor = db1.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LocationGetSet Location = new LocationGetSet();
                Location.setuserid(cursor.getString(0));
                Location.settimestamp(cursor.getString(1));
                Location.setlocation(cursor.getString(2));

                // Adding contact to list
                contactList.add(Location);
            } while (cursor.moveToNext());
        }

        cursor.close();


        // return contact list
        return contactList;

    }
    LocationGetSet getContact(int id) {
        SQLiteDatabase db1 = this.getReadableDatabase();

        Cursor cursor = db1.query(TABLE_CONTACTS, new String[] { KEY_USERID,
                        KEY_TIMESTAMP,
                        KEY_LOCATION,
                        },
                KEY_USERID + "=?",new String[] { "Pending" }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        int g=cursor.getCount();
        Log.v("DatabaseHandler:count",Integer.toString(g));

        lgs = new LocationGetSet(cursor.getString(0), cursor.getString(1),cursor.getString(2));

        String log = "Id: "+lgs.getuserid()+" ,Timestamp: " + lgs.gettimestamp()+" ,Location: " + lgs.getlocation();
        Log.d("Name: ", log);
        cursor.close();
        // return contact
        return lgs;
    }
}
