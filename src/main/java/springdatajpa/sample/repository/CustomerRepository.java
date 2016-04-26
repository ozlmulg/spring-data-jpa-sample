package springdatajpa.sample.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import springdatajpa.sample.entity.Customer;

/**
 * @author ozlem.ulag
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

   List<Customer> findByLastName(String lastName);

}
