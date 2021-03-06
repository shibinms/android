package com.example.linenclub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Bundle;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class login extends AppCompatActivity implements View.OnClickListener {
     Button btn_login, btn_signup;
     EditText ed_name,ed_password;
     boolean isEmailValid, isPasswordValid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_name = (EditText)findViewById(R.id.editText) ;
        ed_password = (EditText)findViewById(R.id.editText2);
        btn_signup = (Button)findViewById(R.id.button8);
        btn_signup.setOnClickListener(this);
        btn_login = (Button)findViewById(R.id.login2);
        btn_login.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        if(view == btn_signup){
            Intent ij=new Intent(getApplicationContext(),signup.class);
            startActivity(ij);
        }
        if(view == btn_login){


        final String uname = ed_name.getText().toString();
        final String pass = ed_password.getText().toString();
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/android_usr_login/";
        SetValidation();
//        Toast.makeText(this,hu, Toast.LENGTH_SHORT).show();



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                          Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                String lid= jsonObj.getString("lid");
                                //text view obj.setText(name)



                                final SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                SharedPreferences.Editor editor=sh.edit();
                                editor.putString("lid",lid);
                                editor.commit();

//                                Toast.makeText(this,lid, Toast.LENGTH_SHORT).show();
                                Intent ij=new Intent(getApplicationContext(),Customer_Home.class);
                                startActivity(ij);

                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "User Doesn't Exist !!", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();


                params.put("username",uname);
                params.put("password",pass);

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=60000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);



    }
    }
    public void SetValidation() {
        // Check for a valid email address.


        if (ed_name.getText().toString().isEmpty()) {
            ed_name.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(ed_name.getText().toString()).matches()) {
            ed_name.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        // Check for a valid password.
        if (ed_password.getText().toString().isEmpty()) {
            ed_password.setError(getResources().getString(R.string.password_error));
            isPasswordValid = false;
        } else if (ed_password.getText().length() < 4) {
            ed_password.setError(getResources().getString(R.string.error_invalid_passwordd));
            isPasswordValid = false;
        } else  {
            isPasswordValid = true;
        }



    }


}
