package com.mac.training.testai_tx2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEntryActivity extends AppCompatActivity {

    EditText txtName;
    EditText txtContent;
    EditText txtImage;
    EditText txtParentKey;
    EditText txtRank;
    EditText txtKW1;
    EditText txtKW2;
    EditText txtKW3;
    EditText txtKW4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtName      = (EditText) findViewById(R.id.Name);
        txtContent   = (EditText) findViewById(R.id.Content);
        txtImage     = (EditText) findViewById(R.id.Img);
        txtParentKey = (EditText) findViewById(R.id.ParentKey);
        txtRank      = (EditText) findViewById(R.id.Rank);
        txtKW1       = (EditText) findViewById(R.id.KW1);
        txtKW2       = (EditText) findViewById(R.id.KW2);
        txtKW3       = (EditText) findViewById(R.id.KW3);
        txtKW4       = (EditText) findViewById(R.id.KW4);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveThisEntry();
                Snackbar.make(view, "Your Entry was saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                resetAll();
            }
        });
    }

    private void resetAll() {
        txtName.setText("");
        txtContent.setText("");
        txtImage.setText("");
        txtParentKey.setText("");
        txtRank.setText("");
        txtKW1.setText("");
        txtKW2.setText("");
        txtKW3.setText("");
        txtKW4.setText("");
    }

    private void saveThisEntry() {
        DatabaseReference myEntries = FirebaseDatabase.getInstance().getReference("Entries");
        FTEntry mE = populateE();
        String key = myEntries.push().getKey();
        mE.setKey(key);
        myEntries.child(key).setValue(mE);
    }

    private FTEntry populateE() {
        FTEntry iE = new FTEntry();

        iE.setName(txtName.getText().toString());
        iE.setContent(txtContent.getText().toString());
        iE.setImage(txtImage.getText().toString());
        iE.setParentKey(txtParentKey.getText().toString());
        iE.setRank(Integer.valueOf( txtRank.getText().toString()));
        iE.setkW1(txtKW1.getText().toString());
        iE.setkW2(txtKW2.getText().toString());
        iE.setkW3(txtKW3.getText().toString());
        iE.setkW4(txtKW4.getText().toString());

        return iE;
    }
}
