package org.example.constant;

public enum Prize {
    GOAT("+------+\n" +
            "|  ((  |\n" +
            "|  oo  |\n" +
            "| /_/|_|\n" +
            "|    | |\n" +
            "|GOAT|||\n" +
            "+------+"),
    CAR("+------+\n" +
            "| CAR! |\n" +
            "|    __|\n" +
            "|  _/  |\n" +
            "| /_ __|\n" +
            "|   O  |\n" +
            "+------+");

    private final String card;

    Prize(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }
}
