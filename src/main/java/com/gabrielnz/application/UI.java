package com.gabrielnz.application;

import com.gabrielnz.boardgame.Board;
import com.gabrielnz.chess.ChessPiece;

public class UI {
    public static void printBoard(ChessPiece[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print(8 - i+" ");
            for (int j = 0; j < board.length; j++) {
                printPiece(board[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    public static void printPiece(ChessPiece piece) {
        if(piece == null) {
            System.out.print("-");
        }else{
            System.out.println(piece);
        }
        System.out.print(" ");
    }
}
