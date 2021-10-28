import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try{
            BaiTap bt = new BaiTap();
            System.out.println("------------ BAI 1 ------------ ");
            bt.readData("data.csv");
            /*bt.printMaxLT(10);
            bt.printMinTK(10);
            bt.exportCSV("output.csv");*/
            bt.printThongKe();

            System.out.println("\n\n\n");
            System.out.println("------------ BAI 2 ------------ ");

            String binName = "outlook.bin";
            BaiTap2 bt2 = new BaiTap2(bt.getLstStudent());
            //bt2.printGMail();
            bt2.exportOutlook(binName);
            bt2.readOutlook(binName);
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }
}
