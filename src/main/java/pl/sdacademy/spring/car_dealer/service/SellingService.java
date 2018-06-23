package pl.sdacademy.spring.car_dealer.service;

import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.model.Purchase;

import java.util.List;

public interface SellingService {
    Purchase sell(Long vehicleId, Customer customer, Long price);
    List<Purchase> loadHistory(String documentNo);
}
