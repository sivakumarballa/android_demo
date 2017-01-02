package com.siva.demoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.siva.demoapp.adapters.EmployeeAdapter;
import com.siva.demoapp.models.Employee;

public class ListViews extends AppCompatActivity {

    public void refreshList(View view) {
        Log.i("Info", "List refreshed");

        Intent i = new Intent(this, AnimationsWithXML.class);

        // Method #1
        //Bundle b = ActivityOptions.makeCustomAnimation(this, R.anim.new_activity_incoming, R.anim.new_activity_outgoing).toBundle();
        //startActivity(i, b);

        // Method #2
        startActivity(i);
        overridePendingTransition(R.anim.new_activity_incoming, R.anim.new_activity_outgoing);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "On Resume...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        Bundle b = getIntent().getExtras();
        if(b != null) {
            Toast.makeText(this, b.getString("Message"), Toast.LENGTH_SHORT).show();
            String s1 = b.getString("Message");
            String s2 = b.getString("Message2");
            String s3 = b.getString("name");
            String s4 = b.getString("designation");
            Employee t = (Employee) getIntent().getSerializableExtra("emp1");
            Employee t2 = (Employee) b.getSerializable("emp1");
        } else {
            Toast.makeText(this, "No Extra...", Toast.LENGTH_SHORT).show();
        }

        final Employee[] emps = {
            new Employee("Sivakumar", "UX Engineer"),
            new Employee("Narendra", "Senior UX Engineer"),
            new Employee("Naresh", "Architect"),
            new Employee("Prasanna", "UX Engineer"),
            new Employee("Kranthi", "UX Engineer"),
            new Employee("Venkat", "Senior UX Engineer"),
            new Employee("Vish", "Project Manager"),
            new Employee("Sumathi", "Architect"),
            new Employee("Hemantha Lakshmi", "Associate UX Engineer"),
            new Employee("Suresh", "UX Engineer"),
            new Employee("Satya", "Accounts Manager")
        };

        /* Custom Array Adapter */
        EmployeeAdapter employeeAdapter = new EmployeeAdapter(this, R.layout.custom_list_item, emps);
        ListView empList = (ListView) findViewById(R.id.emp_list);
        View header = getLayoutInflater().inflate(R.layout.list_header, null);
        empList.addHeaderView(header);

        empList.setAdapter(employeeAdapter);
        /* END */



        /* Simple List */
//        final String[] employees = {"Sivakumar", "Narendra", "Naresh", "Prasanna", "Kranthi", "Venkat", "Vish", "Sumathi", "Suresh", "Venu", "Satya", "Vani", "Hema", "Hemantha"};
//        ArrayAdapter<String> employeeAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.emp_name, employees);
//
//        /* One way */
//        ListView empList = (ListView) findViewById(R.id.emp_list);
//
//        /* Other way */
//        // ListView empList = new ListView(this);
//        // setContentView(empList);
//
//        empList.setAdapter(employeeAdapter);
//        empList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(), "Clicked item: " + employees[i], Toast.LENGTH_SHORT).show();
//            }
//        });
        /* END */


        /*  Extending ArrayAdapter, Custom List */
//        ArrayAdapter<Employee> empAdapter = new ArrayAdapter<Employee>(this, R.layout.custom_list_item, emps) {
//            class EmpViewHolder {
//                TextView emp_name;
//                TextView designation;
//            };
//
//            @Override
//            public View getView(int position,
//                                View convertView,
//                                ViewGroup parent) {
//
//                Employee currentEmp = emps[position];
//
//                if(convertView == null) {
//                    convertView = getLayoutInflater()
//                            .inflate(R.layout.custom_list_item, null, false);
//
//                    EmpViewHolder empViewholder = new EmpViewHolder();
//                    empViewholder.emp_name = (TextView)convertView.findViewById(R.id.emp_name);
//                    empViewholder.designation = (TextView)convertView.findViewById(R.id.designation);
//
//                    convertView.setTag(empViewholder);
//                }
//
//                TextView cheeseName =
//                        ((EmpViewHolder)convertView.getTag()).emp_name;
//                TextView cheeseDescription =
//                        ((EmpViewHolder)convertView.getTag()).designation;
//
//                cheeseName.setText(currentEmp.emp_name);
//                cheeseDescription.setText(currentEmp.designation);
//
//                return convertView;
//            }
//        };
//
//        ListView empList = (ListView) findViewById(R.id.emp_list);
//        empList.setAdapter(empAdapter);
//        empList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                TextView a = (TextView)view.findViewById(R.id.emp_name);
//                Toast.makeText(getApplicationContext(), "Clicked item: " + a.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
        /* END */
    }
}
