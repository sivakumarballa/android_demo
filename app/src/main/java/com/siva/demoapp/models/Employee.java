package com.siva.demoapp.models;

import java.io.Serializable;

/**
 * Created by siva on 19/12/16.
 */

public class Employee implements Serializable{
    public String emp_name;
    public String designation;

    public Employee(String emp_name, String designation) {
        this.emp_name = emp_name;
        this.designation = designation;
    }
}
