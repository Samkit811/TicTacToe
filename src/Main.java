public class Main {
    public static void main(String[] args) {

        System.out.println("--------- Start ------------");

        Player player1 = new Player(0, "Alice", PieceType.CIRCLE);
        Player player2 = new Player(1, "Bob", PieceType.CROSS);
        GameBoard gameBoard = new GameBoard(3,3);
        gameBoard.addPlayer(player1);
        gameBoard.addPlayer(player2);
        gameBoard.startGame(player1);

        System.out.println("--------- End -------------");
    }
}