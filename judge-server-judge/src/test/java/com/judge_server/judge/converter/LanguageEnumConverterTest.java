package com.judge_server.judge.converter;

import com.judge_server.core.entity.judge.Language;
import com.judge_server.runner.dto.RunnerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LanguageEnumConverterTest {

    @Test
    public void test() {
        assertThat(LanguageEnumConverter.convert(Language.C), is(RunnerRequest.Language.C));
        assertThat(LanguageEnumConverter.convert(Language.Cpp), is(RunnerRequest.Language.Cpp));
        assertThat(LanguageEnumConverter.convert(Language.Java), is(RunnerRequest.Language.Java));
    }

}
