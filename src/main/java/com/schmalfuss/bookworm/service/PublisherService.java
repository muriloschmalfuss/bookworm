package com.schmalfuss.bookworm.service;

import com.schmalfuss.bookworm.model.dto.PublisherDTO;
import com.schmalfuss.bookworm.model.entity.PublisherEntity;
import com.schmalfuss.bookworm.model.mapper.PublisherMapper;
import com.schmalfuss.bookworm.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherMapper publisherMapper;

    public List<PublisherDTO> getAll() {
        List<PublisherEntity> entityList = publisherRepository.findAll();
        return entityList.stream().map(publisherEntity -> publisherMapper.update(publisherEntity)).toList();
    }

    public PublisherDTO getById(Long id) {
        Optional<PublisherEntity> publisherEntityOp = publisherRepository.findById(id);
        if (publisherEntityOp.isEmpty()) {
            PublisherEntity publisherEntity = publisherEntityOp.get();
            return publisherMapper.update(publisherEntity);
        }

        throw new EntityNotFoundException("Editora não encontrada");
    }

    public PublisherDTO create(PublisherDTO publisherDTO) {
        PublisherEntity publisher = publisherMapper.update(publisherDTO);

        publisherRepository.save(publisher);
        return publisherMapper.update(publisher);
    }

    public PublisherDTO edit(PublisherDTO publisherDTO, Long id) {
        if(publisherRepository.existsById(id)) {
            PublisherEntity publisherEntity = publisherMapper.update(publisherDTO);
            publisherEntity.setId(id);
            publisherEntity = publisherRepository.save(publisherEntity);

            return publisherMapper.update(publisherEntity);
        }

        throw new EntityNotFoundException("Editora não encontrada");
    }

    public void destroy(Long id) {
        Optional<PublisherEntity> publisherEntityOp = publisherRepository.findById(id);
        if (publisherEntityOp.isPresent()) {
            PublisherEntity publisherEntity = publisherEntityOp.get();
            publisherRepository.delete(publisherEntity);
            return;
        }

        throw new EntityNotFoundException("Editora não encontrada");
    }
}
