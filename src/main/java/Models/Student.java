package Models;

public class Student {
    private String id;
    private String name;
    private String email;
    private Integer bonus;
    private Integer report;
    private Integer app;
    private Float lt;
    private Float tk;

    public Float getTk() {
        if(null == this.tk){
            diemTongKet();
        }
        return tk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(String datum) {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus == null ? 0 : bonus;
    }

    public Integer getReport() {
        return report;
    }

    public void setReport(Integer report) {
        this.report = report == null ? 0 : report;
    }

    public Integer getApp() {
        return app;
    }

    public void setApp(Integer app) {
        this.app = app == null ? 0 : app;
    }

    public Float getLt() {
        return lt;
    }

    public void setLt(Float lt) {
        this.lt = lt == null ? 0 : lt;
    }

    public String printLT(){
        StringBuilder str = new StringBuilder();
        str.append("Id: " + this.id);
        str.append("\nName: " + this.name);
        str.append("\nEmail: " + this.email);
        str.append("\nBonus: " + this.bonus);
        str.append("\nReport: " + this.report);
        str.append("\nApp: " + this.app);
        str.append("\nLT: " + this.lt);

        return str.toString();
    }

    public String printTK(){
        StringBuilder str = new StringBuilder();
        str.append("Id: " + this.id);
        str.append("\nName: " + this.name);
        str.append("\nEmail: " + this.email);
        str.append("\nTK: " + diemTongKet());

        return str.toString();
    }

    private float diemTongKet(){
        this.tk = (this.bonus * 10/100)
                + (this.report * 30/100)
                + (this.app * 15/100)
                + (this.lt * 45/100);
        this.tk = Math.round(this.tk * 10.0f) / 10.0f;
        return this.tk;
    }

    public String[] toStringArray(){
        return new String[] {
                this.id,
                this.name,
                this.email,
                String.valueOf(this.bonus),
                String.valueOf(this.report),
                String.valueOf(this.app),
                String.valueOf(this.lt),
                String.valueOf(this.getTk())
        };
    }
}
