package lesson7;

import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private String name;
    private Vector rentals = new Vector(); // internal data array size 10, capasity increment is 0

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        this.rentals.addElement(rental);
    }

    public String getName() {
        return this.name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration rentals = this.rentals.elements(); // adding elements into the enumeration object
        String result = "Rental Record for " + this.name + "\n";

        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental eachRental = (Rental) rentals.nextElement();

            // determine each amounts for each line
            switch (eachRental.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (eachRental.getDaysRented() > 2) {
                        thisAmount += (eachRental.getDaysRented() - 2) * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += eachRental.getDaysRented() * 3;
                    break;
                case Movie.CHILDREN:
                    thisAmount += 1.5;
                    if (eachRental.getDaysRented() > 3) {
                        thisAmount += (eachRental.getDaysRented() - 3) * 1.5;
                    }
                    break;
            }

            // add frequent renter points
            frequentRenterPoints++;

            // add bonus for a two day new release rental
            if ((eachRental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && eachRental.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            // show figures for this rental
            result += "\t" + eachRental.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";

            totalAmount += thisAmount;
        }
        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";

        return result;
    }

}
