import Models.Student;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BaiTap2 {
    private List<Student> lstStudent = new ArrayList<>();
    private String gmailRegex = "^[a-z0-9](\\.?[a-z0-9]){5,}@g(oogle)?mail\\.com$";
    private String outlookRegex = "^[a-z0-9](\\.?[a-z0-9]){5,}@outlook\\.com$";

    public BaiTap2(List<Student> lst){
        this.lstStudent = lst;
    }

    public void printGMail(){
        System.out.println("------------ Dang sach SV su dung GMAIL ------------ ");
        lstStudent.stream()
                .filter(st -> st.getEmail().matches(this.gmailRegex))
                .forEach(st -> {
                    System.out.println(st.printNameEmail());
                    System.out.println("\n");
                });
        System.out.println("\n\n\n");
    }

    public void exportOutlook(String fileName) throws Exception{
        List<Student> lstOutlook = lstStudent.stream()
                .filter(st -> st.getEmail().matches(this.outlookRegex))
                .collect(Collectors.toList());
        if(!lstOutlook.isEmpty()){
            URL resource = getClass().getClassLoader().getResource(".");
            File file = new File(resource.toURI());

            FileOutputStream fos = new FileOutputStream(file.getPath() + "/" + fileName);
            DataOutputStream dos = new DataOutputStream(fos);

            for(Student st : lstOutlook){
                dos.writeUTF(st.toString());
                dos.writeUTF("\n");
            }
            dos.flush();
            dos.close();
        }
    }

    public void readOutlook(String fileName) throws Exception {
        URL resource = getClass().getClassLoader().getResource(fileName);
        File file = new File(resource.toURI());

        FileInputStream fis = new FileInputStream(resource.getPath());
        DataInputStream dis = new DataInputStream(fis);

        System.out.println("------------ Dang sach SV su dung OUTLOOK ------------ ");
        while(dis.available() > 0){
            System.out.println(dis.readUTF());
        }

    }
}
