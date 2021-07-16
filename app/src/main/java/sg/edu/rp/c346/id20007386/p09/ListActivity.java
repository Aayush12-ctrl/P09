package sg.edu.rp.c346.id20007386.p09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    Button btnStar;
    ListView listView;
    ArrayList<Song> songsArray, not5starsArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnStar = findViewById(R.id.btnStar);
        listView = findViewById(R.id.listView);

        songsArray = new ArrayList<Song>();

        DBHelper dbh = new DBHelper(ListActivity.this);
        songsArray = new ArrayList<Song>();
        songsArray.addAll(dbh.getAllSongs());

        for(int i = 0; i < songsArray.size(); i++){
            if(!(songsArray.get(i)==null)){
                songsArray.get(i).setID(i);
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, songsArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song target = songsArray.get(position);
                Intent intent = new Intent(ListActivity.this, EditActivity.class);
                intent.putExtra("data", target);
                startActivity(intent);
            }
        });

        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ListActivity.this);
                songsArray.clear();
                songsArray.addAll(dbh.getAllSongs("5"));
                adapter.notifyDataSetChanged();
            }
        });
    }
}