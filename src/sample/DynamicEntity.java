package sample;

/**
 * Created by Izabela on 06.01.2016.
 */
public class DynamicEntity extends StaticEntity{
    public double  vel_x;
    public double vel_y;
    public double movement_vector_length = Math.sqrt(Math.pow(this.vel_x,2) + Math.pow(this.vel_y,2));

    public Point movement_vector_top = new Point(this.vel_x + this.point.pos_x, this.vel_y + this.point.pos_y);

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
        Intersect_enum(boolean ifIntersect){
            intersect = ifIntersect;
        }
    };



    public void calculateNewPosition(Direction_enum direction){

        if(direction.equals(Direction_enum.RIGHT)) { point.pos_x += vel_x; }
        if(direction.equals(Direction_enum.LEFT))  { point.pos_x -= vel_x; }
        if(direction.equals(Direction_enum.DOWN))  { point.pos_y -= vel_y; }
        if(direction.equals(Direction_enum.UP))    { point.pos_y += vel_y; }
    }
    public void detectStaticCollison( ){
        this.intersect();
        if (Intersect_enum.LEFT_WALL.intersect)
            collisionWithLeftWall();
        if(Intersect_enum.RIGHT_WALL.intersect)
            collisionWithRightWall();
        if(Intersect_enum.CEILING.intersect)
            collisionWithCeiling();
        if(Intersect_enum.GROUND.intersect){
            collisionWithGround();
        }
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
        if (this.center_point.pos_y > list_of_staticEntity[4].point.pos_y){
            this.vel_x = -vel_x;
        }
        //else
    }

    public double distanceBetweenTwoPoints(int x_1,int y_1,int x_2, int y_2){
        int x = Math.abs(x_1 - x_2);
        int y = Math.abs(y_1 - y_2);
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));

    }
    public Point closestPointOnLine(Point point_line1,Point point_line2,Point point_center ){
        double A = point_line2.pos_y -point_line1.pos_y;
        double B = point_line1.pos_x - point_line2.pos_x;
        double C1 = (point_line2.pos_y - point_line1.pos_y)*point_line1.pos_x + (point_line1.pos_x - point_line2.pos_x)*point_line1.pos_y;
        double C2 = -B*point_center.pos_x + A*point_center.pos_y;
        double det = A*A - -B*B;
        double cx = 0;
        double cy = 0;
        if(det != 0){
            cx = (A*C1 - B*C2)/det;
            cy = (A*C2 - -B*C1)/det;
        }else{
            cx = point_center.pos_x;
            cy = point_center.pos_y;
        }
        return new Point(cx, cy);
    }
    public boolean intersect(){
        StaticEntity static_entity= new StaticEntity();
        Intersect_enum intersect_enum = Intersect_enum.LEFT_WALL;
        if(this.point.pos_x<static_entity.list_of_staticEntity[0].point.pos_x){ // collison with left wall
            intersect_enum.setStatus(true);
        }
        if( this.point.pos_x + this.width > static_entity.list_of_staticEntity[1].point.pos_x){ // collision with right wall
            intersect_enum = Intersect_enum.RIGHT_WALL;
            intersect_enum.setStatus(true);
        }
        if(this.point.pos_y<static_entity.list_of_staticEntity[2].point.pos_y){ // collision with ceiling
            intersect_enum = Intersect_enum.CEILING;
            intersect_enum.setStatus(true);
        }
        if(this.point.pos_y + this.height> static_entity.list_of_staticEntity[3].point.pos_y){ // collison with ground
            intersect_enum = Intersect_enum.GROUND;
            intersect_enum.setStatus(true);
        }
        if (this.point.pos_y + this.height > static_entity.list_of_staticEntity[4].point.pos_y){  // collision with net
            if(this.point.pos_x + this.width > static_entity.list_of_staticEntity[4].point.pos_x &&
                    this.point.pos_x < static_entity.list_of_staticEntity[4].point.pos_x +static_entity.list_of_staticEntity[4].width){
                intersect_enum = Intersect_enum.NET;
                intersect_enum.setStatus(true);
            }
        }
        Point closest_point = closestPointOnLine(this.point,this.movement_vector_top, this.net_top_center);

        /*double distance = distanceBetweenTwoPoints(this.center_x,this.center_y,list_of_staticEntity[4].point.pos_x,list_of_staticEntity[4].point.pos_y);
        if (distance < this.width/2){
            intersect_enum = Intersect_enum.NET;
            intersect_enum.setStatus(true);
        }
        distance = distanceBetweenTwoPoints(this.center_x,this.center_y,
                list_of_staticEntity[4].point.pos_x + list_of_staticEntity[4].width,list_of_staticEntity[4].point.pos_y);
        if (distance < this.width/2){
            intersect_enum = Intersect_enum.NET;
            intersect_enum.setStatus(true);
        }*/


        return true;
    }
}

