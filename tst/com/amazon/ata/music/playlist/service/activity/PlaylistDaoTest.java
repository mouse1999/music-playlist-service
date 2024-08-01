package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.PlaylistNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlaylistDaoTest {
    @InjectMocks
    private PlaylistDao playlistDao;
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @BeforeEach
    void setUp() {
        initMocks(this);

    }

    @Test
    void getPlaylist_inputValidId_returnPlaylist() {

        // GIVEN
        String expectedId = "expectedId";
        String expectedName = "expectedName";
        String expectedCustomerId = "expectedCustomerId";
        int expectedSongCount = 0;


        Playlist playlist = new Playlist();
        playlist.setId(expectedId);
        playlist.setName(expectedName);
        playlist.setCustomerId(expectedCustomerId);
        playlist.setSongCount(expectedSongCount);

        when(dynamoDBMapper.load(Playlist.class, expectedId)).thenReturn(playlist);

        Playlist playlist1 = playlistDao.getPlaylist(expectedId);

        assertEquals(playlist1, playlist);
        assertEquals(expectedId, playlist1.getId(), "Expected id to be same");


    }

    @Test
    void getPlaylist_inputInvalidId_throwAnException() {
        // GIVEN
        String validId = "validId";
        String invalidId = "invalidId";
        String Name = "Name";
        String CustomerId = "CustomerId";
        int expectedSongCount = 0;


        Playlist playlist = new Playlist();
        playlist.setId(validId);
        playlist.setName(Name);
        playlist.setCustomerId(CustomerId);
        playlist.setSongCount(expectedSongCount);

        when(dynamoDBMapper.load(Playlist.class, invalidId)).thenReturn(null);

        assertThrows(PlaylistNotFoundException.class, () -> playlistDao.getPlaylist(invalidId));

    }
}
