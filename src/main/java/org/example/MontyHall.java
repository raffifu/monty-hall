package org.example;

import org.example.service.MontyHallService;

public class MontyHall implements Game {
    private final MontyHallService montyHallService;
    private int numberOfAttempt;

    public MontyHall() {
        montyHallService = new MontyHallService();
        montyHallService.initializeDoor();
    }

    @Override
    public void run() {
        greeting();
        show();

        Door result;
        while (numberOfAttempt != 2) {
            readInput();
            result = openDoor();
            show();
            System.out.println("Door number " + result.getNumber() + " contains " + result.getPrize());
            numberOfAttempt++;
        }

        result();
        quit();
    }

    @Override
    public void greeting() {
        System.out.println(
                "• ▌ ▄ ·.        ▐ ▄ ▄▄▄▄▄ ▄· ▄▌     ▄ .▄ ▄▄▄· ▄▄▌  ▄▄▌  \n" +
                "·██ ▐███▪▪     •█▌▐█•██  ▐█▪██▌    ██▪▐█▐█ ▀█ ██•  ██•  \n" +
                "▐█ ▌▐▌▐█· ▄█▀▄ ▐█▐▐▌ ▐█.▪▐█▌▐█▪    ██▀▐█▄█▀▀█ ██▪  ██▪  \n" +
                "██ ██▌▐█▌▐█▌.▐▌██▐█▌ ▐█▌· ▐█▀·.    ██▌▐▀▐█ ▪▐▌▐█▌▐▌▐█▌▐▌\n" +
                "▀▀  █▪▀▀▀ ▀█▄▀▪▀▀ █▪ ▀▀▀   ▀ •     ▀▀▀ · ▀  ▀ .▀▀▀ .▀▀▀ \n"
                );
        System.out.println("Welcome, You have 3 doors, Only one of the doors has a car\n" +
                "you have to choose one of the doors.");
    }

    @Override
    public void show() {
        montyHallService.showHorizontalCard();
    }

    @Override
    public void readInput() {
        if (numberOfAttempt == 0) {
            montyHallService.selectDoor();
        } else if (numberOfAttempt == 1) {
            if (montyHallService.switchConfirmation())
                montyHallService.switchDoor();
        }
    }

    private Door openDoor() {
        if (numberOfAttempt == 0) {
            return montyHallService.openRandomDoor();
        } else {
            return montyHallService.openAllDoor();
        }
    }

    @Override
    public void result() {
        if (montyHallService.gameStatus()) {
            System.out.println("You won!");
        } else {
            System.out.println("You lost!");
        }
    }

    @Override
    public void quit() {
        montyHallService.close();
        System.exit(0);
    }
}

