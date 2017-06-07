package com.example.tyler.assignmentrestaurant;

/**
 * Created by Tyler's PC on 7/06/2017.
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Tab3Settings extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3settings, container, false);
        Spinner spinner = (Spinner) rootView.findViewById(R.id.spinnerColor);
        String[] categories = {"White", "Red", "Grey", "Blue", "Green", "Yellow"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                switch(position){
                    case 0:
                        getView().getRootView().setBackgroundColor(Color.WHITE);
                        break;
                    case 1:
                        getView().getRootView().setBackgroundColor(Color.RED);
                        break;
                    case 2:
                        getView().getRootView().setBackgroundColor(Color.GRAY);
                        break;
                    case 3:
                        getView().getRootView().setBackgroundColor(Color.BLUE);
                        break;
                    case 4:
                        getView().getRootView().setBackgroundColor(Color.GREEN);
                        break;
                    case 5:
                        getView().getRootView().setBackgroundColor(Color.YELLOW);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        return rootView;
    }



}
