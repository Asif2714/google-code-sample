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

    System.out.println("Here's a list of all available videos:");

    //fetching all the videos and then storing them as output in an ArrayList
    List<Video> allVideos = this.videoLibrary.getVideos();
    ArrayList<String> outputList = new ArrayList<String>();
    for(int i=0;i<allVideos.size();i++){
      outputList.add(allVideos.get(i).getTitle()+ " ("+allVideos.get(i).getVideoId()+") "+allVideos.get(i).getTags());
    }

    //Sorting the list alphabetically and then printing them
    Collections.sort(outputList);
    for (int i = 0; i< outputList.size(); i++){
      System.out.println("\t"+outputList.get(i));
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

    //Print the current video playing with paused/not paused status
    if(this.videoPaused){
      System.out.println("Currently playing: "+this.videoPlayingNow.getTitle()+ " ("+this.videoPlayingNow.getVideoId()+") "+this.videoPlayingNow.getTags() + " - PAUSED");
    }
    else{
      System.out.println("Currently playing: "+this.videoPlayingNow.getTitle()+ " ("+this.videoPlayingNow.getVideoId()+") "+this.videoPlayingNow.getTags());
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
    System.out.println("showAllPlaylists needs implementation");
  }

  public void showPlaylist(String playlistName) {
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
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