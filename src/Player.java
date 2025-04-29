public class Player {
    private Integer id;
    private String name;
    private PieceType pieceType;

    public Player(Integer id, String name, PieceType pieceType){
        this.id = id;
        this.name = name;
        this.pieceType = pieceType;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
