package org.example.inventory.response;


import lombok.Data;
import org.example.inventory.model.Category;

import java.util.List;

@Data
public class CategoryResponse {

    private List<Category> categories;

}
