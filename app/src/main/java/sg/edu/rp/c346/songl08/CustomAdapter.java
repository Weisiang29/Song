package sg.edu.rp.c346.songl08;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;


public class CustomAdapter  extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song>versionList;


    public CustomAdapter( Context context, int resource,ArrayList<Song>objects) {
        super(context, resource,objects);
        parent_context=context;
        layout_id=resource;
        versionList=objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvFirst = rowView.findViewById(R.id.textSongtitle);
        TextView tvSecond = rowView.findViewById(R.id.textSongsinger);
        TextView tvThird = rowView.findViewById(R.id.textSongyear);
        TextView tvFourth = rowView.findViewById(R.id.textSongrating);

        // Obtain the Android Version information based on the position
        Song item = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        String stars ="";
        for (int x=0;x<item.getStars();x++){
            stars+="*";
        }

        tvFirst.setText(item.getTitle());
        tvSecond.setText(item.getSingers());
        tvThird.setText(String.format("%d",item.getYear()));
        int star= item.getStars();
        String set="";
        if(star==1){
             set+="*";
        }
        else if (star==2){
            set+="**";

        }
        else if (star==3){
            set+="***";

        }
        else if (star==4){
            set+="****";

        }
        else if (star==5){
            set+="*****";

        }
        tvFourth.setText(set);




        return rowView;
    }
}