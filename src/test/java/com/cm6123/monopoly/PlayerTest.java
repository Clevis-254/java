package com.cm6123.monopoly;

import com.cm6123.monopoly.game.Board;
import com.cm6123.monopoly.game.Facility;
import com.cm6123.monopoly.game.Player;
import com.cm6123.monopoly.game.Property;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {

    @Test
    public void testMovePlayer() {
        Board board = new Board();
        //Given that the user john wants to move position.
        Player player = new Player("John");
        // when john rolls a total of 4 in both dices.
        int newPosition = player.movePlayer(4, board);
        // then the new position of john will be 5 as 4 + 1 = 5.
        assertEquals(5, newPosition);
    }

    @Test
    public void testBalance() {
        Board b = new Board();
        // Given that the user Henry wants to get 200 pounds for passing through home.
        Player p = new Player("Henry");
        p.movePlayer(10, b);
        assertEquals(11, p.getPosition());
        //when the user rolls 11 the new position will be 1 as 10 + 10 + 1 = 21 - 20 =1
        p.movePlayer(10, b);
        assertEquals(1, p.getPosition());
        // then the new balance will be 1200 pounds.
        assertEquals(1200, p.getBalance());
    }

    @Test
    public void testPurchaseProperty() {
        Player p = new Player("John");
        Property d = new Property("bus", 800, 1);
        Property m = new Property("market", 1200, 2);
        Property w = new Property("mall", 700, 3);
        // Given that John wants to purchase the property.
        p.purchaseProperty(d);
        // when John's balance is greater than the property price.
        assertEquals("John", d.getOwner());
        // then John is able to purchase the property leading to reduction in his balance.
        assertEquals(200, p.getBalance());
        // Given that Derrick wants to purchase the market property.
        Player a = new Player("Derrick");
        // when Derrick's balance is less than the property's price
        a.purchaseProperty(m);
        // then Derrick will not be able to purchase the selected property.
        assertNull(null, m.getOwner());
        assertEquals(1000, a.getBalance());

        // given that sharon wants to purchase a property.
        Player t = new Player("sharon");
        // when the property already has the owner ,owner  wants to keep the property
        t.purchaseProperty(d);
        // then sharon cannot purchase the selected property.
        assertEquals("John", d.getOwner());
    }


    @Test
    public void testRent() {
        // Create a property and a player who purchases it
        Property property = new Property("bus", 800, 1);
        Player owner = new Player("James");
        owner.purchaseProperty(property);

        // Check that the owner's balance has been updated
        assertEquals(200, owner.getBalance());

        // Create another player who rents the property
        Player renter = new Player("White");
        renter.payRent(property);
        owner.getRent(80);

        // Check that the renter's balance has been updated
        assertEquals(920, renter.getBalance());

        // Check that the owner's balance has been updated
        assertEquals(280, owner.getBalance());
    }

    @Test
    public void testPayTax() {
        Board board = mock(Board.class);
        // Given that James wants to pay tax
        Player player = new Player("James");
        // Given that John wants to pay tax.
        Player p = new Player("John");

        // when James rolls a double.
        when(board.rolledDouble()).thenReturn(true);
        // then the tax percentage will be 5% hence the 950 balance.
        player.payTax(board);
        assertEquals(950, player.getBalance());

        // when John did not roll double.
        when(board.rolledDouble()).thenReturn(false);
        // then the tax percentage will be 10% hence the 900 balance.
        p.payTax(board);
        assertEquals(900, p.getBalance());

        // when James does not roll a double
        when(board.rolledDouble()).thenReturn(false);
        // then the tax percentage will be 10% hence 850 in balance.
        player.payTax(board);
        assertEquals(855, player.getBalance());
    }


    @Test
    public void testFare() {
        Player p = new Player("John");
        p.payFare(7);
        int expected = 930;
        int actual = p.getBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void testHandleBankruptcy() {
        Player player = new Player("John");
        Player p = new Player ("James");
        Property e = new Property("Property 1", 600, 1);
        Property Q = new  Property("Property 2", 700, 2);
        Property u = new Property("Property 3", 800, 3);
        player.add(e);
        p.add(Q);
        p.add(u);

        // test case 2: player has only one property
        player.setBalance(0);
        assertEquals(1, player.countPropertyOwned());
        player.handleBankruptcy(player, player.getPropertiesOwned());
        assertEquals(300, player.getBalance());

        // test case 3: player has multiple properties
        p.setBalance(0);
        assertEquals(2, p.countPropertyOwned());
        p.handleBankruptcy(p, p.getPropertiesOwned());
        assertEquals(350, p.getBalance());
    }
}


