package com.gabrielnz.chess.pieces;

import com.gabrielnz.boardgame.Board;
import com.gabrielnz.chess.ChessPiece;
import com.gabrielnz.chess.Color;

public class Pawn extends ChessPiece {

    public Pawn(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }
}
