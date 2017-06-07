package com.example.tyler.assignmentrestaurant;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab1Menu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1menu, container, false);

        String[] menuItems = { "a", "b", "c", "d"};
        ListView listView = (ListView) rootView.findViewById(R.id.listViewMenu);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, menuItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //// TODO: 7/06/2017 methods here will allow actions upon clicking on a item in the list view
            }
        });

        return rootView;
    }
}
