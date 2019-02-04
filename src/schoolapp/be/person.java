/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.be;

import java.util.ArrayList;

/**
 *
 * @author Christian Occhionero
 */
public class person
{
    String name; 
    private boolean wasHere;
    int id; 
    private String email;
    
       public person (String name, int id, String email)
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
