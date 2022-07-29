package sg.edu.rp.c346.id21022186.mymovies_showmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText tvTitle, tvGenre, tvYear;
    Spinner spinner;
    Button btnInsert, btnShow;
    ArrayList<Movies> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        tvGenre = findViewById(R.id.tvGenre);
        tvYear = findViewById(R.id.tvYear);
        spinner = findViewById(R.id.spinner);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = tvTitle.getText().toString();
                String genre = tvGenre.getText().toString();
                int year = Integer.parseInt(tvYear.getText().toString());
                String item = spinner.getSelectedItem().toString();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertMovies(title,genre,year,item);

                if (inserted_id != -1) {
                    al.clear();
                    al.addAll(dbh.getAllMovies());

                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.
                        this,ShowMoviesActivity.class);
                startActivity(intent);


            }
        });
    }
}