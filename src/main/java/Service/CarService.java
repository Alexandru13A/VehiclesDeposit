package Service;

import Main.Main;
import Model.Car;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

import Model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import Hibernate.util.HibernateUtil;

public class CarService {

    private static Configuration cfg;
    private static SessionFactory sf;
    private static Session session;

    public static void getAll() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Car");
        List<Car> cars = query.list();
        for (Car car : cars) {
            System.out.println("Car id: " + car.getId() + " Car Producer: " + car.getProducer() + " Car Model: " + car.getModel()
                    + " Car Capacity: " + car.getEngineCapacity() + " Car availability: " + car.getAvailable());
        }
        session.getTransaction();
        session.close();


    }

    public static void getCarById(Scanner sc) {
        sf = HibernateUtil.getSessionFactory();
        session = sf.openSession();
        session.beginTransaction();
        Customer customer = new Customer();
        System.out.println("Insert the id of the car you want to view:");
        int id = sc.nextInt();
        Query query = session.createQuery("from Car where id =: id ");
        query.setParameter("id", Integer.valueOf(id));
        Car car = (Car) query.uniqueResult();
        System.out.println("Car ID: " + car.getId() + ", Car producer: " + car.getProducer()
                + ", Car model: " + car.getModel() + ", Engine Capacity: "
                + car.getEngineCapacity() + ", Car Availability: " + car.getAvailable() + "Car Customer CNP:"
               +", " + car.getCustomer(customer.getCNP()));
        session.getTransaction();
        session.close();

    }

    public static void insertCar(Scanner sc) {
        cfg = new Configuration().configure();
        sf = cfg.buildSessionFactory();
        Car car = new Car();

        System.out.println("Insert the producer: ");
        String producer = sc.next();
        car.setProducer(producer);

        System.out.println("Insert the model: ");
        String model = sc.next();
        car.setModel(model);

        System.out.println("Insert Engine Capacity: ");
        int capacity = sc.nextInt();
        car.setEngineCapacity(capacity);

        System.out.println("Car availability: ");
        String available = sc.next();
        if (available.equals("true")) {
            car.setAvailable("Available");
        } else if (available.equals("false")) {
            car.setAvailable("Not Avaible");
        } else {
            System.out.println("Not a vailable choise! Try again");
        }
        session = sf.openSession();
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
        session.close();


    }

    public static void deleteCar(Scanner sc) {
        sf = HibernateUtil.getSessionFactory();
        session = sf.openSession();
        session.beginTransaction();
        System.out.println("Insert the id of the car you want to delete: ");
        int id = sc.nextInt();
        Query query = session.createQuery("delete from Car where id =: id ");
        query.setParameter("id", Integer.valueOf(id));
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    public static void updateCar(Scanner sc) {
        sf = HibernateUtil.getSessionFactory();
        session = sf.openSession();
        session.beginTransaction();
        Car car = new Car();
        System.out.println("Insert the id of the car you want to update: ");
        int id = sc.nextInt();
        Query query = session.createQuery("UPDATE Car SET producer=: producer, model=:model ,engineCapacity=:engineCapacity ,available=:available WHERE id =:id");
        query.setParameter("id", Integer.valueOf(id));
        System.out.println("Enter the producer: ");
        String producer = sc.next();
        query.setParameter("producer", producer);

        System.out.println("Enter the new model: ");
        String model = sc.next();
        query.setParameter("model", model);

        System.out.println("Enter the new capacity: ");
        int capacity = sc.nextInt();
        query.setParameter("engineCapacity", capacity);

        System.out.println("Enter teh availability: ");
        String available = sc.next();
        query.setParameter("available", available);
        if (available.equals("true")) {
            query.setParameter("available", String.valueOf("Available"));
        } else if (available.equals("false")) {
            query.setParameter("available", String.valueOf("Not Available"));
        } else {
            System.out.println("Not a valaible choise! try again");
        }
        query.executeUpdate();

        // session.update(car);
        session.getTransaction().commit();
        session.close();
    }

    public static void changeCarAvailability(Scanner sc) {
        sf = HibernateUtil.getSessionFactory();
        session = sf.openSession();
        session.beginTransaction();
        System.out.println("Change the car vailability: ");
        int id = sc.nextInt();
        Query query = session.createQuery("UPDATE Car SET available=:available WHERE id=:id");
        query.setParameter("id", Integer.valueOf(id));
        System.out.println("Change the car availability:");
        String available = sc.next();
        query.setParameter("available", available);
        if (available.equals("true")) {
            query.setParameter("available", String.valueOf("Available"));
        } else if (available.equals("false")) {
            query.setParameter("available", String.valueOf("Not Available"));
        } else {
            System.out.println("Not a valaible choise! try again");
        }
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void menuCar() {

            System.out.println("1.Get all cars, " +
                    " 2.Get car by id, " +
                    "3.insert car, " +
                    "4.delete car by id, " +
                    "5.update car, " +
                    "6.update car availability, " +
                    "7.Back");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Cars List: ");
                    getAll();
                    break;
                case 2:
                    System.out.println("The car with chosen id: ");
                    getCarById(sc);
                    break;
                case 3:
                    System.out.println("Insert the information you asked for!");
                    insertCar(sc);
                    break;
                case 4:
                    System.out.println("You purcede to delete option!");
                    deleteCar(sc);
                    break;
                case 5:
                    System.out.println("Ready to get update!");
                    updateCar(sc);
                    break;
                case 6:
                    System.out.println("Ready to update car availability !");
                    changeCarAvailability(sc);
                    break;
                case 7:
                    new Main();
                    break;
                default:
                    System.out.println("Not a valid option!");
            }

        }

    }

