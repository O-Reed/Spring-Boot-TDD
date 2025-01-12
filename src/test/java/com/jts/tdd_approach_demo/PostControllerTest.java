package com.jts.tdd_approach_demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest
public class PostControllerTest {

    List<Post> posts = new ArrayList<>();

    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private PostService postService;

    @BeforeEach
    void setup() {
        posts = List.of(new Post(1, 1, "Post - 1", "First Post"), new Post(2, 1, "Post - 2","Second Post"));
    }

    @Test
    public void testFindAllPost() throws Exception {
        String jsonResponse = """
                [
                    {
                        "id": 1,
                        "userId": 1,
                        "title": "Post - 1",
                        "body": "First Post"
                    },
                    {
                        "id": 2,
                        "userId": 1,
                        "title": "Post - 2",
                        "body": "Second Post"
                    }
                ]
                """;
        when(postService.findAllPost()).thenReturn(posts);

        ResultActions resultActions = mockMVC.perform(get("/api/findAllPosts")).andExpect(status().isOk()).andExpect(content().json(jsonResponse));

        JSONAssert.assertEquals(jsonResponse, resultActions.andReturn().getResponse().getContentAsString(), false);
    }

    @Test
    public void testFindAllPostById() throws Exception {
        String jsonResponse = """
                    {
                        "id": 1,
                        "userId": 1,
                        "title": "Post - 1",
                        "body": "First Post"
                    }
                """;
        when(postService.findById(1)).thenReturn(new Post(1,1, "Post - 1", "First Post"));

        ResultActions resultActions = mockMVC.perform(get("/api/findById/1")).andExpect(status().isOk()).andExpect(content().json(jsonResponse));

        JSONAssert.assertEquals(jsonResponse, resultActions.andReturn().getResponse().getContentAsString(), false);
    }
}
