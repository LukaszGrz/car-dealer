package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.spring.car_dealer.model.PurchaseFormData;
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
    public String getCar(
            @PathVariable("vehicleId") Long vehicleId,
            Model model) {
        Vehicle vehicle = carDataService.getVehicleById(vehicleId);
        if (vehicle != null) {
            model.addAttribute("vehicle", vehicle);
        }
        return "vehicleDetails";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printAvailableCars(Model model) {
        List<Vehicle> vehicles = carDataService.loadCarsThatCanBeSold();
        model.addAttribute("vehicleList", vehicles);
        return "vehicleList";
    }

    @GetMapping("/new")
    public String addCarForm(Model model) {
        model.addAttribute("addedVehicle", new Vehicle());
        return "addVehicle";
    }

    @PostMapping
    public String saveVehicle(
            @ModelAttribute("addedVehicle") Vehicle vehicleToBeSaved) {
        carDataService.addVehicle(vehicleToBeSaved);
        return "redirect:/vehicles";
    }

    @GetMapping("/{vehicleId}/sell")
    public String sellVehicleForm(
            @PathVariable("vehicleId") Long vehicleId,
            Model model) {
        model.addAttribute("vehicleId", vehicleId);
        model.addAttribute("sellData", new PurchaseFormData());
        return "sellVehicle";
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
