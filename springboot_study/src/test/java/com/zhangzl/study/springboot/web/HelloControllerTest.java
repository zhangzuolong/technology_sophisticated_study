package com.zhangzl.study.springboot.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Description  : 单元测试
 * Create  User : zhangzuolong
 * Create  Time : 2017/9/13 11:08
 */
//引入Spring对JUnit4的支持
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HelloController.class)
public class HelloControllerTest {

    private MockMvc mvc;

    /**
     * JUnit中定义在测试用例@Test内容执行前预加载的内容
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception{
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

    @Test
    public void hello() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello world!")));
    }
}
