import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GameBoard {

    private List<Player> playerList;
    private Integer totalMoves;
    private Integer completedMoves;
    private Integer row;
    private Integer column;
    private Cell[][] gameBoard;
    private Player currentPlayer;
    private Integer currentPlayerIndex;

    public GameBoard(Integer row, Integer column){
        this.playerList = new ArrayList<>();
        this.completedMoves = 0;
        this.row = row;
        this.column = column;
        this.totalMoves = this.row * this.column;
        this.gameBoard = new Cell[this.row][this.column];
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                this.gameBoard[i][j] = new Cell(i, j); // initializing every cell
            }
        }
    }

    public void addPlayer(Player player){
        System.out.println("Player: " + player.getName() + " is adding in the game");
        this.playerList.add(player);
    }

    public void startGame(Player player){
        for (int i = 0; i < this.playerList.size(); i++) {
            Player player1 = this.playerList.get(i);
            if (player1.getId().equals(player.getId())) {
                this.currentPlayerIndex = i;
                this.currentPlayer = player1;
                break;
            }
        }
        Boolean isGameTied = true;
        while(!this.totalMoves.equals(this.completedMoves)){
            Move move = this.takeMoveInput();
            this.makeMove(move);
            if(this.isGameOver(move)){
                isGameTied = false;
                break;
            }
            this.totalMoves++;
            this.moveToNextPlayer();
        }
        if(isGameTied){
            System.out.println("Game is tied.");
        }
        this.printBoard();
    }

    private void printBoard() {
        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                Cell cell = this.gameBoard[i][j];
                if (cell.getPlayer() == null) {
                    System.out.print("   ");
                } else {
                    System.out.print(" " + cell.getPlayer().getPieceType() + " ");
                }

                if (j < this.column - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();

            if (i < this.row - 1) {
                for (int j = 0; j < this.column; j++) {
                    System.out.print("---");
                    if (j < this.column - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println();
            }
        }
    }

    private void moveToNextPlayer(){
        this.currentPlayerIndex = (this.currentPlayerIndex + 1) % this.playerList.size();
        this.currentPlayer = this.playerList.get(this.currentPlayerIndex);
    }

    private Move takeMoveInput(){
        System.out.println("Player: " +  this.currentPlayer.getName() + " is currently playing");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                System.out.print("Enter row: ");
                int row = scanner.nextInt();

                System.out.print("Enter column: ");
                int column = scanner.nextInt();

                if(this.isMoveInvalid(row, column)){
                    System.out.println("Trying to access cell outside of Board, Enter the valid index");
                    continue;
                }

                if(this.isCellOccupied(row, column)){
                    System.out.println("This cell is already occupied");
                    continue;
                }

                Move move = new Move(row, column, this.currentPlayer);
                return move;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type, please enter Integer only");
                scanner.nextLine();
            }
        }
    }

    private Boolean isMoveInvalid(Integer row, Integer column){
        return row < 0 || row >= this.row || column < 0 || column >= this.column;
    }

    private Boolean isCellOccupied(Integer row, Integer column){
        return this.gameBoard[row][column].getPlayer() != null;
    }

    private void makeMove(Move move){
        System.out.println("Making the move at row: " + move.getRow() + " and column: " + move.getColumn() + " for player: " + this.currentPlayer.getName());
        Integer row = move.getRow();
        Integer column = move.getColumn();
        Cell cell = new Cell(row, column);
        cell.setPlayer(this.currentPlayer);
        this.gameBoard[row][column] = cell;
    }

    private Boolean isGameOver(Move move){
        Integer row = move.getRow();
        Integer column = move.getColumn();
        System.out.println("Checking game status after move at row: " + row + " and column: " + column);

        // Check the entire row
        boolean rowWin = true;
        for (int j = 0; j < this.column; j++) {
            if (gameBoard[row][j].getPlayer() != currentPlayer) {
                rowWin = false;
                break;
            }
        }
        if (rowWin) {
            System.out.println("Player: " + currentPlayer.getName() + " is Winner");
            return true;
        }

        // Check the entire column
        boolean columnWin = true;
        for (int i = 0; i < this.row; i++) {
            if (gameBoard[i][column].getPlayer() != currentPlayer) {
                columnWin = false;
                break;
            }
        }
        if (columnWin) {
            System.out.println("Player: " + currentPlayer.getName() + " is Winner");
            return true;
        }

        // Check main diagonal (only if move is on a main diagonal cell)
        if (row == column) {
            boolean mainDiagonalWin = true;
            for (int i = 0; i < Math.min(this.row, this.column); i++) {
                if (gameBoard[i][i].getPlayer() != currentPlayer) {
                    mainDiagonalWin = false;
                    break;
                }
            }
            if (mainDiagonalWin) {
                System.out.println("Player: " + currentPlayer.getName() + " is Winner");
                return true;
            }
        }

        // Check anti-diagonal (only if move is on anti-diagonal cell)
        if (row + column == this.column - 1) {
            boolean antiDiagonalWin = true;
            for (int i = 0; i < Math.min(this.row, this.column); i++) {
                if (gameBoard[i][(this.column - 1) - i].getPlayer() != currentPlayer) {
                    antiDiagonalWin = false;
                    break;
                }
            }
            if (antiDiagonalWin) {
                System.out.println("Player: " + currentPlayer.getName() + " is Winner");
                return true;
            }
        }

        System.out.println("Game is Still going.");
        return false;
    }
}
