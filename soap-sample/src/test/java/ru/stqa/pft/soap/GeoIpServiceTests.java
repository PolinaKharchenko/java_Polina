package ru.stqa.pft.soap;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.lavasoft.GeoIPService;


public class GeoIpServiceTests {

    @Test
    public void testMyIp(){
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("5.44.169.193");
        Assert.assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>53</State></GeoIP>");
    }

    @Test
    public void testInvalidIp(){
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("5.44.169.xxx");
        Assert.assertEquals(ipLocation, "<GeoIP><Country>RU</Country><State>53</State></GeoIP>");
    }
}
