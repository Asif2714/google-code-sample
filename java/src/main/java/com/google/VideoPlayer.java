package com.google;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
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
    System.out.println("playRandomVideo needs implementation");
  }

  public void pauseVideo() {
    System.out.println("pauseVideo needs implementation");
  }

  public void continueVideo() {
    System.out.println("continueVideo needs implementation");
  }

  public void showPlaying() {
    System.out.println("showPlaying needs implementation");
  }

  public void createPlaylist(String playlistName) {
    System.out.println("createPlaylist needs implementation");
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    System.out.println("addVideoToPlaylist needs implementation");
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