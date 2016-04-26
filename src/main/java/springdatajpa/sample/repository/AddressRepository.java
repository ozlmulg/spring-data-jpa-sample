package springdatajpa.sample.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import springdatajpa.sample.entity.Address;
import springdatajpa.sample.entity.Customer;

/**
 * @author ozlem.ulag
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

   List<Address> findByCustomer(Customer customer);
}
