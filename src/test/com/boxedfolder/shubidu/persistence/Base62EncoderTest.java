package com.boxedfolder.shubidu.persistence;

import com.boxedfolder.shubidu.persistence.domain.helper.encoding.Base62Encoder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Base62EncoderTest {
    @Test
    public void testEncoding() {
        Base62Encoder encoder = new Base62Encoder();
        assertThat(encoder.encode(1L), equalTo("b"));
    }
}
