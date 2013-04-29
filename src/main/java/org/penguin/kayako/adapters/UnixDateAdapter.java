package org.penguin.kayako.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;

public class UnixDateAdapter extends XmlAdapter<Integer, Date> {
    
    @Override
    public Integer marshal(Date v) throws Exception {
        return (int) (v.getTime() / 1000);
    }
    
    @Override
    public Date unmarshal(Integer v) throws Exception {
        return new Date((long) v * 1000);
    }
    
}
