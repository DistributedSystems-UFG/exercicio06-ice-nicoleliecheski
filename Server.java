import com.zeroc.Ice.*;

public class Server {

    static class PrinterI implements Demo.Printer {
        @Override
        public String printString(String s, Current current) {
            System.out.println(s);
            return s;
        }

        @Override
        public void printInt(int n, Current current) {
            System.out.println(n);
        }

        @Override
        public String reverseString(String s, Current current) {
            return new StringBuilder(s).reverse().toString();
        }
    }

    static class CalculatorI implements Demo.Calculator {
        @Override
        public int add(int a, int b, Current current) {
            return a + b;
        }

        @Override
        public int subtract(int a, int b, Current current) {
            return a - b;
        }
    }

    public static void main(String[] args) {
        try (Communicator communicator = Util.initialize(args)) {
            ObjectAdapter adapter = communicator.createObjectAdapterWithEndpoints("SimpleAdapter", "default -p 5678");

            adapter.add(new PrinterI(), Util.stringToIdentity("SimplePrinter"));
            adapter.add(new CalculatorI(), Util.stringToIdentity("SimpleCalculator"));

            adapter.activate();
            communicator.waitForShutdown();
        }
    }
}
