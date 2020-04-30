package edu.duke.ece651;


import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ClientTest {
    @Test
    public void testConstructors() throws IOException {
        GamePlay test = new GamePlay();
        Client testLocal = new Client();
        Client testInput = new Client("localhost", 6666);
        Client testExist = new Client(testLocal.ClientSocket);
        test.close();
        testLocal.ClientSocket.close();
        testInput.ClientSocket.close();
        testExist.ClientSocket.close();
    }

    @Test
    public void testCheck() throws IOException {
        GamePlay test = new GamePlay();
        Client testLocal = new Client();
        System.out.println(testLocal.check("end-red"));
        System.out.println(testLocal.check("no-end"));
        test.close();
        testLocal.ClientSocket.close();
    }

    @Test
    public void testSend() throws IOException {
        GamePlay test = new GamePlay();
        Client testLocal = new Client();
        testLocal.Send("testSend()");
        test.close();
        testLocal.ClientSocket.close();
    }

}
