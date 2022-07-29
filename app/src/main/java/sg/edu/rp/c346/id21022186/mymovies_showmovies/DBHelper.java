package sg.edu.rp.c346.id21022186.mymovies_showmovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_MOVIES = "movies";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createMoviesTableSql = "CREATE TABLE " + TABLE_MOVIES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT, "
                + COLUMN_GENRE + " TEXT, "
                + COLUMN_YEAR + " TEXT, "
                + COLUMN_RATING + " TEXT ) ";
        db.execSQL(createMoviesTableSql);
        Log.i("info", "created tables");

        //Dummy records, to be inserted when the database is created
        for (int i = 0; i< 4; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, "Data number " + i);
            values.put(COLUMN_GENRE, "Genre " + i);
            values.put(COLUMN_YEAR, "Data Year " + i);
            values.put(COLUMN_RATING, "Data Rating " + i);
            db.insert(TABLE_MOVIES, null, values);
        }
        Log.i("info", "dummy records inserted");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        onCreate(db);
    }

    public long insertMovies(String title, String genre, int year, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);
        long result = db.insert(TABLE_MOVIES, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Movies> getAllMovies() {
        ArrayList<Movies> movies = new ArrayList<Movies>();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE,COLUMN_YEAR, COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIES, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rate = cursor.getString(4);
                Movies movie = new Movies(title, genre, year, rate);
                movies.add(movie);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }
}
