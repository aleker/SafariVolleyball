package sample;

/**
 * Created by Izabela on 06.01.2016.
 */
public class DynamicEntity extends StaticEntity {

    public DynamicEntity(String imagepath, double width, double height) {
        this.point = new Point(GameConstant.C_START_POS_X,GameConstant.C_START_POS_Y);
        this.path = imagepath;
        vel_x = GameConstant.C_VEL_X;
        vel_y = GameConstant.C_VEL_Y;
        loadScaledImage(width, height);
        this.width = width;
        this.height = height;
        setCenterPoint();
        setRadius();
    }

    public DynamicEntity(String imagepath, double scale) {
        this.point = new Point(GameConstant.C_START_POS_X,GameConstant.C_START_POS_Y);
        this.path = imagepath;
        vel_x = GameConstant.C_VEL_X;
        vel_y = GameConstant.C_VEL_Y;
        loadScaledImage(scale);
        setWidth();
        setHeight();
        setCenterPoint();
        setRadius();
    }


    DynamicEntity(String imagepath) {
        this.point = new Point(GameConstant.C_START_POS_X,GameConstant.C_START_POS_Y);
        this.path = imagepath;
        vel_x = GameConstant.C_VEL_X;
        vel_y = GameConstant.C_VEL_Y;
        loadImage();
        setWidth();
        setHeight();
        setCenterPoint();
        setRadius();
    }

    public double  vel_x;
    public double vel_y;
    public double radius;
   // public boolean change_direction = false;
    public Point net_top_center = getNetTopCenter();
    public boolean top_net_collision = false;
    private Intersect_enum last_collision;

    public enum Intersect_enum{
        LEFT_WALL(false),
        RIGHT_WALL(false),
        NET(false),
        CEILING(false),
        ANIMAL(false),
        GROUND(false);
        boolean intersect;
        private void setStatus(boolean new_status){
            intersect = new_status;
        }
        Intersect_enum(boolean ifIntersect){
            intersect = ifIntersect;
        }
    };

    private void setRadius(){
        this.radius = width/2;
    }

    public void calculateNewPosition(double deltaTime){
      //  if (change_direction) {
      //      change_direction = false;
      //     vel_y = GameConstant.C_SPEED;
      //  }
        this.point.pos_x +=vel_x*deltaTime;
        this.point.pos_y +=vel_y*deltaTime;
        this.vel_y +=GameConstant.C_GRAVITY*deltaTime;

    }

    public void setNewSetPosition(int side){
        if(side == 0){ //left side
            this.point.pos_x = 600;
            this.point.pos_y = 100;

        }
        else{ //right side
            this.point.pos_x = 200;
            this.point.pos_y = 100;
        }
    }

    public void detectStaticCollison( ) {
        updateCenterPoint();
        this.intersect();
        Intersect_enum intersect_enum = Intersect_enum.LEFT_WALL;
        if (Intersect_enum.LEFT_WALL.intersect){
            intersect_enum.setStatus(false);
            collisionWithLeftWall();
        }

        if(Intersect_enum.RIGHT_WALL.intersect){
            intersect_enum = Intersect_enum.RIGHT_WALL;
            intersect_enum.setStatus(false);
            collisionWithRightWall();
        }

        if(Intersect_enum.CEILING.intersect){
            intersect_enum = Intersect_enum.CEILING;
            intersect_enum.setStatus(false);
            collisionWithCeiling();
        }

        if(Intersect_enum.GROUND.intersect){
            intersect_enum = Intersect_enum.GROUND;
            intersect_enum.setStatus(false);
            collisionWithGround();
        }
        if(Intersect_enum.NET.intersect){
            intersect_enum = Intersect_enum.NET;
            intersect_enum.setStatus(false);
            collisionWithNet();
        }
        //calculateNewPosition();
    }

    private void collisionWithLeftWall(){
        this.vel_x = Math.abs(0.9*vel_x);
    }
    private void collisionWithRightWall(){
        this.vel_x = - Math.abs(0.9*vel_x);
    }
    private void collisionWithCeiling(){
        this.vel_y = Math.abs(0.9*vel_y) ;
    }
    private void collisionWithGround(){

        // loose score, end game or sth
        //it's only TEST
        double new_vel_y = - vel_y;
    //    if(this.vel_y > 0 && new_vel_y <0) {
    //        this.change_direction = true;
    //    }
        vel_y = new_vel_y;
        //END TEST
        vel_y = -0.9 * Math.abs(vel_y);
    }
    private void collisionWithNet() {
        if(top_net_collision) {
            double dx = this.center_point.pos_x - net_top_center.pos_x;
            double dy = this.center_point.pos_y - net_top_center.pos_y;
            double d = distanceBetweenTwoPoints(this.center_point, net_top_center);
            double speed = Math.sqrt(vel_x * vel_x + vel_y * vel_y);
            speed *= 0.9;
            //double speed = Math.sqrt(GameConstant.C_SPEED * GameConstant.C_SPEED + 2 * GameConstant.C_GRAVITY *
            //        (point.pos_y - GameConstant.C_START_POS_Y));
            vel_x = speed * (dx / d);
            double new_vel_y = speed * (dy / d);
         //   if (this.vel_y > 0 && new_vel_y < 0) {
         //       this.change_direction = true;
         //   }
            vel_y = new_vel_y;
            top_net_collision = false;
        }
        else{
            this.vel_x = -vel_x;

        }
    }

