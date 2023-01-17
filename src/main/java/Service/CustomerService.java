package Service;
import java.awt.*;
import java.util.List;
import java.util.Scanner;

import Main.Main;
import Model.Car;
import Model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import Hibernate.util.HibernateUtil;
public class CustomerService {
    private static Configuration cfg;
    private static SessionFactory sf;
    private static Session session;

    public static void getAllCustomers(){
        sf=HibernateUtil.getSessionFactory();
        session=sf.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Customer");
        List<Customer> customers = query.list();
        for (Customer customer : customers){
            System.out.println("Customer ID: "+customer.getCustomerId()+", Customer Name: "+
                    customer.getName()+" "+customer.getLastName()+", Customer Age: "+customer.getCustomerAge()+", Customer CNP: "+customer.getCNP());
        }
        session.getTransaction();
        session.close();
    }

    public static void getCustomerById(Scanner sc){
        sf=HibernateUtil.getSessionFactory();
        session=sf.openSession();
        session.beginTransaction();
        Car car = new Car();
        System.out.println("Enter the Customer CNP: ");
        int cnp = sc.nextInt();
        Query query = session.createQuery("from Customer where CNP =: CNP");
        query.setParameter("CNP",Integer.valueOf(cnp));
        Customer customer = (Customer) query.uniqueResult();
        System.out.println("Customer ID: "+customer.getCustomerId()+", Customer Name: "+
        customer.getName()+" "+customer.getLastName()+", Customer Age: "+customer.getCustomerAge()+", Customer CNP: "+customer.getCNP()+",Customer Car ID: "+customer.getCar(car.getId()));
        session.getTransaction();
        session.close();
    }



    public static void insertCustomer(Scanner sc){
        cfg = new Configuration().configure();
        sf = cfg.buildSessionFactory();
        Customer customer = new Customer();

        System.out.println("Introduce the Customer Name: ");
        String name =sc.next();
        customer.setName(name);

        System.out.println("Introduce the Customer Last Name: ");
        String lastName = sc.next();
        customer.setLastName(lastName);

        System.out.println("Introduce the Customer Age: ");
        int age = sc.nextInt();
        customer.setCustomerAge(age);

        System.out.println("Insert the Customer CNP:");
        int cnp = sc.nextInt();
        customer.setCNP(cnp);

        session = sf.openSession();
        session.beginTransaction();
        session.save(customer);
        session.getTransaction().commit();
        session.close();
    }

    public static void deleteCustomer(Scanner scanner){
        sf=HibernateUtil.getSessionFactory();
        session=sf.openSession();
        session.beginTransaction();
        System.out.println("Insert the Customer CNP to delete: ");
        int cnp = scanner.nextInt();
        Query query = session.createQuery("DELETE from Customer where CNP =: CNP ");
        query.setParameter("CNP",Integer.valueOf(cnp));
        query.executeUpdate();
        session.beginTransaction().commit();
        session.close();
    }

    public static void updateCustomer(Scanner sc){
        sf=HibernateUtil.getSessionFactory();
        session=sf.openSession();
        session.beginTransaction();

        System.out.println("Insrte the CNP of the customer you want to update: ");
        int cnp = sc.nextInt();
        Query query = session.createQuery("UPDATE Customer SET Name=: Name,LastName=:LastName,CustomerAge=:CustomerAge, CustomerId =: CustomerId WHERE CNP=:CNP");
        query.setParameter("CNP",Integer.valueOf(cnp));

        System.out.println("Introduce the new ID:");
        int id = sc.nextInt();
        query.setParameter("CustomerId",id);

        System.out.println("Introduce the new Name: ");
        String name = sc.next();
        query.setParameter("Name",name);

        System.out.println("Introduce the new Last Name: ");
        String lastName= sc.next();
        query.setParameter("LastName",lastName);

        System.out.println("Introduce the new Csutomer Age: ");
        int age = sc.nextInt();
        query.setParameter("CustomerAge",age);

        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }
    public static void setCustomerCar(Scanner sc){
        sf=HibernateUtil.getSessionFactory();
        session=sf.openSession();
        session.beginTransaction();
        System.out.println("Search the Customer: ");
        int cnp = sc.nextInt();
        Query query=session.createQuery("UPDATE Customer SET CustomerId=: CustomerId WHERE CNP =: CNP");
        query.setParameter("CNP",Integer.valueOf(cnp));

        System.out.println("Set the car id to the customer: ");
        int id = sc.nextInt();
        query.setParameter("CustomerId", id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public static void menuCustomer() {
        System.out.println("1.Get all customers, " +
                " 2.Get customer by CNP, " +
                "3.insert customer, " +
                "4.delete customer by CNP, " +
                "5.update customer, " +
                "6.assing car to customer"+
                "7.Exit");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Cars List: ");
                    getAllCustomers();
                    break;
                case 2:
                    System.out.println("Customer with chosen id ");
                    getCustomerById(sc);
                    break;
                case 3:
                    System.out.println("Insert the information you asked for!");
                    insertCustomer(sc);
                    break;
                case 4:
                    System.out.println("You purcede to delete option!");
                    deleteCustomer(sc);
                    break;
                case 5:
                    System.out.println("Ready to get update!");
                    updateCustomer(sc);
                    break;
                case 6:
                    System.out.println("assing car to customer!");
                    setCustomerCar(sc);
                    break;
                case 7:
                    new Main();
                    break;
                default:
                    System.out.println("Not a valid option!");
            }

        }

    }



