package pl.sdacademy.spring.car_dealer.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.sdacademy.spring.car_dealer.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.documentNo = :docNoParam")
    Optional<Customer> findByDocumentNo(@Param("docNoParam") String documentNo);
}
