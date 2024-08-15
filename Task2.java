package stocktrading;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task2 {

    private static ArrayList<Stock> market = new ArrayList<>();
    private static Map<Integer, Stock> portfolio = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("\n\nSTOCK TRADING PLATFORM");
        initializeMarket();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. See market data");
            System.out.println("2. See my portfolio");
            System.out.println("3. Buy stock");
            System.out.println("4. Sell stock");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    seeMarketData();
                    break;
                case 2:
                    seePortfolio();
                    break;
                case 3:
                    buyStock(scanner);
                    break;
                case 4:
                    sellStock(scanner);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private static void initializeMarket() {
        market.add(new Stock(1, "Apple", 100, 150.0));
        market.add(new Stock(2, "Google", 100, 2800.0));
        market.add(new Stock(3, "Microsoft", 100, 300.0));
    }

    private static void seeMarketData() {
        System.out.println("\nMarket Data:");
        for (Stock stock : market) {
            System.out.println(stock);
        }
    }

    private static void seePortfolio() {
        if (portfolio.isEmpty()) {
            System.out.println("\nYour portfolio is empty.");
        } else {
            System.out.println("\nYour Portfolio:");
            for (Stock stock : portfolio.values()) {
                System.out.println(stock);
            }
        }
    }

    private static void buyStock(Scanner scanner) {
        System.out.print("Enter stock number to buy: ");
        int stockNumber = scanner.nextInt();
        Stock marketStock = getMarketStock(stockNumber);

        if (marketStock != null) {
            System.out.print("Enter number of shares to buy: ");
            int sharesToBuy = scanner.nextInt();

            if (sharesToBuy <= marketStock.getNumberOfShares()) {
                marketStock.setNumberOfShares(marketStock.getNumberOfShares() - sharesToBuy);
                portfolio.put(stockNumber, new Stock(stockNumber, marketStock.getCompany(), sharesToBuy, marketStock.getPrice()));
                System.out.println("Stock bought successfully.");
            } else {
                System.out.println("Not enough shares available.");
            }
        } else {
            System.out.println("Stock not found.");
        }
    }

    private static void sellStock(Scanner scanner) {
        System.out.print("Enter stock number to sell: ");
        int stockNumber = scanner.nextInt();
        Stock portfolioStock = portfolio.get(stockNumber);

        if (portfolioStock != null) {
            System.out.print("Enter number of shares to sell: ");
            int sharesToSell = scanner.nextInt();

            if (sharesToSell <= portfolioStock.getNumberOfShares()) {
                portfolioStock.setNumberOfShares(portfolioStock.getNumberOfShares() - sharesToSell);
                if (portfolioStock.getNumberOfShares() == 0) {
                    portfolio.remove(stockNumber);
                }
                Stock marketStock = getMarketStock(stockNumber);
                marketStock.setNumberOfShares(marketStock.getNumberOfShares() + sharesToSell);
                System.out.println("Stock sold successfully.");
            } else {
                System.out.println("Not enough shares in your portfolio.");
            }
        } else {
            System.out.println("Stock not found in your portfolio.");
        }
    }

    private static Stock getMarketStock(int stockNumber) {
        for (Stock stock : market) {
            if (stock.getNumber() == stockNumber) {
                return stock;
            }
        }
        return null;
    }
}
