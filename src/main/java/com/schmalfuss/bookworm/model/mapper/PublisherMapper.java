package com.schmalfuss.bookworm.model.mapper;

import com.schmalfuss.bookworm.model.dto.BookDTO;
import com.schmalfuss.bookworm.model.dto.PublisherDTO;
import com.schmalfuss.bookworm.model.entity.PublisherEntity;
import org.springframework.stereotype.Component;

@Component
public class PublisherMapper {
    public PublisherDTO update(PublisherEntity publisherEntity) {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(publisherEntity.getId());
        publisherDTO.setName(publisherEntity.getName());
        publisherDTO.setDescription(publisherEntity.getDescription());
        return publisherDTO;
    }

    public PublisherEntity update(PublisherDTO publisherDTO) {
        PublisherEntity publisherEntity = new PublisherEntity();
        publisherEntity.setId(publisherDTO.getId());
        publisherEntity.setName(publisherDTO.getName());
        publisherEntity.setDescription(publisherDTO.getDescription());
        return publisherEntity;
    }
}
