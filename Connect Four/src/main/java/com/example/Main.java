package com.example;

import com.example.entities.Game;
import com.example.entities.Player;
import com.example.enums.DiscColor;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("A", DiscColor.R);
        Player player2 = new Player("B", DiscColor.Y);

        Game game = new Game(player1,player2);
        game.start();
    }
}