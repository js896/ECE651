package edu.duke.ece651;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class GamePlayTest {
    @Test
    public void testInit() throws IOException {
        GamePlay test = new GamePlay();
        for (int i = 0; i < 5; ++i) {
            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Client currClient = new Client();
                        String initString = currClient.Recv();
                        System.out.println(initString);
                        currClient.ClientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        test.init();
        test.close();
    }

}
