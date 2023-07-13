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
        for (int x = 0; x < al.size(); x++) {
            int year = al.get(x).getYear();
            if (!spinneryear.contains(year)) {
                spinneryear.add(year);
            }
        }




        db.close();

        ArrayAdapter<String> bb = new ArrayAdapter(this,android.R.layout.simple_list_item_1,spinneryear );
        showyears.setAdapter(bb);

        ArrayAdapter  aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);

        ShowList.setAdapter(aa);


        btn5star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                al.clear();
                al.addAll(db.get5stars());
                aa.notifyDataSetChanged();
                buttonActivated=true;

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