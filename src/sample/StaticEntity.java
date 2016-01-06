package sample;
import javafx.scene.image.Image;
import java.lang.String;
/**
 * Created by Izabela on 06.01.2016.
 */

public class StaticEntity {

    StaticEntity(){//w scenie gameplay wywolywac konstruktory w kolejnosci left_wall,right_wall,celling,net,ground
        addStaticEntity();
    }

    public  class Point{
        public double pos_x;
        public double pos_y;
        Point(double new_pos_x, double new_pos_y){
            pos_x = new_pos_x;
            pos_y = new_pos_y;
        }
    }
    Point point;
    Point net_top_center = new Point(this.list_of_staticEntity[4].width/2 + this.list_of_staticEntity[4].point.pos_x,
            this.list_of_staticEntity[4].width/2 + this.list_of_staticEntity[4].point.pos_y);
    Point center_point = new Point( point.pos_x + this.width/2,point.pos_y + this.height/2);
    public int width;
    public int height;
    Image image;
    public String path;
    public StaticEntity[] list_of_staticEntity;

    public  void addStaticEntity(){
        int elem_counter = list_of_staticEntity.length;
        list_of_staticEntity[elem_counter] = this;
    }

    public void loadImage(){

        image = new Image(path,width,height,false,false);
    }

}
