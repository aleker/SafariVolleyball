package sample;
import javafx.scene.image.Image;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Izabela on 06.01.2016.
 */

public class StaticEntity {

    public StaticEntity(String imagepath, double pos_x, double pos_y, double width, double height) {
        this.point = new Point(pos_x, pos_y);
        this.path = imagepath;
        loadScaledImage(width, height);
        list_of_staticEntity.add(this);
        this.width = width;
        this.height = height;
        setCenterPoint();
    }

    StaticEntity(String imagepath, double pos_x, double pos_y){//w scenie gameplay wywolywac konstruktory w kolejnosci left_wall,right_wall,celling,ground,net
        this.point = new Point(pos_x,pos_y);
        this.path = imagepath;
        loadImage();
        list_of_staticEntity.add(this);
        setWidth();
        setHeight();
        setCenterPoint();
    }
    StaticEntity( double pos_x, double pos_y){
        this.point = new Point(pos_x,pos_y);
        list_of_staticEntity.add(this);
    }
    StaticEntity(){}

    Point point;
    public double width ;
    public double height  ;
    Point center_point;

    Image image;
    public String path;

    public static List<StaticEntity> list_of_staticEntity = new ArrayList<StaticEntity>();


    public void setCenterPoint(){
        center_point = new Point( point.pos_x + this.width/2,point.pos_y + this.height/2);
    }

    public void updateCenterPoint(){
        center_point.pos_x = point.pos_x + width/2;
        center_point.pos_y = point.pos_y + height/2;
    }
    public void setWidth(){
        this.width = this.image.getWidth();
    }
    public void  setHeight(){
        this.height = this.image.getHeight();
    }


    public void loadImage(){

        image = new Image(path);
    }

    public void loadScaledImage(double width, double height) {
        // image scaling is done only once -- when it is being loaded so we can use smooth algorithm
        image = new Image(path, width, height, false, true);
    }

    public Point getNetTopCenter(){
        Point net_top_center = new Point((list_of_staticEntity.get(4).width)/2 + list_of_staticEntity.get(4).point.pos_x,
                list_of_staticEntity.get(4).point.pos_y +  (list_of_staticEntity.get(4).width)/2);
        return net_top_center;
    }

}

