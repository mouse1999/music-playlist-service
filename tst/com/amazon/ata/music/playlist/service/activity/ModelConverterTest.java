package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.models.SongModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelConverterTest {
    private ModelConverter modelConverter;
    private AlbumTrack albumTrack;
    private Playlist playlist;

    @BeforeEach
    public void setUp() {
        modelConverter = new ModelConverter();
        albumTrack = new AlbumTrack();
        playlist = new Playlist();


    }
    @Test
     void toPlaylistModel_receivedPlaylist_returnPlaylistModel() {
        //GIVEN
        String name = "Name";
        String id = "id";
        Integer songCount = 5;
        String customerId = "customerId";
        Set<String> tags = new HashSet<>();

        List<AlbumTrack> songList = new ArrayList<>();
        songList.add(albumTrack);



        playlist.setSongCount(songCount);
        playlist.setCustomerId(customerId);
        playlist.setName(name);
        playlist.setId(id);
        playlist.setTags(tags);
        playlist.setSongList(songList);

        //WHEN
        PlaylistModel playlistModel = modelConverter.toPlaylistModel(playlist);

        //THEN

        assertEquals(customerId, playlistModel.getCustomerId(), "Customer ID must be same");

    }
    @Test
    void toSongModel_receivedAlbumTrack_returnSongModel() {
       //GIVEN
        String albumName = "albumName";
        String asin = "asin";
        Integer trackNumber = 5;
        String songTitle = "ogechi";


        albumTrack.setAlbumName(albumName);
        albumTrack.setAsin(asin);
        albumTrack.setSongTitle(songTitle);
        albumTrack.setTrackNumber(trackNumber);

        //WHEN
        SongModel songModel = modelConverter.toSongModel(albumTrack);

        //THEN
        assertEquals(asin, songModel.getAsin(), "asin must be equal");

    }


}
