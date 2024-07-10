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
public class Snake{
    int head;
    int tail;

    Snake(int head, int tail){
        this.head = head;
        this.tail = tail;
    }

    void setTail(int tail){
        this.tail = tail;
    }
    void setHead(int head){
        this.head = head;
    }
    int getTail(){
        return this.tail;
    }
    int getHead(){
        return this.head;
    }
}