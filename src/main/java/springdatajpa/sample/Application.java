package springdatajpa.sample;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springdatajpa.sample.entity.Customer;
import springdatajpa.sample.repository.CustomerRepository;

/**
 * @author ozlem.ulag
 */
@SpringBootApplication
public class Application {

   private static final Logger log = LoggerFactory.getLogger(Application.class);

   public static void main(String[] args) {
      SpringApplication.run(Application.class);
   }

   @Bean
   public CommandLineRunner demo(CustomerRepository repository) {
      return (args) -> {
         // save a couple of customers
         repository.save(new Customer("Ali", "Yıldız"));
         repository.save(new Customer("Veli", "Yılmaz"));
         repository.save(new Customer("Mehmet", "Keskin"));
         repository.save(new Customer("Ahmet", "Pala"));
         repository.save(new Customer("Orhan", "Yıldız"));
         repository.save(new Customer("Vedat", "Kılıç"));

         // fetch all customers
         log.info("Customers found with findAll():");
         log.info("-------------------------------");
         for (Customer customer : repository.findAll()) {
            log.info(customer.toString());
         }
         log.info("");

         // fetch an individual customer by ID
         Customer customer = repository.findOne(1L);
         log.info("Customer found with findOne(1L):");
         log.info("--------------------------------");
         log.info(customer.toString());
         log.info("");

         // fetch customers by last name
         log.info("Customer found with findByLastName('Yıldız'):");
         log.info("--------------------------------------------");
         for (Customer customerByLastName : repository.findByLastName("Yıldız")) {
            log.info(customerByLastName.toString());
         }
         log.info("");

         // fetch customers by first name
         List<Customer> customersByFirstName = repository.findByFirstName("Veli");
         Optional<Customer> customerByFirstName = customersByFirstName.stream().findFirst();
         if (customerByFirstName.isPresent()) {
            log.info("Customer found with findfindByFirstName('Veli'):");
            log.info("--------------------------------");
            log.info(customerByFirstName.get().toString());
            log.info("");
         }

         // fetch customers by first name like
         log.info("Customer found with findByFirstNameLike('%Ve%'):");
         log.info("--------------------------------------------");
         for (Customer firstNameLike : repository.findByFirstNameLike("%Ve%")) {
            log.info(firstNameLike.toString());
         }
         log.info("");


         // fetch customers by last name like
         log.info("Customer found with findByLastNameLike('%Yıl%'):");
         log.info("--------------------------------------------");
         for (Customer lastNameLike : repository.findByLastNameLike("%Yıl%")) {
            log.info(lastNameLike.toString());
         }
         log.info("");


         // fetch customers by name
         log.info("Customer found with findByName('Orhan', 'Yıldız'):");
         log.info("--------------------------------------------");
         for (Customer byName : repository.findByName("Orhan", "Yıldız")) {
            log.info(byName.toString());
         }
         log.info("");
      };
   }

}
