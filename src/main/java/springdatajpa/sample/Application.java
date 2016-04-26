package springdatajpa.sample;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import springdatajpa.sample.entity.Address;
import springdatajpa.sample.entity.Customer;
import springdatajpa.sample.repository.AddressRepository;
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
   public CommandLineRunner demo(CustomerRepository customerRepository, AddressRepository addressRepository) {
      return (args) -> {
         // save a couple of customers
         Customer customer1 = new Customer("Ali", "Yıldız");
         Customer customer2 = new Customer("Veli", "Yılmaz");
         Customer customer3 = new Customer("Mehmet", "Keskin");
         Customer customer4 = new Customer("Ahmet", "Pala");
         Customer customer5 = new Customer("Orhan", "Yıldız");
         Customer customer6 = new Customer("Vedat", "Kılıç");
         customerRepository.save(customer1);
         customerRepository.save(customer2);
         customerRepository.save(customer3);
         customerRepository.save(customer4);
         customerRepository.save(customer5);
         customerRepository.save(customer6);

         // fetch all customers
         log.info("Customers found with findAll():");
         log.info("-------------------------------");
         for (Customer customer : customerRepository.findAll()) {
            log.info(customer.toString());
         }
         log.info("");

         // fetch an individual customer by ID
         Customer customer = customerRepository.findOne(1L);
         log.info("Customer found with findOne(1L):");
         log.info("--------------------------------");
         log.info(customer.toString());
         log.info("");

         // fetch customers by last name
         log.info("Customer found with findByLastName('Yıldız'):");
         log.info("--------------------------------------------");
         for (Customer customerByLastName : customerRepository.findByLastName("Yıldız")) {
            log.info(customerByLastName.toString());
         }
         log.info("");

         // fetch customers by first name
         List<Customer> customersByFirstName = customerRepository.findByFirstName("Veli");
         Optional<Customer> customerByFirstName = customersByFirstName.stream().findFirst();
         if (customerByFirstName.isPresent()) {
            log.info("Customer found with findByFirstName('Veli'):");
            log.info("--------------------------------");
            log.info(customerByFirstName.get().toString());
            log.info("");
         }

         // fetch customers by first name like
         log.info("Customer found with findByFirstNameLike('Ve%'):");
         log.info("--------------------------------------------");
         for (Customer firstNameLike : customerRepository.findByFirstNameLike("Ve%")) {
            log.info(firstNameLike.toString());
         }
         log.info("");

         // fetch customers by last name like
         log.info("Customer found with findByLastNameLike('%Yıl%'):");
         log.info("--------------------------------------------");
         for (Customer lastNameLike : customerRepository.findByLastNameLike("%Yıl%")) {
            log.info(lastNameLike.toString());
         }
         log.info("");

         // fetch customers by name
         log.info("Customer found with findByName('Orhan', 'Yıldız'):");
         log.info("--------------------------------------------");
         for (Customer byName : customerRepository.findByName("Orhan", "Yıldız")) {
            log.info(byName.toString());
         }
         log.info("");

         // save addresses
         Address address1 = new Address("İstanbul", "Ataşehir", "Atatürk dist.", "1.st", "No:1", customer1);
         Address address2 = new Address("İstanbul", "Ataşehir", "Atatürk dist.", "2.st", "No:2", customer2);
         Address address3 = new Address("İstanbul", "Ataşehir", "Atatürk dist.", "3.st", "No:3", customer3);
         Address address4 = new Address("İstanbul", "Ataşehir", "Atatürk dist.", "4.st", "No:4", customer1);
         addressRepository.save(address1);
         addressRepository.save(address2);
         addressRepository.save(address3);
         addressRepository.save(address4);

         // fetch addresses by customer
         log.info("Addresses of customer1:");
         log.info("--------------------------------------------");
         for (Address address : addressRepository.findByCustomer(customer1)) {
            log.info(address.toString());
         }
         log.info("");

         addressRepository.delete(4l);
         // fetch addresses by customer
         log.info("After deletion of address4, addresses of customer1:");
         log.info("--------------------------------------------");
         for (Address address : addressRepository.findByCustomer(customer1)) {
            log.info(address.toString());
         }
         log.info("");

         boolean exists = addressRepository.exists(5l);
         log.info("Is there any address by id = 5 ? {}", exists);

         PageRequest pageRequest = new PageRequest(0, 2, Sort.Direction.DESC, "id");
         Page<Address> addressPage = addressRepository.findAll(pageRequest);
         List<Address> addressPageContent = addressPage.getContent();
      };
   }
}
