package com.kmelx.kmemobile.Courses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.kmelx.kmemobile.Myapplication;
import com.kmelx.kmemobile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 26/05/16.
 */
public class FragmentAllCourses extends Fragment {

    ListView lista;
    RequestQueue mRequestQueue;
    //private final String url = "http://kmelx.com/api/courses/get/?limit=10";
    // Log tag
    private static final String TAG = FragmentAllCourses.class.getSimpleName();

    // Movies json url
    //private static final String url = "http://api.androidhive.info/json/movies.json";

    private static final String url = "http://kmelx.com/api/courses/get/?limit=10&public=true";

    private List<Course> courseList = new ArrayList<Course>();
    private ListView listView;
    private AdapterCourses adapter;
    public FragmentAllCourses() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.list_allcourses,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.listView);
        adapter = new AdapterCourses(getActivity(), courseList);
        listView.setAdapter(adapter);


        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Course course = new Course();
                                course.setTitle(obj.getString("name"));
                                course.setThumbnailUrl(obj.getString("image"));
                                course.setDescription(obj.getString("description"));

                                course.setUuid(obj.getString("uuid"));


                                // adding movie to movies array
                                courseList.add(course);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());


            }
        });

        // Adding request to request queue
        Myapplication.getInstance().addToRequestQueue(movieReq);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String datoPulsado = (parent.getAdapter().getItem(position)).toString();
                String datoPulsado = ((TextView) view.findViewById(R.id.uuid)).getText().toString();

                Toast.makeText(getActivity(), "Abriendo contenido", Toast.LENGTH_SHORT).show();
                SharedPreferences sp = getActivity().getSharedPreferences("spPersonalData",0);
                Intent web= new Intent(Intent.ACTION_VIEW, Uri.parse("http://kmelx.com/api/login_and_redirect/?username="+sp.getString("Username","") +"&next=/lms/course/"+datoPulsado+"/simple/&password="+ sp.getString("Password","")+""));
                startActivity(web);
            }
        });

    }







}
