package pl.sdacademy.spring.car_dealer.service;

import org.springframework.stereotype.Service;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.VehicleFinder;
import pl.sdacademy.spring.car_dealer.repository.VehicleRepository;

import java.util.List;

@Service
public class DefaultCarDataService implements CarDataService {
    private final VehicleRepository vehicleRepository;
    private final VehicleFinder vehicleFinder;

    public DefaultCarDataService(VehicleRepository vehicleRepository, VehicleFinder vehicleFinder) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleFinder = vehicleFinder;
    }

    public List<Vehicle> loadCarsThatCanBeSold() {
        return vehicleFinder.getAvailable();
    }

    @Override
    public Vehicle addVehicle(Vehicle newVehicleToBeSaved) {
        return vehicleRepository.save(newVehicleToBeSaved);
    }

    @Override
    public Vehicle getVehicleById(Long vehicleId) {
        return vehicleRepository.findOne(vehicleId);
    }
}
