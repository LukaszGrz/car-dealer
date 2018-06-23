package pl.sdacademy.spring.car_dealer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.spring.car_dealer.model.Customer;
import pl.sdacademy.spring.car_dealer.model.Purchase;
import pl.sdacademy.spring.car_dealer.model.Vehicle;
import pl.sdacademy.spring.car_dealer.repository.CustomerRepository;
import pl.sdacademy.spring.car_dealer.repository.PurchaseRepository;
import pl.sdacademy.spring.car_dealer.repository.VehicleRepository;

import java.util.Date;
import java.util.List;
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

    @Transactional
    public Purchase sell(Long vehicleId, final Customer customer, Long price) {
//        Optional<Vehicle> notSoldVehicle =
//                vehicleRepository.findNotSoldVehicle(vehicleId);
//        Purchase purchase = notSoldVehicle
//                .map(vehicle -> performSell(vehicle, customer, price))
//                .orElse(null);
//        return purchase;
        return vehicleRepository.findNotSoldVehicle(vehicleId)
                .map(vehicle -> performSell(vehicle, customer, price))
                .orElse(null);
    }

    @Override
    public List<Purchase> loadHistory(String documentNo) {
        return purchaseRepository.findByCustomerDocumentNo(documentNo);
    }

    private Purchase performSell(Vehicle veh, Customer customer, Long price) {
        veh.setSold(true);
        vehicleRepository.save(veh);
        // z repozytorium spróbuj pobrać klienta po jego numerze dokumentu
        Customer persistedCustomer = customerRepository
                .findByDocumentNo(customer.getDocumentNo())
                // a jeżeli nie było klienta z takim numerem dokumentu w bazie
                // to użyj danych klienta, które dopiero co otrzymaliśmy
                .orElseGet(() -> customerRepository.save(customer));
        if (true) {
            throw new RuntimeException("Przerwanie transakcji");
        }
        Purchase purchase = new Purchase();
        purchase.setVehicle(veh);
        purchase.setCustomer(persistedCustomer);
        purchase.setDate(new Date());
        purchase.setPrice(price);
        return purchaseRepository.save(purchase);
    }
}
