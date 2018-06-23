package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.service.CarDataService;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Controller
@RequestMapping("/vehicles")
public class CarDataController {

    private final CarDataService carDataService;

    public CarDataController(CarDataService carDataService) {
        this.carDataService = carDataService;
    }

    @RequestMapping("/{vehicleId}")
    public String printVehicle(
            @PathVariable("vehicleId") Long vehicleId,
            Model model) {
        Vehicle vehicle = carDataService.getVehicleById(vehicleId);
        if (vehicle != null) {
            model.addAttribute("vehicle", vehicle);
        }
        return "vehicleDetails";
    }

    public void printAvailableCars() {
        List<Vehicle> vehicles = carDataService.loadCarsThatCanBeSold();
        vehicles.sort(Comparator.comparing(Vehicle::getId));
        vehicles.forEach(vehicle -> {
            System.out.println(String.format("%d: %s %s from %d with %s powered engine and total mileage of %d for only %d!",
                    vehicle.getId(),
                    vehicle.getManufacturer(),
                    vehicle.getModel(),
                    vehicle.getProductionYear(),
                    vehicle.getFuel(),
                    vehicle.getMileage(),
                    vehicle.getPrice()));
        });
    }

    public void createCar() {
        Vehicle vehicle = new Vehicle();
        System.out.println("Provide vehicle data:");
        System.out.print("   Manufacturer: ");
        vehicle.setManufacturer(readStringInput());
        System.out.print("   Model: ");
        vehicle.setModel(readStringInput());
        System.out.print("   Production Year: ");
        vehicle.setProductionYear(readLongInput());
        System.out.print("   Mileage: ");
        vehicle.setMileage(readLongInput());
        System.out.print("   Fuel: ");
        vehicle.setFuel(readStringInput());
        System.out.print("   Price: ");
        vehicle.setPrice(readLongInput());
        vehicle.setSold(false);

        carDataService.addVehicle(vehicle);
    }

    private String readStringInput() {
        return new Scanner(System.in).nextLine();
    }

    private Long readLongInput() {
        return Long.parseLong(new Scanner(System.in).nextLine());
    }

}
