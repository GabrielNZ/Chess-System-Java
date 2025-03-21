package com.gabrielnz.chess.pieces;
import com.gabrielnz.boardgame.Board;
import com.gabrielnz.chess.ChessPiece;
import com.gabrielnz.chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }
}
