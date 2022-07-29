package sg.edu.rp.c346.id21022186.mymovies_showmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ModifyMovieActivity extends AppCompatActivity {
    TextView ID;
    EditText tvTitle, tvGenre, tvYear;
    Spinner spinner;
    Button btnUpdate, btnDel, btnCancel;
    Movies details;
    Object value;
    int rate = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_movie);

        ID = findViewById(R.id.ID);

        tvTitle = findViewById(R.id.tvTitle);
        tvGenre = findViewById(R.id.tvGenre);
        tvYear = findViewById(R.id.tvYear);
        spinner = findViewById(R.id.spinner);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDel = findViewById(R.id.btnDel);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        details = (Movies) i.getSerializableExtra("details");

        //ID.setText(details.getId());
        tvTitle.setText(details.getTitle());
        tvGenre.setText(details.getGenre());
        tvYear.setText(details.getYear()+"");


        /*spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                *//*if(details.getRating() == "G") {
                    rate = 0;
                }else if(details.getRating() == "PG"){
                    rate = 1;
                }else if(details.getRating() == "PG13"){
                    rate = 2;
                }else if(details.getRating() == "NC16"){
                    rate = 3;
                }else if(details.getRating() == "M18"){
                    rate = 4;
                }else if(details.getRating() == "R21"){
                    rate = 5;
                }*//*
                value = adapterView.getItemAtPosition(i);
                switch (i){
                    case 0:
                        rate = 0;
                        break;

                    case 1:
                        rate = 1;
                        break;

                    case 2:
                        rate = 2;
                        break;

                    case 3:
                        rate = 3;
                        break;

                    case 4:
                        rate = 4;
                        break;

                    case 5:
                        rate = 5;
                        break;
                }
                spinner.setSelection(rate);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        //spinner.setOnItemSelectedListener(details.getRating());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyMovieActivity.this);
                details.setTitle(tvTitle.getText().toString());
                details.setGenre(tvGenre.getText().toString());
                details.setYear(Integer.parseInt(tvYear.getText().toString()));

               dbh.updateMovie(details);
                dbh.close();
                Intent i = new Intent(ModifyMovieActivity.this,
                        ShowMoviesActivity.class);
                startActivity(i);
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifyMovieActivity.this);
              dbh.deleteMovie(details.getId());
                Intent i = new Intent(ModifyMovieActivity.this,
                        ShowMoviesActivity.class);
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ModifyMovieActivity.this,
                        ShowMoviesActivity.class);
                startActivity(i);

            }
        });


    }

}