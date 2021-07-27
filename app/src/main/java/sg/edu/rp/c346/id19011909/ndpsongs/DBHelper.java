package sg.edu.rp.c346.id19011909.ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    //Creating Variable,
    private static final String DATABASE_NAME = "NDPSongs.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NOTE = "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_0 = "title";
    private static final String COLUMN_1 = "singers";
    private static final String COLUMN_2 = "year";
    private static final String COLUMN_3 = "stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createSongTableSQL = "CREATE TABLE " + TABLE_NOTE + "("
                                  + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                  + COLUMN_0 + " TEXT, "
                                  + COLUMN_1 + " TEXT, "
                                  + COLUMN_2 + " INTEGER, "
                                  + COLUMN_3 + " INTEGER)";

        db.execSQL(createSongTableSQL);

        Log.i("Info", "Created Tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        onCreate(db);
    }

    //Creating Inserting Function,
    public long insertSong(String title, String singer, int year, int stars)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_0, title);
        values.put(COLUMN_1, singer);
        values.put(COLUMN_2, year);
        values.put(COLUMN_3, stars);

        long result = db.insert(TABLE_NOTE, null, values);
        db.close();
        Log.d("SQL Insert", "ID: " + result);
        return result;

    }

    //Getting All Data,
    public ArrayList<Song> getAllSongs()
    {
        ArrayList<Song> Songs = new ArrayList<Song>();

        String selectedQuery = "SELECT " + COLUMN_ID + ", "
                             + COLUMN_0 + ", "
                             + COLUMN_1 + ", "
                             + COLUMN_2 + ", "
                             + COLUMN_3
                             + " FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectedQuery, null);

        if(cursor.moveToFirst())
        {
            do{
              int id = cursor.getInt(0);
              String title = cursor.getString(1);
              String singers = cursor.getString(2);
              int year = cursor.getInt(3);
              int stars = cursor.getInt(4);

              Song song = new Song(id, title, singers, year, stars);
              Songs.add(song);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return Songs;
    }

    //Getting 5 Stars Song Function,
    public ArrayList<Song> getAllSongs5()
    {
        ArrayList<Song> Songs = new ArrayList<Song>();

        String selectedQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_0 + ", "
                + COLUMN_1 + ", "
                + COLUMN_2 + ", "
                + COLUMN_3
                + " FROM " + TABLE_NOTE + " WHERE " + COLUMN_3 + "= 5";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectedQuery, null);

        if(cursor.moveToFirst())
        {
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song song = new Song(id, title, singers, year, stars);
                Songs.add(song);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return Songs;
    }

    //Getting Date Function,
    public ArrayList<Song> getAllSongsYEAR(int data)
    {
        ArrayList<Song> Songs = new ArrayList<Song>();

        String selectedQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_0 + ", "
                + COLUMN_1 + ", "
                + COLUMN_2 + ", "
                + COLUMN_3
                + " FROM " + TABLE_NOTE + " WHERE " + COLUMN_2 + "== " + data;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectedQuery, null);

        if(cursor.moveToFirst())
        {
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song song = new Song(id, title, singers, year, stars);
                Songs.add(song);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return Songs;
    }

    //Getting Date Function Sort to 5 stars,
    public ArrayList<Song> getAllSongsYEAR5(int data)
    {
        ArrayList<Song> Songs = new ArrayList<Song>();

        String selectedQuery = "SELECT " + COLUMN_ID + ", "
                + COLUMN_0 + ", "
                + COLUMN_1 + ", "
                + COLUMN_2 + ", "
                + COLUMN_3
                + " FROM " + TABLE_NOTE
                + " WHERE " + COLUMN_2 + "= " + data
                + " AND " + COLUMN_3 + "= 5";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectedQuery, null);

        if(cursor.moveToFirst())
        {
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);

                Song song = new Song(id, title, singers, year, stars);
                Songs.add(song);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return Songs;
    }

    //Creating Update Function,
    public int updateSongs(Song data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, data.get_id());
        values.put(COLUMN_0, data.getTitle());
        values.put(COLUMN_1, data.getSingers());
        values.put(COLUMN_2, data.getYear());
        values.put(COLUMN_3, data.getStars());

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.get_id())};

        int results = db.update(TABLE_NOTE, values, condition, args);
        db.close();
        return results;
    }

    //Creating Delete Function,
    public int deleteSong(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};

        int result = db.delete(TABLE_NOTE, condition, args);
        db.close();
        return result;
    }

}
