package sg.edu.rp.c346.id21022186.mymovies_showmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ShowMoviesActivity extends AppCompatActivity {

    ArrayList<Movies> al;
    ArrayAdapter<Movies> aa;
    ListView lv;
    ToggleButton tbAge;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ShowMoviesActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs());

        aa.notifyDataSetChanged();

        year = findViewById(R.id.yearFilter);

        ArrayAdapter aaYear = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dbh.getAllYears());
        aaYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(aaYear);
        aaYear.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);
    }
}