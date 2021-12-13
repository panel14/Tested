package app.bean;

import app.connection.DBConnecter;
import app.pointClass.NewEntity;
import app.pointClass.Point;
import lombok.Data;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "dataBean")
@ApplicationScoped
@Data
public class DataBean implements Serializable {
    //private Point curPoint = new Point();

    public NewEntity getEntity() {
        return entity;
    }

    public void setEntity(NewEntity entity) {
        this.entity = entity;
    }

    private NewEntity entity = new NewEntity();

    public ArrayList<NewEntity> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<NewEntity> points) {
        this.points = points;
    }

    //private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<NewEntity> points = new ArrayList<>();
    private List<String> selectedX;

    @ManagedProperty("#{dBConnecter}")
    DBConnecter dbConnecter;


    public DataBean(){}

/*    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }*/

/*    public Point getCurPoint() {
        return curPoint;
    }

    public void setCurPoint(Point point) {
        this.curPoint = point;
    }*/

    public void tryAddPoint(){
        long start = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        if (selectedX.size() > 0)
            entity.setX(Double.parseDouble(selectedX.get(0)));

        entity.prosPoint();
        long subTime = System.currentTimeMillis() - start;
        entity.setNow(format.format(start));
        entity.setWorkTime(subTime);

        points.add(entity);

        addToDB();

        entity = new NewEntity();
        selectedX.clear();
    }

    private void addToDB(){
        dbConnecter.addToDB(entity);
    }

    public List<String> getSelectedX() {
        return selectedX;
    }

    public void setSelectedX(List<String> selectedX) {
        this.selectedX = selectedX;
    }
}
