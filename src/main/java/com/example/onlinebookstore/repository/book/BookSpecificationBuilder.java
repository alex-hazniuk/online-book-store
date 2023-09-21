package com.example.onlinebookstore.repository.book;

import com.example.onlinebookstore.model.Book;
import com.example.onlinebookstore.repository.SpecificationBuilder;
import com.example.onlinebookstore.repository.SpecificationProviderManager;
import com.example.onlinebookstore.search.BookSearchParameters;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private static final int PRICES_PARAMETER_LENGTH = 2;
    private static final String KEY_FOR_PRICE = "price";
    private static final String KEY_FOR_TITLE = "tittle";
    private static final String KEY_FOR_AUTHOR = "author";
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParameters searchParameters) {
        Specification<Book> defaultSpec = Specification.where(null);
        String[] prices = searchParameters.prices();
        String[] titles = searchParameters.titles();
        String[] authors = searchParameters.authors();
        if (prices != null && prices.length == PRICES_PARAMETER_LENGTH) {
            defaultSpec = defaultSpec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(KEY_FOR_PRICE)
                    .getSpecification(prices));
        }
        if (titles != null && titles.length > 0) {
            defaultSpec = defaultSpec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(KEY_FOR_TITLE)
                    .getSpecification(titles));
        }
        if (authors != null && authors.length > 0) {
            defaultSpec = defaultSpec.and(bookSpecificationProviderManager
                    .getSpecificationProvider(KEY_FOR_AUTHOR)
                    .getSpecification(authors));
        }
        return defaultSpec;
    }
}
