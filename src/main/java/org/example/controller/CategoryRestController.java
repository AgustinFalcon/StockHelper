package org.example.controller;



import org.example.inventory.response.CategoryResponseRest;
import org.example.inventory.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

    @Autowired
    private ICategoryService service;

    @GetMapping("/categories")
    public ResponseEntity<CategoryResponseRest> searchAllCategories() {
        ResponseEntity<CategoryResponseRest> response = service.search();
        return response;
    }

}
