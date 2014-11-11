package edu.se329.teddygrahams.boardgamestrain;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.widget.TextView;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testBoardGameModel() throws IllegalArgumentException {
        String name = "Test";
        int min = 0;
        int max = 3;
        int time = 30;
        BoardGame game = new BoardGame(name,min,max,time);
        assertEquals(game.getName(), name);
    }
    public void testBoardGameModel1() throws IllegalArgumentException {
        String name = "Test";
        int min = 0;
        int max = 3;
        int time = 30;
        BoardGame game = new BoardGame(name,min,max,time);
        assertEquals(game.getMinPlayers(), min);
    }
    public void testBoardGameModel2() throws IllegalArgumentException {
        String name = "Test";
        int min = 0;
        int max = 3;
        int time = 30;
        BoardGame game = new BoardGame(name,min,max,time);
        assertEquals(game.getMaxPlayers(), max);
    }
    public void testBoardGameModel3() throws IllegalArgumentException {
        String name = "Test";
        int min = 0;
        int max = 3;
        int time = 30;
        BoardGame game = new BoardGame(name,min,max,time);
        assertEquals(game.getPlayTime(), time);
    }

}