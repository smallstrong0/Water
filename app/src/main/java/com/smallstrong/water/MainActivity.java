package com.smallstrong.water;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.smallstrong.water.app.AppEnvEnum;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText("hello");

        Toast.makeText(this, AppEnvEnum.appEnvEnum+"",Toast.LENGTH_LONG).show();
    }

}
