package com.layermark.library.repository;

import com.layermark.library.repository.entity.AuthorEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {

    @Query("SELECT c FROM AuthorEntity c WHERE (:name is null or c.name = :name)")
    List<AuthorEntity> findAllByName(String name);
}
