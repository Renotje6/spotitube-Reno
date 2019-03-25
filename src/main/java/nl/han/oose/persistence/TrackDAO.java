package nl.han.oose.persistence;

import nl.han.oose.entity.tracks.Song;
import nl.han.oose.entity.tracks.Track;
import nl.han.oose.entity.tracks.Tracks;
import nl.han.oose.entity.tracks.Video;

import javax.enterprise.inject.Default;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackDAO {
    ConnectionFactory connectionFactory;

    public TrackDAO() {
        connectionFactory = new ConnectionFactory();
    }

    public Tracks getTracksInPlaylist(int playlistId, boolean inPlaylist){
        List<Track> tracks = new ArrayList<>();
        if(inPlaylist) {
            tracks.addAll(getSongsInPlaylist(playlistId));
            tracks.addAll(getVideosInPlaylist(playlistId));
        } else if(!inPlaylist){
            tracks.addAll(getSongsNotInPlaylist(playlistId));
            tracks.addAll(getVideosNotInPlaylist(playlistId));
        }
        return new Tracks(tracks);
    }

    private List<Track> getVideosNotInPlaylist(int playlistId) {
        List<Track> tracks = new ArrayList<>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM videos_view " +
                        "LEFT JOIN tracks_in_playlist " +
                        "ON tracks_in_playlist.trackId = videos_view.trackId " +
                        "AND tracks_in_playlist.playlistId = ? " +
                        "WHERE videos_view.trackId NOT IN(SELECT trackId FROM tracks_in_playlist WHERE playlistId = ?);")
        ){
            statement.setInt(1, playlistId);
            statement.setInt(2, playlistId);
            ResultSet resultSet = statement.executeQuery();
            getVideosFromResultSet(tracks, resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return tracks;
    }

    private List<Track> getVideosInPlaylist(int playlistId) {
        List<Track> tracks = new ArrayList<>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM videos_view " +
                        "LEFT JOIN tracks_in_playlist " +
                        "ON tracks_in_playlist.trackId = videos_view.trackId " +
                        "AND tracks_in_playlist.playlistId = ? " +
                        "WHERE videos_view.trackId IN(SELECT trackId FROM tracks_in_playlist WHERE playlistId = ?);")
        ){
            statement.setInt(1, playlistId);
            statement.setInt(2, playlistId);
            ResultSet resultSet = statement.executeQuery();
            getVideosFromResultSet(tracks, resultSet);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return tracks;
    }

    private List<Track> getSongsInPlaylist(int playlistId) {
        List<Track> tracks = new ArrayList<>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM songs_view " +
                        "LEFT JOIN tracks_in_playlist " +
                        "ON tracks_in_playlist.trackId = songs_view.trackId " +
                        "AND tracks_in_playlist.playlistId = ? " +
                        "WHERE songs_view.trackId IN(SELECT trackId FROM tracks_in_playlist WHERE playlistId = ?);")
        ){
            statement.setInt(1, playlistId);
            statement.setInt(2, playlistId);
            ResultSet resultSet = statement.executeQuery();

            getSongsFromResultSet(resultSet, tracks);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return tracks;
    }

    private List<Track> getSongsNotInPlaylist(int playlistId) {
        List<Track> tracks = new ArrayList<>();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM songs_view " +
                        "LEFT JOIN tracks_in_playlist " +
                        "ON tracks_in_playlist.trackId = songs_view.trackId " +
                        "AND tracks_in_playlist.playlistId = ? " +
                        "WHERE songs_view.trackId NOT IN(SELECT trackId FROM tracks_in_playlist WHERE playlistId = ?);")
        ){
            statement.setInt(1, playlistId);
            statement.setInt(2, playlistId);
            ResultSet resultSet = statement.executeQuery();

            getSongsFromResultSet(resultSet, tracks);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return tracks;
    }

    private List<Track> getSongsFromResultSet(ResultSet resultSet, List<Track> tracks) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("trackId");
            String title = resultSet.getString("title");
            String performer = resultSet.getString("performer");
            int duration = resultSet.getInt("duration");
            int playcount = resultSet.getInt("playcount");
            String url = resultSet.getString("url");
            String album = resultSet.getString("album");
            boolean offlineAvailabe = resultSet.getBoolean("offlineAvailable");
            tracks.add(new Song(id, performer, title, url, duration, playcount, offlineAvailabe, album));
        }
        return tracks;
    }

    private List<Track> getVideosFromResultSet(List<Track> tracks, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt("trackId");
            String title = resultSet.getString("title");
            String performer = resultSet.getString("performer");
            int duration = resultSet.getInt("duration");
            int playcount = resultSet.getInt("playcount");
            String url = resultSet.getString("url");
            String publicationDate = resultSet.getString("publicationDate");
            String description = resultSet.getString("description");
            boolean offlineAvailabe = resultSet.getBoolean("offlineAvailable");
            tracks.add(new Video(id, performer, title, url, duration, playcount, offlineAvailabe, publicationDate, description));
        }
        return tracks;
    }

    public void deleteTrackFromPlaylist(int playlistId, int trackId) {
        try(
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE FROM tracks_in_playlist WHERE playlistId = ? AND trackId = ?")
                ) {
            statement.setInt(1, playlistId);
            statement.setInt(2, trackId);
            statement.execute();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void addTrackToPlaylist(Track track, int playlistId){
        try(
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO tracks_in_playlist (playlistId, trackId, offlineAvailable) " +
                        "VALUES (?, ?, ?)")
                ){
            statement.setInt(1, playlistId);
            statement.setInt(2, track.getId());
            statement.setBoolean(3, track.isOfflineAvailable());
            statement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
