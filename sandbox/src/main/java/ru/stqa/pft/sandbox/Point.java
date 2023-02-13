package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distanceToAnotherPoint(Point P2)
  {
    double s = (P2.x - this.x)*(P2.x - this.x) + (P2.y - this.y)*(P2.y - this.y);
    double res = Math.sqrt(s);
    return res;
  };
}
