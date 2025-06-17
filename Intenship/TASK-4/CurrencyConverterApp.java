import java.util.HashMap;
import java.util.Scanner;

class Currency {
    private String code;
    private String symbol;

    public Currency(String code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public String getSymbol() {
        return symbol;
    }
}

class ExchangeRateProvider {
    private HashMap<String, Double> rates;

    public ExchangeRateProvider() {
        rates = new HashMap<>();
        rates.put("USDINR", 83.10);
        rates.put("USDEUR", 0.93);
        rates.put("EURINR", 89.5);
        rates.put("INRUSD", 0.012);
        rates.put("INREUR", 0.011);
        rates.put("EURUSD", 1.07);
    }

    public double getExchangeRate(String from, String to) {
        String key = from + to;
        return rates.getOrDefault(key, -1.0); // -1 indicates an unknown conversion rate
    }
}

class CurrencyConverter {
    private ExchangeRateProvider rateProvider;

    public CurrencyConverter(ExchangeRateProvider rateProvider) {
        this.rateProvider = rateProvider;
    }

    public double convert(String from, String to, double amount) {
        double rate = rateProvider.getExchangeRate(from, to);
        if (rate == -1.0) {
            System.out.println("Exchange rate not available for " + from + " to " + to);
            return -1.0;
        }
        return amount * rate;
    }
}

public class CurrencyConverterApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        HashMap<String, Currency> currencies = new HashMap<>();
        currencies.put("USD", new Currency("USD", "$"));
        currencies.put("INR", new Currency("INR", "₹"));
        currencies.put("EUR", new Currency("EUR", "€"));

        System.out.println("Available Currencies: USD, INR, EUR");

        System.out.print("Enter base currency (e.g., USD): ");
        String base = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String target = scanner.next().toUpperCase();

        System.out.print("Enter amount: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid amount entered. Please enter a numeric value.");
            scanner.close();
            return;
        }
        double amount = scanner.nextDouble();

        if (!currencies.containsKey(base) || !currencies.containsKey(target)) {
            System.out.println("Invalid currency code entered.");
            scanner.close();
            return;
        }

        ExchangeRateProvider provider = new ExchangeRateProvider();
        CurrencyConverter converter = new CurrencyConverter(provider);

        double result = converter.convert(base, target, amount);

        if (result != -1.0) {
            System.out.printf("Converted Amount: %s%.2f\n", currencies.get(target).getSymbol(), result);
        }

        scanner.close(); // Properly close the scanner
    }
}
