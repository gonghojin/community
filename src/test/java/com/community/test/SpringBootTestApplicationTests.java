package com.community.test;

import com.community.CommunityApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(/*value = "value=test",*/ properties = {"property.value=propertyTest"}
    , classes = {CommunityApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
) // value, properties 프로퍼티는 함께 사용 불가
public class SpringBootTestApplicationTests {
/*
    @Value("${value}")
    private String value;*/

    @Value("${property.value}")
    private String propertyValue;

    @Test
    public void name() {
//        assertThat(value, is("test"));
        assertThat(propertyValue, is("propertyTest"));
    }
}
