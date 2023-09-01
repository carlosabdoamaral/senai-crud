package com.senai.crud.repository;

import com.senai.crud.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Long> {
    @Query(value = "SELECT * FROM post_tb WHERE title LIKE %?1%", nativeQuery = true)
    List<PostModel> findByTitle(String searchString);
}