    public double distanceBetweenTwoPoints(Point point1, Point point2){
        double x = Math.abs(point1.pos_x - point2.pos_x);
        double y = Math.abs(point1.pos_y - point2.pos_y);
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }

    public void intersect(){
        Intersect_enum intersect_enum = Intersect_enum.LEFT_WALL;
        if(this.point.pos_x< 0){ // collison with left wall
            if(true){
                intersect_enum.setStatus(true);
                last_collision = Intersect_enum.LEFT_WALL;
            }

        }
        if( this.point.pos_x + this.width > 800){ // collision with right wall
            if(true){
                intersect_enum = Intersect_enum.RIGHT_WALL;
                intersect_enum.setStatus(true);
                last_collision = Intersect_enum.RIGHT_WALL;
            }

        }

        if(this.point.pos_y < 0){ // collision with ceiling
            if(true){
                intersect_enum = Intersect_enum.CEILING;
                intersect_enum.setStatus(true);
                last_collision = Intersect_enum.CEILING;
            }


        }
        if(this.point.pos_y + this.height> 600){ // collison with ground
            if(true){
                intersect_enum = Intersect_enum.GROUND;
                intersect_enum.setStatus(true);
                last_collision = Intersect_enum.GROUND;
            }


        }
        updateCenterPoint();
        if (distanceBetweenTwoPoints(net_top_center,this.center_point)< Math.sqrt(radius * radius + StaticEntity.list_of_staticEntity.get(4).width * StaticEntity.list_of_staticEntity.get(4).width) && a(net_top_center, this.center_point) ){// collision with net
            if(true){
                intersect_enum = Intersect_enum.NET;
                intersect_enum.setStatus(true);
                top_net_collision = true;
                last_collision = Intersect_enum.NET;
            }


        }
        if (this.center_point.pos_y>net_top_center.pos_y ){  // collision with net
            if(this.point.pos_x + this.width > list_of_staticEntity.get(4).point.pos_x &&
                    this.point.pos_x < list_of_staticEntity.get(4).point.pos_x +  list_of_staticEntity.get(4).width){
                if(true){
                    intersect_enum = Intersect_enum.NET;
                    intersect_enum.setStatus(true);
                    last_collision = Intersect_enum.NET;
                }


            }
        }



    }

    private boolean a(Point net_top_center, Point center_point) {
        double dx = net_top_center.pos_x - center_point.pos_x;
        double dy = net_top_center.pos_y - center_point.pos_y;
        if (((Math.signum(vel_x) == 0) || (Math.signum(vel_x) == Math.signum(dx))) && ((Math.signum(vel_y) == 0) || (Math.signum(vel_y) == Math.signum(dy))))
            return true;
        return false;
    }

    public int detectDynamicCollision(Animal animal) {    // returns 1 if collision, else returns 0

        if(GamePlay.stop()) return 0;   // case when the ball touches the ground and animal tries to push ball under ground.
        updateCenterPoint();
        // solve distance
        double dx = center_point.pos_x - animal.getCenter().pos_x;
        double dy = center_point.pos_y - animal.getCenter().pos_y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        double radius = image.getWidth()/2;
        if (distance <= Math.sqrt(radius * radius + animal.getRadius() * animal.getRadius())) { // if collision then count new vel_x and vel_y
            // assuming that mass of animal is much greater than mass of ball
            if(last_collision !=Intersect_enum.ANIMAL) {
                double speed = Math.sqrt(vel_x * vel_x + vel_y * vel_y);
                //double speed = Math.sqrt(GameConstant.C_SPEED * GameConstant.C_SPEED + 2 * GameConstant.C_GRAVITY *
                //        (point.pos_y - GameConstant.C_START_POS_Y));
                if (animal.inTheAir)
                    speed *= 1.1;
                vel_x = speed * (dx / distance);
                vel_y = speed * (dy / distance);
                last_collision = Intersect_enum.ANIMAL;
            }
            //calculateNewPosition();
            return 1;
        }
        return 0;
    }
}

