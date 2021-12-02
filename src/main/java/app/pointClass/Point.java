package app.pointClass;

import javax.persistence.*;

@Table(name = "for_points")
@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private double x;
    private double y;
    private double r;
    private String now;
    private String answer;
    private long workTime;

    public Point(){}

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    private boolean pointCheck() {
        int quarter = getQuarter();
        switch (quarter){
            case 0 -> {
                return true;
            }
            case 1 -> {
                if (y <= -2 * x + r)
                    return true;
            }
            case 2 ->{
                if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r/2, 2))
                    return true;
            }
            case 3 -> {
                if (Math.abs(x) <= r && Math.abs(y) <= r)
                    return true;
            }
            case 4 -> {
                return false;
            }
        }
        return false;
    }

    private int getQuarter() {
        if (x > 0 && y > 0)
            return 1;
        if (x < 0 && y > 0)
            return 2;
        if(x < 0 && y < 0)
            return 3;
        if (x > 0 && y < 0)
            return 4;
        return 0;
    }

    public void prosPoint(){
        answer = (pointCheck()) ? "Yes" : "No";
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getNow() {
        return now;
    }

    public void setNow(String now) {
        this.now = now;
    }

    public long getWorkTime() {
        return workTime;
    }

    public void setWorkTime(long workTime) {
        this.workTime = workTime;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}