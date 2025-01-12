package com.jts.tdd_approach_demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class PostServiceTest {

    List<Post> posts = new ArrayList<>();

    @Mock
    private PostRepository repository;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        posts = List.of(new Post(1, 1, "Post - 1", "First Post"), new Post(2, 1, "Post - 2","Second Post"));
    }

    @Test
    public void testFindAllPost() {
        when(repository.findAll()).thenReturn(posts);

        List<Post> posts = postService.findAllPost();

        assertNotNull(posts);
        assertEquals(posts.size(), 2);
    }

    @Test
    public void testFindById() {
        when(repository.findById(1)).thenReturn(Optional.of(new Post(1, 1, "Post - 1", "First Post")));

        Post posts = postService.findById(1);

        assertNotNull(posts);
    }
}
