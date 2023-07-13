package sg.edu.rp.c346.songl08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SONGedit extends AppCompatActivity {

    TextView tvID;
    EditText et1 ,et2,et3 ,edtID;
    Button btnUpdate, btnDelete,btncancel;

    RadioButton rd1,rd2,rd3,rd4,rd5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songedit);
        tvID=findViewById(R.id.tvID1);
        et1=findViewById(R.id.edit1);
        et2=findViewById(R.id.edit2);
        et3=findViewById(R.id.edit3);
        btncancel=findViewById(R.id.btncancel);
        btnDelete=findViewById(R.id.btndelete);
        btnUpdate=findViewById(R.id.btnupdate);
        rd1=findViewById(R.id.edtrdbt1);
        rd2=findViewById(R.id.edtrdbt2);
        rd3=findViewById(R.id.edtrdbt3);
        rd4=findViewById(R.id.edtrdbt4);
        rd5=findViewById(R.id.edtrdbt5);
        edtID=findViewById(R.id.editID);

        Song data;




        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        String title=data.getTitle();
        String singer=data.getSingers();
        int year= data.getYear();
        int id =data.getId();



        edtID.setText(String.format("%d",id));
        edtID.setEnabled(false);
        et1.setText(data.getTitle());
        et2.setText(singer);
        et3.setText(String.format("%d",year));


        dbHelper db= new dbHelper(SONGedit.this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.setTitle(et1.getText().toString());
                data.setSingers(et2.getText().toString());
                data.setYear(Integer.parseInt(et3.getText().toString()));
                int rating=0;
                if(rd1.isChecked()){ rating=1;}
                else if (rd2.isChecked()) {
                    rating=2;
                }
                else if (rd3.isChecked()) {
                    rating=3;
                }
                else if (rd4.isChecked()) {
                    rating=4;
                }
                else if (rd5.isChecked()) {
                    rating=5;
                }
                data.setStars(rating);
                db.insertSong2(data);
                db.close();
                finish();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteNote(title);
                db.close();
                finish();
            }
        });

       btncancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });










    }

}