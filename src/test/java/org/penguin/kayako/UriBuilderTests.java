package org.penguin.kayako;

import junit.framework.Assert;

import org.junit.Test;

public class UriBuilderTests {
    
    @Test
    public void testPathAddedCorrectly() throws Exception {
        // arrange
        UriBuilder builder = new UriBuilder("my.kayako.com");
        
        // act
        builder = builder.path("test");
        String result = builder.toURL().toString();
        
        // assert
        Assert.assertEquals("http://my.kayako.com/test", result);
    }
    
    @Test
    public void testMultiplePathAddedCorrectly() throws Exception {
        // arrange
        UriBuilder builder = new UriBuilder("my.kayako.com");
        
        // act
        builder = builder.path("test").path("this").path("function");
        String result = builder.toURL().toString();
        
        // assert
        Assert.assertEquals("http://my.kayako.com/test/this/function", result);
    }
    
    @Test
    public void testQueryPathAddedCorrectly() throws Exception {
        // arrange
        UriBuilder builder = new UriBuilder("my.kayako.com");
        
        // act
        builder = builder.queryPath("test");
        String result = builder.toURL().toString();
        
        // assert
        Assert.assertEquals("http://my.kayako.com?/test", result);
    }
    
    @Test
    public void testMultipleQueryPathAddedCorrectly() throws Exception {
        // arrange
        UriBuilder builder = new UriBuilder("my.kayako.com");
        
        // act
        builder = builder.queryPath("test").queryPath("multiple");
        String result = builder.toURL().toString();
        
        // assert
        Assert.assertEquals("http://my.kayako.com?/test/multiple", result);
    }
    
    @Test
    public void testQueryPathAndPath() throws Exception {
        // arrange
        UriBuilder builder = new UriBuilder("my.kayako.com");
        
        // act
        builder = builder.path("api").queryPath("base").queryPath("department").path("test");
        String result = builder.toURL().toString();
        
        // assert
        Assert.assertEquals("http://my.kayako.com/api/test?/base/department", result);
    }
    
    @Test
    public void testQueryParameter() throws Exception {
        // arrange
        UriBuilder builder = new UriBuilder("my.kayako.com");
        
        // act
        builder = builder.queryParam("somekey", "lolvalue");
        String result = builder.toURL().toString();
        
        // assert
        Assert.assertEquals("http://my.kayako.com?somekey=lolvalue", result);
    }
    
    @Test
    public void testMultipleQueryParameter() throws Exception {
        // arrange
        UriBuilder builder = new UriBuilder("my.kayako.com");
        
        // act
        builder = builder.queryParam("somekey", "lolvalue").queryParam("anotherkey", "value");
        String result = builder.toURL().toString();
        
        // assert
        Assert.assertEquals("http://my.kayako.com?somekey=lolvalue&anotherkey=value", result);
    }
    
    @Test
    public void testQueryPathWithQueryParameter() throws Exception {
        // arrange
        UriBuilder builder = new UriBuilder("my.kayako.com");
        
        // act
        builder = builder.queryParam("somekey", "lolvalue").queryParam("anotherkey", "value").queryPath("query").queryPath("path");
        String result = builder.toURL().toString();
        
        // assert
        Assert.assertEquals("http://my.kayako.com?/query/path&somekey=lolvalue&anotherkey=value", result);
    }
    
}
