import com.zeroc.Ice.*;

public class Client {
    public static void main(String[] args) {
        // 1. Initialize the Ice communicator within a try-with-resources block
        try (Communicator communicator = Util.initialize(args)) {

            // 2. Create a proxy to the remote 'Printer' object
            // Replace '10.0.0.5' with the actual IP of your ICE server
            ObjectPrx base = communicator.stringToProxy("SimplePrinter:default -h 98.90.53.6 -p 5678");

            // 3. Downcast the proxy to the Printer interface
            Demo.PrinterPrx printer = Demo.PrinterPrx.checkedCast(base);

            if (printer == null) {
                throw new Error("Invalid proxy");
            }

            // 4. Call the remote method
            String response = printer.printString("Hello from Goiania!");
            System.out.println("Server responded: " + response);

            printer.printInt(42);

            String reversed = printer.reverseString("Hello from Goiania!");
            System.out.println("Reversed: " + reversed);

            ObjectPrx base2 = communicator.stringToProxy("SimpleCalculator:default -h 98.90.53.6 -p 5678");
            Demo.CalculatorPrx calculator = Demo.CalculatorPrx.checkedCast(base2);
            if (calculator == null) {
                throw new Error("Invalid proxy");
            }

            System.out.println("10 + 5 = " + calculator.add(10, 5));
            System.out.println("10 - 5 = " + calculator.subtract(10, 5));

        } catch (LocalException e) {
            e.printStackTrace();
        }
    }
}
