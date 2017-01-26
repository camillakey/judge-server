package com.judge_server.judge.component;

import com.judge_server.core.entity.judge.Language;
import com.judge_server.core.entity.judge.Submit;
import com.judge_server.core.entity.judge.SubmitState;
import com.judge_server.core.entity.judge.simple.SimpleJudgeIO;
import com.judge_server.core.repository.judge.SubmitRepository;
import com.judge_server.core.repository.judge.simple.SimpleJudgeIORepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleJudgeComponentTest extends AbstractJudgeComponentTest {

    @Autowired
    private JudgeComponent judgeComponent;

    @Autowired
    private SubmitRepository submitRepository;

    @Autowired
    private SimpleJudgeIORepository simpleJudgeIORepository;

    @Test
    public void accepted() throws Exception {
        String ln = System.lineSeparator();

        judgeIo(ln, "Hello World" + ln);


        Submit submit = submit(Language.C,
                "#include <stdio.h>" + ln
                + "int main(void) {" + ln
                + "    printf(\"Hello World\\n\");"+ ln
                + "    return 0;" + ln
                + "}"
        );

        judgeComponent.judge(submit);
        Thread.sleep(5000L);

        assertThat(submitRepository.findOne(submit.getId()).getSubmitState(), is(SubmitState.Accepted));
    }


    @Test
    public void multiInputAccepted() throws Exception {
        String ln = System.lineSeparator();

        judgeIo("1 2" + ln, "3" + ln);
        judgeIo("2 3" + ln, "5" + ln);
        judgeIo("3 4" + ln, "7" + ln);

        Submit submit = submit(Language.C,
                "#include <stdio.h>" + ln
                + "int main() {" + ln
                + "    int a, b;" + ln
                + "    scanf(\"%d %d\", &a, &b);" + ln
                + "    printf(\"%d\\n\", a + b);"+ ln
                + "    return 0;" + ln
                + "}"
        );

        judgeComponent.judge(submit);
        Thread.sleep(5000L);

        assertThat(submitRepository.findOne(submit.getId()).getSubmitState(), is(SubmitState.Accepted));
    }

    @Test
    public void multiInputRejected() throws Exception {
        String ln = System.lineSeparator();

        judgeIo("1 2" + ln, "2" + ln);
        judgeIo("2 1" + ln, "2" + ln);
        judgeIo("2 2" + ln, "4" + ln);

        Submit submit = submit(Language.C,
                "#include <stdio.h>" + ln
                + "int main() {" + ln
                + "    int a, b;" + ln
                + "    scanf(\"%d %d\", &a, &b);" + ln
                + "    printf(\"%d\\n\", a + b - 1);"+ ln
                + "    return 0;" + ln
                + "}"
        );

        judgeComponent.judge(submit);
        Thread.sleep(5000L);

        assertThat(submitRepository.findOne(submit.getId()).getSubmitState(), is(SubmitState.Rejected));
    }

    private void judgeIo(String input, String output) {
        SimpleJudgeIO simpleJudgeIO = new SimpleJudgeIO();
        simpleJudgeIO.setProblem(problem());
        simpleJudgeIO.setInput(input);
        simpleJudgeIO.setOutput(output);
        simpleJudgeIORepository.save(simpleJudgeIO);
    }

}
