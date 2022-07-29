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
        al.addAll(dbh.getAllMovies());

        aa.notifyDataSetChanged();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        lv = findViewById(R.id.lv);

        al = new ArrayList<Movies>();
        aa = new ArrayAdapter<Movies>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);
    }
}