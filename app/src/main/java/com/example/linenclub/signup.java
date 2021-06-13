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
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class signup extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    EditText ed_name,ed_gender,ed_place,ed_post,ed_pin,ed_district,ed_state,ed_email,ed_phone;
    RadioButton btn_radio, btn_radio2;
    ImageView img22;
    String path, atype, fname, attach, attatch1;
    byte[] byteArray = null;
    boolean isNameValid, isPlaceValid, isPostValid, isPinValid, isDistrictValid, isStateValid, isEmailValid, isPhoneValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ed_name = (EditText)findViewById(R.id.editText6);
        ed_place = (EditText)findViewById(R.id.editText10);
        ed_post = (EditText)findViewById(R.id.editText13);
        ed_pin = (EditText)findViewById(R.id.editText14);
        ed_district = (EditText)findViewById(R.id.editText15);
        ed_state = (EditText)findViewById(R.id.editText16);
        ed_email = (EditText)findViewById(R.id.editText17);
        ed_phone= (EditText)findViewById(R.id.editText18);
        img22 = (ImageView) findViewById(R.id.imageView4);
        btn_radio = (RadioButton)findViewById(R.id.radioButton5);
        btn_radio2 = (RadioButton)findViewById(R.id.radioButton4);
        btn = (Button)findViewById(R.id.button6);
        img22.setOnClickListener(this);
        btn.setOnClickListener(this);
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
        SetValidation();
        if(view.equals(img22)){
            showfilechooser(1);

        }else{
        if (btn_radio.isChecked()==true){
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
        final String email = ed_email.getText().toString();
        final String phone = ed_phone.getText().toString();
//        final String gender = btn_radio.getText().toString();

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/android_usr_regestration/";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
//                                String lid= jsonObj.getString("lid");
//                                //text view obj.setText(name)
//
//
//
//                                final SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                                SharedPreferences.Editor editor=sh.edit();
//                                editor.putString("lid",lid);
//                                editor.commit();

//                                Toast.makeText(this,lid, Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();

                                Intent ij=new Intent(getApplicationContext(),login.class);
                                startActivity(ij);
                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
//                                Toast.makeText(getApplicationContext(), "Already Registered.. Try to Login", Toast.LENGTH_SHORT).show();
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
                params.put("email",email);
                params.put("phone",phone);
                params.put("gender",gen22);
                params.put("img",attach);


                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);
    }

    }
    public void SetValidation() {

        if (ed_name.getText().toString().isEmpty()) {
            ed_name.setError(getResources().getString(R.string.empty_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
        }
        if (ed_place.getText().toString().isEmpty()) {
            ed_place.setError(getResources().getString(R.string.empty_error));
            isPlaceValid = false;
        } else  {
            isPlaceValid = true;
        }
        if (ed_post.getText().toString().isEmpty()) {
            ed_post.setError(getResources().getString(R.string.empty_error));
            isPostValid = false;
        } else  {
            isPostValid = true;
        }
        if (ed_pin.getText().toString().isEmpty()) {
            ed_pin.setError(getResources().getString(R.string.empty_error));
            isPinValid = false;
        } else if (ed_pin.getText().length() != 6) {
            ed_pin.setError(getResources().getString(R.string.error_pincode));
            isPinValid = false;
        } else  {
            isPinValid = true;
        }
        if (ed_phone.getText().toString().isEmpty()) {
            ed_phone.setError(getResources().getString(R.string.empty_error));
            isPhoneValid = false;
        } else if (ed_phone.getText().length() != 10) {
            ed_phone.setError(getResources().getString(R.string.error_phone));
            isPhoneValid = false;
        } else  {
            isPhoneValid = true;
        }
        if (ed_email.getText().toString().isEmpty()) {
            ed_email.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(ed_email.getText().toString()).matches()) {
            ed_email.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
        }

        if (ed_district.getText().toString().isEmpty()) {
            ed_district.setError(getResources().getString(R.string.empty_error));
            isDistrictValid = false;
        } else  {
            isDistrictValid = true;
        }
        if (ed_state.getText().toString().isEmpty()) {
            ed_state.setError(getResources().getString(R.string.empty_error));
            isStateValid = false;
        } else  {
            isStateValid = true;
        }



        return;




    }

//    @Override
//    public void onClick(View view) {
//
}
//}

