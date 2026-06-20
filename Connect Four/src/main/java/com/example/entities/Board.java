package com.example.entities;

import com.example.enums.DiscColor;

public class Board {
    private final static int row = 6;
    private final static  int col = 7;
    private DiscColor[][] grid;

    public Board(){
        this.grid = new DiscColor[row][col];
    }

    public void printBoard(){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                System.out.print(grid[i][j]==null?"_ ":grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isFull(){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==null) return false;
            }
        }
        return true;
    }

    public int placeDisc(int currCol, DiscColor discColor){
        int emptyRowIdx= findFirstEmptyRow(currCol);
        if(emptyRowIdx==-1) return -1;
        grid[emptyRowIdx][currCol]=discColor;
        return emptyRowIdx;
    }

    private int findFirstEmptyRow(int col){
        for(int i=row-1;i>=0;i--){
            if(grid[i][col]==null) return i;
        }
        return -1;
    }

    public DiscColor[][] getBoard(){
        return grid;
    }

    public int getRow(){
        return row;
    }

    public  int getCol(){return col;}
}
