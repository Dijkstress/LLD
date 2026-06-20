package com.example.entities;

import com.example.enums.DiscColor;
import com.example.enums.GameState;

import java.util.Objects;
import java.util.Scanner;

public class Game {
    private final Scanner sc = new Scanner(System.in);
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private GameState state;
    private Player winner;

    public Game(Player player1, Player player2){
        this.board = new Board();
        this.player1=player1;
        this.player2=player2;
        this.currentPlayer=player1;
        this.state=GameState.IN_PROGRESS;
    }

    public void start(){
        printBoard();
        while(state.equals(GameState.IN_PROGRESS)){
            System.out.print(currentPlayer.getName() + " - Enter col:");
            int col = sc.nextInt();
            col --;
            if(!isValidCol(col)){
                System.out.println("Not a valid cell");
                continue;
            }
            int row  = board.placeDisc(col,currentPlayer.getColor());
            if(row==-1){
                System.out.println("Select a different cell");
                continue;
            }
            printBoard();
            if(check(row,col,currentPlayer)){
                setState(GameState.WON);
                winner=currentPlayer;
                System.out.println(winner.getName() + " : " + winner.getColor() + " wins!!!");
            }
            if(board.isFull()){
                setState(GameState.DRAW);
            }
            if(Objects.equals(currentPlayer.getName(), player1.getName())){
                currentPlayer=player2;
            }else{
                currentPlayer=player1;
            }
        }
    }

    private boolean isValidCol(int currCol){
        return currCol>=0 && currCol< board.getCol();
    }

    private boolean isValidCell(int currRow,int currCol){
        int row = board.getRow();
        int col = board.getCol();
        return currRow >= 0 && currRow < row && currCol >= 0 && currCol < col;
    }

    private boolean check(int row,int col, Player currentPlayer){
        int[][] directions = {
                {0, 1},
                {1, 0},
                {1, 1},
                {-1, 1}
        };

        for(int[] dir : directions){
            int count = 1;
            int d_row = dir[0];
            int d_col = dir[1];
            count += countInDirection(row,col, d_row,d_col,currentPlayer.getColor());
            count += countInDirection(row,col,-d_row,-d_col,currentPlayer.getColor());
            if (count >= 4) {
                return true;
            }
        }

        return  false;
    }

    private int countInDirection(int row, int col, int dr, int dc, DiscColor color){
        int count = 0;
        int r = row + dr;
        int c = col + dc;
        while(isValidCell(r,c) && board.getBoard()[r][c]==color){
            count ++;
            r +=dr;
            c+=dc;
        }
        return count;
    }

    public void printBoard(){
        board.printBoard();
    }

    public void setState(GameState state) {
        this.state = state;
    }
}
