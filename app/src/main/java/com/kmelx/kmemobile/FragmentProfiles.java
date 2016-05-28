package com.kmelx.kmemobile;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jonathan on 28/05/16.
 */
public class FragmentProfiles extends Fragment {
    public FragmentProfiles(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vistaFragmento = inflater.inflate(R.layout.fragment_profile, container,false);
        return vistaFragmento;
    }
}

