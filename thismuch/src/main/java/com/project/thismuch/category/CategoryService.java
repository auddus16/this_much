package com.project.thismuch.category;

import com.project.thismuch.data.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    //
    List<CategoryDTO> findAllCategoriesByUserNo(Long user_no);

    CategoryDTO registerNewCategory(CategoryDTO categoryDto);

    List<CategoryDTO> registerNewCategories(List<CategoryDTO> categoriesDto);
}
