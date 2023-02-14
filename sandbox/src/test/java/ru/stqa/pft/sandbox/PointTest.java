package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

  Point P = new Point(3, 3);
  @Test
  public void testdistanceToAnotherPoint(){
    Point P1 = new Point(9,9);
    Point P2 = new Point(6,6);
    Assert.assertEquals( P.distanceToAnotherPoint(P1),8.48528137423857);
    Assert.assertEquals( P.distanceToAnotherPoint(P2),4.242640687119285);
  }
}
