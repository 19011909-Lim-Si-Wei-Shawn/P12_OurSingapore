package sg.edu.rp.c346.id19011909.ndpsongs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

public class EditActivity extends AppCompatActivity {

    //Creating Variable,
    EditText ETID;
    EditText ETSongEdit;
    EditText ETSingerEdit;
    EditText ETYearEdit;

    RatingBar RBEdit;

    Button BtnUpdate;
    Button BtnDelete;
    Button BtnCancel;

    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //Linking Variable,
        ETID = findViewById(R.id.ETID);
        ETSongEdit = findViewById(R.id.ETSongEdit);
        ETSingerEdit = findViewById(R.id.ETSingerEdit);
        ETYearEdit = findViewById(R.id.ETYearEdit);

        RBEdit = findViewById(R.id.ratingBarEdit);

        BtnUpdate = findViewById(R.id.BtnUpdate);
        BtnDelete = findViewById(R.id.BtnDelete);
        BtnCancel = findViewById(R.id.BtnCancel);

        //Setting Intent.
        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        ETID.setText(String.valueOf(data.get_id()));
        ETSongEdit.setText(data.getTitle());
        ETSingerEdit.setText(data.getSingers());
        ETYearEdit.setText(String.valueOf(data.getYear()));
        RBEdit.setRating(data.getStars());

        //Setting Action,
        BtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);

                data.setTitle(ETSongEdit.getText().toString());
                data.setSingers(ETSingerEdit.getText().toString());
                data.setYear(Integer.parseInt(ETYearEdit.getText().toString()));
                data.setStars((int)RBEdit.getRating());

                dbh.updateSongs(data);
                dbh.close();

                Intent i = new Intent(EditActivity.this, DisplayActivity.class);
                startActivity(i);
            }
        });

        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Triggering Dialog,
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are You Sure you want to Delete the Island\n" + ETSongEdit.getText().toString());
                myBuilder.setCancelable(false);

                //Configure the 'Positive' Button,
                myBuilder.setPositiveButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                //Configure the 'Negative' Button,
                myBuilder.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DBHelper dbh = new DBHelper(EditActivity.this);
                        dbh.deleteSong(data.get_id());

                        Intent i = new Intent(EditActivity.this, DisplayActivity.class);
                        startActivity(i);
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Triggering Dialog,
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(EditActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are You Sure you want to Discard the Changes");
                myBuilder.setCancelable(false);

                //Configure the 'Positive' Button,
                myBuilder.setPositiveButton("DO NOT DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                //Configure the 'Negative' Button,
                myBuilder.setNegativeButton("DISCARD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent i = new Intent(EditActivity.this, DisplayActivity.class);
                        startActivity(i);
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
}