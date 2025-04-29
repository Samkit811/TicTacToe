public class Cell {
    private Integer row;
    private Integer column;
    private Player player;

    public Cell(Integer row, Integer column){
        this.row = row;
        this.column = column;
        this.player = null;
    }

    public Integer getColumn() {
        return column;
    }

    public Integer getRow() {
        return row;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
