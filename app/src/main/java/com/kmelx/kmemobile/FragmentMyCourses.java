package com.kmelx.kmemobile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 26/05/16.
 */
public class FragmentMyCourses extends Fragment {
    ListView lista;
    RequestQueue mRequestQueue;
    //private final String url = "http://kmelx.com/api/courses/get/?limit=10";
    // Log tag
    private static final String TAG = FragmentMyCourses.class.getSimpleName();

    // Movies json url
    //private static final String url = "http://api.androidhive.info/json/movies.json";


    private static final String url = "http://kmelx.com/api/courses/get/?limit=10&me=true";

    private List<Course> movieList = new ArrayList<Course>();
    private ListView listView;
    private AdapterCourses adapter;
    public FragmentMyCourses() {

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
        adapter = new AdapterCourses(getActivity(), movieList);
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
                                Course movie = new Course();
                                movie.setTitle(obj.getString("name"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setDescription(obj.getString("description"));

                                movie.setYear(obj.getInt("pk"));


                                // adding movie to movies array
                                movieList.add(movie);

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
    }




}
