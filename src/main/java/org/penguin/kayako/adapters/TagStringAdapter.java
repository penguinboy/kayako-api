package org.penguin.kayako.adapters;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.List;

public class TagStringAdapter extends XmlAdapter<String, List<String>> {
    @Override
    public String marshal(List<String> arg0) throws Exception {
        return Joiner.on(' ').skipNulls().join(arg0);
    }
    
    @Override
    public List<String> unmarshal(String arg0) throws Exception {
        return Lists.newArrayList(Splitter.on(' ').trimResults().omitEmptyStrings().split(arg0));
    }
}
