package Main;

import Service.CarService;
import Service.CustomerService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        boolean isInLoop = true;

        while (isInLoop) {
            System.out.println("1.Cars settings , 2.Customer settings , 3.EXIT");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("THE CAR MENU");
                    CarService.menuCar();
                    break;
                case 2:
                    System.out.println("THE CUSTOMER MENU");
                    CustomerService.menuCustomer();
                    break;
                case 3 :
                    isInLoop=false;
                    break;
                default:
                    System.out.println("Wrong choose, TRY AGAIN !");
            }

        }


    }

}
