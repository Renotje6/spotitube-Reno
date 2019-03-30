package nl.han.oose.persistence;

import nl.han.oose.entity.playlists.Playlist;
import nl.han.oose.entity.playlists.Playlists;
import nl.han.oose.entity.tracks.Track;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistsDAO {

    @Inject
    private ConnectionFactory connectionFactory;

    public Playlists getAllPlaylists(String username) {
        List<Playlist> playlistList = new ArrayList<>();
        int playlistTotalLength = 0;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM playlist")
        ) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("playlistId");
                String name = resultSet.getString("name");
                boolean isOwner = username.equals(resultSet.getString("username"));
                playlistList.add(new Playlist(id, name, isOwner, new ArrayList<Track>()));
                playlistTotalLength += getTotalLengthOfPlaylist(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Playlists(playlistList, playlistTotalLength);
    }

    private int getTotalLengthOfPlaylist(int id) {
        int length = 0;
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT SUM(duration) AS lengthOfPlaylist FROM tracks WHERE trackId IN(SELECT trackId FROM tracks_in_playlist WHERE playlistId = ?)")
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                length += resultSet.getInt("lengthOfPlaylist");
            }
            return length;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deletePlaylist(int playlistId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM playlist WHERE playlistId = ?")
        ) {
            deleteStatement.setInt(1, playlistId);
            deleteStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPlaylist(Playlist playlistToAdd, String username) {
        String playlistName = playlistToAdd.getName();

        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("INSERT INTO playlist (name, username) VALUES (?, ?)")
        ) {
            statement.setString(1, playlistName);
            statement.setString(2, username);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editPlaylist(Playlist playlist, int playlistId) {
        try (
                Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE playlist SET name = ? WHERE playlistId = ?")
        ) {
            statement.setString(1, playlist.getName());
            statement.setInt(2, playlistId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
