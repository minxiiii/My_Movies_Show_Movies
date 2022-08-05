package sg.edu.rp.c346.id21022186.mymovies_showmovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ModifyMovieActivity extends AppCompatActivity {

    EditText tvTitle, tvGenre, tvYear, ID;
    Spinner spinner;
    Button btnUpdate, btnDel, btnCancel;
    Movies details;
    Object value;
    int rate = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_movie);

        ID = findViewById(R.id.tvId);

        tvTitle = findViewById(R.id.tvTitle);
        tvGenre = findViewById(R.id.tvGenre);
        tvYear = findViewById(R.id.tvYear);
        spinner = findViewById(R.id.spinner);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDel = findViewById(R.id.btnDel);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        details = (Movies) i.getSerializableExtra("details");

        ID.setText(details.getId()+"");
        tvTitle.setText(details.getTitle());
        tvGenre.setText(details.getGenre());
        tvYear.setText(details.getYear()+"");

        if(details.getRating().equals("G")) {
            rate = 0;
        }else if(details.getRating().equals("PG")){
            rate = 1;
        }else if(details.getRating().equals("PG13")){
            rate = 2;
        }else if(details.getRating().equals("NC16")){
            rate = 3;
        }else if(details.getRating().equals("M18")){
            rate = 4;
        }else if(details.getRating().equals("R21")){
            rate = 5;
        }
        spinner.setSelection(rate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyMovieActivity.this);
                details.setTitle(tvTitle.getText().toString());
                details.setGenre(tvGenre.getText().toString());
                details.setYear(Integer.parseInt(tvYear.getText().toString()));
                String item = spinner.getSelectedItem().toString();
                details.setRating(item);

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
                LayoutInflater inflater =
                        (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.danger, null);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovieActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setView(viewDialog);
                myBuilder.setMessage("Are you sure you want to delete the movie?");
                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Intent i = new Intent(ModifyMovieActivity.this,
                                ShowMoviesActivity.class);
                        startActivity(i);
                    }
                });
                myBuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });
/*
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

 */
/*
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ModifyMovieActivity.this,
                        ShowMoviesActivity.class);
                startActivity(i);
            }
        });

 */

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater =
                        (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.danger, null);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovieActivity.this);

                myBuilder.setTitle("Danger");
                myBuilder.setView(viewDialog);
                myBuilder.setMessage("Are you sure you want discard the changes?");
                myBuilder.setPositiveButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Intent i = new Intent(ModifyMovieActivity.this,
                                ShowMoviesActivity.class);
                        startActivity(i);
                    }
                });
                myBuilder.setNegativeButton("DO NOT DISCARD", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });


    }

}