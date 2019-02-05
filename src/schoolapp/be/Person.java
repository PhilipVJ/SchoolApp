/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.be;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Christian Occhionero
 */
public class Person
{
    String name; 
   
    int id; 
    private String email;
    
       public Person (String name, int id, String email)
    {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }
    public void getEmail(String email)
    {
        this.email = email;
    }
    

}
