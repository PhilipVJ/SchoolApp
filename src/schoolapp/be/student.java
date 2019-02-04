/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolapp.be;



/**
 *
 * @author Christian Occhionero
 */
public class student
{
    private final String name; 
    private boolean wasHere;
    private final int id; 
    
// Constructer 
    public student (String name, int id)
    {
        this.name = name;
        this.id = id;
    }

    public boolean getWasHere()
    {
        return wasHere;
    }

    public void setWasHere(boolean wasHere)
    {
        this.wasHere = wasHere;
    }

    public String getName()
    {
        return name;
    }

    public int getId()
    {
        return id;
    }
    


} // end class
