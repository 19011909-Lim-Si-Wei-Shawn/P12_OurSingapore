package sg.edu.rp.c346.id19011909.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Creating Variable,
    EditText ETSong;
    EditText ETSinger;
    EditText ETYear;

    RadioGroup RBGroup;

    Button BtnInsert;
    Button BtnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking Variable,
        ETSong = findViewById(R.id.ETSong);
        ETSinger = findViewById(R.id.ETSinger);
        ETYear = findViewById(R.id.ETYear);

        RBGroup = findViewById(R.id.RBGroup);

        BtnInsert = findViewById(R.id.BtnInsert);
        BtnShow = findViewById(R.id.BtnShow);

        //Setting Action,
        BtnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data0 = ETSong.getText().toString();
                String data1 = ETSinger.getText().toString();
                int data2 = Integer.parseInt(ETYear.getText().toString());
                int data3 = 0;

                //Getting RadioCheck Result,
                int RadioChecker = RBGroup.getCheckedRadioButtonId();
                if(RadioChecker == R.id.RB1)
                {   data3 = 1;      }

                else if(RadioChecker == R.id.RB2)
                {   data3 = 2;      }

                else if(RadioChecker == R.id.RB3)
                {   data3 = 3;      }

                else if(RadioChecker == R.id.RB4)
                {   data3 = 4;      }

                else
                {   data3 = 5;      }

                //Clearing Fields,
                ETSong.setText("");
                ETSinger.setText("");
                ETYear.setText("");

                DBHelper dbh = new DBHelper(MainActivity.this);
                long dataInsert = dbh.insertSong(data0, data1, data2, data3);

                Log.i("Insert", String.valueOf(dataInsert));

                if(dataInsert != -1) {
                    Toast.makeText(MainActivity.this, "Insert Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DisplayActivity.class);
                startActivity(i);
            }
        });

    }

}