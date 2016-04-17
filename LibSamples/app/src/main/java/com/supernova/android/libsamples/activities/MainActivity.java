package com.supernova.android.libsamples.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.supernova.android.floatlabeledittext.FloatLabelEditText;
import com.supernova.android.libsamples.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                FloatLabelEditText editText = (FloatLabelEditText) findViewById(R.id.float_label_edit_text_2);
                if (editText.isErrorEnabled()) {
                    editText.hideError();
                } else {
                    editText.showError("Error");
                }
            }
        });

        FloatLabelEditText editText = (FloatLabelEditText) findViewById(R.id.float_label_edit_text_2);
        editText.setHint("TESTE");

    }

}
