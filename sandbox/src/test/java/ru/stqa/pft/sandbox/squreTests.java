package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class squreTests {

  @Test
    public void testArea() {
    Squre s = new Squre(5);
    Assert.assertEquals(s.area(), 25.0);
  }
}
