package com.senai.crud.controller;

import com.senai.crud.model.PostModel;
import com.senai.crud.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("")
    List<PostModel> findAll() {
        return this.postService.findAll();
    }

    @GetMapping("/{id}")
    Optional<PostModel> findById(@PathVariable Long id) {
        return this.postService.findById(id);
    }

    @GetMapping("/search/{searchString}")
    List<PostModel> findByTitle(@PathVariable String searchString) {

        if (searchString.isEmpty()) {
            return List.of();
        }

        return this.postService.findByTitle(searchString);
    }

    @PostMapping("/save")
    ResponseEntity<Optional<PostModel>> save(@Valid @RequestBody PostModel postModel) {
        Optional<PostModel> savedPost = this.postService.save(postModel);

        if (savedPost.isEmpty()) {
            return new ResponseEntity<>(Optional.empty(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteById(@PathVariable Long id) {
        return this.postService.deleteById(id);
    }

    @PutMapping("/update")
    Optional<PostModel> updatePost(@RequestBody PostModel body) {
        return this.postService.updatePost(body);
    }
}
