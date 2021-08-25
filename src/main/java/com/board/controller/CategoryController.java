package com.board.controller;

import com.board.domain.Category;
import com.board.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryMapper categoryMapper;

    @GetMapping("/category")
    public String get () {
        return "/board/category";
    }

    @PostMapping("/category")
    public String get(Category category){
        int result = categoryMapper.insertSelectKey(category);

        return "/board/list";
    }

}
