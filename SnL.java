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

import java.util.ArrayList;
import java.util.Scanner;

public class SnL {

    //states, variable, or properties
    private int boardSize;
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int gameStatus;
    private int currentTurn;
    private int nb_player;

    //constructor
    public SnL (int size){
        this.boardSize = size;
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.players= new ArrayList<Player>();
        this.gameStatus = 0;
    }

    public void initiateGame(String standard){
        if(standard=="yes")
        {
            int [][] ladders =
                    {    {2, 23},
                            {8, 34},
                            {20, 77},
                            {32,68},
                            {41, 79},
                            {74, 88},
                            {82, 100},
                            {85, 95}
                    };
            setLadders(ladders);
            int [][] snakes =
                    {    {47, 5},
                            {29, 9},
                            {38, 15},
                            {97,25},
                            {53, 33},
                            {92, 70},
                            {86, 54},
                            {97, 25}
                    };
            setSnakes(snakes);
        }
        else
        {
            int [][] snakes= new int[8][2];
            int [][] ladders= new int[8][2];
            for(int i=0;i<8;i++)
            {
                int x=(int)(Math.random()*100)+1;
                int y=(int)(Math.random()*x)+1;
                snakes[i][0]=x;
                snakes[i][1]=y;
            }
            setSnakes(snakes);
            for(int i=0;i<8;i++)
            {
                int min=1;
                int max=100;
                int x=min+(int)(Math.random()*(max-min));
                min=x;
                int y=min +(int)(Math.random()*(max-min));
                ladders[i][0]=x;
                ladders[i][1]=y;
            }
            setLadders(ladders);
        }

    }

    public Player getTurn() {
        if (this.gameStatus == 0) {
            int r = (int)(Math.random()*this.nb_player);
            this.currentTurn=r;
        }
        this.currentTurn=(this.currentTurn+1)%this.nb_player;
        if(this.currentTurn==0)
        {
            return this.players.get(this.nb_player-1);
        }
        return this.players.get(this.currentTurn-1);
    }

    //setter methods
    public void setSizeBoard(int size){
        this.boardSize = size;
    }
    public void addPlayer(Player p){
        this.players.add(p);
    }
    public void setLadders(int[][] ladders){
        int s = ladders.length;
        for(int i = 0; i < s; i++){
            this.ladders.add(new Ladder(ladders[i][0],ladders[i][1]));
        }
    }

    public void setSnakes(int[][] snakes)
    {
        int s = snakes.length;
        for(int i = 0; i < s; i++){
            this.snakes.add(new Snake(snakes[i][0],snakes[i][1]));
        }
    }

    public int getBoardSize(){
        return this.boardSize;
    }
    public ArrayList<Player> getPlayers(){
        return this.players;
    }
    public ArrayList<Snake> getSnakes(){
        return this.snakes;
    }
    public ArrayList<Ladder> getLadders(){
        return this.ladders;
    }

    public int getGameStatus(){
        return this.gameStatus;
    }
    public void play(){
        Scanner sc= new Scanner(System.in);
        System.out.println("Do you want the default board? (yes/no)");
        String std=sc.nextLine();
        initiateGame(std);
        System.out.println("Enter the number of players:");
        this.nb_player=sc.nextInt();
        sc.nextLine();
        for(int i=1;i<=nb_player;i++)
        {
            System.out.println("Enter the name of the player number "+i+":");
            String name=sc.nextLine();
            Player p=new Player(name);
            addPlayer(p);
        }
        //System.out.println("enter first player name:");
        //String firstPlayer= sc.nextLine();
        //System.out.println("enter second player name:");
        //String secondPlayer= sc.nextLine();
        //Player p1 = new Player(firstPlayer);
        //Player p2 = new Player(secondPlayer);

        //addPlayer(p1);
        //addPlayer(p2);

        Player nowPlaying;
        int counter=0;
        do{
            if(counter==0)
            {
                showBoard();
            }
            System.out.println("----------------------------------------------");
            nowPlaying = getTurn();
            System.out.println("Now Playing: "+ nowPlaying.getName()+" the current position is "+nowPlaying.getPosition());
            System.out.println(nowPlaying.getName()+" it's your turn, please press enter to roll dice");

            String input= sc.nextLine();
            int x = 0;
            if (input.isEmpty()){
                x = nowPlaying.rollDice();
            }

            System.out.println(nowPlaying.getName()+ " is rolling dice and get number: "+x);
            movePlayer(nowPlaying, x);
            System.out.println(nowPlaying.getName()+ " new position is "+ nowPlaying.getPosition());
            counter=(counter+1)%this.nb_player;

        } while(getGameStatus()!=2);

        System.out.println("The Game is Over, the winner is: "+nowPlaying.getName());
    }

    public void movePlayer(Player p, int x){
        this.gameStatus=1;
        p.moveAround(x, this.boardSize);
        for(Ladder l: this.ladders){
            if(l.getFromPosition() == p.getPosition()){
                p.setPosition(l.getToPosition());
                System.out.println(p.getName()+" got ladder so jumps to "+p.getPosition());
            }
        }

        for(Snake s: this.snakes){
            if(s.getHead() == p.getPosition()){
                p.setPosition(s.getTail());
                System.out.println(p.getName()+" got snake so slide down to "+p.getPosition());
            }
        }

        //If a player arrive on the case of another player, the second player go back of 10 cases
        for(Player p2:this.players)
        {
            if(p2.getPosition()==p.getPosition()&&p2.getName()!=p.getName())
            {
                if((p2.getPosition()-10)>0)
                {
                    System.out.println(p.getName()+" arrives on "+p2.getName()+" case, "+p2.getName()+" go back of 10 cases!");
                    p2.setPosition(p2.getPosition()-10);
                    System.out.println(p2.getName()+"'s new position is: "+p2.getPosition());
                }
            }
        }

        if (p.getPosition()==this.boardSize){
            this.gameStatus=2;
        }
    }

    public void showBoard()
    {
        for(int i=5;i>0;i--)
        {
            for(int j=10;j>0;j--)
            {
                System.out.print("| ");
                int x=(i-1)*20+10+j;
                int found=0;
                for(int a=0;a<this.nb_player;a++)
                {
                    Player current=this.players.get(a);
                    if(current.getPosition()==x)
                    {
                        System.out.print(current.getName()+" ");
                        found=1;
                    }
                }
                if(found==0)
                {
                    System.out.print(x+" ");
                }
                found=0;
            }
            System.out.println();
            for(int k=1;k<=10;k++)
            {
                System.out.print("| ");
                int x=(i-1)*20+k;
                int found=0;
                for(int a=0;a<this.nb_player;a++)
                {
                    Player current=this.players.get(a);
                    if(current.getPosition()==x)
                    {
                        System.out.print(current.getName()+" ");
                        found=1;
                    }
                }
                if(found==0)
                {
                    System.out.print(x+" ");
                }
                found=0;
            }
            System.out.println();
        }
    }
}



