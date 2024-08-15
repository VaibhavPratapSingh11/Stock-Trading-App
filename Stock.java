package stocktrading;

public class Stock {
    private int number;
    private String company;
    private int numberOfShares;
    private double price;

    public Stock(int number, String company, int numberOfShares, double price) {
        this.number = number;
        this.company = company;
        this.numberOfShares = numberOfShares;
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public String getCompany() {
        return company;
    }

    public int getNumberOfShares() {
        return numberOfShares;
    }

    public double getPrice() {
        return price;
    }

    public void setNumberOfShares(int numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String formattedNumber = String.format("%-3s", (number + "."));
        String formattedName = String.format("%-15s", company);
        String formattedShares = String.format("%-5s", numberOfShares);
        return formattedNumber + formattedName + "Shares: " + formattedShares + "Price: " +price;
    }
}