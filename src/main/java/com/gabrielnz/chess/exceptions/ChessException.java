package com.gabrielnz.chess.exceptions;
import com.gabrielnz.boardgame.exceptions.BoardException;

public class ChessException extends BoardException {
    public ChessException(String message) {
        super(message);
    }
}
