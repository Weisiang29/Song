package sg.edu.rp.c346.songl08;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class dbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "Song.db";

    private static final String TABLE_SONG = "Songs";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";


    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_SINGERS + " TEXT, "
                + COLUMN_YEAR + " INTEGER, "
                + COLUMN_STARS + " INTEGER)";
                 db.execSQL(createTableSql);
                 Log.i("info" ,"created tables");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONG);
        // Create table(s) again
        onCreate(db);

    }

    public void insertTask(String title, String singer,int year,int stars){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TITLE, title);
        // Store the column name as key and the date as value
        values.put(COLUMN_SINGERS, singer);

        values.put(COLUMN_YEAR, year);
        // Store the column name as key and the date as value
        values.put(COLUMN_STARS, stars);



        // Insert the row into the TABLE_TASK
        db.insert(TABLE_SONG, null, values);
        // Close the database connection
        db.close();

    }
    public ArrayList<Song> getSong() {
        ArrayList<Song> tasks = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS,COLUMN_YEAR,COLUMN_STARS};
         Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SONG, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year=cursor.getInt(3);
                int star=cursor.getInt(4);
                Song obj = new Song( title, singer,year,star);
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }
    public ArrayList<Song> get5stars() {
        ArrayList<Song> tasks = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS,COLUMN_YEAR,COLUMN_STARS};
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SONG, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year=cursor.getInt(3);
                int star=cursor.getInt(4);
                Song obj = new Song( title, singer,year,star);
                if (star==5){
                    tasks.add(obj);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tasks;
    }
    public int updateNote(Song data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,data.getTitle());
        values.put(COLUMN_SINGERS,data.getSingers());
        values.put(COLUMN_YEAR,data.getYear());
        values.put(COLUMN_STARS,data.getStars());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.get_id())};
        int result = db.update(TABLE_SONG, values, condition, args);
        db.close();
        return result;
    }
    public int deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }
}
