package com.example.sandali.vast;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class GridViewAdapter extends BaseAdapter {

    Context context;
    String[] Images;
    String[] Id;
    String[] Names;
    String From;

    public GridViewAdapter(Context context, String[] Images, String[] Id, String[] Names, String From){

        this.context = context;
        this.Images = Images;
        this.Id = Id;
        this.Names = Names;
        this.From = From;

    }

    String[] Colors = {"#fc5a5a", "#8efc58", "#59c3fc", "#defc58", "#c4669d", "#d1b57d", "#a7e2d0",};
    int x;

    String[] Ide = {"ict11E","sinhala11E","english11E","ict11E","sinhala11E","english11E","ict11E","sinhala11E","english11E", "ict11E","sinhala11E","english11E"};
    String[] Namese = {"ict","sinhala","english","ict","sinhala","english","ict","sinhala","english","ict","sinhala","english"};

    @Override
    public int getCount() {
        return Ide.length;
    }

    @Override
    public Object getItem(int position) {
        return Ide[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Button getView(final int position, View convertView, ViewGroup parent) {
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                //LinearLayout.LayoutParams.MATCH_PARENT,
                //LinearLayout.LayoutParams.WRAP_CONTENT);
        final Button btn = new Button(context);
        btn.setId(position);

        btn.setText(Namese[position]);

        if (position < Colors.length){
            btn.setBackgroundColor(Color.parseColor(Colors[position]));
        }else {
            x = position % Colors.length;
            btn.setBackgroundColor(Color.parseColor(Colors[x]));
        }

        btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.chem,0,0,0);

        switch (From){

            case "PracticeQuestions":
                break;
            case "Home":
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intentPracticeUnits = new Intent();
                        //intentPracticeUnits.setClass(context, PracticeUnits.class);
                        //intentPracticeUnits.putExtra("subId", Id[position]);

                        //context.startActivity(intentPracticeUnits);

                        Toast.makeText(context, Ide[position] + " was clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case "PracticeUnits":
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intentPractice = new Intent();
                        ///intentPractice.setClass(context, Practice.class);
                        //intentPractice.putExtra("unitId", Id[position]);

                        //context.startActivity(intentPractice);

                        Toast.makeText(context, Id[position] + " was clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                break;

        }

        return btn;

    }
}
