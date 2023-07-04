package br.com.grupo63.techchallenge.core.application.usecase;

import br.com.grupo63.techchallenge.core.application.repository.IProductRepository;
import br.com.grupo63.techchallenge.core.application.usecase.dto.ProductDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
public class ProductUseCase implements ICRUDUseCase<ProductDTO> {

    private final IProductRepository repository;

    @Override
    public ProductDTO create(@Valid ProductDTO productDTO) {
        return ProductDTO.toDto(repository.saveAndFlush(productDTO.toDomain()));
    }

    public ProductDTO read(Long id) {
        return ProductDTO.toDto(repository.findById(id).orElseThrow());
    }

    public List<ProductDTO> list() {
        return repository.findByDeletedFalse().stream().map(ProductDTO::toDto).collect(Collectors.toList());
    }

    public ProductDTO update(@Valid ProductDTO productDTO, Long id) {
        return ProductDTO.toDto(repository.saveAndFlush(productDTO.toDomain()));
    }

    public void delete(Long id) {
        repository.removeLogicallyById(id);
    }
}
