package com.cm6123.monopoly;

import com.cm6123.monopoly.game.Facility;
import com.cm6123.monopoly.game.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {

    @Test
    public void testfacility () {
        ArrayList<Player> players = new ArrayList<>();
        Player.addPlayers(players);
        assertTrue(players.size() == 3);
    }
}
