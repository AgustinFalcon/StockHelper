package org.example.controller;



import org.example.inventory.model.Category;
import org.example.inventory.response.CategoryResponseRest;
import org.example.inventory.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    @Autowired
    private ICategoryService service;

    /**
     * get all categories
     */
    @GetMapping("/getCategories")
    public ResponseEntity<CategoryResponseRest> searchAllCategories() {
        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;
    }

    /**
     * get categories by id
     * @param id
     */
    @GetMapping("/getCategory/{id}")
    public ResponseEntity<CategoryResponseRest> searchCategoryById(@PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = service.searchById(id);
        return response;
    }

    /**
     * save categories
     * @param category
     */
    @PostMapping("/saveCategory")
    public ResponseEntity<CategoryResponseRest> saveCategory(@RequestBody Category category) {
        ResponseEntity<CategoryResponseRest> response = service.save(category);
        return response;
    }

    /**
     * update categories
     * @param category
     * @param id
     */
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<CategoryResponseRest> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        ResponseEntity<CategoryResponseRest> response = service.update(category, id);
        return response;
    }

}
