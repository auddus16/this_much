package com.project.thismuch.category;

import com.project.thismuch.data.dto.CategoryDTO;
import com.project.thismuch.data.dto.UserDTO;
import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.UserEntity;

import java.util.List;

public interface CategoryService {
    //
    List<CategoryDTO> findAllCategoriesByUserNo(Long user_no);

    CategoryDTO registerNewCategory(CategoryDTO categoryDto);

    List<CategoryDTO> registerNewCategories(List<CategoryDTO> categoriesDto);

    UserDTO findUserById(Long id);

	  CategoryEntity findCateNoByName(String name, UserEntity user);

}

