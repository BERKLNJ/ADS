package Assignment2;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;
import java.util.LinkedList;

class BankAccount {
    int accountNumber;
    String username;
    int balance;
    BankAccount next;

    public BankAccount(int accountNumber, String username, int balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
        this.next = null;
    }

    @Override
    public String toString() {
        return "ID: " + accountNumber + " | User: " + username + " | Balance: $" + balance;
    }
}

class Mylinkedlist {
    BankAccount head;
    Stack<String> history = new Stack<>();
    Queue<String> billQueue = new LinkedList<>();
    Queue<BankAccount> accountRequests = new LinkedList<>();

    // --- Linked List Methods (Account Storage) ---

    void registerAccount(BankAccount account) {
        if (account == null) return;
        account.next = head; // Link new node to current head
        head = account;      // Move head to new node
        System.out.println("System: Account for " + account.username + " is now LIVE.");
    }

    void listAllRecords() {
        if (head == null) {
            System.out.println("Database empty.");
            return;
        }
        BankAccount current = head;
        System.out.println("\n--- Current Bank Records ---");
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    void findAccount(String name) {
        BankAccount current = head;
        while (current != null) {
            if (current.username.equalsIgnoreCase(name)) {
                System.out.println("Result: " + current);
                return;
            }
            current = current.next;
        }
        System.out.println("Search Error: User not found.");
    }

    // --- Transaction Methods (With Stack History) ---

    void addFunds(String name, int amount) {
        BankAccount current = head;
        while (current != null) {
            if (current.username.equalsIgnoreCase(name)) {
                current.balance += amount;
                history.push("Added $" + amount + " to " + name);
                System.out.println("Success. New Balance: $" + current.balance);
                return;
            }
            current = current.next;
        }
        System.out.println("Error: User not found.");
    }

    void deductFunds(String name, int amount) {
        BankAccount current = head;
        while (current != null) {
            if (current.username.equalsIgnoreCase(name)) {
                if (current.balance >= amount) {
                    current.balance -= amount;
                    history.push("Deducted $" + amount + " from " + name);
                    System.out.println("Success. New Balance: $" + current.balance);
                } else {
                    System.out.println("Denied: Insufficient funds.");
                }
                return;
            }
            current = current.next;
        }
        System.out.println("Error: User not found.");
    }

    void revertLastAction() {
        if (!history.isEmpty()) {
            System.out.println("Reverting: " + history.pop());
        } else {
            System.out.println("No actions to revert.");
        }
    }

    // --- Queue Methods (Bills & Requests) ---

    void enqueueBill(String billType) {
        billQueue.add(billType);
        System.out.println("Bill '" + billType + "' added to payment queue.");
    }

    void processPendingBill() {
        if (!billQueue.isEmpty()) {
            System.out.println("Processing Bill: " + billQueue.poll());
        } else {
            System.out.println("No bills in queue.");
        }
    }

    void showBillQueue() {
        if (billQueue.isEmpty()) {
            System.out.println("Bill queue is clear.");
            return;
        }
        System.out.println("\n--- Unpaid Bills ---");
        for (String b : billQueue) {
            System.out.println("- " + b);
        }
    }

    void enqueueRequest(BankAccount acc) {
        accountRequests.add(acc);
        System.out.println("Request for " + acc.username + " queued for Admin.");
    }

    void approveAccount() {
        if (!accountRequests.isEmpty()) {
            registerAccount(accountRequests.poll());
        } else {
            System.out.println("No pending requests.");
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Mylinkedlist bankSystem = new Mylinkedlist();

        while (true) {
            System.out.println("\n--- BANKING HUB ---");
            System.out.println("1. Customer Services (Bank)");
            System.out.println("2. Quick ATM");
            System.out.println("3. Management (Admin)");
            System.out.println("4. Shutdown");
            System.out.print("Select: ");

            int mainChoice = sc.nextInt();
            if (mainChoice == 1) customerMenu(sc, bankSystem);
            else if (mainChoice == 2) atmMenu(sc, bankSystem);
            else if (mainChoice == 3) adminMenu(sc, bankSystem);
            else if (mainChoice == 4) break;
        }
    }

    static void customerMenu(Scanner sc, Mylinkedlist sys) {
        System.out.println("\n[Customer] 1. Request Account | 2. Deposit | 3. Pay a Bill | 4. Undo");
        int choice = sc.nextInt();
        switch(choice) {
            case 1:
                System.out.print("New ID: "); int id = sc.nextInt();
                System.out.print("Name: "); String n = sc.next();
                System.out.print("Initial Bal: "); int b = sc.nextInt();
                sys.enqueueRequest(new BankAccount(id, n, b));
                break;
            case 2:
                System.out.print("Name: "); String dn = sc.next();
                System.out.print("Amount: "); int da = sc.nextInt();
                sys.addFunds(dn, da);
                break;
            case 3:
                System.out.print("Bill Type (e.g., Electric, Water): ");
                sys.enqueueBill(sc.next());
                break;
            case 4:
                sys.revertLastAction();
                break;
        }
    }

    static void atmMenu(Scanner sc, Mylinkedlist sys) {
        System.out.println("\n[ATM] 1. Balance Enquiry | 2. Cash Withdrawal");
        int choice = sc.nextInt();
        System.out.print("Name: "); String n = sc.next();
        if (choice == 1) sys.findAccount(n);
        else if (choice == 2) {
            System.out.print("Amount: "); int a = sc.nextInt();
            sys.deductFunds(n, a);
        }
    }

    static void adminMenu(Scanner sc, Mylinkedlist sys) {
        System.out.println("\n[Admin] 1. Approve Account | 2. View All Accounts | 3. View Bill Queue | 4. Process Next Bill");
        int choice = sc.nextInt();
        switch(choice) {
            case 1: sys.approveAccount(); break;
            case 2: sys.listAllRecords(); break;
            case 3: sys.showBillQueue(); break;
            case 4: sys.processPendingBill(); break;
        }
    }
}