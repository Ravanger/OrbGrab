//package Project;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.util.ArrayList;
//
//public class Cube {
//
//    Face[] faces;
//
//    public Cube(Vertex p, int width, int height, int depth) {
//        Vertex[] t = new Vertex[8];
//        t[0] = new Vertex(p.getX() + width, p.getY() + height, p.getZ());
//        t[1] = new Vertex(p.getX() + width, p.getY() + height, p.getZ() + depth);
//        t[2] = new Vertex(p.getX() + width, p.getY(), p.getZ() + depth);
//        t[3] = new Vertex(p.getX() + width, p.getY(), p.getZ());
//        t[4] = new Vertex(p.getX(), p.getY() + height, p.getZ());
//        t[5] = new Vertex(p.getX(), p.getY() + height, p.getZ() + depth);
//        t[6] = new Vertex(p.getX(), p.getY(), p.getZ() + depth);
//        t[7] = new Vertex(p.getX(), p.getY(), p.getZ());
//        Cube(t);
//    }
//
//    public void Cube(Vertex[] points) {
//        faces = new Face[12];
//        faces[0] = new Face(points[2], points[1], points[0], Color.gray);//fill
//        faces[1] = new Face(points[3], points[2], points[0], Color.gray);//fill
//        faces[2] = new Face(points[1], points[6], points[5], Color.gray);
//        faces[3] = new Face(points[1], points[2], points[6], Color.gray);
//        faces[4] = new Face(points[5], points[6], points[4], Color.gray);//fill
//        faces[5] = new Face(points[4], points[6], points[7], Color.gray);//fill
//        faces[6] = new Face(points[4], points[3], points[0], Color.gray);
//        faces[7] = new Face(points[7], points[3], points[4], Color.gray);
//        faces[8] = new Face(points[0], points[1], points[4], Color.gray);//fill
//        faces[9] = new Face(points[4], points[1], points[5], Color.gray);//fill
//        faces[10] = new Face(points[2], points[7], points[6], Color.gray);//fill
//        faces[11] = new Face(points[7], points[2], points[3], Color.gray);//fill
//
//    }
//
//    public Vertex getCenter() {
//        Vertex p = new Vertex(0, 0, 0);
//        for (int i = 0; i < faces.length; i++) {
//            p.Move(faces[i].getCenter().getX() / (faces.length), faces[i].getCenter().getY() / (faces.length), faces[i].getCenter().getZ() / (faces.length));
//        }
//        return p;
//    }
//
//    public void Move(double x, double y, double z) {
//        for (int i = 0; i < faces.length; i++) {
//            faces[i].Move(x, y, z);
//        }
//    }
//
//    public void turnX(double a) {
//        Vertex p = getCenter();
//        this.Move(-p.getX(), -p.getY(), -p.getZ());
//        for (int i = 0; i < faces.length; i++) {
//            faces[i].TurnX(a);
//        }
//        this.Move(p.getX(), p.getY(), p.getZ());
//    }
//
//    public void turnX(double a, Vertex p) {
//        this.Move(-p.getX(), -p.getY(), -p.getZ());
//        for (int i = 0; i < faces.length; i++) {
//            faces[i].TurnX(a);
//        }
//        this.Move(p.getX(), p.getY(), p.getZ());
//    }
//
//    public void turnY(double a) {
//        Vertex p = getCenter();
//        this.Move(-p.getX(), -p.getY(), -p.getZ());
//        for (int i = 0; i < faces.length; i++) {
//            faces[i].TurnY(a);
//        }
//        this.Move(p.getX(), p.getY(), p.getZ());
//    }
//
//    public void turnY(double a, Vertex p) {
//        this.Move(-p.getX(), -p.getY(), -p.getZ());
//        for (int i = 0; i < faces.length; i++) {
//            faces[i].TurnY(a);
//        }
//        this.Move(p.getX(), p.getY(), p.getZ());
//    }
//
//    public void turnZ(double a) {
//        Vertex p = getCenter();
//        this.Move(-p.getX(), -p.getY(), -p.getZ());
//        for (int i = 0; i < faces.length; i++) {
//            faces[i].TurnZ(a);
//        }
//        this.Move(p.getX(), p.getY(), p.getZ());
//    }
//
//    public void turnZ(double a, Vertex p) {
//        this.Move(-p.getX(), -p.getY(), -p.getZ());
//        for (int i = 0; i < faces.length; i++) {
//            faces[i].TurnZ(a);
//        }
//        this.Move(p.getX(), p.getY(), p.getZ());
//    }
//
//    public void zoom(double a) {
//        Vertex p = getCenter();
//        for (int i = 0; i < faces.length; i++) {
//            faces[i].Zoom(a, p);
//        }
//    }
//
//    public void zoom(double a, Vertex p) {
//        for (int i = 0; i < faces.length; i++) {
//            faces[i].Zoom(a, p);
//        }
//    }
//
//    public void paint(Graphics g) {
//
//        for (int i = 0; i < faces.length; i += 2) {
//
//            double a;
//            Color c;
//            a = faces[i].HowSeen(i);
//            c = faces[i].SetRGBColor(a);
//            if (a > 0) {
//                faces[i].paint(g, c);
//                faces[i + 1].paint(g, c);
//            }
//        }
//    }
//
//    public ArrayList PerspectiveProjection() {
//        ArrayList<Triangle> triangles = new ArrayList<Triangle>();
//        for (int i = 0; i < faces.length; i += 2) {
//            double vertex;
//            Color color;
//            vertex = faces[i].HowSeen(i);
//            color = faces[i].SetRGBColor(vertex);
//
//            if (vertex > 0) {
//                triangles.add(faces[i].PerspectiveProjection(color));
//                triangles.add(faces[i + 1].PerspectiveProjection(color));
//            }
//
//        }
//        return triangles;
//    }
// }
