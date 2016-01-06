package sample;

/**
 * Created by Izabela on 06.01.2016.
 */
public class DynamicEntity extends StaticEntity{
    public double  vel_x;
    public double vel_y;
    public double speed;
    public double gravity;
    public enum Direction_enum{RIGHT, LEFT ,UP, DOWN};
    public enum Intersect_enum{
        LEFT_WALL(false),
        RIGHT_WALL(false),
        NET(false),
        CEILING(false),
        GROUND(false);
        boolean intersect;
        private void setStatus(boolean new_status){
            intersect = new_status;
        }
        private Intersect_enum(boolean ifIntersect){
            intersect = ifIntersect;
        }
    };



    public void calculateNewPosition(Direction_enum direction){

        if(direction.equals(Direction_enum.RIGHT)) { pos_x += vel_x; }
        if(direction.equals(Direction_enum.LEFT))  { pos_x -= vel_x; }
        if(direction.equals(Direction_enum.DOWN))  { pos_y -= vel_y; }
        if(direction.equals(Direction_enum.UP))    { pos_y += vel_y; }
    }
    public void detectStaticCollison( ){
        this.intersect();


    }

    public void collisionWithLeftWall(){

    }
    public double distanceBetweenTwoPoints(int x_1,int y_1,int x_2, int y_2){
        int x = Math.abs(x_1 - x_2);
        int y = Math.abs(y_1 - y_2);
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));

    }
    public boolean intersect(){
        StaticEntity static_entity= new StaticEntity();
        Intersect_enum intersect_enum = Intersect_enum.LEFT_WALL;
        if(this.pos_x<static_entity.list_of_staticEntity[0].pos_x){ // collison with left wall
            intersect_enum.setStatus(true);
        }
        if(this.pos_x + this.width > static_entity.list_of_staticEntity[1].pos_x){ // collision with right wall
            intersect_enum = Intersect_enum.RIGHT_WALL;
            intersect_enum.setStatus(true);
        }
        if(this.pos_y<static_entity.list_of_staticEntity[2].pos_y){ // collision with ceiling
            intersect_enum = Intersect_enum.CEILING;
            intersect_enum.setStatus(true);
        }
        if(this.pos_y + this.height> static_entity.list_of_staticEntity[3].pos_y){ // collison with ground
            intersect_enum = Intersect_enum.GROUND;
            intersect_enum.setStatus(true);
        }
        if (this.pos_y + this.height > static_entity.list_of_staticEntity[4].pos_y){  // collision with net
            if(this.pos_x + this.width > static_entity.list_of_staticEntity[4].pos_x &&
                    this.pos_x < static_entity.list_of_staticEntity[4].pos_x +static_entity.list_of_staticEntity[4].width){
                intersect_enum = Intersect_enum.NET;
                intersect_enum.setStatus(true);
            }
        }
        double distance = distanceBetweenTwoPoints(this.center_x,this.center_y,list_of_staticEntity[4].pos_x,list_of_staticEntity[4].pos_y);
        if (distance < this.width/2){
            intersect_enum = Intersect_enum.NET;
            intersect_enum.setStatus(true);
        }
        distance = distanceBetweenTwoPoints(this.center_x,this.center_y,
                list_of_staticEntity[4].pos_x + list_of_staticEntity[4].width,list_of_staticEntity[4].pos_y);
        if (distance < this.width/2){
            intersect_enum = Intersect_enum.NET;
            intersect_enum.setStatus(true);
        }


        return true;
    }
}

