package sg.edu.rp.c346.id20007386.p09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {


    EditText editTitle, editSingers, editYear;
    RadioGroup radiogroup;
    Button btnUpdate,btnDelete;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    TextView editID;
    Song data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);



        editTitle = findViewById(R.id.editTitle);
        editSingers = findViewById(R.id.editSingers);
        editYear = findViewById(R.id.editYear);
        radiogroup = findViewById(R.id.radiogroup);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        editID = findViewById(R.id.editID);


        Intent intent = getIntent();
        data = (Song) intent.getSerializableExtra("data");
        System.out.println(data);

        editID.setText(String.valueOf(data.get_id()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                if(editTitle.getText().toString().isEmpty() || editSingers.getText().toString().isEmpty() || editYear.getText().toString().isEmpty()){
                    Toast.makeText(EditActivity.this, "Missing input", Toast.LENGTH_SHORT).show();
                }
                else{
                    int selectedId = radiogroup.getCheckedRadioButtonId();
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);
                    int stars = Integer.parseInt(radioButton.getText().toString());

                    data.setTitle(editTitle.getText().toString());
                    data.setSingers(editSingers.getText().toString());
                    data.setYear(Integer.parseInt(editYear.getText().toString()));
                    data.setStars(stars);

                    System.out.println(data);
                    dbh.updateSong(data);
                    dbh.close();
                    Toast.makeText(EditActivity.this, "Successfully inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteNote(data.get_id());
            }
        });
    }
}