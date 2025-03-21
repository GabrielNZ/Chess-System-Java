package com.gabrielnz.chess;

import com.gabrielnz.boardgame.Position;
import com.gabrielnz.chess.exceptions.ChessException;

public class ChessPosition {

    private char column;
    private int row;

    public ChessPosition(char row, char column) {
        if (column < 'a' || column > 'h' || row < 1 || row > 8) {
            throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8.");
        }
        this.column = column;
        this.row = row;
    }

    protected Position toPosition(){
        return new Position(8 - row, column - 'a');
    }
    protected static ChessPosition fromPosition(Position pos){
        return new ChessPosition((char) (8 - pos.getRow()), (char) ('a' - pos.getColumn()));
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return ""+column+row;
    }
}
