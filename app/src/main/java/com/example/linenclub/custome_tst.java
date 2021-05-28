package com.example.linenclub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custome_tst extends BaseAdapter {
    private android.content.Context Context;

    String[] c,d,e,f;





    public custome_tst(android.content.Context applicationContext, String[] c, String[] d,String[] e,String[] f) {

        this.Context=applicationContext;
        this.c=c;
        this.d=d;
        this.e=e;
        this.f=f;



    }

    @Override
    public int getCount() {

        return d.length;
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {


        LayoutInflater inflator=(LayoutInflater)Context.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(Context);
            gridView=inflator.inflate(R.layout.cust_new_home, null);



        }
        else
        {
            gridView=(View)convertview;

        }





        TextView tv1=(TextView)gridView.findViewById(R.id.textView43);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView44);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView45);
        ImageView im=(ImageView)gridView.findViewById(R.id.imageView2);


        //  ImageView im=(ImageView)gridView.findViewById(R.id.imageView1);










        //


        /////////////////////





        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);



        //tv6.setTextColor(Color.BLACK);
        //tv1.setText(c[position]);
        //tv2.setText(d[position]);

        tv1.setText(c[position]);
        tv2.setText(d[position]);
        tv3.setText(e[position]);




//        Button b3=(Button) gridView.findViewById(R.id.button7);
//
//        b3.setTag(g[position]);
//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String kk=v.getTag().toString();
//
//                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);
//
//                SharedPreferences.Editor ed=sh.edit();
//                ed.putString("master_id",kk);
//
//
//                ed.commit();
//                Intent i=new Intent(Context,product_details.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                Context.startActivity(i);
//
//
//
//
//            }
//        });






// image
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);
        String ss=sh.getString("ip", "");
        String url = "http://" + ss + ":8000"+f[position];
        //Toast.makeText(Context, "tstid ass="+url, Toast.LENGTH_LONG).show();

        Picasso.with(Context).load(url).into(im);
//        Picasso.with(Context).load(url).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).transform(new CircleTransform()).into(im);


        return gridView;
    }


}


