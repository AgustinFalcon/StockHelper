package org.example.inventory.services;

import org.example.inventory.dao.ICategoryDao;
import org.example.inventory.model.Category;
import org.example.inventory.response.CategoryResponse;
import org.example.inventory.response.CategoryResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDao categoryDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> search() {

        CategoryResponseRest response = new CategoryResponseRest();
        try {

            List<Category> categories = (List<Category>) categoryDao.findAll();
            response.getCategoryResponse().setCategories(categories);
            response.setMetadata("SUCCESS", "00", "Respuesta exitosa");

        } catch (Exception e) {
            response.setMetadata("ERROR", "404", "Error al obtener todas las categorias");
            e.printStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoryResponseRest> searchById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try {
            Optional<Category> category = categoryDao.findById(id);

            if (category.isPresent()) {
                list.add(category.get());
                response.getCategoryResponse().setCategories(list);
                response.setMetadata("SUCCESS", "200", "Categoria encontrada");

            } else {
                response.setMetadata("ERROR", "403", "Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }


        } catch (Exception e) {
            response.setMetadata("ERROR", "404", "Error al obtener categoria especifica");
            e.printStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> save(Category category) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try {
            Category categorySaved = categoryDao.save(category);

            if (categorySaved != null) {
                list.add(categorySaved);
                response.getCategoryResponse().setCategories(list);
                response.setMetadata("SUCCESS", "200", "Categoria guardada exitosamente!");

            } else {
                response.setMetadata("SUCCESS", "406", "Categoria no guardada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            response.setMetadata("ERROR", "405", "Error al guardar categoria");
            e.printStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        List<Category> list = new ArrayList<>();

        try {
            Optional<Category> categorySearch = categoryDao.findById(id);

            if (categorySearch.isPresent()) {
                categorySearch.get().setName(category.getName());
                categorySearch.get().setDescription(category.getDescription());

                Category categoryToUpdate = categoryDao.save(categorySearch.get());

                if (categoryToUpdate != null) {
                    list.add(categoryToUpdate);
                    response.getCategoryResponse().setCategories(list);
                    response.setMetadata("SUCCESS", "200", "Categoria actualizada exitosamente!");
                } else {
                    response.setMetadata("SUCCESS", "200", "Categoria actualizada exitosamente!");
                    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
                }

            } else {
                response.setMetadata("SUCCESS", "404", "Categoria no encontrada");
                return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            response.setMetadata("ERROR", "405", "Error al guardar categoria");
            e.printStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoryResponseRest> deleteById(Long id) {
        CategoryResponseRest response = new CategoryResponseRest();
        try {

            categoryDao.deleteById(id);
            response.setMetadata("SUCCESS", "200", "Se elimino la categoria");

        } catch (Exception e) {
            response.setMetadata("ERROR", "404", "Error al obtener todas las categorias");
            e.printStackTrace();
            return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
    }
}
