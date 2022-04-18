package com.layermark.library.repository;

import com.layermark.library.repository.entity.BookEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Integer> {

    @Query("SELECT c FROM BookEntity c WHERE (:name is null or c.name = :name) and (:isbn is null"
        + " or c.isbn = :isbn)")
    List<BookEntity> findByIsbnAndName(String isbn, String name);
}
