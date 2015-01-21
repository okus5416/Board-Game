
/**
 * The playerPieces Class moves the pieces according to the player's input received from the main class.
 * 
 * Jay Chung, Min Kim, Bethany Kon
 *January 21, 2014
 */
import becker.robots.*;
import java.util.*;

public class playerPieces extends Robot
{
    //retrieve arguments from the parent class
    public playerPieces(City c, int s, int a, Direction d)
    {
        super(c, s, a, d);
    }

    //moves pieces according to player's input
    public boolean safeMove( String m, int l)
    {
        //declare variable
        boolean checkTeleport;

        //when piece moves up, turn left and move
        if (m.equals("up"))
        {
            turnLeft();
            //if piece is on the top street, call teleport method
            if ( getStreet() == 0)
            {
                //check if teleport worked with boolean variable
                checkTeleport = teleport(l);
                while ( getDirection() != Direction.EAST)
                {
                    turnLeft();
                }
                if (checkTeleport)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                move(); //it is not an original method from Robot class
                //if piece is on top of another piece, call redo method
                if ( getIntersection().countSims(IPredicate.anyRobot) > 1 ) 
                {
                    redo();
                    turnLeft();
                    return false;   //return false because move was illegal
                }
                else
                {
                    //if move works, turn piece to face east and return true because move was legal
                    for (int i=0; i<3; i++)
                    {
                        turnLeft();
                    }
                    return true;
                }
            }
        }
        //when piece moves down, turn right and move 
        else if (m.equals("down"))
        {
            for (int i=0; i<3; i++)
            {
                turnLeft();
            }
            //if piece is on bottom of the street, call teleport method
            if ( getStreet() == 3)
            {
                //check if teleport worked with boolean variable
                checkTeleport = teleport(l);  
                while ( getDirection() != Direction.EAST)
                {
                    turnLeft();
                }
                if (checkTeleport)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                move();
                //if piece is on top of another piece, call redo method
                if ( getIntersection().countSims(IPredicate.anyRobot) > 1 ) 
                {
                    redo();
                    for (int i=0; i<3; i++)
                    {
                        turnLeft();
                    }
                    return false;
                }
                else
                {
                    //if move is legal, turn piece to face east and return true
                    turnLeft();
                    return true;
                }
            }
        }
        //when piece moves left, turn west and move
        else if (m.equals("left"))
        {
            turnAround();
            //if piece is on the avenue farthest to the left, call teleport method
            if( getAvenue() == 0)
            {
                 //check if teleport worked with boolean variable
                checkTeleport = teleport(l);
                while ( getDirection() != Direction.EAST)
                {
                    turnLeft();
                }
                if (checkTeleport)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                move();
                //if piece is on top of another piece, call redo method
                if ( getIntersection().countSims(IPredicate.anyRobot) > 1 ) 
                {
                    redo();
                    return false;
                }
                else
                {
                    //if move is legal, turn piece to face east and return true
                    turnAround();
                    return true;
                }
            }
        }
        //when piece moves right, move (since already facing east)
        else if (m.equals("right"))
        {
            //if piece is on the avenue farthest to the right, call teleport method
            if ( getAvenue() == 3)
            {
                 //check if teleport worked with boolean variable
                checkTeleport = teleport(l);
                while ( getDirection() != Direction.EAST)
                {
                    turnLeft();
                }
                if (checkTeleport)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                move();
                //if piece is on top of another piece, call redo method
                if ( getIntersection().countSims(IPredicate.anyRobot) > 1 ) 
                {
                    redo();
                    turnAround();
                    return false;
                }
                else
                {
                    return true;
                }
            }
        }
        else
        {
            //if player does not input a valid move, return false
            return false;
        }
    }
    //redo method returns piece to previous location
    public void redo() 
    {
        turnAround();
        move("Unfortunately.."); //not a move method from original robot class, overloaded move method with a parameter
        System.out.println("it came back to.. ("+ getStreet() +"," + getAvenue() +")!!"); 

    }
    //specialRedo method returns a teleported piece to previous location
    public void specialRedo(int nu)
    {
        turnAround();
        super.move(); //original move method, indicated by the word 'super' in front
        setTransparency(1);     //turn piece invisible
        setLabel("");           //turn label invisible
        turnAround();   
        for (int shif=0; shif<5; shif++)    
        {
            super.move();       //move back to previous position
        }
        setTransparency(0);
        setLabel(Integer.toString(nu));
        turnAround();
        move("Unfortunately..");    //output message to let user know of illegal move
        System.out.println("it came back to.. ("+ getStreet() +"," + getAvenue() +")!!");

    }
    //teleport method teleports a piece from one side to the other
    public boolean teleport(int numb){
        System.out.println("Teleporting...");   //let player know it's taking time to teleport
        super.move();

        setTransparency(1);     //turn piece invisible
        setLabel("");           //turn label invisible
        turnAround();
        for (int shift=0; shift<5; shift++)
        {
            super.move();       //move piece to the other side
        }

        setTransparency(0);     //turn piece back to visible
        setLabel(Integer.toString(numb));   //turn label back to visible

        turnAround();

        move();
        //if piece is on top of another piece, call specialRedo to return piece to the other side
        if ( getIntersection().countSims(IPredicate.anyRobot) > 1)
        {
            specialRedo(numb);
            return false;
        }
        return true;
    }
    //move(String 1) outputs a string when called
    public void move(String l) //Overloading, that I used to conatin the word "Unfortunately" only when there's a redo() 
    {
        super.move();
        System.out.println(l);
    }
    //move() overrides the parent method
    public void move() //this is an overriding of one of the method from Robot class (move())
    {
        super.move();

        System.out.println("nice move! you are piece is at ("+ getStreet() +"," + getAvenue() +")!!");
    }
    //moving method accessed the parent's move method when the reset class is used
    public void moving() //for reset class (in order to avoid using overrided move method
    {
        super.move();
    }
    //turnAround method reduces lines of code by having the pieces face the opposite direction
    public void turnAround()
    {
        turnLeft();
        turnLeft();
    }

}
