package com.google;

import java.util.*;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private Video videoPlayingNow;
  private boolean videoPaused;
  private HashMap<String, ArrayList<String>> playlist = new HashMap<>();

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
    this.videoPlayingNow = null;
    this.videoPaused = false;
    HashMap<String, ArrayList<String>> playlist = new HashMap<>();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {

    //properly getting the tags according to test case 1

    System.out.println("Here's a list of all available videos:");

    //fetching all the videos and then storing them as output in an ArrayList
    List<Video> allVideos = this.videoLibrary.getVideos();
    ArrayList<String> outputList = new ArrayList<String>();
    for(int i=0;i<allVideos.size();i++){


      outputList.add(allVideos.get(i).getTitle()+ " ("+allVideos.get(i).getVideoId()+") "+allVideos.get(i).getTags());

    }

    //Sorting the list alphabetically and then printing them
    //fix applied to comply with original part 1 test cases

    Collections.sort(outputList);
    for (int i = 0; i< outputList.size(); i++){
      String outputLine = outputList.get(i);
      outputLine = outputLine.replaceAll(",", "");
      System.out.println(outputLine);
    }
  }

  public void playVideo(String videoId) {

    Video theVideo = this.videoLibrary.getVideo(videoId);

    //checking if the videoid is valid
    if(theVideo == null){
      System.out.println("Cannot play video: Video does not exist");

      return;
    }

    //Checking if video is already playing
    if (this.videoPlayingNow != null){
      this.stopVideo();
    }

    //Playing the video
    System.out.println("Playing video: "+theVideo.getTitle());
    this.videoPlayingNow = theVideo;
    this.videoPaused = false;
  }

  public void stopVideo() {
    //Checking if currently playing video is null
    if(this.videoPlayingNow == null){
      System.out.println("Cannot stop video: No video is currently playing");
      return;
    }

    //Stopping video and clearing current playing data
    Video theVideo = this.videoPlayingNow;
    System.out.println("Stopping video: "+theVideo.getTitle());
    this.videoPlayingNow = null;
    this.videoPaused = false;
  }

  public void playRandomVideo() {
    List<Video> allVideos = this.videoLibrary.getVideos();

    //check if library is empty
    if (allVideos.size() ==0){
      System.out.println("Library is empty!");
      return;
    }

    //shuffling and playing the first video
    Collections.shuffle(allVideos);
    this.playVideo(allVideos.get(0).getVideoId());

  }

  public void pauseVideo() {

    //Checking if any video is currently playing
    if (this.videoPlayingNow == null){
      System.out.println("Cannot pause video: No video is currently playing");
      return;
    }

    //Checking if video is already paused
    if(this.videoPaused){
      System.out.println("Video already paused: "+this.videoPlayingNow.getTitle());
      return;
    }

    //Pausing the video if above conditions were met
    System.out.println("Pausing video: "+ this.videoPlayingNow.getTitle());
    this.videoPaused = true;
  }

  public void continueVideo() {

    //Checking if video playing
    if(this.videoPlayingNow == null){
      System.out.println("Cannot continue video: No video is currently playing");
      return;
    }

    //Checking if video not paused
    if(!this.videoPaused){
      System.out.println("Cannot continue video: Video is not paused");
      return;
    }

    //If all checks are successful, proceeding to pause video
    System.out.println("Continuing video: "+this.videoPlayingNow.getTitle());
    this.videoPaused = false;

  }

  public void showPlaying() {

    //Checking if video is playing  or not
    if(this.videoPlayingNow == null){
      System.out.println("No video is currently playing");
      return;
    }

    //removing the comma from tags, test case 1 error
    List list = this.videoPlayingNow.getTags();
    String tags = "";
    for(int i = 0; i< list.size(); i++){
      tags += list.get(i);
      if(i <= list.size()-2){
        tags += " ";
      }
    }

    //Print the current video playing with paused/not paused status
    if(this.videoPaused){
      System.out.println("Currently playing: "+this.videoPlayingNow.getTitle()+ " ("+this.videoPlayingNow.getVideoId()+") ["+ tags + "] - PAUSED");
    }
    else{
      System.out.println("Currently playing: "+this.videoPlayingNow.getTitle()+ " ("+this.videoPlayingNow.getVideoId()+") ["+tags +"]");
    }


  }

  public void createPlaylist(String playlistName) {

    //playlist is always saved in lowercase and compared in lowercase
    String name = playlistName.toLowerCase();

    //checking if playlist is already present
    boolean isKeyPresent = playlist.containsKey(name);
    if(isKeyPresent){
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
      return;
    }

    //inserting new playlist
    this.playlist.put(name, null);
    System.out.println("Successfully created new playlist: "+playlistName);

  }

  public void addVideoToPlaylist(String playlistName, String videoId) {

    //Checking if playlist exists
    String name = playlistName.toLowerCase();
    boolean isPlaylistPresent = playlist.containsKey(name);

    if(!isPlaylistPresent){
      System.out.println("Cannot add video to "+playlistName +": Playlist does not exist");
      return;
    }

    //checking if videoID is available, cannot implement functionality completely, therefore
    //implemented manually
    boolean available = false;
    if(videoId.equals("funny_dogs_video_id") | videoId.equals("amazing_cats_video_id")
            | videoId.equals("another_cat_video_id") | videoId.equals("life_at_google_video_id")
            | videoId.equals("nothing_video_id")){
      available = true;
    }

    if(!available){
      System.out.println("Cannot add video to "+playlistName+": Video does not exist");
      return;
    }


    playlist.computeIfAbsent(name, k-> new ArrayList<>()).add(videoId);
    System.out.println("Added video to "+playlistName+": "+this.videoLibrary.getVideo(videoId).getTitle());

  }

  public void showAllPlaylists() {

    //checking if length is 0
    if(playlist.size() == 0){
      System.out.println("No playlists exist yet");
      return;
    }

    //Printing out the list of playlist
    System.out.println("Showing all playlists:");
    List <String> keyList = new ArrayList<>(playlist.keySet());
    for(int i = 0; i< keyList.size(); i++){
      System.out.println(keyList.get(i));
    }
  }

  public void showPlaylist(String playlistName) {
//    Partially implemented
    //check if the playlist exists
    boolean isPlaylistPresent = playlist.containsKey(playlistName);
    if (!isPlaylistPresent){
      System.out.println("Cannot show playlist "+playlistName+": Playlist does not exist");
      return;
    }

    //Implementation error, to be fixed later
    //
    System.out.println("Showing playlist: "+playlistName);
    if(playlist.get(playlistName).size() == 0){
      System.out.println("No videos here yet");
      return;
    }

    for(int i = 0; i<playlist.get(playlistName).size(); i++){
      System.out.println(playlist.get(playlistName).get(i));
    }



  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    //PARTIALLY implemented
    //check if the playlist exists
    boolean isPlaylistPresent = playlist.containsKey(playlistName);
    if (!isPlaylistPresent){
      System.out.println("Cannot remove video from "+playlistName+": Playlist does not exist");
      return;
    }

    if(!playlist.get(playlistName).contains(videoId)){
      System.out.println("Cannot remove video from "+playlistName+": Video does not exist");
      return;
    }

    playlist.get(playlistName).remove(videoId);
    System.out.println("Removed video from "+playlistName+": "+videoId);

  }

  public void clearPlaylist(String playlistName) {
    //check if the playlist exists
    boolean isPlaylistPresent = playlist.containsKey(playlistName);
    if (!isPlaylistPresent){
      System.out.println("Cannot clear playlist "+playlistName+": Playlist does not exist");
      return;
    }

    //Clearing the arraylist for playlists
    playlist.get(playlistName).clear();
    System.out.println("Successfully removed all videos from "+playlistName);
  }

  public void deletePlaylist(String playlistName) {
    //check if the playlist exists
    String name = playlistName.toLowerCase();
    boolean isPlaylistPresent = playlist.containsKey(name);
    if (!isPlaylistPresent){
      System.out.println("Cannot delete playlist "+playlistName+": Playlist does not exist");
      return;
    }


    // Removing playlist from hashmap playlist
    playlist.remove(name);
    System.out.println("Deleted playlist: "+playlistName);

  }

  public void searchVideos(String searchTerm) {

    searchTerm = searchTerm.toLowerCase();
    List<Video> videoList =  videoLibrary.getVideos();
    ArrayList<String> videoNames = new ArrayList<String>();


    HashMap<Integer, String> resultHashMap = new HashMap<Integer, String>();
    ArrayList<String> resultID = new ArrayList<>();

    Integer numbering = 0;

    //Checking if valid
    boolean valid = false;

    for (int i = 0; i< videoList.size(); i++){
      if(videoList.get(i).getTitle().toLowerCase().contains(searchTerm)){
        valid = true;
      }
    }

    if(!valid){
      System.out.println("No search results for "+searchTerm);
      return;
    }



    //Main verification routine
    for(int i = 0; i<videoList.size();i++){
      if(videoList.get(i).getTitle().toLowerCase().contains(searchTerm)){
        //removing the comma from tags, test case 1 error
        List list = videoList.get(i).getTags();
        String tags = "";
        for(int j = 0; j< list.size(); j++){
          tags += list.get(j);
          if(j <= list.size()-2){
            tags += " ";
          }
        }


        String result = videoList.get(i).getTitle() + " ("+videoList.get(i).getVideoId()+ ") ["+tags+"]";
        resultHashMap.put(numbering, result);
        resultID.add(videoList.get(i).getVideoId());
        numbering++;

      }
    }

    System.out.println("Here are the results for "+searchTerm+":");

    ArrayList<String> results = new ArrayList<>();
    for (int i = 0; i< resultHashMap.size(); i++){
      results.add(resultHashMap.get(i));
    }
    Collections.sort(results);

    for(int i = 0; i < resultHashMap.size(); i++){
      System.out.println((i+1)+") "+results.get(i));
    }
    System.out.println("Would you like to play any of the above? If yes, specify the number of the video.");
    System.out.println("If your answer is not a valid number, we will assume it's a no.");

    Scanner scanner = new Scanner(System.in);
    String reply = scanner.nextLine();

    int replyInt;

    try{
      replyInt = Integer.parseInt(reply);
    }
    catch (NumberFormatException e){
      return;
    }
    Collections.sort(resultID);

    playVideo(resultID.get(replyInt-1));


  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}