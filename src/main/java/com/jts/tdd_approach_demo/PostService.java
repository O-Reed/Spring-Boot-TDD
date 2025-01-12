package com.jts.tdd_approach_demo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Post findById(int id) {
        return postRepository.findById(id).get();
    }
}
