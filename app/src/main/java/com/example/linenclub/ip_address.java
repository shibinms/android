package com.example.linenclub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ip_address extends AppCompatActivity implements View.OnClickListener {
    EditText ed_ip;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_address);
        ed_ip = (EditText)findViewById(R.id.editText3);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String ip_addrs = ed_ip.getText().toString();
        final SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor=sh.edit();
        editor.putString("ip",ip_addrs);
        editor.commit();

        Toast.makeText(this,"Hosting..",Toast.LENGTH_SHORT).show();

        Intent ij=new Intent(getApplicationContext(),login.class);
        startActivity(ij);

    }
}


