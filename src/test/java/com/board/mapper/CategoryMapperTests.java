package com.board.mapper;

import com.board.config.TestConfiguration;
import com.board.domain.Category;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@TestConfiguration
class CategoryMapperTests {

    static final Logger log = LoggerFactory.getLogger(CategoryMapperTests.class);

    @Setter(onMethod_ = @Autowired)
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void setup() {
        Category category = new Category();
        category.setCategoryName("자유게시판");
        int r = categoryMapper.insertSelectKey(category);

        log.info("======= > {}", r);
    }

    @Test
    public void insert() {
        Category vo = new Category();
        vo.setCategoryName("자유게시판");
        categoryMapper.insertSelectKey(vo);
        log.info("{} : ", vo);

    }

    @Test
    public void read() {
        log.info("{} ", categoryMapper.read(1L));
    }

    @Test
    void ListRead() {
        log.info("{} ", categoryMapper.List());
    }

}