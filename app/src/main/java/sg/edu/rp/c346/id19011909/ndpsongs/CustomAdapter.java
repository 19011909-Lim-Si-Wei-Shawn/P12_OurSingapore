package sg.edu.rp.c346.id19011909.ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    //Creating Variable,
    Context parent_context;
    int layout_id;
    ArrayList<Song> PositionList;

    //Creating Class,
    public CustomAdapter(Context context, int resource, ArrayList<Song> objects)
    {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        PositionList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Getting LayoutInflater Object,
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Inflating View for Each Row,
        View rowView = inflater.inflate(layout_id, parent, false);

        //Obtaining UI Components with Binding,
        TextView tvTitle = rowView.findViewById(R.id.TitleTV);
        TextView tvSinger = rowView.findViewById(R.id.SingersTV);
        TextView tvYear = rowView.findViewById(R.id.YearTV);
        TextView tvStar = rowView.findViewById(R.id.StarTV);

        //Obtaining Android Version Information using Position,
        Song Current = PositionList.get(position);

        //Setting Value to TextView with Information,
        tvTitle.setText(Current.getTitle());
        tvSinger.setText(Current.getSingers());
        tvYear.setText(String.valueOf(Current.getYear()));
        tvStar.setText(Current.toString());

        return rowView;
    }

}
