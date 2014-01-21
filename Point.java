import java.util.LinkedList;


public class Point {
        public int x, y;
        //public Point up, left, right, down;
        public Point (int x, int y) {
                this.x=x;
                this.y=y;
                //up=null;
                //left=null;
                //right=null;
                //down=null;
        }
        public LinkedList<Point> neighbors() {
                LinkedList<Point> list=new LinkedList<Point>();
                list.add(new Point(x-1,y));
                list.add(new Point(x+1,y));
                list.add(new Point(x,y-1));
                list.add(new Point(x,y+1));
                list.add(new Point(x+1,y+1));
                list.add(new Point(x+1,y-1));
                list.add(new Point(x-1,y+1));
                list.add(new Point(x-1,y-1));
                return list;
        }

        @Override
        public boolean equals (Object o) {
                if (o instanceof Point) {
                        Point p=(Point)o;
                        return (p.x==x && p.y==y);
                }
                return false;
        }
        @Override
        public int hashCode() {
                //100* to reduce hash collisions - can be any number theoretically
                return x+100*y;
        }
}
