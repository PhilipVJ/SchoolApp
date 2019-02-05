/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp;

import schoolapp.dal.SchoolAppDAL;

/**
 *
 * @author Christian Occhionero
 */
public class tester
{
     public static void main (String[] args) {

SchoolAppDAL tester = new SchoolAppDAL();
         System.out.println(""+tester.getStudent().getAbsencePercentage());
     }


} //end class
