package com.github.bruce_mig.mssc.repository;

import com.github.bruce_mig.mssc.model.Post;
import org.springframework.data.repository.ListCrudRepository;

public interface PostRepository extends ListCrudRepository<Post, Integer> {
}
