package com.gabrielnz.chess;

import com.gabrielnz.boardgame.Board;
import com.gabrielnz.boardgame.Piece;
import com.gabrielnz.boardgame.Position;
import com.gabrielnz.chess.exceptions.ChessException;
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

    public ChessPiece performChessMove(ChessPosition position, ChessPosition destination) {
        Position positionStart = position.toPosition();
        Position positionDestination = destination.toPosition();
        validateSourcePosition(positionStart);
        Piece capturedPiece = makeMove(positionStart, positionDestination);
        return (ChessPiece) capturedPiece;
    }

    public Piece makeMove(Position position, Position destination) {
        Piece piece = board.removePiece(position);
        Piece capturedPiece = board.removePiece(destination);
        board.placePiece(piece, destination);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if(!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece at this position");
        }
        if(board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There are no possible moves at this position");
        }
    }

    private void placeNewPiece(int row, char column, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(row, column).toPosition());
    }

    private void inicialSetup(){
        placeNewPiece(1,'a',new Rook(board, Color.WHITE));
        placeNewPiece(1,'h',new Rook(board, Color.WHITE));
        placeNewPiece(8,'a',new Rook(board, Color.BLACK));
        placeNewPiece(8,'h',new Rook(board, Color.BLACK));

        placeNewPiece(1,'e',new King(board, Color.WHITE));
        placeNewPiece(8,'e',new King(board, Color.BLACK));

        placeNewPiece(1,'d',new Queen(board, Color.WHITE));
        placeNewPiece(8,'d',new Queen(board, Color.BLACK));

        placeNewPiece(1,'b',new Knight(board, Color.WHITE));
        placeNewPiece(1,'g',new Knight(board, Color.WHITE));
        placeNewPiece(8,'b',new Knight(board, Color.BLACK));
        placeNewPiece(8,'g',new Knight(board, Color.BLACK));

        placeNewPiece(2,'a',new Pawn(board, Color.WHITE));
        placeNewPiece(2,'b',new Pawn(board, Color.WHITE));
        placeNewPiece(2,'c',new Pawn(board, Color.WHITE));
        placeNewPiece(2,'d',new Pawn(board, Color.WHITE));
        placeNewPiece(2,'e',new Pawn(board, Color.WHITE));
        placeNewPiece(2,'f',new Pawn(board, Color.WHITE));
        placeNewPiece(2,'g',new Pawn(board, Color.WHITE));
        placeNewPiece(2,'h',new Pawn(board, Color.WHITE));

        placeNewPiece(7,'a',new Pawn(board, Color.BLACK));
        placeNewPiece(7,'b',new Pawn(board, Color.BLACK));
        placeNewPiece(7,'c',new Pawn(board, Color.BLACK));
        placeNewPiece(7,'d',new Pawn(board, Color.BLACK));
        placeNewPiece(7,'e',new Pawn(board, Color.BLACK));
        placeNewPiece(7,'f',new Pawn(board, Color.BLACK));
        placeNewPiece(7,'g',new Pawn(board, Color.BLACK));
        placeNewPiece(7,'h',new Pawn(board, Color.BLACK));


        placeNewPiece(1,'c',new Bishop(board, Color.WHITE));
        placeNewPiece(1,'f',new Bishop(board, Color.WHITE));
        placeNewPiece(8,'c',new Bishop(board, Color.BLACK));
        placeNewPiece(8,'f',new Bishop(board, Color.BLACK));


    }
}
