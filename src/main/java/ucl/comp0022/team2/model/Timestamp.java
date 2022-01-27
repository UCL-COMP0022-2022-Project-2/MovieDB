package ucl.comp0022.team2.model;

public class Timestamp {
    private final int year;
    private final int month;
    private final int date;
    private final String time;
    public Timestamp(String timestamp) {
        year = Integer.parseInt(timestamp.split(" ")[1].split("-")[0]);
        month = Integer.parseInt(timestamp.split(" ")[1].split("-")[1]);
        date = Integer.parseInt(timestamp.split(" ")[1].split("-")[2]);
        time = timestamp.split(" ")[1];
    }
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Timestamp{" +
                "year=" + year +
                ", month=" + month +
                ", date=" + date +
                ", time='" + time + '\'' +
                '}';
    }
}
