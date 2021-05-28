package com.example.linenclub;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custome_delivered_product_detail extends BaseAdapter {
    private android.content.Context Context;
    String[] c;
    String[] d,e,f,g;





    public custome_delivered_product_detail(android.content.Context applicationContext, String[] c, String[] d,String[] e,String[] f,String[] g) {

        this.Context=applicationContext;
        this.c=c;
        this.d=d;
        this.e=e;
        this.f=f;
        this.g=g;



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
            gridView=inflator.inflate(R.layout.cust_delivered_product_detail, null);



        }
        else
        {
            gridView=(View)convertview;

        }





        TextView tv1=(TextView)gridView.findViewById(R.id.textView11);

        TextView tv2=(TextView)gridView.findViewById(R.id.textView12);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView21);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView22);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView23);


        //  ImageView im=(ImageView)gridView.findViewById(R.id.imageView1);










        //


        /////////////////////





        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);

        //tv6.setTextColor(Color.BLACK);
        //tv1.setText(c[position]);
        //tv2.setText(d[position]);

        tv1.setText(c[position]);
        tv2.setText(d[position]);
        tv3.setText(e[position]);
        tv4.setText(f[position]);
        tv5.setText(g[position]);









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

