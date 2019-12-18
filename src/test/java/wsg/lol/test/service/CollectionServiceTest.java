package wsg.lol.test.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import wsg.lol.service.intf.CollectionService;
import wsg.lol.test.base.BaseTest;

/**
 * Test for collection.
 *
 * @author Kingen
 */
public class CollectionServiceTest extends BaseTest {

    @Autowired
    private CollectionService collectionService;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void updateItems() {
        testVersion(version -> collectionService.updateItems(version));
    }

    @Test
    public void updateRunes() {
        testVersion(version -> collectionService.updateRunes(version));
    }

    @Test
    public void updateMaps() {
        testVersion(version -> collectionService.updateMaps(version));
    }

    @Test
    public void updateProfileIcons() {
        testVersion(version -> collectionService.updateProfileIcons(version));
    }

    @Test
    public void updateImages() {
    }
}