package com.project.thismuch.category;

import com.project.thismuch.data.dto.CategoryDTO;
import com.project.thismuch.data.dto.UserDTO;
import com.project.thismuch.data.entities.CategoryEntity;
import com.project.thismuch.data.entities.UserEntity;
import com.project.thismuch.repositories.CategoryRepository;
import com.project.thismuch.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CategoryDTO> findAllCategoriesByUserNo(Long user_no) {
        List<CategoryEntity> categoryEntities = this.categoryRepository.findAllByUserUserNo(user_no);;

        return categoryEntities.stream().map(
                entity -> entity.toDTO()
        ).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO registerNewCategory(CategoryDTO categoryDto) {
        Long user_no = categoryDto.getUserNo();
        Optional<UserEntity> user = this.userRepository.findById(user_no);

        if (user.isPresent()) {
            CategoryEntity category = CategoryEntity.builder()
                    .categoryName(categoryDto.getCategoryName())
                    .user(user.get())
                    .upperBound(categoryDto.getUpperBound())
                    .build();

            return this.categoryRepository.saveAndFlush(category).toDTO();
        }

        return null;
    }

    @Override
    public List<CategoryDTO> registerNewCategories(List<CategoryDTO> categoriesDto) {
        return null;
    }

    @Override
    public UserDTO findUserById(Long id) {
        Optional<UserEntity> user = this.userRepository.findById(id);

        if (user.isPresent()) {
            return user.get().toDTO();
        }

        // it should throw runtime exception if there is no entity.
        return null;
    }
    
    @Override
    public CategoryEntity findCateNoByName(String name, UserEntity user) {
    	
    	Optional<CategoryEntity> category = categoryRepository.findCategoryNo(user, name);
    	
    	return category.get();
    }
}
