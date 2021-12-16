package app.pointClass;

import javax.persistence.*;

import java.io.Serializable;

@Table(name = "points", schema = "s312434")
@Entity
public class Point implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "x")
    private double x;
    @Column(name = "y")
    private double y;
    @Column(name = "r")
    private double r;
    @Column(name = "now")
    private String now;
    @Column(name = "answer")
    private String answer;
    @Column(name = "worktime")
    private long workTime;

    public Point(){};

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
            case 0: return true;
            case 1:
                if (y <= -2 * x + r)
                    return true;
                break;
            case 2:
                if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r/2, 2))
                    return true;
                break;
            case 3:
                if (Math.abs(x) <= r && Math.abs(y) <= r)
                    return true;
                break;
            case 4: return false;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}