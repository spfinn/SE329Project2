package edu.se329.teddygrahams.boardgamestrain;

/**
 * A model used to hold data about specific board games.
 * Arrays of this object will be searched and returned if it meets the search criteria.
 */
public class BoardGame {

    private String name;
    private int minPlayers;
    private int maxPlayers;
    private int playTime;
    private int ratingValue;
    private String desc;
    private String notes;
    private boolean end = false;
    private boolean favorite = false;

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

    public String getNotes() {
        if(notes == null)
            return "No Notes Available";
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String desc) {
        this.desc = desc;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }
}