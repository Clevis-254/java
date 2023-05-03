package com.cm6123.monopoly;

import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Facility;

import com.cm6123.monopoly.game.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static com.cm6123.monopoly.game.Facility.addFacilities;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacilityTest {

    @Test
    public void testAddFacility(){

        ArrayList<Facility> facilities = new ArrayList<Facility>();


        String input = "Tax-office\n1\nRoad\n2\ndone\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        addFacilities(facilities);

        // Check that the facilities were added correctly
        assertEquals("Tax-office", facilities.get(0).getName());
        assertEquals(1, facilities.get(0).getPosition());
        assertEquals("Road", facilities.get(1).getName());
        assertEquals(2, facilities.get(1).getPosition());
    }


    @Test
    public void facilityPosition() {
        Board board = new Board();
        Player player = new Player("Jannet");
        int dice = player.rolledFigure(board);
        player.movePlayer(dice, board);
        Facility road = new Facility("road", 4);
        Facility taxOffice = new Facility("tax-office", 5);
        Facility railway = new Facility("railway", 6);
        int position = player.getPosition();

        if (position == road.getPosition()) {
            String expected = "road";
            String actual = road.getName();
            assertEquals(expected, actual);
        } else if (position == taxOffice.getPosition()) {
            String expected = "tax-office";
            String actual = taxOffice.getName();
            assertEquals(expected, actual);
        } else if (position == railway.getPosition()) {
            String expected = "railway";
            String actual = railway.getName();
            assertEquals(expected,actual);
        }

    }



        @Test
        public void testFacilityName() {
            // Create a list of facilities
            Board b = new Board();
            ArrayList<Facility> facilities = new ArrayList<Facility>();
            facilities.add(new Facility("Facility A", 5));
            facilities.add(new Facility("Facility B", 10));
            facilities.add(new Facility("Facility C", 15));

            // Create a player with a position on Facility B
            Player player = new Player("Test Player");
            player.movePlayer(9, b); // move the player to position 10 (Facility B)

            // Test that the facilityName method returns the correct facility name
            assertEquals("Facility B", Facility.facilityName(player.getPosition(), facilities));
        }
    }

