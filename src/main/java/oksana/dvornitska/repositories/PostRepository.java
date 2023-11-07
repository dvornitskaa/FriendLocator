package oksana.dvornitska.repositories;

import oksana.dvornitska.entities.Post;
import oksana.dvornitska.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAll();
}
