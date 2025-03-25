package com.gabrielnz.chess;

import com.gabrielnz.boardgame.Board;
import com.gabrielnz.boardgame.Piece;
import com.gabrielnz.boardgame.Position;
import com.gabrielnz.chess.exceptions.ChessException;
import com.gabrielnz.chess.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Color.WHITE;
        inicialSetup();
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);

        if (testCheck(currentPlayer)) {
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can't put yourself in check");
        }

        check = (testCheck(opponent(currentPlayer))) ? true : false;

        if(testCheckMate(opponent(currentPlayer))) {
            checkMate = true;
        }else{
            nextTurn();
        }
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        ChessPiece p = (ChessPiece) board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);

        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }

        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("There is no piece on source position");
        }
        if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
            throw new ChessException("The chosen piece is not yours");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.piece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color) {
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color) {
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color).toList();
        for (Piece p : list) {
            if (p instanceof King) {
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("There is no " + color + " king on the board");
    }

    private boolean testCheck(Color color) {
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == opponent(color)).toList();
        for (Piece p : opponentPieces) {
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color) {
        if (!testCheck(color)) {
            return false;
        }
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).toList();
        for (Piece p : list) {
            boolean[][] mat = p.possibleMoves();
            for (int i=0; i<board.getRows(); i++) {
                for (int j=0; j<board.getColumns(); j++) {
                    if (mat[i][j]) {
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!testCheck) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    private void placeNewPiece(int row, char column, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(row, column).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void inicialSetup() {

        placeNewPiece(1, 'a', new Rook(board, Color.WHITE));
        placeNewPiece(1, 'h', new Rook(board, Color.WHITE));
        placeNewPiece(8, 'a', new Rook(board, Color.BLACK));
        placeNewPiece(8, 'h', new Rook(board, Color.BLACK));

        placeNewPiece(1, 'e', new King(board, Color.WHITE));
        placeNewPiece(8, 'e', new King(board, Color.BLACK));

        placeNewPiece(1, 'd', new Queen(board, Color.WHITE));
        placeNewPiece(8, 'd', new Queen(board, Color.BLACK));

        placeNewPiece(1, 'b', new Knight(board, Color.WHITE));
        placeNewPiece(1, 'g', new Knight(board, Color.WHITE));
        placeNewPiece(8, 'b', new Knight(board, Color.BLACK));
        placeNewPiece(8, 'g', new Knight(board, Color.BLACK));

        placeNewPiece(2, 'a', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'b', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'c', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'd', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'e', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'f', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'g', new Pawn(board, Color.WHITE));
        placeNewPiece(2, 'h', new Pawn(board, Color.WHITE));

        placeNewPiece(7, 'a', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'b', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'c', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'd', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'e', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'f', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'g', new Pawn(board, Color.BLACK));
        placeNewPiece(7, 'h', new Pawn(board, Color.BLACK));


        placeNewPiece(1, 'c', new Bishop(board, Color.WHITE));
        placeNewPiece(1, 'f', new Bishop(board, Color.WHITE));
        placeNewPiece(8, 'c', new Bishop(board, Color.BLACK));
        placeNewPiece(8, 'f', new Bishop(board, Color.BLACK));
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean isCheckMate() {
        return checkMate;
    }
}
