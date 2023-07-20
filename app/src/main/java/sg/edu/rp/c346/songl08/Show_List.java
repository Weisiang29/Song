package sg.edu.rp.c346.songl08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Show_List extends AppCompatActivity {
    Spinner  showyears;
    Button btn5star;
    ListView ShowList;
    boolean buttonActivated=false;
    CustomAdapter adapterV2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        ShowList=findViewById(R.id.showallsong);
        btn5star=findViewById(R.id.btn5star);
        showyears=findViewById(R.id.spnyear);
        ArrayList<Song> al , spinnerList;

        al = new ArrayList<Song>();
        dbHelper db = new dbHelper(Show_List.this);
        al.addAll(db.getSong());


        ArrayList<Integer> spinneryear = new ArrayList<Integer>();
        spinneryear.clear();
        for (int x = 0; x < al.size(); x++) {
            int year = al.get(x).getYear();
            if (!spinneryear.contains(year)) {
                spinneryear.add(year);
            }
        }

        final boolean[] isUserSelection = { false };

        showyears.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Handle the item selection here
                al.addAll(db.getSong());
                if (isUserSelection[0]) {
                    int selectedYear = spinneryear.get(i);
                    ArrayList<Song> filteredSongs = new ArrayList<>();
                    for (Song song : al) {
                        if (song.getYear() == selectedYear) {
                            filteredSongs.add(song);
                        }
                    }
                    al.clear();
                    al.addAll(filteredSongs);
                    adapterV2.notifyDataSetChanged();
                } else {
                    // Reset the flag for the next user selection
                    isUserSelection[0] = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Handle the case where nothing is selected in the Spinner
                // For example, you can show all songs again by clearing any filters
                if (isUserSelection[0]) {
                    al.clear();
                    al.addAll(db.getSong());
                    adapterV2.notifyDataSetChanged();
                }
            }
        });



        db.close();

        ArrayAdapter<String> bb = new ArrayAdapter(this,android.R.layout.simple_list_item_1,spinneryear );
        showyears.setAdapter(bb);

        adapterV2=new CustomAdapter(this,R.layout.row,al);

        ShowList.setAdapter(adapterV2);


        btn5star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                al.clear();
                al.addAll(db.get5stars());

                buttonActivated=true;
                adapterV2.notifyDataSetChanged();

            }
        });
        ShowList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Intent intent= new Intent(Show_List.this,SONGedit.class);
                if(buttonActivated==true){

                    Song data = al.get(position);
                    Toast.makeText(Show_List.this, data.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent pass = new Intent(Show_List.this,SONGedit.class);
                    pass.putExtra("data",data);
                    startActivity(pass);
                }
                else{

                    Song data = al.get(position);
                    Intent pass = new Intent(Show_List.this,SONGedit.class);
                    pass.putExtra("data", data);
                    startActivity(pass);
                }
                startActivity(intent);
            }
        });








    }

}