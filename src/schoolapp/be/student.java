/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.be;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Christian Occhionero
 */
public class student extends person
{
    
    private boolean wasHere;
    private final ArrayList<Boolean> daysAttended;
// Constructer 
    
    
    public student (String name, int id)
    {
       super(name,  id);
        daysAttended = new ArrayList<>();
    }

    public boolean getWasHere()
    {
        return wasHere;
    }

    public void setWasHere(boolean wasHere)
    {
        this.wasHere = wasHere;
    }




} // end class
