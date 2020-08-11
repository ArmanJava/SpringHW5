package ru.geekbrains.homework5.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.homework5.entity.Product;

import java.util.List;

@Repository
public interface ProductPaginationRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findByCostBetween(Pageable pageable, Integer min, Integer max);

    List<Product> findByCostBetween(Integer min, Integer max);
}
