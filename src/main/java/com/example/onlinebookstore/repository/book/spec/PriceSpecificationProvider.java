package com.example.onlinebookstore.repository.book.spec;

import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.repository.SpecificationProvider;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecificationProvider implements SpecificationProvider<Book> {
    private static final String ATTRIBUTE = "price";
    private static final int FROM = 0;
    private static final int TO = 1;

    @Override
    public String getKey() {
        return ATTRIBUTE;
    }

    @Override
    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.and(
                criteriaBuilder.gt(root.get(ATTRIBUTE), new BigDecimal(params[FROM])),
                criteriaBuilder.lt(root.get(ATTRIBUTE), new BigDecimal(params[TO])));
    }
}
