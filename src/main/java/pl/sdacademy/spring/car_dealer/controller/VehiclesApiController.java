package pl.sdacademy.spring.car_dealer.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.VehicleRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehiclesApiController {

    private final VehicleRepository vehicleRepository;

    public VehiclesApiController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Vehicle get(@PathVariable("vehicleId") Long vehicleId) {
        return vehicleRepository.findOne(vehicleId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Vehicle> getAll() {
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Vehicle add(@RequestBody Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @PutMapping(
            value = "/{vehicleId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Vehicle update(
            @PathVariable("vehicleId") Long vehicleId,
            @RequestBody Vehicle vehicle) {
        Vehicle vehicleToBeUpdated = vehicleRepository.findOne(vehicleId);
        vehicleToBeUpdated.setManufacturer(vehicle.getManufacturer());
        vehicleToBeUpdated.setModel(vehicle.getModel());
        vehicleToBeUpdated.setMileage(vehicle.getMileage());
        vehicleToBeUpdated.setProductionYear(vehicle.getProductionYear());
        vehicleToBeUpdated.setPrice(vehicle.getPrice());
        vehicleToBeUpdated.setFuel(vehicle.getFuel());
        vehicleToBeUpdated.setSold(vehicle.isSold());
        return vehicleRepository.save(vehicleToBeUpdated);
    }

}
