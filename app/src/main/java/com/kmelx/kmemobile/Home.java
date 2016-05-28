package com.kmelx.kmemobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kmelx.kmemobile.Account.Login;
import com.kmelx.kmemobile.Courses.FragmentAllCourses;
import com.kmelx.kmemobile.Courses.FragmentMyCourses;


public class Home extends AppCompatActivity {
    Fragment allcourses;
    Fragment mycourses;
    FragmentProfiles profile;

    public static final String TAG = Home.class.getSimpleName();


    private static Home mInstance;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        allcourses=new FragmentAllCourses();
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, allcourses, "AllCourses").commit();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.logout:
                SharedPreferences sp = getSharedPreferences("spPersonalData", MODE_PRIVATE);
                sp.edit().clear().commit();
                Intent intento = new Intent (Home.this, Login.class);
                startActivity(intento);

                break;

        }

        return super .onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        System.exit(0);
        super.onBackPressed();
    }
    public void MyCourses(View v){
        mycourses =new FragmentMyCourses();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mycourses, "mycourses").commit();
    }
    public void AllCourses(View v){
        allcourses =new FragmentAllCourses();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, allcourses, "allcourses").commit();
    }
    public void Profile(View v){
        profile =new FragmentProfiles();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, profile, "profile").commit();
    }

}
