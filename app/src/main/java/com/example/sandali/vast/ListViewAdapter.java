package com.example.sandali.vast;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ListViewAdapter extends BaseAdapter {

    Context context;
    String[] Images;
    String[] Id;
    String[] Names;
    String From;

    public ListViewAdapter(Context context, String[] Images, String[] Id, String[] Names, String From){

        this.context = context;
        this.Images = Images;
        this.Id = Id;
        this.Names = Names;
        this.From = From;

    }

    //String[] Colors = {"#fc5a5a", "#8efc58", "#59c3fc", "#defc58", "#c4669d", "#d1b57d", "#a7e2d0",};
    //int x;

    //String[] Ide = {"ict11E","sinhala11E","english11E","ict11E","sinhala11E","english11E","ict11E","sinhala11E","english11E", "ict11E","sinhala11E","english11E"};
    //String[] Namese = {"ict","sinhala","english","ict","sinhala","english","ict","sinhala","english","ict","sinhala","english"};
    //String[] Imagese = {"http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png","http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png",
            //"http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png","http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png",
            //"http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png","http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png",
            //"http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png","http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png",
            //"http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png","http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png",
            //"http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png","http://mcq.infinisolutionslk.com/pc/appData/images/btns/ict.png"};


    @Override
    public int getCount() {
        return Id.length;
    }

    @Override
    public Object getItem(int position) {
        return Id[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(context);
        View btnView = myInflater.inflate(R.layout.btn_layout,null);

        ImageView image = (ImageView)btnView.findViewById(R.id.image);
        TextView name = (TextView)btnView.findViewById(R.id.name);

        Picasso.with(context).load(Images[position]).resize(60,60).into(image);
        //image.setImageResource(R.mipmap.ic_launcher);
        name.setText(Names[position]);


        /*
        if (position < Colors.length){
            btn.setBackgroundColor(Color.parseColor(Colors[position]));
        }else {
            x = position % Colors.length;
            btn.setBackgroundColor(Color.parseColor(Colors[x]));
        }
        */


        switch (From){

            case "Home":
                btnView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentPapersList = new Intent();
                        intentPapersList.setClass(context, PapersList.class);
                        intentPapersList.putExtra("subId", Id[position]);
                        intentPapersList.putExtra("subName", Names[position]);

                        context.startActivity(intentPapersList);

                        //Toast.makeText(context, Ide[position] + " was clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case "SubjectList":
                btnView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentPapersList = new Intent();
                        intentPapersList.setClass(context, PapersList.class);
                        intentPapersList.putExtra("subId", Id[position]);
                        intentPapersList.putExtra("subName", Names[position]);

                        context.startActivity(intentPapersList);

                        //Toast.makeText(context, Ide[position] + " was clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case "PracticeUnits":
                btnView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intentPractice = new Intent();
                        ///intentPractice.setClass(context, Practice.class);
                        //intentPractice.putExtra("unitId", Ide[position]);
                        //intentPractice.putExtra("unitName", Namese[position]);

                        //context.startActivity(intentPractice);

                        Toast.makeText(context, Id[position] + " was clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

        }

        return btnView;

    }

}
