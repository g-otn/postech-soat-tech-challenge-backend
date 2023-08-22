package br.com.grupo63.techchallenge.usecase.product;

import br.com.grupo63.techchallenge.entity.product.Product;
import br.com.grupo63.techchallenge.entity.validation.group.Create;
import br.com.grupo63.techchallenge.entity.validation.group.Update;
import br.com.grupo63.techchallenge.exception.NotFoundException;
import br.com.grupo63.techchallenge.exception.ValidationException;
import br.com.grupo63.techchallenge.gateway.repository.IProductRepository;
import br.com.grupo63.techchallenge.usecase.Validator;
import jakarta.validation.ConstraintViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor

@Service
public class ProductUseCase implements IProductUseCase {

    private final Validator validator;

    @Override
    public Product create(Product entity, IProductRepository gateway) throws ValidationException {
        validator.validate(entity, Create.class);
        return gateway.saveAndFlush(entity);
    }

    @Override
    public Product read(Long id, IProductRepository gateway) throws NotFoundException {
        return gateway.findByIdAndDeletedFalse(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Product> list(IProductRepository gateway) {
        return gateway.findByDeletedFalse();
    }


    @Override
    public Product update(Product entity, IProductRepository gateway) throws ValidationException, NotFoundException {
        validator.validate(entity, Update.class);
        return gateway.saveAndFlush(entity);
    }

    @Override
    public void delete(Product entity, IProductRepository gateway) {
        entity.delete();
        gateway.saveAndFlush(entity);
    }

    @Override
    public List<Product> listByCategoryName(String categoryName, IProductRepository gateway) {
        return gateway.findByDeletedFalseAndCategory_Name(categoryName);
    }
}
