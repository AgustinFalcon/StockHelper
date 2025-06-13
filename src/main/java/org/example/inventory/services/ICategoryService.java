package org.example.inventory.services;

import org.example.inventory.model.Category;
import org.example.inventory.response.CategoryResponse;
import org.example.inventory.response.CategoryResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {

    public ResponseEntity<CategoryResponseRest> search();

    public ResponseEntity<CategoryResponseRest> searchById(Long id);

    public ResponseEntity<CategoryResponseRest> save(Category category);

    public ResponseEntity<CategoryResponseRest> update(Category category, Long id);

    public ResponseEntity<CategoryResponseRest> deleteById(Long id);

}
