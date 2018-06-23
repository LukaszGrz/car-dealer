package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.sdacademy.spring.car_dealer.model.Vehicle;

import java.util.Optional;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    @Query("SELECT v FROM Vehicle v WHERE v.id = :vehId AND v.sold = false")
    Optional<Vehicle> findNotSoldVehicle(@Param("vehId") Long id);
}
