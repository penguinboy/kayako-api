package org.penguin.kayako.util;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Util class for loading xml data for tests.
 *
 * @author fatroom
 */
public class ContentLoader {
    public static String loadXMLFromFileInClassPath(String filename) throws IOException {
        return CharStreams.toString(new InputStreamReader(ContentLoader.class.getResourceAsStream(filename)));
    }
}
