package sg.edu.rp.c346.songl08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText edt1,edt2,edt3;
    TextView test;
    Button btn1,btn2;
    RadioGroup rdRating;
    RadioButton rating1,rating2,rating3,rating4,rating5;
    ListView SongList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        test=findViewById(R.id.test);
        edt1=findViewById(R.id.edtSong);
        edt2=findViewById(R.id.edtSinger);
        edt3=findViewById(R.id.edtYear);
        btn1=findViewById(R.id.btnINSERT);
        btn2=findViewById(R.id.btnSHOW);
        rdRating=findViewById(R.id.Rating);
        rating1=findViewById(R.id.rdbt1);
        rating2=findViewById(R.id.rdbt2);
        rating3=findViewById(R.id.rdbt3);
        rating4=findViewById(R.id.rdbt4);
        rating5=findViewById(R.id.rdbt5);
        SongList=findViewById(R.id.Songlist);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edit1= edt1.getText().toString();
                String edit2=edt2.getText().toString();
                String edit3 =edt3.getText().toString();
                if(edit1.isEmpty()||edit2.isEmpty()||edit3.isEmpty()){

                    Toast.makeText(MainActivity.this, "Please enter all of the input fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    dbHelper db = new dbHelper(MainActivity.this);
                    String year = edt3.getText().toString();
                    int yearOK = Integer.parseInt(year);
                    int selectedRadioButtonId = rdRating.getCheckedRadioButtonId();
                    int rating =0;
                    if (selectedRadioButtonId==R.id.rdbt1){
                        rating+=1;
                    }
                    else if (selectedRadioButtonId==R.id.rdbt2){
                        rating +=2;
                    }
                    else if (selectedRadioButtonId==R.id.rdbt3){
                        rating +=3;
                    }
                    else if (selectedRadioButtonId==R.id.rdbt4){
                        rating +=4;
                    }
                    else if (selectedRadioButtonId==R.id.rdbt5){
                        rating +=5;
                    }
                    // Insert a task
                    db.insertTask(edt1.getText().toString(),edt2.getText().toString(),yearOK,rating);
                    Toast.makeText(MainActivity.this, "Insert sucessfully", Toast.LENGTH_SHORT).show();
                }






            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    dbHelper db = new dbHelper(MainActivity.this);
                    ArrayList<Song> data = db.getTasks();
                    db.close();
                    int sum=0;
                    for(int x=0;x<data.size();x++){
                        sum+=1;
                    }
                    String[] output = new String[sum];
                    for(int j=0;j<data.size();j++){

                        output[j]=String.format("%-5d %-20s  %-15s %-8d %d   ",j+1,data.get(j).getTitle(),data.get(j).getSingers(),data.get(j).getYear(),data.get(j).getStar());
                    }
                    ArrayAdapter adapter= new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,output);
                    SongList.setAdapter(adapter);





            }
        });


    }
}