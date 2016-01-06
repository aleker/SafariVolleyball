package sample;
import javafx.scene.image.Image;
import java.lang.String;
/**
 * Created by Izabela on 06.01.2016.
 */
public class StaticEntity {
    public int pos_x;
    public int pos_y;
    public int width;
    public int height;
    public int center_x = pos_x + width/2;
    public int center_y = pos_y + height/2;
    Image image;
    public String path;
    public StaticEntity[] list_of_staticEntity;

    public  void addStaticEntity(){ //w scenie gameplay wywolywac konstruktory w kolejnosci left_wall,right_wall,celling,net,ground
        int elem_counter = list_of_staticEntity.length;
        list_of_staticEntity[elem_counter] = this;
    }

    public void loadImage(){

        image = new Image(path,width,height,false,false);
    }

}
