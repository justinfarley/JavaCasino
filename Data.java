public class Data {
    public int day;
    public int job;
    public double walletBalance;
    public double bankBalance;
    public int currentDay;
    Data(int d, double wBal, double bBal, int j, int d2){
        day = d;
        currentDay = d2;
        walletBalance = wBal;
        bankBalance = bBal;
        job = j;
    }
}
