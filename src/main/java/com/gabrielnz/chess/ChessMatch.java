package com.gabrielnz.chess;

import com.gabrielnz.boardgame.Board;
import com.gabrielnz.boardgame.Position;
import com.gabrielnz.chess.pieces.*;

public class ChessMatch {
    private final Board board;

    public ChessMatch() {
        this.board = new Board(8,8);
        inicialSetup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] pieces = new ChessPiece[board.getRows()][board.getColumns()];
        for(int i = 0; i < board.getRows(); i++) {
            for(int j = 0; j < board.getColumns(); j++) {
                pieces[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return pieces;
    }

    private void inicialSetup(){
        board.placePiece(new Rook(board, Color.WHITE), new Position(7,0));
        board.placePiece(new Rook(board, Color.WHITE), new Position(7,7));
        board.placePiece(new Rook(board, Color.BLACK), new Position(0,0));
        board.placePiece(new Rook(board, Color.BLACK), new Position(0,7));

        board.placePiece(new King(board, Color.WHITE), new Position(7,4));
        board.placePiece(new King(board, Color.BLACK), new Position(0,4));

        board.placePiece(new Queen(board, Color.WHITE), new Position(7,3));
        board.placePiece(new Queen(board, Color.BLACK), new Position(0,3));

        board.placePiece(new Knight(board, Color.WHITE), new Position(7,1));
        board.placePiece(new Knight(board, Color.WHITE), new Position(7,6));
        board.placePiece(new Knight(board, Color.BLACK), new Position(0,1));
        board.placePiece(new Knight(board, Color.BLACK), new Position(0,6));

        board.placePiece(new Pawn(board, Color.WHITE), new Position(6,0));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6,1));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6,2));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6,3));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6,4));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6,5));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6,6));
        board.placePiece(new Pawn(board, Color.WHITE), new Position(6,7));

        board.placePiece(new Pawn(board, Color.BLACK), new Position(1,0));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1,1));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1,2));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1,3));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1,4));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1,5));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1,6));
        board.placePiece(new Pawn(board, Color.BLACK), new Position(1,7));

        board.placePiece(new Bishop(board, Color.WHITE), new Position(7,2));
        board.placePiece(new Bishop(board, Color.WHITE), new Position(7,5));
        board.placePiece(new Bishop(board, Color.BLACK), new Position(0,2));
        board.placePiece(new Bishop(board, Color.BLACK), new Position(0,5));


    }
}
