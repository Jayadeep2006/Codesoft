
import java.util.Scanner;

class Account {

    private double balence;
    private double initalbalence;

    public Account(double initialbalence) {
        this.balence = initalbalence;
    }

    public void Deposit(double amount) {
        balence += amount;
        System.out.println("deposited  in ATM successful. Your balence:" + balence);
    }

    public void Withdraw(double amount) {
        if (balence > amount) {
            balence -= amount;
            System.out.println("Amount withdrawn by ATM successfully. Your balence:" + balence);
        } else {
            System.out.println("Insufficient balence. Current balence:" + balence);
        }
    }

    public void Checkbalence() {
        System.out.println("Your current balence:" + balence);
    }
}

public class AtmInterface {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Account account = new Account(1000);
        while (true) {
            System.out.println("\n*********ATM Menu*********");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = s.nextInt();
            switch (choice) {
                case 1:
                    account.Checkbalence();
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = s.nextDouble();
                    account.Deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = s.nextDouble();
                    account.Withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    s.close();
                    return;
                default:
                    System.out.println("Invalid choice13. Please try again.");
            }
        }
    }
}
