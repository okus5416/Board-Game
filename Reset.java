
/**
 * The Reset Class moves all pieces to original starting positions.
 * 
 * @Jay Chung, Bethany Kon , Min Kim
 * @January 21, 2014
 */
public class Reset //Reset class for users to play again
{
    public void playAgain(playerPieces[] redPieces, playerPieces[] greenPieces)
    {
        //call method for pieces on either the left side or the right side
        leftSide(redPieces[0]);
        leftSide(redPieces[1]);
        leftSide(greenPieces[0]);
        leftSide(greenPieces[1]);
        rightSide(redPieces[2]);
        rightSide(redPieces[3]);
        rightSide(greenPieces[2]);
        rightSide(greenPieces[3]);
        //for red 1 and green 3, move 3 spaces north
        for (int first=0; first<3; first++)
        {
            redPieces[0].moving(); //not using move() because it's been overrided
            greenPieces[2].moving();
        }
        //for green 1 and red 3, move 2 spaces north
        for (int second=0; second<2; second++)
        {
            greenPieces[0].moving();
            redPieces[2].moving();
        }
        //for red 2 and green 4, move 1 space north
        redPieces[1].moving();
        greenPieces[3].moving();
        //turn all pieces to face east
        for (int faceEast=0; faceEast<3; faceEast++)
        {
            for (int allRed=0; allRed<4; allRed++)
            {
                redPieces[allRed].turnLeft();
            }
            for (int allGreen=0; allGreen<4; allGreen++)
            {
                greenPieces[allGreen].turnLeft();
            }
        }
    }

    public void leftSide(playerPieces leftPiece)
    {
        //call method to move all pieces down to street 3
        headDown(leftPiece);
        //turn pieces west
        for (int turning=0; turning<3; turning++)
        {
            leftPiece.turnLeft();
        }
        //move pieces west to bottom left corner
        while (leftPiece.getAvenue() != 0)
        {
            leftPiece.moving();
        }
        //turn pieces north
        for (int turning=0; turning<3; turning++)
        {
            leftPiece.turnLeft();
        }
    }

    public void rightSide(playerPieces rightPiece)
    {
        //call method to move all pieces down to street 3
        headDown(rightPiece);
        //turn pieces east
        rightPiece.turnLeft();
        //move pieces east to bottom right corner
        while (rightPiece.getAvenue() != 3)
        {
            rightPiece.moving();
        }
        //turn pieces north
        rightPiece.turnLeft();
    }

    public void headDown(playerPieces all)
    {
        //turn all pieces south
        for (int turning=0; turning<3; turning++)
        {
            all.turnLeft();
        }
        //move pieces down to street 3
        while (all.getStreet() != 3)
        {
            all.moving();
        }
    }
}
