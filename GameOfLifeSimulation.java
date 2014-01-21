import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import javax.imageio.ImageIO;


public class GameOfLifeSimulation {
        public BufferedImage drawImage(HashMap<Point, Integer> grid) {
                int minX=Integer.MAX_VALUE, maxX=Integer.MIN_VALUE, maxY=Integer.MIN_VALUE, minY=Integer.MAX_VALUE;
                Iterator<Point> iterator=grid.keySet().iterator();
                if (!iterator.hasNext()) {
                        BufferedImage b=new BufferedImage(100,100,BufferedImage.TYPE_INT_RGB);
                        Graphics g=b.getGraphics();
                        g.setColor(Color.white);
                        g.fillRect(0,0,100,100);
                        g.dispose();
                        return b;
                }
                while (iterator.hasNext()) {
                        Point p=iterator.next();
                        if (minX>p.x) minX=p.x;
                        if (maxX<p.x) maxX=p.x;
                        if (minY>p.y) minY=p.y;
                        if (maxY<p.y) maxY=p.y;
                }
                int dX=maxX-minX;
                int dY=maxY-minY;
                BufferedImage b=new BufferedImage(dX+1, dY+1, BufferedImage.TYPE_INT_RGB);
                Graphics g=b.getGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, dX+1, dY+1);
                g.setColor(Color.black);
                iterator=grid.keySet().iterator();
                while (iterator.hasNext()) {
                        Point p=iterator.next();
                        g.fillRect(p.x-minX, p.y-minY, 1, 1);
                }
                g.dispose();
                return b;


        }
        public Point getPoint(Scanner s) {
                System.out.println("Point coordinates:");
                return new Point(s.nextInt(), s.nextInt());
        }
        public GameOfLifeSimulation() {
                Scanner s=new Scanner(System.in);
                System.out.println("Num rounds?");
                int numRounds=s.nextInt();
                System.out.println("Num points?");
                int numPoints=s.nextInt();
                HashMap<Point, Integer> grid=new HashMap<Point, Integer>();
                for (int i=0; i<numPoints; i++) {
                        grid.put(getPoint(s), new Integer(0));
                }
                File f=new File("0.png");
                try {
                        ImageIO.write(drawImage(grid), "png",f);
                } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                for (int i=0; i<numRounds; i++) {
                        grid=nextGrid(grid);
                        f=new File((i+1)+".png");
                        try {
                                ImageIO.write(drawImage(grid), "png",f);
                        } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                }
        }
        public HashMap<Point, Integer> nextGrid(HashMap<Point, Integer> grid) {
                HashMap<Point, Integer> newGrid=new HashMap<Point, Integer>();
                Iterator<Point> iterator=grid.keySet().iterator();
                while (iterator.hasNext()) {
                        Point curPoint=iterator.next();
                        if (!newGrid.containsKey(curPoint)) {
                                newGrid.put(curPoint, new Integer(0));
                        }
                        LinkedList<Point>neighbors=curPoint.neighbors();
                        Iterator<Point> neighborIterator=neighbors.iterator();
                        while (neighborIterator.hasNext()) {
                                Point curNeighbor=neighborIterator.next();
                                if (!newGrid.containsKey(curNeighbor)) {
                                        newGrid.put(curNeighbor, new Integer(1));
                                }
                                else {
                                        newGrid.put(curNeighbor, new Integer((newGrid.get(curNeighbor)).intValue()+1));
                                }
                        }
                }
                //pruning
                iterator=newGrid.keySet().iterator();
                while (iterator.hasNext()) {
                        Point p=iterator.next();
                        int val=newGrid.get(p).intValue();
                        boolean existsPrev=grid.containsKey(p);
                        if (existsPrev) {
                                if (val!=2 && val!=3) {
                                        iterator.remove();
                                }
                        }
                        else {
                                if (val!=3) {
                                        iterator.remove();
                                }
                        }
                }
                return newGrid;
        }
        public static void main(String[] args)
        {
            new GameOfLifeSimulation();
        }
}
