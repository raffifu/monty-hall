package org.example.service;

import org.example.Door;
import org.example.constant.Prize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.junit.jupiter.api.Assertions.*;

class MontyHallServiceTest {

    MontyHallService service = new MontyHallService();

    @BeforeEach
    void setUp() {
        service.initializeDoor();
    }

    @Test
    void initializeDoor_WillReturnValidDoors() {

        ArrayList<Door> doorPrizes = service.getDoorPrizes();

        assertNotNull(doorPrizes);
        assertEquals(3, doorPrizes.size());

        long totalCarPrize = doorPrizes
                .stream()
                .filter(door -> door.getPrize() == Prize.CAR)
                .count();

        assertEquals(1, totalCarPrize);
    }

    @Test
    void selectDoor_WillChangeIfMatchInput() throws Exception {
        withTextFromSystemIn("A", "3")
                .execute(() -> {
                    Scanner scanner = new Scanner(System.in);
                    service.setScanner(scanner);

                    service.selectDoor();
                    assertEquals(3, service.getSelectedDoor());
                });
    }

    @Test
    void switchConfirmation_WillReturnBoolean() throws Exception {
        withTextFromSystemIn("1", "y", "n")
                .execute(() -> {
                    Scanner scanner = new Scanner(System.in);
                    service.setScanner(scanner);

                    assertTrue(service.switchConfirmation());
                    assertFalse(service.switchConfirmation());
                });
    }

    @Test
    void switchDoor_WillChangeSelectedDoor() throws Exception {
        withTextFromSystemIn("1")
                .execute(() -> {
                    Scanner scanner = new Scanner(System.in);
                    service.setScanner(scanner);
                    service.selectDoor();
                    service.openRandomDoor();
                    int initialSelectedDoor = service.getSelectedDoor();

                    service.switchDoor();
                    int finalSelectedDoor = service.getSelectedDoor();
                    Door finalDoor = service.getDoorPrizes().get(finalSelectedDoor - 1);

                    assertFalse(finalDoor.isOpen());
                    assertNotEquals(initialSelectedDoor, finalSelectedDoor);
                });
    }

    @Test
    void openRandomDoor_WillReturnOpenedDoor() throws Exception {
        withTextFromSystemIn("1")
                .execute(() -> {
                    Scanner scanner = new Scanner(System.in);
                    service.setScanner(scanner);

                    service.selectDoor();
                    Door openedDoor = service.openRandomDoor();
                    assertNotNull(openedDoor);
                    assertTrue(openedDoor.isOpen());
                    assertNotEquals(Prize.CAR, openedDoor.getPrize());
                    assertNotEquals(service.getSelectedDoor(), openedDoor.getNumber());
                });
    }

    @Test
    void openAllDoor_WillReturnDoorThatHasPrize() {


        Door doorThatHasPrize = service.openAllDoor();
        assertNotNull(doorThatHasPrize);
        assertEquals(Prize.CAR, doorThatHasPrize.getPrize());
    }

    @Test
    void gameStatus_WillReturnTrueIfWin() throws Exception {

        Door doorThatHasPrize = service.getDoorPrizes()
                .stream()
                .filter(door -> door.getPrize() == Prize.CAR)
                .collect(Collectors.toList())
                .get(0);

        withTextFromSystemIn(String.valueOf(doorThatHasPrize.getNumber()))
                .execute(() -> {
                    Scanner scanner = new Scanner(System.in);
                    service.setScanner(scanner);

                    service.selectDoor();
                    service.openAllDoor();

                    boolean gameStatus = service.gameStatus();
                    int doorIndex = service.getSelectedDoor();
                    Door selectedDoor = service.getDoorPrizes().get(doorIndex - 1);

                    assertTrue(gameStatus);
                    assertTrue(selectedDoor.isOpen());
                    assertEquals(Prize.CAR, selectedDoor.getPrize());
                });
    }
}