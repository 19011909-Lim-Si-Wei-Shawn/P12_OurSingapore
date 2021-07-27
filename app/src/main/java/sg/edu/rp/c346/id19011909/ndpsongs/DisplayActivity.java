package sg.edu.rp.c346.id19011909.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    //Creating Variable,
    Button BtnShow5;

    Spinner SPNOption;

    ArrayList<Song> List;
    CustomAdapter ArrayList;
    //ArrayAdapter<Song> ArrayAdapterList;
    ListView LVDisplay;

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(DisplayActivity.this);

        List.clear();
        List.addAll(dbh.getAllSongs());
        ArrayList = new CustomAdapter(this, R.layout.row, List);

        LVDisplay.setAdapter(ArrayList);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        final Boolean[] Checker = {false};

        //Linking Variable,
        BtnShow5 = findViewById(R.id.BtnShow5);
        LVDisplay = findViewById(R.id.LVDisplay);
        SPNOption = findViewById(R.id.SPNOption);

        DBHelper dbh = new DBHelper(DisplayActivity.this);

        //Setting ArrayList to ListView,
        List = new ArrayList<Song>();
        List.addAll(dbh.getAllSongs());
        ArrayList = new CustomAdapter(this, R.layout.row, List);

        LVDisplay.setAdapter(ArrayList);

        LVDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = List.get(position);

                Intent i = new Intent(DisplayActivity.this, EditActivity.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        //Setting Action,
        BtnShow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List.clear();

                if(Checker[0])
                {
                    List.addAll(dbh.getAllSongs5());
                    Checker[0] = false;
                }

                else
                {
                    List.addAll(dbh.getAllSongs());
                    Checker[0] = true;
                }

                ArrayList.notifyDataSetChanged();
            }
        });

        SPNOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                List.clear();

                if(SPNOption.getSelectedItem().equals("1984"))
                {
                    List.addAll(dbh.getAllSongsYEAR(1984));
                }

                else if(SPNOption.getSelectedItem().equals("1999"))
                {
                    List.addAll(dbh.getAllSongsYEAR(1999));
                }

                else if(SPNOption.getSelectedItem().equals("2007"))
                {
                    List.addAll(dbh.getAllSongsYEAR(2007));
                }

                else if(SPNOption.getSelectedItem().equals("2021"))
                {
                  List.addAll(dbh.getAllSongsYEAR(2021));
                }

                else
                {
                    List.addAll(dbh.getAllSongs());     
                }

                ArrayList.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}