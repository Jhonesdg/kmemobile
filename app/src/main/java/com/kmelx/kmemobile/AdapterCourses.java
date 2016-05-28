package com.kmelx.kmemobile;



import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class AdapterCourses extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Course> movieItems;
    ImageLoader imageLoader = Myapplication.getInstance().getImageLoader();

    public AdapterCourses(Activity activity, List<Course> movieItems) {
        Log.d("RESPUESTA",activity.toString());
        Log.d("RESPUESTA", String.valueOf(movieItems));

        this.activity = activity;
        this.movieItems = movieItems;
    }


    @Override
    public int getCount() {
        return movieItems.size();
    }

    @Override
    public Object getItem(int location) {
        return movieItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.all_courses, null);

        if (imageLoader == null)
            imageLoader = Myapplication.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.id_image_courses);
        TextView title = (TextView) convertView.findViewById(R.id.courses_name);
        TextView detail = (TextView) convertView.findViewById(R.id.courses_detail);


        // getting movie data for the row
        Course m = movieItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);

        // title
        String sTitle = String.valueOf(m.getTitle());
        String sSubTitle ="Algo";
        if (sTitle.length()>25){
            sSubTitle = sTitle.substring(0,25)+"...";

        }else{
            sSubTitle = sTitle;
        }
        title.setText(sSubTitle);
        String sCadena = String.valueOf(m.getDescription());
        String sSubCadena = "Algomas";
        if (sCadena.length()>100){
            sSubCadena = sCadena.substring(0,100) +"...";

        }else{
            sSubCadena = sCadena;
        }
        // rating
        detail.setText(sSubCadena);

        // genre

        // release year


        return convertView;
    }

}
/**
 * Created by jonathan on 26/05/16.
 */

