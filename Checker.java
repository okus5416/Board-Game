
/**
 * Checker class checks for a win - if 3 pieces are alligned next to each other (horizontally, vertically, or diagonally) 
 * 
 * @Jay Chung, Bethany Kon, Min Kim,
 * @January 21, 2014
 */
public class Checker
{   
    //checks for win and returns true or false
    public boolean checkWin(playerPieces[] allPieces)
    {
        //create a 2D array
        int[][] locations = new int[4][4];

        //pinpoint all locations for either green or red pieces in the 2D array with "1"
        for (int pieceNumber=0; pieceNumber<4; pieceNumber++)
        {
            locations[allPieces[pieceNumber].getStreet()][allPieces[pieceNumber].getAvenue()] = 1;
        }

        //for every index in the 2D array that doesn't have a "1", set it to "0"
        for (int row=0; row<locations.length; row++)
        {
            for (int col=0; col<locations[0].length; col++)
            {
                if (locations[row][col] != 1)
                {
                    locations[row][col] = 0;
                }
            }
        }

        //horizontal check - for every row, check if there are pieces that are three in a row
        for (int row=0; row<locations.length; row++)
        {
            if ((locations[row][1] == locations[row][2]) && (locations[row][2] == locations[row][0] || locations[row][2] == locations[row][3]) && (locations[row][1] == 1))
            {
                return true;
            }
        }

        //vertical check - for every column, check if there are pieces that are three in a row
        for (int col=0; col<locations[0].length; col++)
        {
            if ((locations[1][col] == locations[2][col]) && (locations[1][col] == locations[0][col] || locations[1][col] == locations[3][col]) && (locations[1][col] == 1))
            {
                return true;
            }
        }

        //diagonal check - top to bottom
        if((locations[1][1] == locations[2][2]) && (locations[2][2] == locations[0][0] || locations[2][2] == locations[3][3]) && (locations[1][1] == 1))
        {
            return true;
        }
        else if ((locations[0][1] == locations[1][2]) && (locations[1][2] == locations[2][3]) && (locations[0][1] == 1))
        {
            return true;
        }
        else if ((locations[1][0] == locations[2][1]) && (locations[2][1] == locations[3][2]) && (locations[1][0] == 1))
        {
            return true;
        }
        //diagonal check - bottom to top
        if ((locations[2][1] == locations[1][2]) && (locations[2][1] == locations[3][0] || locations[2][1] == locations[0][3]) && (locations[2][1] == 1))
        {
            return true;
        }
        else if ((locations[2][0] == locations[1][1]) && (locations[1][1] == locations[0][2]) && (locations[2][0] == 1))
        {
            return true;
        }
        else if ((locations[3][1] == locations[2][2]) && (locations[2][2] == locations[1][3]) && (locations[3][1] == 1))
        {
            return true;
        }

        //if there are no set of pieces that are lcoated three in a row...
        return false;
    }
}
