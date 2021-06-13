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
import android.widget.TextView;

public class custome_delivered_product extends BaseAdapter {
    private android.content.Context Context;
    String[] c;
    String[] d,e,f,g;





    public custome_delivered_product(android.content.Context applicationContext, String[] c, String[] d,String[] e,String[] f,String[] g) {

        this.Context=applicationContext;
        this.c=c;
        this.d=d;
        this.e=e;
        this.f=f;
        this.g=g;//master_id


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
            gridView=inflator.inflate(R.layout.cust_delivered_product, null);



        }
        else
        {
            gridView=(View)convertview;

        }





        TextView tv1=(TextView)gridView.findViewById(R.id.textView14);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView16);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView18);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView20);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView55);
        TextView tv6=(TextView)gridView.findViewById(R.id.textView56);








        tv1.setTextColor(Color.RED);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);


        //tv6.setTextColor(Color.BLACK);
        //tv1.setText(c[position]);
        //tv2.setText(d[position]);

        tv1.setText(f[position]);
        tv2.setText(d[position]);
        tv3.setText(c[position]);
        tv4.setText(e[position]);
        tv5.setText("\u20B9 ");
        tv6.setText("\u20B9 ");


        Button b3=(Button) gridView.findViewById(R.id.button7);

        b3.setTag(g[position]);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kk=v.getTag().toString();

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);

                SharedPreferences.Editor ed=sh.edit();
                ed.putString("master_id",kk);


                ed.commit();
                Intent i=new Intent(Context,product_details.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Context.startActivity(i);




            }
        });

        Button b4=(Button) gridView.findViewById(R.id.feedback);

        b4.setTag(g[position]);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kk=v.getTag().toString();

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);

                SharedPreferences.Editor ed=sh.edit();
                ed.putString("master_id",kk);


                ed.commit();
                Intent ii=new Intent(Context,feedback.class);
                ii.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Context.startActivity(ii);




            }
        });






// image
//        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(Context);
//        String ss=sh.getString("ip", "");
//        String url = "http://" + ss + ":5000"+e[position];
//        //Toast.makeText(Context, "tstid ass="+url, Toast.LENGTH_LONG).show();
//
//        Picasso.with(Context).load(url).into(im);
////        Picasso.with(Context).load(url).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).transform(new CircleTransform()).into(im);


        return gridView;
    }


}


