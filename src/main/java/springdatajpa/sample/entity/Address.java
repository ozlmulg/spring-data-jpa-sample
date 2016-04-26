package springdatajpa.sample.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author ozlem.ulag
 */
@Entity
public class Address {

   @Id
   @GeneratedValue
   private Long id;

   private String city;

   private String town;

   private String district;

   private String street;

   private String explicitAddress;

   @ManyToOne
   private Customer customer;

   public Address() {

   }

   public Address(String city, String town, String district, String street, String explicitAddress, Customer customer) {
      this.city = city;
      this.town = town;
      this.district = district;
      this.street = street;
      this.explicitAddress = explicitAddress;
      this.customer = customer;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   public String getTown() {
      return town;
   }

   public void setTown(String town) {
      this.town = town;
   }

   public String getDistrict() {
      return district;
   }

   public void setDistrict(String district) {
      this.district = district;
   }

   public String getStreet() {
      return street;
   }

   public void setStreet(String street) {
      this.street = street;
   }

   public String getExplicitAddress() {
      return explicitAddress;
   }

   public void setExplicitAddress(String explicitAddress) {
      this.explicitAddress = explicitAddress;
   }

   public Customer getCustomer() {
      return customer;
   }

   public void setCustomer(Customer customer) {
      this.customer = customer;
   }

   @Override
   public String toString() {
      return "Address{" +
             "id=" + id +
             ", city='" + city + '\'' +
             ", town='" + town + '\'' +
             ", district='" + district + '\'' +
             ", street='" + street + '\'' +
             ", explicitAddress='" + explicitAddress + '\'' +
             ", customer=" + customer +
             '}';
   }
}
