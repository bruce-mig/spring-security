package com.github.bruce_mig.mssc.controller;

import com.github.bruce_mig.mssc.model.Post;
import com.github.bruce_mig.mssc.repository.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostRepository repository;


    public PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Post> findAll(){
        return repository.findAll();
    }
}
