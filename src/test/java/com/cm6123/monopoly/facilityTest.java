package com.cm6123.monopoly;

import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Facility;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class facilityTest {
    @Test
    public void testfacility (){
        ArrayList<Facility> facilities = new ArrayList<>();
        Facility.addFacilities(facilities);
        assertTrue(facilities.size()==3);

    }
    @Test
    public void facilityPosition(){
        Board b = new Board();
        b.movePlayer();
        Facility r = new Facility("road",4);
        Facility y = new Facility("tax-office",5);
        Facility t = new Facility("railway",6);
        if (b.movePlayer() == 4){
            String expect = "road";
            String actual = r.facilityName();
            assertEquals(expect,actual);
        } else if(b.movePlayer() == 5){
            String expect = "tax-office";
            String actual = y.facilityName();
            assertEquals(expect,actual);
        } else if(b.movePlayer() == 6);{
            String expect = "railway";
            String actual = t.facilityName();
            assertEquals(expect,actual);
        }
    }
}
