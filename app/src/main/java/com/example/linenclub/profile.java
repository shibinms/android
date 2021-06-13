package com.example.linenclub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class profile extends AppCompatActivity implements View.OnClickListener {
    TextView name,gender,place,post,pin,district,state,email,phone;
    ImageView img;
    Button btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name = (TextView)findViewById(R.id.textView33);
        gender = (TextView)findViewById(R.id.textView34);
        place = (TextView)findViewById(R.id.textView35);
        post = (TextView)findViewById(R.id.textView36);
        pin = (TextView)findViewById(R.id.textView37);
        district = (TextView)findViewById(R.id.textView38);
        state = (TextView)findViewById(R.id.textView39);
        email = (TextView)findViewById(R.id.textView40);
        phone = (TextView)findViewById(R.id.textView41);
        img = (ImageView)findViewById(R.id.imageView3) ;
        btn_edit = (Button)findViewById(R.id.button5);
        btn_edit.setOnClickListener(this);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/android_view_profile/";
//        Toast.makeText(this,hu, Toast.LENGTH_SHORT).show();



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);

                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                String name1= jsonObj.getString("name");
                                String gender1= jsonObj.getString("gender");
                                String place1= jsonObj.getString("place");
                                String post1= jsonObj.getString("post");
                                String pin1= jsonObj.getString("pin");
                                String district1= jsonObj.getString("district");
                                String state1= jsonObj.getString("state");
                                String email1= jsonObj.getString("email");
                                String phone1= jsonObj.getString("phone");
                                String profile1= jsonObj.getString("image");



                                //text view obj.setText(name)
                                name.setText(name1);
                                gender.setText(gender1);
                                place.setText(place1);
                                post.setText(post1);
                                pin.setText(pin1);
                                district.setText(district1);
                                state.setText(state1);
                                email.setText(email1);
                                phone.setText(phone1);

                                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ss=sh.getString("ip", "");
                                String url = "http://" + ss + ":8000"+profile1;
//                                Toast.makeText(getApplicationContext(), "tstid ass="+url, Toast.LENGTH_LONG).show();

                                Picasso.with(getApplicationContext()).load(url).into(img);




                                SharedPreferences.Editor editor=sh.edit();
                                editor.putString("name1",name1);
                                editor.putString("gender1",gender1);
                                editor.putString("place1",place1);
                                editor.putString("post1",post1);
                                editor.putString("pin1",pin1);
                                editor.putString("district1",district1);
                                editor.putString("state1",state1);
                                editor.putString("profile1",profile1);

                                editor.commit();

//                                Toast.makeText(this,lid, Toast.LENGTH_SHORT).show();
//                                Intent ij=new Intent(getApplicationContext(),view_profile.class);
//                                startActivity(ij);

                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
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


                params.put("lid",sh.getString("lid",""));


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

    @Override
    public void onClick(View view) {
        Intent ij=new Intent(getApplicationContext(),view_profile.class);
        startActivity(ij);
    }
}
