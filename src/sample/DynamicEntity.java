package sample;

/**
 * Created by Izabela on 06.01.2016.
 */
public class DynamicEntity extends StaticEntity{

    DynamicEntity(){}

    GameConstant gameConstant;
    public double  vel_x = gameConstant.C_VEL_X ;
    public double vel_y = gameConstant.C_VEL_Y;
    public double radius = width/2;
    public boolean change_direction = false;
    public Point net_top_center = getNetTopCenter();

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
        Intersect_enum(boolean ifIntersect){
            intersect = ifIntersect;
        }
    };


    public void calculateNewPosition(){
        if (change_direction) {
            change_direction = false;
            vel_y = gameConstant.C_SPEED;
        }
        this.point.pos_x +=vel_x;
        this.point.pos_y +=vel_y;
        this.vel_y +=gameConstant.C_GRAVITY;
    }


    public void detectStaticCollison( ){
        this.intersect();
        if (Intersect_enum.LEFT_WALL.intersect)
            collisionWithLeftWall();
        if(Intersect_enum.RIGHT_WALL.intersect)
            collisionWithRightWall();
        if(Intersect_enum.CEILING.intersect)
            collisionWithCeiling();
        if(Intersect_enum.GROUND.intersect)
            collisionWithGround();
        if(Intersect_enum.NET.intersect)
            collisionWithNet();

    }

    private void collisionWithLeftWall(){
        this.vel_x = - vel_x;
    }
    private void collisionWithRightWall(){
        this.vel_x = - vel_x;
    }
    private void collisionWithCeiling(){
        this.vel_y = - vel_y;
    }
    private void collisionWithGround(){
        //end game or sth ;p
    }
    private void collisionWithNet(){
        if (this.center_point.pos_y > list_of_staticEntity[4].point.pos_y + list_of_staticEntity[4].width/2){
            this.vel_x = -vel_x;
        }
        else {
            double dx = this.center_point.pos_x - net_top_center.pos_x;
            double dy = this.center_point.pos_y - net_top_center.pos_y;
            double d = distanceBetweenTwoPoints(this.center_point,net_top_center);
            vel_x = gameConstant.C_SPEED *(dx/d);
            double new_vel_y = gameConstant.C_SPEED *(dy/d);
            if(this.vel_y>0 && new_vel_y <0)
                this.change_direction = true;
            vel_y = new_vel_y;
        }
    }

    public double distanceBetweenTwoPoints(Point point1, Point point2){
        double x = Math.abs(point1.pos_x - point2.pos_x);
        double y = Math.abs(point1.pos_y - point2.pos_y);
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }


    public void intersect(){

        Intersect_enum intersect_enum = Intersect_enum.LEFT_WALL;
        if(this.point.pos_x<list_of_staticEntity[0].point.pos_x){ // collison with left wall
            intersect_enum.setStatus(true);
        }

        if( this.point.pos_x + this.width > list_of_staticEntity[1].point.pos_x){ // collision with right wall
            intersect_enum = Intersect_enum.RIGHT_WALL;
            intersect_enum.setStatus(true);
        }

        if(this.point.pos_y < list_of_staticEntity[2].point.pos_y){ // collision with ceiling
            intersect_enum = Intersect_enum.CEILING;
            intersect_enum.setStatus(true);
        }

        if(this.point.pos_y + this.height>list_of_staticEntity[3].point.pos_y){ // collison with ground
            intersect_enum = Intersect_enum.GROUND;
            intersect_enum.setStatus(true);
        }

        if (this.point.pos_y + this.height > list_of_staticEntity[4].point.pos_y +list_of_staticEntity[4].width/2 ){  // collision with net
            if(this.point.pos_x + this.width > list_of_staticEntity[4].point.pos_x &&
                    this.point.pos_x < list_of_staticEntity[4].point.pos_x +  list_of_staticEntity[4].width){
                intersect_enum = Intersect_enum.NET;
                intersect_enum.setStatus(true);
            }
        }

        if (distanceBetweenTwoPoints(net_top_center,this.center_point)<= this.radius + list_of_staticEntity[4].width/2 ){
            intersect_enum = Intersect_enum.NET;
            intersect_enum.setStatus(true);
        }
    }
}

