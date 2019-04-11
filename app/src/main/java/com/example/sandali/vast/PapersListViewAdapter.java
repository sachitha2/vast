package com.example.sandali.vast;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class PapersListViewAdapter extends BaseAdapter {

    Context context;
    String[] Id;
    String[] Names;
    String[] Creator;
    String[] Images;
    String[] NumOfQuestions;
    String[] NumOfHours;
    String From;

    public PapersListViewAdapter(Context context, String[] Id, String[] Names, String[] Creator, String[] Images, String[] NumOfQuestions, String[] NumOfHours, String From){

        this.context = context;
        this.Id = Id;
        this.Names = Names;
        this.Creator = Creator;
        this.Images = Images;
        this.NumOfQuestions = NumOfQuestions;
        this.NumOfHours = NumOfHours;
        this.From = From;

    }

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
        View btnView = myInflater.inflate(R.layout.btn_layout_papers,null);

        ImageView image = (ImageView)btnView.findViewById(R.id.image);
        TextView name = (TextView)btnView.findViewById(R.id.name);
        TextView creator = (TextView)btnView.findViewById(R.id.creator);
        TextView numOfQuestions = (TextView)btnView.findViewById(R.id.numOfQuestions);
        TextView numOfHours = (TextView)btnView.findViewById(R.id.numOfHours);

        Picasso.with(context).load(Images[position]).resize(60,60).into(image);
        //image.setImageResource(R.mipmap.ic_launcher);
        name.setText(Names[position]);
        creator.setText(Creator[position]);
        numOfQuestions.setText(NumOfQuestions[position] + " Questions");
        numOfHours.setText(NumOfHours[position] + " h");


        /*
        if (position < Colors.length){
            btn.setBackgroundColor(Color.parseColor(Colors[position]));
        }else {
            x = position % Colors.length;
            btn.setBackgroundColor(Color.parseColor(Colors[x]));
        }
        */


        switch (From){

            case "PapersList":
                btnView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intentPaper = new Intent();
                        intentPaper.setClass(context, Paper.class);
                        intentPaper.putExtra("paperId", Id[position]);
                        intentPaper.putExtra("paperName", Names[position]);

                        context.startActivity(intentPaper);

                        //Toast.makeText(context, Id[position] + " was clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

        }

        return btnView;

    }

}
