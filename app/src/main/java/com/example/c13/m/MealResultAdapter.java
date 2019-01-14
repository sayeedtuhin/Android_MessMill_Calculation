package com.example.c13.m;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MealResultAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<MealInfo> contactList;
    ViewHolder viewHolder;
    MealInfo mealInfo=new MealInfo();
    public MealResultAdapter(Context context, ArrayList<MealInfo> contactList) {
        super(context, R.layout.result_row_layout,contactList);
        this.context=context;
        this.contactList=contactList;

    }
    public class ViewHolder{
        TextView sirialNoRRTV;
        TextView nameRRTV;
        TextView dipositRRTV;
        TextView millRRTV;
        TextView millCostRRTV;
        TextView extraRRTV;
        TextView totalCostRRTV;
        TextView restMoneyRRTV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.result_row_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.sirialNoRRTV = (TextView) convertView.findViewById(R.id.sirialNoRR);
            viewHolder.nameRRTV = (TextView) convertView.findViewById(R.id.nameRR);
            viewHolder.dipositRRTV = (TextView) convertView.findViewById(R.id.dipositRR);
            viewHolder.millRRTV =(TextView)convertView.findViewById(R.id.millRR);
            viewHolder.millCostRRTV =(TextView)convertView.findViewById(R.id.millCostRR);
            viewHolder.extraRRTV=(TextView)convertView.findViewById(R.id.extraRR);
            viewHolder.totalCostRRTV=(TextView)convertView.findViewById(R.id.totalCostRR);
            viewHolder.restMoneyRRTV =(TextView)convertView.findViewById(R.id.restMonyRR);

        }
        else {
            convertView.getTag();
        }

        DecimalFormat precision = new DecimalFormat("0.00");



        viewHolder.sirialNoRRTV.setText(String.valueOf(position+1));
        viewHolder.nameRRTV.setText(contactList.get(position).getName());
        viewHolder.dipositRRTV.setText(String.valueOf(mealInfo.checkInteger(contactList.get(position).getDeposit())));
        viewHolder.millRRTV.setText(mealInfo.checkInteger(contactList.get(position).getMeal()));
        viewHolder.millCostRRTV.setText(String.valueOf(precision.format(contactList.get(position).getMealCost())));
        viewHolder.extraRRTV.setText(mealInfo.checkInteger(Float.valueOf(precision.format(contactList.get(position).getEachPersonExtra()))));
        viewHolder.totalCostRRTV.setText(mealInfo.checkInteger(Float.valueOf(precision.format(contactList.get(position).getTotalCost()))));
        viewHolder.restMoneyRRTV.setText(mealInfo.checkInteger(Float.valueOf(precision.format(contactList.get(position).getRestMony()))));
        return convertView;
    }


}
