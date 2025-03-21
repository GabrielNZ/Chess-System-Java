package com.gabrielnz.chess.pieces;

import com.gabrielnz.boardgame.Board;
import com.gabrielnz.chess.ChessPiece;
import com.gabrielnz.chess.Color;

public class King extends ChessPiece {

    public King(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }
}
