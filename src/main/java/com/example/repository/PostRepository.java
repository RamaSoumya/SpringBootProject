package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Post;

@Repository
public interface PostRepository  extends JpaRepository <Post, Long>{
	List<Post> findPostByTitle(String title);
	
	Optional<Post> findPostById(Long id);
	
	Post findPostByTitleAndDescription(String title, String description);
	
	List<Post> findPostByTitleOrDescription(String title, String description);
	
	List<Post> findPostByDescriptionIn(List<String> descriptions);
	
	List<Post> findPostByTitleContains (String title);
	
	List<Post> findPostByTitleStartsWith (String title);
	
	@Query("From Post where title=:title and description=:description")
	Post getPostByTitleAndDescription(String title, String description);
	
	@Modifying
	@Transactional
	@Query("Update Post set title=:title where id=:id")
	Integer UpdatePostByTitle(Long id, String title);
	
	@Modifying
	@Transactional
	@Query("Delete from Post where title=:title")
	Integer DeletePostByTitle(String title);
}
 