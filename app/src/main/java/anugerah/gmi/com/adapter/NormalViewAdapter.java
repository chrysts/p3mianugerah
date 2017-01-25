package anugerah.gmi.com.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import anugerah.gmi.com.model.NormalViewModel;
import anugerah.gmi.com.pemuda.R;

/**
 * Created by christian.simon on 29/08/2016.
 */
public class NormalViewAdapter extends ArrayAdapter<NormalViewModel> {

        private static class ViewHolder {
            private TextView itemView;
        }

        public NormalViewAdapter(Context context, int resourceId, ArrayList<NormalViewModel> items) {
            super(context, resourceId, items);
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        NormalViewModel content= getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_normal_list, parent, false);
        }
        // Lookup view for data population
        TextView textTitle = (TextView) convertView.findViewById(R.id.textTitle);
        TextView textVerse = (TextView) convertView.findViewById(R.id.textVerse);
        TextView textContent = (TextView) convertView.findViewById(R.id.textContent);
        TextView textJempol = (TextView) convertView.findViewById(R.id.textJempol);
        ImageButton btnJempol = (ImageButton) convertView.findViewById(R.id.btnJempol);
        btnJempol.setColorFilter(0xffff0000, PorterDuff.Mode.MULTIPLY);
        // Populate the data into the template view using the data object
        textTitle.setText(content.getTitle());
        textVerse.setText(content.getVerse());
        textContent.setText(content.getContent());
        textJempol.setText("x "+ content.getJempol());

        // Return the completed view to render on screen
        return convertView;


    }

}
