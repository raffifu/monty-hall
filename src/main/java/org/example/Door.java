package org.example;

import org.example.constant.Prize;

public class Door {
    private int number;
    private Prize prize;
    private boolean isOpen;

    public Door(int number) {
        this.number = number + 1;
        this.prize = Prize.GOAT;
        this.isOpen = false;
    }

    @Override
    public String toString() {
        return isOpen ? prize.getCard()
                : "+------+\n" +
                "|      |\n" +
                "|   " + number + "  |\n" +
                "|      |\n" +
                "|      |\n" +
                "|      |\n" +
                "+------+";
    }

    public void setPrize(Prize prize) {
        this.prize = prize;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public int getNumber() {
        return number;
    }

    public Prize getPrize() {
        return prize;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
