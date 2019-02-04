/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp;

import schoolapp.be.student;

/**
 *
 * @author Christian Occhionero
 */
public class tester
{
     public static void main (String[] args) {
         student s = new student("Wilhelm", 2, "wilhelm@msn.com");
         System.out.println("name  "+s.getName());
         System.out.println("ID  "+s.getId());
         System.out.println("was here  "+s.getWasHere());
     }


} //end class
