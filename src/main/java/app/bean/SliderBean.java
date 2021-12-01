package app.bean;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SlideEndEvent;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
@ApplicationScoped
public class SliderBean implements Serializable {

    public SliderBean(){}

    public void onSlideEnd(SlideEndEvent event){
        PrimeFaces.current().executeScript("printForRad(" + event.getValue() + ");");
    }
}
