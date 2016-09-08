package in.dezyne.fieldexecutive;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dezyne 2 on 9/8/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FieldDB.db";
    public static final String FIELD_TABLE_NAME = "field";
    public static final String FIELD_COLUMN_ID = "id";
    public static final String FIELD_COLUMN_NAME = "name";
    public static final String FIELD_COLUMN_SEX = "sex";
    public static final String FIELD_COLUMN_AGE = "age";
    public static final String FIELD_ADDRESS = "address";
    public static final String FIELD_CURRENT_SALARY = "salary";
    public static final String FIELD_SAVING_STATUS = "saving";
    private HashMap hp;


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table field " +
                        "(id integer primary key, name text,age integer,sex text,address text, salary text,saving text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS field");
        onCreate(db);

    }

    public boolean insertField  (String name, String sex,Integer age, String address, String salary,String saving)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("sex", sex);
        contentValues.put("age", age);
        contentValues.put("address", address);
        contentValues.put("salary", salary);
        contentValues.put("saving",  saving);
        db.insert("field", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from field where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, FIELD_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String sex,Integer age, String address, String salary,String saving)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("sex", sex);
        contentValues.put("age", age);
        contentValues.put("address", address);
        contentValues.put("salary", salary);
        contentValues.put("saving", saving);
        db.update("field", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllCotacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from field", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndex(FIELD_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

}
