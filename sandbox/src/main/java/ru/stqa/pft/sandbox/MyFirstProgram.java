package ru.stqa.pft.sandbox;

import org.w3c.dom.ls.LSOutput;

public class MyFirstProgram {

  public static void main(String[] args) {

    hello("world");
    hello("Polina");

    Squre s = new Squre(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " см и " + r.b + "см = " + r.area() + "см");

    //Point P1 = new Point(1,2);
    Point P2 = new Point(4, 5);
    Point p = new Point(1, 2);
    System.out.println("Длина отрезка от точки А(" + p.x + ", " + p.y + ") до точки B(" + P2.x + ", " + P2.y + ") = " + p.distanceToAnotherPoint(P2));

  }

  /*public static double distance(Point P1, Point P2)
  {
    double s = (P2.x - P1.x)*(P2.x - P1.x) + (P2.y - P1.y)*(P2.y - P1.y);
    double res = Math.sqrt(s);
    return res;
  };*/

  public static void hello(String somebody) {

    System.out.println("Hello " + somebody + "!");
  }


}