public class Move {
    private Integer row;
    private Integer column;
    private Player player;

    public Move(Integer row, Integer column, Player player){
        this.row = row;
        this.column = column;
        this.player = player;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    public Player getPlayer() {
        return player;
    }
}
