package com.jts.tdd_approach_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/findAllPosts")
    public List<Post> findAllPosts() {
        return postService.findAllPost();
    }

    @GetMapping("/findById/{id}")
    public Post findById(@PathVariable int id) {
        return postService.findById(id);
    }
}
