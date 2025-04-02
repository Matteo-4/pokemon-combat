package pokemon.type;

public enum EnumType {
    FIRE("Fire"),
    WATER("Water"),
    PLANT("Plant"),
    ELECTRIC("electric");

    private final String typeName;

    EnumType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public double getBonus(EnumType opponentType) {
        if (opponentType == null) return 1.0;

        switch (this) {
            case FIRE:
                return opponentType == PLANT ? 1.5 : 1.0;
            case WATER:
                return opponentType == FIRE ? 1.5 : 1.0;
            case PLANT:
                return opponentType == WATER ? 1.5 : 1.0;
            default:
                return 1.0;
        }
    }
}

