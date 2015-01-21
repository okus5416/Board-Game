import becker.robots.*;
import java.util.*;
import becker.robots.icons.*;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
/**
 * The Board Class creates the background of the board the pieces are played on.
 * It has a rotating set of black and gray square icons. 
 * 
 * @Jay Chung,Bethany Kon,  Min Kim, 
 * @January 21, 2014
 */
public class Board
{
    private City location;
    private int side;
    private int blocks;

    //constructor
    public Board(City l, int s, int b)
    {
        location = l;
        side = s;
        blocks = b;

        //creating icons
        Icon blackIcon = new ShapeIcon(new Rectangle2D.Double(0.0, 0.0, 1.0, 1.0), Color.black);
        Icon grayIcon = new ShapeIcon(new Rectangle2D.Double(0.0, 0.0, 1.0, 1.0), Color.gray);
        //creating an array of things for each section of the board
        Thing[] black = new Thing[blocks];
        Thing[] gray = new Thing[blocks];

        //declaring and initialiing variables
        int count = 0;
        int start = 0;

        //for every street and every other avenue, create a thing with a square black icon
        for (int street=0; street<side; street++)
        {
            for (int avenue=start; avenue<side; avenue=avenue+2)
            {
                black[count] = new Thing(location, street, avenue, Direction.EAST, true, blackIcon);
                count++;

            }
            //for every even street, start at avenue 0 for the next street
            if (street == 0 || street ==2)
            {
                start++;
            }
            //for every odd street, start at avenue 1 for the next street
            else 
            {
                start--;
            }
        }

        //reset variables for second set of things
        count = 0;
        start = 1;
        //for every street and every other avenue, create a thing with a square gray icon
        for (int street=0; street<side; street++)
        {
            for (int avenue=start; avenue<side; avenue=avenue+2)
            {
                gray[count] = new Thing(location, street, avenue, Direction.EAST, true, grayIcon);
                count++;
            }
            //for every even street, start at avenue 1 for the next street
            if (street == 0 || street ==2)
            {
                start--;
            }
            //for every odd street, start at avenue 0 for the next street
            else 
            {
                start++;
            }
        }
    }
}
