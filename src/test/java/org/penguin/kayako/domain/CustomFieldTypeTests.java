package org.penguin.kayako.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.penguin.kayako.domain.CustomFieldType;

public class CustomFieldTypeTests {
    @Test
    public void testMarshalText() throws Exception {
        // arrange
        CustomFieldType type = CustomFieldType.TEXT;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        Integer v = adapter.marshal(type);
        
        // assert
        assertEquals(Integer.valueOf(1), v);
    }
    
    @Test
    public void testMarshalTextArea() throws Exception {
        // arrange
        CustomFieldType type = CustomFieldType.TEXTAREA;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        Integer v = adapter.marshal(type);
        
        // assert
        assertEquals(Integer.valueOf(2), v);
    }
    
    @Test
    public void testMarshalPassword() throws Exception {
        // arrange
        CustomFieldType type = CustomFieldType.PASSWORD;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        Integer v = adapter.marshal(type);
        
        // assert
        assertEquals(Integer.valueOf(3), v);
    }
    
    @Test
    public void testMarshalCheckbox() throws Exception {
        // arrange
        CustomFieldType type = CustomFieldType.CHECKBOX;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        Integer v = adapter.marshal(type);
        
        // assert
        assertEquals(Integer.valueOf(4), v);
    }
    
    @Test
    public void testMarshalRadio() throws Exception {
        // arrange
        CustomFieldType type = CustomFieldType.RADIO;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        Integer v = adapter.marshal(type);
        
        // assert
        assertEquals(Integer.valueOf(5), v);
    }
    
    @Test
    public void testMarshalSelect() throws Exception {
        // arrange
        CustomFieldType type = CustomFieldType.SELECT;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        Integer v = adapter.marshal(type);
        
        // assert
        assertEquals(Integer.valueOf(6), v);
    }
    
    @Test
    public void testMarshalMultiSelect() throws Exception {
        // arrange
        CustomFieldType type = CustomFieldType.MULTISELECT;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        Integer v = adapter.marshal(type);
        
        // assert
        assertEquals(Integer.valueOf(7), v);
    }
    
    @Test
    public void testMarshalCustom() throws Exception {
        // arrange
        CustomFieldType type = CustomFieldType.CUSTOM;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        Integer v = adapter.marshal(type);
        
        // assert
        assertEquals(Integer.valueOf(8), v);
    }
    
    @Test
    public void testMarshalLinkedFields() throws Exception {
        // arrange
        CustomFieldType type = CustomFieldType.LINKED_FIELDS;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        Integer v = adapter.marshal(type);
        
        // assert
        assertEquals(Integer.valueOf(9), v);
    }
    
    @Test
    public void testMarshalDate() throws Exception {
        // arrange
        CustomFieldType type = CustomFieldType.DATE;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        Integer v = adapter.marshal(type);
        
        // assert
        assertEquals(Integer.valueOf(10), v);
    }
    
    @Test
    public void testMarshalFile() throws Exception {
        // arrange
        CustomFieldType type = CustomFieldType.FILE;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        Integer v = adapter.marshal(type);
        
        // assert
        assertEquals(Integer.valueOf(11), v);
    }
    
    @Test
    public void testUnmarshalText() throws Exception {
        // arrange
        int v = 1;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.TEXT, accessibility);
    }
    
    @Test
    public void testUnmarshalTextArea() throws Exception {
        // arrange
        int v = 2;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.TEXTAREA, accessibility);
    }
    
    @Test
    public void testUnmarshalPassword() throws Exception {
        // arrange
        int v = 3;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.PASSWORD, accessibility);
    }
    
    @Test
    public void testUnmarshalCheckbox() throws Exception {
        // arrange
        int v = 4;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.CHECKBOX, accessibility);
    }
    
    @Test
    public void testUnmarshalRadio() throws Exception {
        // arrange
        int v = 5;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.RADIO, accessibility);
    }
    
    @Test
    public void testUnmarshalSelect() throws Exception {
        // arrange
        int v = 6;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.SELECT, accessibility);
    }
    
    @Test
    public void testUnmarshalMultiSelect() throws Exception {
        // arrange
        int v = 7;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.MULTISELECT, accessibility);
    }
    
    @Test
    public void testUnmarshalCustom() throws Exception {
        // arrange
        int v = 8;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.CUSTOM, accessibility);
    }
    
    @Test
    public void testUnmarshalLinkedFields() throws Exception {
        // arrange
        int v = 9;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.LINKED_FIELDS, accessibility);
    }
    
    @Test
    public void testUnmarshalDate() throws Exception {
        // arrange
        int v = 10;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.DATE, accessibility);
    }
    
    @Test
    public void testUnmarshalFile() throws Exception {
        // arrange
        int v = 11;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.FILE, accessibility);
    }
    
    @Test
    public void testUnmarshalUnknown() throws Exception {
        // arrange
        int v = -100;
        CustomFieldType.CustomFieldTypeAdapter adapter = new CustomFieldType.CustomFieldTypeAdapter();
        
        // act
        CustomFieldType accessibility = adapter.unmarshal(v);
        
        // assert
        assertEquals(CustomFieldType.UNKNOWN, accessibility);
    }
    
}
