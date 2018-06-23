package pl.sdacademy.spring.car_dealer.service;

import org.springframework.stereotype.Service;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.model.Purchase;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.CustomerRepository;
import pl.sdacademy.spring.car_dealer.repository.PurchaseRepository;
import pl.sdacademy.spring.car_dealer.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class DefaultSellingService implements SellingService {

    private VehicleRepository vehicleRepository;
    private CustomerRepository customerRepository;
    private PurchaseRepository purchaseRepository;

    public DefaultSellingService(
            VehicleRepository vehicleRepository,
            CustomerRepository customerRepository,
            PurchaseRepository purchaseRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
        this.purchaseRepository = purchaseRepository;
    }

    public Purchase sell(Long vehicleId, final Customer customer, Long price) {
//        Optional<Vehicle> notSoldVehicle =
//                vehicleRepository.findNotSoldVehicle(vehicleId);
//        Purchase purchase = notSoldVehicle
//                .map(vehicle -> perfromSell(vehicle, customer, price))
//                .orElse(null);
//        return purchase;
        return vehicleRepository.findNotSoldVehicle(vehicleId)
                .map(vehicle -> perfromSell(vehicle, customer, price))
                .orElse(null);
    }

    private Purchase perfromSell(Vehicle veh, Customer customer, Long price) {
        veh.setSold(true);
        vehicleRepository.save(veh);
        Customer persistedCustomer = customerRepository.save(customer);
        Purchase purchase = new Purchase();
        purchase.setVehicle(veh);
        purchase.setCustomer(persistedCustomer);
        purchase.setDate(new Date());
        purchase.setPrice(price);
        return purchaseRepository.save(purchase);
    }
}
