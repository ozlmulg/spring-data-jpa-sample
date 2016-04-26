package springdatajpa.sample.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import springdatajpa.sample.entity.Customer;

/**
 * @author ozlem.ulag
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

   public final static String FIND_BY_NAME_QUERY = "SELECT c " +
                                                   "FROM Customer c " +
                                                   "WHERE c.firstName = :firstName AND c.lastName = :lastName";

   List<Customer> findByFirstName(String firstName);

   List<Customer> findByLastName(String lastName);

   List<Customer> findByFirstNameLike(String firstName);

   List<Customer> findByLastNameLike(String lastName);

   /**
    * Find persons by first and last name.
    */
   @Query(FIND_BY_NAME_QUERY)
   List<Customer> findByName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
