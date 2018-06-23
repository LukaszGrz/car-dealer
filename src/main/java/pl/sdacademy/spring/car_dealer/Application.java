package pl.sdacademy.spring.car_dealer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.sdacademy.spring.car_dealer.controller.CarDataController;
import pl.sdacademy.spring.car_dealer.controller.SellingController;

import javax.annotation.PostConstruct;
import java.util.Scanner;

@Component
public class Application {

    @Autowired
    private CarDataController carDataController;
    @Autowired
    private SellingController sellingController;

    public void start() {
        Long choice = -1L;
        while (choice != 9L) {
            printMenu();
            choice = readInput();
            switch (choice.intValue()) {
                case 1:
                    carDataController.printAvailableCars();
                    break;
                case 2:
                    System.out.print("Which car you want to sell? ");
                    Long vehicleId = readInput();
                    sellingController.buyVehicle(vehicleId);
                    break;
                case 3:
                    carDataController.createCar();
                    break;
                case 4:
                    sellingController.showPurchaseHistory();
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
        }

    }

    private void printMenu() {
        System.out.println();
        System.out.println();
        System.out.println("Welcome in Car Dealer application! What you want to do?");
        System.out.println("1) Show Vehicles");
        System.out.println("2) Sell Vehicle");
        System.out.println("3) Add Vehicle");
        System.out.println("4) Show customer history");
        System.out.println("9) Exit");
        System.out.print("What is your choice? ");
    }

    private Long readInput() {
        try {
            return Long.parseLong(new Scanner(System.in).nextLine());
        } catch (NumberFormatException e) {
            return -1L;
        }
    }
}
