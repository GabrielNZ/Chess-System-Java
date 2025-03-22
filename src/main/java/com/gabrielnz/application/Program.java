package com.gabrielnz.application;
import com.gabrielnz.chess.ChessMatch;
import com.gabrielnz.chess.ChessPiece;
import com.gabrielnz.chess.ChessPosition;
import com.gabrielnz.chess.exceptions.ChessException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch match = new ChessMatch();
        while(true) {
            try {
                UI.clearScreen();
                UI.printBoard(match.getPieces());
                System.out.println();
                System.out.print("Position: ");
                ChessPosition position = UI.readChessPosition(sc);
                System.out.println();
                System.out.print("Destination: ");
                ChessPosition destination = UI.readChessPosition(sc);
                ChessPiece capturedPiece = match.performChessMove(position, destination);
            }catch (ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }catch(InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
    }
}