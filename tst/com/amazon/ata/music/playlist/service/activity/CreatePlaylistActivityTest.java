package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

import com.amazon.ata.music.playlist.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.CreatePlaylistResult;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;



import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.MockitoAnnotations.initMocks;

public class CreatePlaylistActivityTest {
    @InjectMocks
    private CreatePlaylistActivity createPlaylistActivity;
    @Mock
    private PlaylistDao playlistDao;

    @BeforeEach
    void setUp() {
        initMocks(this);


    }

    @Test
    public void handleRequest_createPlaylistAndSave_returnsPlaylistModelInResult() {
        // GIVEN
        int expectedIdLength = 5;
        String expectedName = "expectedName";
        String expectedCustomerId = "expectedCustomerId";


        CreatePlaylistRequest request = CreatePlaylistRequest.builder()
                .withCustomerId(expectedCustomerId)
                .withName(expectedName)
                .build();




        // WHEN
        CreatePlaylistResult result = createPlaylistActivity.handleRequest(request, null);

        // THEN
        assertNotNull(result.getPlaylist().getId(), "An id should be created");
        assertEquals(expectedName, result.getPlaylist().getName());
        assertEquals(expectedCustomerId, result.getPlaylist().getCustomerId());
        assertEquals(expectedIdLength, result.getPlaylist().getId().length());


    }
}