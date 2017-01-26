package com.judge_server.web.integrated;

import com.judge_server.core.entity.judge.JudgeSystem;
import com.judge_server.core.entity.judge.Language;
import com.judge_server.core.entity.judge.SubmitState;
import com.judge_server.core.repository.judge.SubmitRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProblemIntegratedTest extends AbstractIntegratedTest {

    @Autowired
    private SubmitRepository submitRepository;

    @Test
    public void problem01IntegratedTest() throws Exception {
        integrate(JudgeSystem.Simple, "problem01IntegratedTest", "\r\n", "hello\r\n",
                Language.Cpp,
                "#include <iostream>\nint main() {std::cout<<\"hello\"<<std::endl;}", SubmitState.Accepted);
    }

    @Test
    public void problem02IntegratedTest() throws Exception {
        integrate(JudgeSystem.Simple, "problem02IntegratedTest", "\r\n", "hello\r\nhello\r\n",
                Language.Cpp, "#include <iostream>\r\nint main() {"
                        + "std::cout<<\"hello\"<<std::endl;std::cout<<\"hello\"<<std::endl;}", SubmitState.Accepted);
    }

    @Test
    public void problem03IntegratedTest() throws Exception {
        integrate(JudgeSystem.Simple, "problem03IntegratedTest", "2\r\n", "hello\r\nhello\r\n",
                Language.Cpp, "#include <iostream>\r\nusing namespace std;int main() {"
                        + "int n;cin>>n;for(int i=0;i<n;i++){cout<<\"hello\"<<endl;}}", SubmitState.Accepted);
    }

    @Test
    public void problem04IntegratedTest() throws Exception {
        integrate(JudgeSystem.Simple, "problem04IntegratedTest", "\r\n", "hello\r\n",
                Language.Cpp,
                "#include <iostream>\nint main() {std::cout<<\"hello\";}", SubmitState.Rejected);
    }


    private void integrate(JudgeSystem judgeSystem, String id, String input, String output,
                           Language language, String source, SubmitState assertState) throws Exception {
        mockMvc().perform(post("/problem/create/" + judgeSystem.toString().toLowerCase()).with(signInAsAdmin())
                .param("problemId", id)
                .param("judgeSystem", judgeSystem.toString())
                .param("title", id)
                .param("detail", id)
                .param("timeLimit", "1000")
                .param("memoryLimit", "65536")
                .param("input", input)
                .param("output", output))
                .andExpect(redirectedUrl("/problem/" + id));

        mockMvc().perform(post("/submit/" + id).with(signIn())
                .param("language", language.toString())
                .param("source", source))
                .andExpect(redirectedUrl("/submit"));

        Thread.sleep(2000L);

        assertThat(getLatestSubmitState(), is(assertState));
    }

    private SubmitState getLatestSubmitState() {
        return submitRepository.findAllByOrderByCreatedDesc(new PageRequest(0, 10))
                .getContent().get(0).getSubmitState();
    }

}
