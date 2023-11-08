package pl.edu.uwm.student.aleksiejczyk.bartlomiej.pojos;

public class ResultPojo {
    public String[] headers;
    public String[][] data;
    public ResultPojo(String[] headers, String[][] data) {
        this.headers = headers;
        this.data = data;
    }

}
