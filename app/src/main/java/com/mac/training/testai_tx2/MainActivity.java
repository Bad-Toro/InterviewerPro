package com.mac.training.testai_tx2;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseReference myEntriesDB;

    RecyclerView rv;

    List<FTEntry> myEntries = new ArrayList<>();
    List<FTEntry> useEntries = new ArrayList<>();

    TextView theText;
    EditText mET;
    ImageButton imgBtt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        theText = (TextView) findViewById(R.id.theText);
        mET = (EditText) findViewById(R.id.txtSearch);
        imgBtt = (ImageButton) findViewById(R.id.imgBtt);

        mET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length()!=0){
                    imgBtt.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
                    doSearch(s.toString());
                }else{
                    imgBtt.setImageResource(android.R.drawable.ic_menu_search);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        rv = (RecyclerView) findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);


        reloadData();

    }

    private void reloadData() {
        myEntries.clear();
        myEntriesDB = FirebaseDatabase.getInstance().getReference("Entries");

        myEntriesDB.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot mySnapshot: dataSnapshot.getChildren()) {
                    FTEntry tmpEntry = mySnapshot.getValue(FTEntry.class);
                    myEntries.add(tmpEntry);
                    Log.d("FTU Tag  ", tmpEntry.getName() + " was added");
                }
                filterData("np");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("FTU Tag  ", "loadPost:onCancelled", databaseError.toException());
            }
        });

    }

    private void setAdapter() {
        EntryAdapter adapter = new EntryAdapter(useEntries);
        rv.setAdapter(adapter);
    }

    public void onAdd(View view) {


        Intent i = new Intent(this, AddEntryActivity.class);
        startActivity(i);

    }


    public void onHome(View view) {

        filterData("np");
        theText.setText("");
    }

    public void onDo2(View view) {

        filterData("-KVzTr_5bC0X6ji7JYyG");
    }

    private void filterData(String np) {
        useEntries.clear();

        for (FTEntry mE: myEntries){
            if (mE.getParentKey().equals(np)){
                useEntries.add(mE);
            }
        }
        setAdapter();
    }

    public void onCk(View view) {
        TextView tv = (TextView) view;
        String tmp = tv.getContentDescription().toString();
        Log.d("FTU Tag", "The Key: " + tmp );
        filterData(tmp);
        theText.setText(getTheText(tmp));
    }

    private String getTheText(String tmp) {
        for (FTEntry mE: myEntries){
            if (mE.getKey().equals(tmp)){
                return mE.getContent();
            }
        }
        return "";
    }



    public void onSearch(View view) {

        String tmp = mET.getText().toString();

        if(tmp.length() == 0){
            
        }

        doSearch(tmp);

    }

    private void doSearch(String tmp) {
        useEntries.clear();

        for (FTEntry mE: myEntries){
            if (mE.getName().toLowerCase().contains(tmp.toLowerCase()) ||
                    mE.getkW1().toLowerCase().contains(tmp.toLowerCase())
                    ){
                useEntries.add(mE);
            }
        }
        setAdapter();
    }
}
