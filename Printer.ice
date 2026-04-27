module Demo
{
    interface Printer
    {
        string printString(string s);
        void printInt(int n);
        string reverseString(string s);
    }

    interface Calculator
    {
        int add(int a, int b);
        int subtract(int a, int b);
    }
}
