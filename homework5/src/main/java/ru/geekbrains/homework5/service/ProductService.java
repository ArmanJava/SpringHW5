package ru.geekbrains.homework5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.homework5.entity.Product;
import ru.geekbrains.homework5.repository.ProductPaginationRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductPaginationRepository productPaginationRepository;

    @Autowired
    public void setProductPaginationRepository(ProductPaginationRepository productPaginationRepository) {
        this.productPaginationRepository = productPaginationRepository;
    }

    public long getNumberOfPages(Integer min, Integer max) {
        long count = productPaginationRepository.findByCostBetween(min, max).size();
        return count % 3 == 0 ? count / 3 : (count / 3) + 1;
    }

    public List<Product> getPagingList(Integer pageNumber, Integer min, Integer max) {
        Page<Product> page = productPaginationRepository
                .findByCostBetween(PageRequest.of(pageNumber, 3), min, max);
        return page.getContent();
    }
}
