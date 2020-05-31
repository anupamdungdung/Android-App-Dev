package com.app.firebase;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class TodoFragment extends Fragment {
    EditText todo;
    Button add;
    ListView list;

    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;//It helps in filling in item lists

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_todo,container,false);
        todo=(EditText)v.findViewById(R.id.todo);
        add=(Button)v.findViewById(R.id.add);
        list=(ListView)v.findViewById(R.id.listView);

        items= FileHelperToDo.readData(getContext());
        adapter=new ArrayAdapter<String>(getContext(),R.layout.list_view_layout,items);
        list.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemEntered=todo.getText().toString();
                if(itemEntered.isEmpty()){
                    Toast.makeText(getContext(), "Please Enter a Task", Toast.LENGTH_SHORT).show();
                }
                else {
                    adapter.add(itemEntered);
                    todo.setText("");
                    FileHelperToDo.writeData(items,getContext());
                }
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                items.remove(position);
                adapter.notifyDataSetChanged();
                FileHelperToDo.writeData(items, getContext());
                return true;
            }
        });
        return v;
    }
}
