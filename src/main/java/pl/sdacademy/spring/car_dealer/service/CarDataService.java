package pl.sdacademy.spring.car_dealer.service;

import pl.sdacademy.spring.car_dealer.model.Vehicle;

import java.util.List;

public interface CarDataService {
    List<Vehicle> loadCarsThatCanBeSold();
    Vehicle addVehicle(Vehicle newVehicleToBeSaved);
    Vehicle getVehicleById(Long vehicleId);
}
