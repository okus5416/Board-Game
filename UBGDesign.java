import becker.robots.*;
import java.util.*;
import becker.robots.icons.*;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
/**
 * In this game, players move pieces until 3 of them are alligned either horizontally, vertically, or diagonally 
 * 
 * @Jay Chung, Bethany Kon  Min Kim,
 * @January 21, 2014
 */
public class UBGDesign
{
    public static void main (String [] args)
    {
        Scanner in = new Scanner(System.in);

        //getting player's names
        System.out.println("Player 1, enter your name:");
        String playerOne = in.nextLine();
        System.out.println("Player 2, enter your name:");
        String playerTwo = in.nextLine();

        //creating board, checker, and reset objects
        City UBG = new City(0,0, 4,4, 100);
        Board game = new Board(UBG, 4, 8);
        Checker check = new Checker();
        Reset replay = new Reset();

        //declaring and positioning array of robots for pieces        
        playerPieces[] red = new playerPieces[4];
        playerPieces[] green = new playerPieces[4];
        red[0] = new playerPieces(UBG, 0,0, Direction.EAST);       
        red[1] = new playerPieces(UBG, 2,0, Direction.EAST);        
        red[2] = new playerPieces(UBG, 1,3, Direction.EAST);
        red[3] = new playerPieces(UBG, 3,3, Direction.EAST);
        green[0] = new playerPieces(UBG, 1,0, Direction.EAST);       
        green[1] = new playerPieces(UBG, 3,0, Direction.EAST);        
        green[2] = new playerPieces(UBG, 0,3, Direction.EAST);        
        green[3] = new playerPieces(UBG, 2,3, Direction.EAST);

        //setting icons and labels for each piece
        CircleIcon[] redCircle = new CircleIcon[4];
        CircleIcon[] greenCircle = new CircleIcon[4]; 
        for (int circles=0; circles<4; circles++)
        {
            redCircle[circles] = new CircleIcon(Color.red,0.5);
            red[circles].setIcon(redCircle[circles]);
            red[circles].setLabel(Integer.toString(circles+1));
        }
        for (int circles=0; circles<4; circles++)
        {
            greenCircle[circles] = new CircleIcon(Color.green,0.5);
            green[circles].setIcon(greenCircle[circles]);
            green[circles].setLabel(Integer.toString(circles+1));
        }

        //instructions
        System.out.println("WELCOME " + playerOne + " and " + playerTwo + "!");
        System.out.println("Take turns moving the pieces to get three in a row - horizontally, vertically,");
        System.out.println("or diagonally!");
        System.out.println("*Note: If you move a piece off the board, it will appear on the other side");
        System.out.println("Game on! Try not to suck!");

        //declaring variables for gameplay
        int piece;
        String direction;
        boolean moveType;
        boolean redWin;
        boolean greenWin;
        int redScore = 0;
        int greenScore = 0;
        String answer;
        
        //allow players to play again and again
        do
        {
            //set undeclared winner
            redWin = false;
            greenWin = false;
            
            //begin game
            do
            { 
                //determine moving piece
                do
                {
                    System.out.println(playerOne + "(red), which piece would you like to move?(From 1 to 4)");
                    piece = Integer.parseInt(in.nextLine());
                } while (piece != 1 && piece!= 2 && piece != 3 && piece!= 4);   //loop if player does not choose a valid piece number

                //move piece 
                do
                {
                    moveType = true;
                    //get player's decision as to where to move piece
                    System.out.println("Where do you want to move it? (up, down, left, right)");
                    direction = in.nextLine();

                    moveType = red[piece-1].safeMove(direction,piece);  //call safeMove method to move the piece and determine if move is legal

                    //if move is illegal, tell player
                    if (moveType == false)
                    {
                        System.out.println("That's an illegal move! Are you trying to cheat?!");
                    }
                } while (moveType == false);    //loop while move is illegal

                //call checkWin method to determine if player 1 has made a winning move
                redWin = check.checkWin(red);
                if (redWin == true)
                {
                    System.out.println(playerOne + " Wins!");
                    redScore++; //update player 1's score
                    break;  //stop the game if player 1 wins
                }

                //determine moving piece
                do
                {
                    System.out.println(playerTwo + "(green), which piece would you like to move?(From 1 to 4)");
                    piece = Integer.parseInt(in.nextLine());
                } while (piece != 1 && piece!= 2 && piece != 3 && piece!= 4); //loop if player does not choose a valid piece number

                //move piece
                do
                {
                    moveType = true;
                    //get player's decision as to where to move piece
                    System.out.println("Where do you want to move it? (up, down, left, right)");
                    direction = in.nextLine();

                    moveType = green[piece-1].safeMove(direction,piece); //call safeMove method to move the piece and determine if move is legal
                    
                    //if move is illegal, tell player
                    if (moveType == false)
                    {
                        System.out.println("That's an illegal move! Are you trying to cheat?!");
                    }
                } while (moveType == false); //loop while move is illegal

                //call checkWin method to determine if player 2 has made a winning move
                greenWin = check.checkWin(green);
                if (greenWin == true)
                {
                    System.out.println(playerTwo + " Wins!");
                    greenScore++;   //update player 2's score
                    break;  //stop the game if player 2 wins
                }
            } while (redWin == false || greenWin == false);     //loop game while neither player has won
            
            //output score and ask if players want a rematch
            System.out.println("The score is: " + playerOne + " - " + redScore + ", " + playerTwo + " - " + greenScore);
            System.out.println("Do you want to play again? (yes or no)");
            answer = in.nextLine();
            
            //call playAgain method if players want to play again
            if (answer.equals("yes"))
            {
                System.out.println("Resetting... Please wait.");
                replay.playAgain(red, green);
            }
            //if not, output winner and end game
            else
            {
                if (redScore > greenScore)
                {
                    System.out.println("Congratulations " + playerOne + "! You beat " + playerTwo + "!");
                }
                else if (greenScore > redScore)
                {
                    System.out.println("Congratulations " + playerTwo + "! You beat " + playerOne + "!");
                }
                else
                {
                    System.out.println("TIE! Guess you're both equally intelligent...");
                }
                System.out.println("Bye! Thanks for playing!");
                break;
            }
            
        } while (answer.equals("yes"));     //loop while players want to play again
    }
}

