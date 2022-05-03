package org.example.service;

import org.example.Door;
import org.example.constant.Color;
import org.example.constant.Prize;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MontyHallService {
    private ArrayList<Door> doorPrizes;
    private Integer doorThatHasPrize;
    private int selectedDoor;
    private Scanner scanner;

    public void initializeDoor() {
        doorPrizes = new ArrayList<>();
        scanner = new Scanner(System.in);

        int total = 3;
        for (int i = 0; i < total; i++) {
            doorPrizes.add(new Door(i));
        }

        doorThatHasPrize = new Random().nextInt(total);
        doorPrizes.get(doorThatHasPrize).setPrize(Prize.CAR);
    }

    public void showHorizontalCard() {
        ArrayList<String[]> cardView = new ArrayList<>();

        for (Door door : doorPrizes) {
            cardView.add(door.toString().split("\n"));
        }

        for (int i = 0; i < cardView.get(0).length; i++) {
            int numOfCard = 1;
            for (String[] card : cardView) {
                String text = card[i] + " ";

                if (numOfCard == selectedDoor)
                    System.out.print(Color.YELLOW + text + Color.RESET);
                else
                    System.out.print(text);

                numOfCard++;
            }
            System.out.println();
        }
    }

    public void selectDoor() {
        System.out.print("Choose a door (1-3): ");
        if (scanner.hasNext("[1-3]"))
            selectedDoor = Integer.parseInt(scanner.next("[1-3]"));
        else {
            scanner.nextLine();
            System.out.println("Invalid input");
            selectDoor();
        }
    }

    public void switchDoor() {
        for (Door door : doorPrizes) {
            if (!door.isOpen() && door.getNumber() != selectedDoor) {
                selectedDoor = door.getNumber();
                break;
            }
        }
    }

    public boolean switchConfirmation() {
        System.out.print("Do you want to switch door? (y/n): ");
        if (scanner.hasNext("[yYnN]")) {
            String answer = scanner.next("[yYnN]");
            return answer.equals("y") || answer.equals("Y");
        } else {
            scanner.nextLine();
            System.out.println("Invalid input");
            return switchConfirmation();
        }
    }

    public Door openRandomDoor() {
        while (true) {
            int doorToOpen = new Random().nextInt(doorPrizes.size());
            Door door = doorPrizes.get(doorToOpen);
            if (!door.isOpen() && door.getNumber() != selectedDoor && door.getPrize() != Prize.CAR) {
                door.setOpen(true);
                return door;
            }
        }
    }

    public Door openAllDoor() {
        for (Door door : doorPrizes) {
            if (!door.isOpen()) {
                door.setOpen(true);
            }
        }

        return doorPrizes.get(doorThatHasPrize);
    }

    public boolean gameStatus() {
        Door doorThatHasPrize = doorPrizes.get(selectedDoor - 1);
        return doorThatHasPrize.isOpen() && doorThatHasPrize.getPrize() == Prize.CAR;
    }

    public void close() {
        scanner.close();
    }

    public ArrayList<Door> getDoorPrizes() {
        return doorPrizes;
    }

    public int getSelectedDoor() {
        return selectedDoor;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
