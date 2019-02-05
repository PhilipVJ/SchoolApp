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
public class Teacher extends Person
{
private ArrayList<String> classes;

 public Teacher (String name, int id, String email)
    {
       super(name,  id, email);
        classes = new ArrayList<>();
    }
}
