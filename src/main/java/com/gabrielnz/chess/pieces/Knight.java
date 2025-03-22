package com.gabrielnz.chess.pieces;

import com.gabrielnz.boardgame.Board;
import com.gabrielnz.chess.ChessPiece;
import com.gabrielnz.chess.Color;

public class Knight extends ChessPiece {

    public Knight(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "K";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
    }
}
