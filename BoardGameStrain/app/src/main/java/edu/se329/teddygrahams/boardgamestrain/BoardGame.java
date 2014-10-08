package edu.se329.teddygrahams.boardgamestrain;

/**
 * Created by bkallaus on 9/29/14.
 * A model used to hold data about specific board games.
 * Arrays of this object will be searched and returned if it meets the search criteria.
 */
public class BoardGame {

    private String name;
    private int minPlayers;
    private int maxPlayers;
    private int playTime;
    private int ratingValue;
    private boolean end = false;

    public BoardGame (String name){
        this.name = name;
    }

    public BoardGame(String name, int min, int max, int play){
        this.name = name;
        minPlayers = min;
        maxPlayers = max;
        playTime = play;
        ratingValue = 0;
    }

    public BoardGame(String name, int min, int max, int play, int rating){
        this.name = name;
        minPlayers = min;
        maxPlayers = max;
        playTime = play;
        ratingValue = rating;
    }

    public void setEnd(){
        end = true;
    }

    public boolean isEnd(){
        return end;
    }


    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}

//<item objecttype="thing" objectid="7865" subtype="boardgame" collid="1108162">
//<name sortindex="1">10 Days in Africa</name>
//<yearpublished>2003</yearpublished>
//<image>http://cf.geekdo-images.com/images/pic1229634.jpg</image>
//<thumbnail>
//http://cf.geekdo-images.com/images/pic1229634_t.jpg
//</thumbnail>
//<stats minplayers="2" maxplayers="4" playingtime="25" numowned="1707">
//<rating value="8">
//<usersrated value="1524"/>
//<average value="6.57129"/>
//<bayesaverage value="6.25256"/>
//<stddev value="1.17318"/>
//<median value="0"/>
//</rating>
//</stats>
//<status own="1" prevowned="0" fortrade="0" want="0" wanttoplay="0" wanttobuy="0" wishlist="0" preordered="0" lastmodified="2007-10-12 22:51:58"/>
//<numplays>20</numplays>
//<comment>
//Light filler and as a nice side effect you learn some African geography.
//</comment>
