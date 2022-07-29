package sg.edu.rp.c346.id21022186.mymovies_showmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ShowMoviesActivity extends AppCompatActivity {

    ArrayList<Movies> al;
    ArrayAdapter<Movies> aa;
    CustomAdapter caMovies;
    ListView lv;
    ToggleButton tbAge;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ShowMoviesActivity.this);
        al.clear();
        al.addAll(dbh.getAllMovies());

        caMovies.notifyDataSetChanged();


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        lv = findViewById(R.id.lv);
        tbAge = findViewById(R.id.tbAge);

        al = new ArrayList<Movies>();
        /*aa = new ArrayAdapter<Movies>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);*/

        caMovies = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(caMovies);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movies details = al.get(position);
                Intent i = new Intent(ShowMoviesActivity.this,
                        ModifyMovieActivity.class);
                i.putExtra("details", details);
                startActivity(i);
            }
        });

        tbAge.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                String filterText = "PG13";

                DBHelper dbh = new DBHelper(ShowMoviesActivity.this);

                al.clear();
                if(checked){
                    al.addAll(dbh.getPG13(filterText));
                }else{
                    al.addAll(dbh.getAllMovies());
                }

                caMovies.notifyDataSetChanged();
            }
        });

    }



}