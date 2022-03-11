package com.bk.movies;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

    @Query("SELECT title from Movie m WHERE m.title LIKE %:titleMovie%")
    String findByName(String titleMovie);

    @Query("SELECT id from Movie m WHERE m.id LIKE %:idMovie")
    String findById(String idMovie);

    @Query("DELETE from Movie m WHERE m.id LIKE %:idMovie")
    String deleteById(String idMovie);
}
