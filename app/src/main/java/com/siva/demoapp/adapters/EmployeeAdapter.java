package com.siva.demoapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.siva.demoapp.models.Employee;
import com.siva.demoapp.R;

/**
 * Created by siva on 21/12/16.
 */

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    Context context;
    int layoutResourceId;
    Employee[] data = null;

    public EmployeeAdapter(Context context, int layoutResourceId, Employee[] data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EmployeeHolder holder = null;

        if(convertView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, null, false);

            holder = new EmployeeHolder();
            holder.emp_name = (TextView)convertView.findViewById(R.id.emp_name);
            holder.designation = (TextView)convertView.findViewById(R.id.designation);

            convertView.setTag(holder);
        } else {
            holder = (EmployeeHolder)convertView.getTag();
        }

        Employee currentEmp = data[position];
        holder.emp_name.setText(currentEmp.emp_name);
        holder.designation.setText(currentEmp.designation);

        return convertView;
    }

    static class EmployeeHolder {
        TextView emp_name;
        TextView designation;
    }
}
