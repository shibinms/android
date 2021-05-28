package com.example.linenclub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


public class view_profile extends AppCompatActivity implements View.OnClickListener {
    EditText ed_name,ed_gender,ed_place,ed_post,ed_pin,ed_district,ed_state;
    RadioButton r1,r2;
    ImageButton btn_image;
    Button btn_update;
    String gen = ";";
    String path, atype, fname, attach, attatch1;
    byte[] byteArray = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        ed_name = (EditText)findViewById(R.id.editText5);
        ed_place = (EditText)findViewById(R.id.editText7);
        ed_post = (EditText)findViewById(R.id.editText8);
        ed_pin = (EditText)findViewById(R.id.editText9);
        ed_district = (EditText)findViewById(R.id.editText11);
        ed_state = (EditText)findViewById(R.id.editText12);
        btn_image = (ImageButton)findViewById(R.id.imageButton);
        r1 = (RadioButton)findViewById(R.id.radioButton);
        r2 = (RadioButton)findViewById(R.id.radioButton2);
        btn_update = (Button)findViewById(R.id.button3);
        btn_image.setOnClickListener(this);



        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        ed_name.setText(sh.getString("name1",""));
        ed_place.setText(sh.getString("place1",""));
        ed_post.setText(sh.getString("post1",""));
        ed_pin.setText(sh.getString("pin1",""));
        ed_district.setText(sh.getString("district1",""));
        ed_state.setText(sh.getString("state1",""));
        gen = sh.getString("gender1","");
        if (gen.equalsIgnoreCase("Male")){
            r1.setChecked(true);
        }else {
            r2.setChecked(true);
        }



        btn_update.setOnClickListener(this);
    }
    void showfilechooser(int string) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //getting all types of files

        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), string);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the M
            Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                ////
                Uri uri = data.getData();

                try {
                    path = FileUtils.getPath(this, uri);

                    File fil = new File(path);
                    float fln = (float) (fil.length() / 1024);
                    atype = path.substring(path.lastIndexOf(".") + 1);


                    fname = path.substring(path.lastIndexOf("/") + 1);
                }
                catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                try {

                    File imgFile = new File(path);

                    if (imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                        img.setImageBitmap(myBitmap);

                    }
                    File file = new File(path);
                    byte[] b = new byte[8192];
                    Log.d("bytes read", "bytes read");

                    InputStream inputStream = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    int bytesRead = 0;

                    while ((bytesRead = inputStream.read(b)) != -1) {
                        bos.write(b, 0, bytesRead);
                    }
                    byteArray = bos.toByteArray();

                    String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                    attach = str;


                } catch (Exception e) {
                    Toast.makeText(this, "String :" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }}}
            String gen22 = "";



    @Override
    public void onClick(View view) {
        if(view == btn_image){
            showfilechooser(1);

        }else{
            if (r2.isChecked()==true){
                gen22 = "Female";
            }else {
                gen22 = "Male";
            }
        final String name = ed_name.getText().toString();
        final String place = ed_place.getText().toString();
        final String post = ed_post.getText().toString();
        final String pin = ed_pin.getText().toString();
        final String district = ed_district.getText().toString();
        final String state = ed_state.getText().toString();
        final String gender = r1.getText().toString();
//        final String image = btn_image.getText().toString();
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/android_update_profile/";
        Toast.makeText(this,hu, Toast.LENGTH_SHORT).show();



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {

                                Toast.makeText(getApplicationContext(), "Updated Successfully", Toast.LENGTH_SHORT).show();

//                                Toast.makeText(this,lid, Toast.LENGTH_SHORT).show();
                                Intent ij=new Intent(getApplicationContext(),Customer_Home.class);
                                startActivity(ij);


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


                params.put("name",name);
                params.put("place",place);
                params.put("post",post);
                params.put("pin",pin);
                params.put("district",district);
                params.put("state",state);
                params.put("gender",gen22);
                params.put("image",attach);
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
}}
