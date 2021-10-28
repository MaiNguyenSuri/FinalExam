import Models.Student;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BaiTap {

    private List<Student> lstStudent = new ArrayList<>();

    public void readData(String fileName) throws IOException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        File file = new File(resource.toURI());
        BufferedReader csvReader = new BufferedReader(new FileReader(file));

        String row;
        int i = 0;
        while ((row = csvReader.readLine()) != null) {
            if (i > 0)
            {
                Student student = new Student();
                String[] data = row.split(",");
                student.setId((String)data[0]);
                student.setName((String)data[1]);
                student.setEmail((String)data[2]);
                student.setBonus(Integer.valueOf(data[3]));
                student.setReport(Integer.valueOf(data[4]));
                student.setApp(Integer.valueOf(data[5]));
                student.setLt(Float.valueOf(data[6]));
                lstStudent.add(student);
            }
            i++;
        }
        csvReader.close();
    }

    public void printMaxLT(int max){
        lstStudent.sort((s1, s2) ->
            s2.getLt().compareTo(s1.getLt())
        );
        int size = lstStudent.size();
        int total = size > max ? max : size;

        System.out.println("------------ Dang sach 10 SV diem LT cao nhat ------------ ");
        for(int i = 0; i < total; i++){
            Student st = lstStudent.get(i);
            System.out.println("\n--> " + (i + 1) + ": ");
            System.out.println(st.printLT());
        }
        System.out.println("\n\n\n");
    }

    public void printMinTK(int max){
        lstStudent.sort((s1, s2) ->
                s1.getTk().compareTo(s2.getTk())
        );
        int size = lstStudent.size();
        int total = size > 10 ? 10 : size;

        System.out.println("------------ Dang sach 10 SV diem TK thap nhat ------------ ");
        for(int i = 0; i < total; i++){
            Student st = lstStudent.get(i);
            System.out.println("\n--> " + (i + 1) + ": ");
            System.out.println(st.printTK());
        }
        System.out.println("\n\n\n");
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public void exportCSV(String fileName) throws FileNotFoundException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource(".");
        File file = new File(resource.toURI());
        File csvOutputFile = new File(file.getPath() + "/" + fileName);

        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(new String[]
                { "ID", "Name", "Email", "Bonus", "Report", "App", "LT", "TK" });

        for(Student st : lstStudent){
            dataLines.add(st.toStringArray());
        }

        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }
    }

    private float roundDigit(float value){
        return Math.round(value * 100.0f) / 100.0f;
    }

    public void printThongKe(){
        int totalSV = lstStudent.size();

        long totalPass = countPass();
        float percentPass = roundDigit(totalPass * 100 / totalSV);

        long totalFail = countFail();
        float percentFail = roundDigit(totalFail * 100 / totalSV);

        long totalGood = countGood();
        float percentGood = roundDigit(totalGood * 100 / totalSV);

        long totalFair = countFair();
        float percentFair = roundDigit(totalFair * 100 / totalSV);

        long totalNormal = countNormal();
        float percentNormal = roundDigit(totalNormal * 100 / totalSV);

        System.out.println("Số lượng sinh viên: " + totalSV);
        System.out.println("Đạt (điểm tổng kết > 4.5): " + totalPass + "      tỉ lệ: " + percentPass + "%");
        System.out.println("Không đạt: " + totalFail+ "      tỉ lệ: " + percentFail + "%");
        System.out.println("Giỏi (điểm tổng kết 8 - 10): " + totalGood + "      tỉ lệ: " + percentGood + "%");
        System.out.println("Khá (điểm tổng kết 6.5 - 8) : " + totalFair+ "      tỉ lệ: " + percentFair + "%");
        System.out.println("Trung bình (điểm tổng kết 5 - 6.5): " + totalNormal + "      tỉ lệ: " + percentNormal + "%");
    }

    private long countPass(){
        return lstStudent.stream().filter(st ->
            st.getTk() > 4.5f
        ).count();
    }

    private long countFail(){
        return lstStudent.stream().filter(st ->
                st.getTk() <= 4.5f
        ).count();
    }

    private long countGood(){
        return lstStudent.stream().filter(st ->
                st.getTk() >= 8f
        ).count();
    }

    private long countFair(){
        return lstStudent.stream().filter(st ->
                st.getTk() >= 6.5f
                        && st.getTk() < 8f
        ).count();
    }

    private long countNormal(){
        return lstStudent.stream().filter(st ->
                st.getTk() >= 5f
                        && st.getTk() < 6.5f
        ).count();
    }
}
