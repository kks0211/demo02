package com.board.mapper;


import com.board.domain.Category;

import java.util.List;

public interface CategoryMapper {

    int insertSelectKey(Category category);

    Category read(Long categoryId);

    List<Category> List();
}
