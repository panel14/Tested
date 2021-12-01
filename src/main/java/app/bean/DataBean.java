package app.bean;

import app.pointClass.Point;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "dataBean")
@ApplicationScoped
public class DataBean implements Serializable {
    private Point curPoint = new Point();
    private ArrayList<Point> points = new ArrayList<>();
    private List<String> selectedX;

    public DataBean(){}

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public Point getCurPoint() {
        return curPoint;
    }

    public void setCurPoint(Point point) {
        this.curPoint = point;
    }

    public void tryAddPoint(){
        long start = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        if (selectedX.size() > 0)
            curPoint.setX(Double.parseDouble(selectedX.get(0)));

        curPoint.prosPoint();
        long subTime = System.currentTimeMillis() - start;
        curPoint.setNow(format.format(start));
        curPoint.setWorkTime(subTime);

        points.add(curPoint);

        curPoint = new Point();
        selectedX.clear();
    }

    public List<String> getSelectedX() {
        return selectedX;
    }

    public void setSelectedX(List<String> selectedX) {
        this.selectedX = selectedX;
    }
}
