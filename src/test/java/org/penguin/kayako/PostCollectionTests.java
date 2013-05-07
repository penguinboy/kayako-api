package org.penguin.kayako;

import org.junit.Test;
import org.penguin.kayako.domain.PostCollection;
import org.penguin.kayako.util.ContentLoader;

import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PostCollectionTests {
    @Test
    public void testPostsUnmarshall() throws Exception {
        // arrange
        String xml = ContentLoader.loadXMLFromFileInClassPath("/example_xml_posts.xml");

        Unmarshaller unmarshaller = UnmarshallerFactory.getMapper(PostCollection.class);

        // act
        PostCollection posts = (PostCollection) unmarshaller.unmarshal(new StringReader(xml));

        // assert
        assertNotNull(posts);
        assertEquals(2, posts.getPosts().size());
    }
}
