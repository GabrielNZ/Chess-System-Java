package com.gabrielnz.chess;

import com.gabrielnz.boardgame.Board;
import com.gabrielnz.boardgame.Position;
import com.gabrielnz.chess.pieces.King;
import com.gabrielnz.chess.pieces.Rook;

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
    }
}
