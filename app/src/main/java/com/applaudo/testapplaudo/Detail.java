package com.applaudo.testapplaudo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    TextView myText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_left_row);

        myText = (TextView) findViewById(R.id.description);

        Bundle extras = getIntent().getExtras();
        final Integer dataObject = Integer.valueOf(extras.getString("datos"));

        myText.setText(dataObject);
    }
}
