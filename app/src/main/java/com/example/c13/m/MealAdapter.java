package com.example.c13.m;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MealAdapter  extends ArrayAdapter {

    private Context context;
    private ArrayList<MealInfo> contactList;
    ViewHolder viewHolder;
    MealInfo mealInfo=new MealInfo();
    public MealAdapter(Context context, ArrayList<MealInfo> contactList) {
        super(context, R.layout.row_layout,contactList);
        this.context=context;
        this.contactList=contactList;
    }
    public class ViewHolder{
        TextView sirialNoTV;
        TextView nameTV;
        TextView dipositTV;
        TextView millTV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.sirialNoTV = (TextView) convertView.findViewById(R.id.sirialNo);
            viewHolder.nameTV = (TextView) convertView.findViewById(R.id.name);
            viewHolder.dipositTV = (TextView) convertView.findViewById(R.id.deposit);
            viewHolder.millTV =(TextView)convertView.findViewById(R.id.meal);
            convertView.setTag(viewHolder);
        }
        else {
            convertView.getTag();
        }

        viewHolder.sirialNoTV.setText(String.valueOf(position+1));
        viewHolder.nameTV.setText(contactList.get(position).getName());
        viewHolder.dipositTV.setText(mealInfo.checkInteger(contactList.get(position).getDeposit()));
        viewHolder.millTV.setText(mealInfo.checkInteger(contactList.get(position).getMeal()));
        return convertView;
    }
}
