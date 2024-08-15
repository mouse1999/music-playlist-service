package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.AlbumTrackDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.helpers.AlbumTrackTestHelper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AlbumTrackDaoTest {

    @InjectMocks
    private AlbumTrackDao albumTrackDao;
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @BeforeEach
    void setUp() {
        initMocks(this);

    }

    @Test
    void getAlbumTrack_validIdandTrackNumber_returnAnAlbum() {
        //GIVEN
        AlbumTrack albumTrack = AlbumTrackTestHelper.generateAlbumTrack(8);
        String expectedAsin = albumTrack.getAsin();
        Integer expectedTrackNumber = albumTrack.getTrackNumber();

        when(dynamoDBMapper.load(AlbumTrack.class,expectedAsin, expectedTrackNumber)).thenReturn(albumTrack);

        //WHEN
        AlbumTrack albumTrackResult = albumTrackDao.getAlbumTrack(expectedAsin, expectedTrackNumber);

        //THEN

        assertEquals(expectedAsin, albumTrackResult.getAsin(), "Asin must be equal");

    }
    @Test
    void getAlbumTrack_inputInvalidIdOrTrackNumber_returnNull() {

        AlbumTrack albumTrack = AlbumTrackTestHelper.generateAlbumTrack(9);
        String expectedAsin = albumTrack.getAsin();
        Integer invalidTrackNumber = 20;


        when(dynamoDBMapper.load(AlbumTrack.class,expectedAsin, invalidTrackNumber)).thenReturn(null);

        //WHEN
        AlbumTrack albumTrackResult = albumTrackDao.getAlbumTrack(expectedAsin, invalidTrackNumber);

        //THEN
        assertNull(albumTrackResult, "album track must be null");
    }
}
