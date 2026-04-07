package Assignment2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

class BankAccount {
    String accountNumber;
    String username;
    double balance;

    public BankAccount(String accountNumber, String username, double balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return username + " (Acc: " + accountNumber + ") - Balance: " + balance;
    }
}

public class BankingSystem {
    // Task 1
    static LinkedList<BankAccount> accounts = new LinkedList<>();

    // Task 3
    static Stack<String> transactionHistory = new Stack<>();

    // Task 4
    static Queue<String> billQueue = new LinkedList<>();

    // Task 5
    static Queue<BankAccount> accountRequests = new LinkedList<>();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Task 6
        System.out.println("Predefined Accounts");
        BankAccount[] predefinedAccounts = new BankAccount[3];
        predefinedAccounts[0] = new BankAccount("001", "Ali", 150000);
        predefinedAccounts[1] = new BankAccount("002", "Sara", 220000);
        predefinedAccounts[2] = new BankAccount("003", "John", 50000);

        for (BankAccount acc : predefinedAccounts) {
            System.out.println(acc);
            accounts.add(acc);
        }
        System.out.println("----------------------------------------------------\n");

        boolean running = true;
        while (running) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("4 - Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    bankMenu();
                    break;
                case 2:
                    atmMenu();
                    break;
                case 3:
                    adminMenu();
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Mini Banking Menu (Bank)
    private static void bankMenu() {
        System.out.println("\n--- Bank Menu ---");
        System.out.println("1 - Submit account opening request");
        System.out.println("2 - Deposit money");
        System.out.println("3 - Withdraw money");
        System.out.println("4 - Add Bill Payment Request");
        System.out.println("5 - View Transaction History");
        System.out.println("6 - Undo Last Transaction");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter new account number: ");
                String accNum = scanner.nextLine();
                System.out.print("Enter username: ");
                String user = scanner.nextLine();
                System.out.print("Enter initial balance: ");
                double bal = scanner.nextDouble();
                accountRequests.add(new BankAccount(accNum, user, bal));
                System.out.println("Account request submitted to queue.");
                break;
            case 2:
                processTransaction("Deposit");
                break;
            case 3:
                processTransaction("Withdraw");
                break;
            case 4:
                System.out.print("Enter bill name (e.g., Electricity Bill): ");
                String bill = scanner.nextLine();
                billQueue.add(bill);
                transactionHistory.push("Bill payment request added: " + bill);
                System.out.println("Added: " + bill);
                break;
            case 5:
                if (transactionHistory.isEmpty()) {
                    System.out.println("No transactions yet.");
                } else {
                    System.out.println("Last transaction: " + transactionHistory.peek());
                }
                break;
            case 6:
                if (!transactionHistory.isEmpty()) {
                    System.out.println("Undo -> Removed: " + transactionHistory.pop());
                } else {
                    System.out.println("No transactions to undo.");
                }
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    //ATM
    private static void atmMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1 - Balance enquiry");
        System.out.println("2 - Withdraw");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter username: ");
            String user = scanner.nextLine();
            BankAccount acc = searchAccount(user);
            if (acc != null) {
                System.out.println("Current balance: " + acc.balance);
            } else {
                System.out.println("Account not found.");
            }
        } else if (choice == 2) {
            processTransaction("Withdraw");
        } else {
            System.out.println("Invalid option.");
        }
    }

    //Admin
    private static void adminMenu() {
        System.out.println("\n--- Admin Area ---");
        System.out.println("1 - View and process account queue");
        System.out.println("2 - View and process bill payment queue");
        System.out.println("3 - Display all active accounts");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                if (accountRequests.isEmpty()) {
                    System.out.println("No pending account requests.");
                } else {
                    BankAccount newAcc = accountRequests.poll();
                    accounts.add(newAcc);
                    System.out.println("Processed and activated account for: " + newAcc.username);
                }
                break;
            case 2:
                if (billQueue.isEmpty()) {
                    System.out.println("No pending bills.");
                } else {
                    System.out.println("Processing: " + billQueue.poll());
                    System.out.println("Remaining bills in queue: " + billQueue.size());
                }
                break;
            case 3:
                System.out.println("Accounts List:");
                for (int i = 0; i < accounts.size(); i++) {
                    System.out.println((i + 1) + ". " + accounts.get(i).username + " - Balance: " + accounts.get(i).balance);
                }
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void processTransaction(String type) {
        System.out.print("Enter username: ");
        String user = scanner.nextLine();
        BankAccount acc = searchAccount(user);

        if (acc != null) {
            System.out.print("Enter amount to " + type + ": ");
            double amount = scanner.nextDouble();
            if (type.equals("Deposit")) {
                acc.balance += amount;
                transactionHistory.push("Deposit " + amount + " to " + user);
            } else if (type.equals("Withdraw")) {
                if (acc.balance >= amount) {
                    acc.balance -= amount;
                    transactionHistory.push("Withdraw " + amount + " from " + user);
                } else {
                    System.out.println("Insufficient funds!");
                    return;
                }
            }
            System.out.println("New balance: " + acc.balance);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Search account by username
    private static BankAccount searchAccount(String username) {
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(username)) {
                return acc;
            }
        }
        return null;
    }
}