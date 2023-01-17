package Model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")

public class Car {
    @Id
    @SequenceGenerator(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int id;
    @Column(name = "car_producer")
    private String producer;
    @Column(name = "car_model")
    private String model;
    @Column(name = "car_capacity")
    private int engineCapacity;
    @Column(name = "car_disponibility")
    private String available;

    @OneToOne(mappedBy = "car")
   @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Customer customer;


    public Car() {

    }

    public Car(int id, String producer, String model, int engineCapacity, String available) {
        this.id = id;
        this.producer = producer;
        this.model = model;
        this.engineCapacity = engineCapacity;
        this.available = available;

    }

    public int getCustomer(int cnp) {
        if(this.customer == null){
            return Integer.valueOf(0);
        }else {
            return customer.getCNP();
        }

    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", isAvable=" + available +
                '}';
    }
}
