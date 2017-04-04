package ru.tisov.denis.platform.async.callbacks;

import org.junit.Assert;
import org.junit.Test;
import ru.tisov.denis.platform.domain.Property;

import java.util.Arrays;

public class StartAfterPullImageCallbackTest {

    @Test
    public void testGetProperties() throws Exception {
        String properties = StartAfterPullImageCallback.getProperties(Arrays.asList(
                new Property("name1", "value1"),
                new Property("name2", "value2")));

        Assert.assertEquals("-Dname1=value1 -Dname2=value2", properties);
    }

}