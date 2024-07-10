/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : Q
 * Group    : 12
 * Members  :
 * 1. 5026231187 - Marvello Adipertama 
 * 2. 5999232029 - Gaspard Lucien Jolly
 * 3. 5999232024 - Aurelien Etienne
 * ------------------------------------------------------
 */
public class Player{
    //states
    private String name;
    private int position;

    //constructor method
    public Player (String name){
        this.name=name;
    }
    //setter methods

    public void setName (String name){
        this.name=name;
    }

    public void setPosition(int position){
        this.position = position;
    }

    //getter methods
    public String getName() {
        return this.name;
    }


    public int getPosition() {
        return this.position;
    }

    //another method
    public int rollDice()
    {
        int roll = (int)((Math.random() * 6) + 1);

        if (roll == 6) {
            roll += rollDice();
        }

        return roll;
    }

    public void moveAround(int x, int boardSize)
    {
        if (this.position + x > boardSize){
            this.position = (boardSize - this.position) + (boardSize - x);
        }
        else {
            this.position = this.position + x;

        }
    }
}