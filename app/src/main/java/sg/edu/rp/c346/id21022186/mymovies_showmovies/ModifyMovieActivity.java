package sg.edu.rp.c346.id21022186.mymovies_showmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
        ID.setText(details.getId());
        tvTitle.setText(details.getTitle());
        tvGenre.setText(details.getGenre());
        tvYear.setText(details.getYear());

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
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifyMovieActivity.this);
              dbh.deleteMovie(details.getId());

            }
        });


    }

    protected void onResume() {
        super.onResume();

        btnCancel.performClick();

    }
}