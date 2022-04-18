package com.layermark.library.mapper;

import com.layermark.library.api.dto.AuthorDto;
import com.layermark.library.repository.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "books", ignore = true)
    AuthorDto entityToDto(AuthorEntity source);
    AuthorEntity dtoToEntity(AuthorDto source);
}
