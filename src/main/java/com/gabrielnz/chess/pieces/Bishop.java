package com.gabrielnz.chess.pieces;

import com.gabrielnz.boardgame.Board;
import com.gabrielnz.chess.ChessPiece;
import com.gabrielnz.chess.Color;

public class Bishop extends ChessPiece {

    public Bishop(Board board, Color color) {
        super(board, color);
    }

    public String toString() {
        return "B";
    }
}
