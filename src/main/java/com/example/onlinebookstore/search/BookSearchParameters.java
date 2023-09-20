package com.example.onlinebookstore.search;

import java.math.BigDecimal;

public record BookSearchParameters(BigDecimal[] prices, String[] titles, String[] authors) {
}
