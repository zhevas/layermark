package com.layermark.library.mapper;

import com.layermark.library.api.dto.AuthorDto;
import com.layermark.library.api.dto.BookDto;
import com.layermark.library.repository.entity.AuthorEntity;
import com.layermark.library.repository.entity.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(target = "author", ignore = true)
    BookDto entityToDto(BookEntity source);
    BookEntity dtoToEntity(BookDto source);
}
