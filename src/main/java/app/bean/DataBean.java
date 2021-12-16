package app.bean;

import app.connection.HibernateSessionFactory;
import app.pointClass.Point;
import lombok.Data;
import org.hibernate.Session;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "dataBean")
@ApplicationScoped
@Data
public class DataBean implements Serializable {

    public Point getEntity() {
        return entity;
    }

    public void setEntity(Point entity) {
        this.entity = entity;
    }

    private Point entity = new Point();

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    private ArrayList<Point> points = new ArrayList<>();
    private List<String> selectedX;

    Session session;


    public DataBean(){}

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

        entity = new Point();
        selectedX.clear();
    }

    private void addToDB(){
        session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    public List<String> getSelectedX() {
        return selectedX;
    }

    public void setSelectedX(List<String> selectedX) {
        this.selectedX = selectedX;
    }
}
