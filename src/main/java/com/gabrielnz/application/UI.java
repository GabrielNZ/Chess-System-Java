package com.gabrielnz.application;

import com.gabrielnz.chess.ChessPiece;
import com.gabrielnz.chess.ChessPosition;
import com.gabrielnz.chess.Color;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // https://stackoverflow.com/questions/2979383/java-clear-the-console
    public static void clearScreen() {
        for(int i = 0; i < 50; i++){
            System.out.println();
        }
    }

    public static ChessPosition readChessPosition(Scanner sc) {
       try {
           String s = sc.nextLine();
           char column = s.charAt(0);
           int row = Integer.parseInt(s.substring(1));
           return new ChessPosition(row, column);
       }catch (RuntimeException e) {
           throw new InputMismatchException("Error reading ChessPosition. Valid values are from a1 to h8.");
       }
    }

    public static void printBoard(ChessPiece[][] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print(8 - i + " ");
            for (int j = 0; j < board.length; j++) {
                printPiece(board[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    public static void printPiece(ChessPiece piece) {
        if (piece == null) {
            System.out.print("-");
        } else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
}
