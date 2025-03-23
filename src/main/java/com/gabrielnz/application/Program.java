package com.gabrielnz.application;
import com.gabrielnz.chess.ChessMatch;
import com.gabrielnz.chess.ChessPiece;
import com.gabrielnz.chess.ChessPosition;
import com.gabrielnz.chess.exceptions.ChessException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch match = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<ChessPiece>();
        while(true) {
            try {
                UI.clearScreen();
                UI.printMatch(match, captured);
                System.out.println();
                System.out.print("Position: ");
                ChessPosition position = UI.readChessPosition(sc);

                boolean[][] possibleMoves = match.possibleMoves(position);
                UI.clearScreen();
                UI.printBoard(match.getPieces(), possibleMoves);
                System.out.println();
                System.out.print("Destination: ");
                ChessPosition destination = UI.readChessPosition(sc);
                ChessPiece capturedPiece = match.performChessMove(position, destination);
                if(capturedPiece != null) {
                    captured.add(capturedPiece);
                }
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