package edu.se329.teddygrahams.boardgamestrain;

import android.app.Activity;
import android.app.Application;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.widget.TextView;

import junit.framework.TestCase;

/**
 * Created by Ben on 11/10/2014.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

    public void testBackStack(){
        assertEquals(mActivity.getFragmentManager().getBackStackEntryCount(),0);
    }

    public void testXMLPulling(){
        new XMLPullingUtil(mActivity, new OnItemParsedListener() {
            @Override
            public void itemParsed(BoardGame game) {
                assertNotSame(game.getName(),"No Games Found");
            }
        });

    }

    //insert networking test cases

}