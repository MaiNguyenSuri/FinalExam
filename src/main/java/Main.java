import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            BaiTap bt = new BaiTap();
            bt.readData("data.csv");
            bt.printMaxLT(10);
            bt.printMinTK(10);
            bt.exportCSV("output.csv");
            bt.printThongKe();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
}
