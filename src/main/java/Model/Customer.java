package Model;

import jakarta.persistence.*;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Customers")
@ToString(exclude = "car")

public class Customer {


    @Id
    @SequenceGenerator(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_id", unique = true)
    private int CustomerId;
    @Column(name = "e_name")
    private String Name;
    @Column(name = "e_LastName")
    private String LastName;
    @Column(name = "e_age")
    private int CustomerAge;
   @Column(name="e_cnp",unique = true)
    private int CNP;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Car car ;



    public Customer() {
    }

    public Customer(int CustomerId, String Name, String LastName, int CustomerAge,int CNP) {
        this.CustomerId = CustomerId;
        this.Name = Name;
        this.LastName = LastName;
        this.CustomerAge = CustomerAge;
        this.CNP=CNP;
    }


    public int getCar(int id) {
        if(this.car == null){
            return Integer.valueOf(0);
        }else {
            return car.getId();
        }

    }


    public void setCar(Car car) {
        this.car = car;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getCustomerAge() {
        return CustomerAge;
    }

    public void setCustomerAge(int CustomerAge) {
        this.CustomerAge = CustomerAge;
    }

    public int getCNP() {
        return CNP;
    }

    public void setCNP(int CNP) {
        this.CNP = CNP;
    }
}
