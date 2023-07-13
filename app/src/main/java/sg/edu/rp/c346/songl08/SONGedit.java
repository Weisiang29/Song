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
    EditText et1 ,et2,et3;
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
        Song data;




        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");
        tvID.setText(data.get_id());
        et1.setText(data.getTitle());
        et2.setText(data.getSingers());
        et3.setText(data.getYear());
        int star=data.getStars();
        if (star==1){
            rd1.setChecked(true);
        }
        else if (star==2){
            rd2.setChecked(true);
        }
        else if (star==3){
            rd3.setChecked(true);
        }
        else if (star==4){
            rd4.setChecked(true);
        }
        else if (star==5){
            rd5.setChecked(true);
        }











    }

}