package com.senai.crud.service;

import com.senai.crud.model.PostModel;
import com.senai.crud.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public Optional<PostModel> save(PostModel body) {
        try {
            PostModel p = this.repository.save(body);
            return Optional.of(p);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public ResponseEntity<String> deleteById(Long id) {
        try {
            if (id <= 0) {
                return new ResponseEntity<String>("ID must be greater than 0", HttpStatus.OK);
            }

            this.repository.deleteById(id);
            return new ResponseEntity<String>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<PostModel> findAll() {
        return this.repository.findAll();
    }

    public Optional<PostModel> findById(Long id) {
        try {
            if (id <= 0) {
                return Optional.empty();
            }

            return this.repository.findById(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<PostModel> findByTitle(String searchString) {
        return this.repository.findByTitle(searchString);
    }

    public Optional<PostModel> updatePost(PostModel post) {
        try {
            boolean founded = this.repository.findById(post.id).isPresent();
            if (!founded) {
                return Optional.empty();
            }

            PostModel p = this.repository.save(post);
            return Optional.of(p);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
