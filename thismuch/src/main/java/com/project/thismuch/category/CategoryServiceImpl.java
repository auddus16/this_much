package com.project.thismuch.category;

import com.project.thismuch.data.dto.CategoryDTO;
import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> findAllCategoriesByUserNo(Long user_no) {
        List<CategoryEntity> categoryEntities = this.categoryRepository.findAll();

        this.categoryRepository.findAllByUserNoUserNo(user_no);

        return categoryEntities.stream().map(
                entity -> entity.toDTO()
        ).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO registerNewCategory(CategoryDTO categoryDto) {
        return null;
    }

    @Override
    public List<CategoryDTO> registerNewCategories(List<CategoryDTO> categoriesDto) {
        return null;
    }

}
