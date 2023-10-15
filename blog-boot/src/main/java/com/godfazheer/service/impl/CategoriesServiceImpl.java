package com.godfazheer.service.impl;

import com.godfazheer.dao.CategoriesDao;
import com.godfazheer.entity.Categories;
import com.godfazheer.model.CategoryCardDTO;
import com.godfazheer.model.dto.ArchiveCardDTO;
import com.godfazheer.model.dto.CategoriesDTO;
import com.godfazheer.model.vo.CategoriesVO;
import com.godfazheer.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@Service
public class CategoriesServiceImpl implements CategoriesService {

    @Autowired
    private CategoriesDao categoriesDao;

    @Override
    public List<CategoriesDTO> getCategories() {
        return categoriesDao.selectAll();
    }
    @Override
    public List<CategoryCardDTO> getCategoryCard() {
        return categoriesDao.selectHomeCategoryCard();
    }
    @Override
    public List<CategoryCardDTO> getAdminCategoryCard(Long pageNum) {
        return categoriesDao.selectAdminCategoryCard((pageNum-1)*10);
    }
    @Override
    public List<ArchiveCardDTO> getArchiveCard() {
        return categoriesDao.selectHomeArchiveCard();
    }
    @Override
    public void delCategories(List<Long> categoriesId) {
        categoriesDao.deleteBatchIds(categoriesId);
    }
    @Override
    public void updateCategories(CategoriesVO categoriesVO) {
        categoriesDao.updateById(Categories.builder()
                .id(categoriesVO.getId())
                .name(categoriesVO.getName())
                .build());
    }

    @Override
    public void saveCategories(CategoriesVO categoriesVO) {
        categoriesDao.insert(Categories.builder()
                .name(categoriesVO.getName())
                .build());
    }
    @Override
    public Long getCategoryCardCount() {
        return categoriesDao.selectCount(null);
    }
    @Override
    public List<CategoryCardDTO> getAdminCategoryCardByName(String name,Long pageNum){
        return categoriesDao.selectAdminCategoryCardByName(name, (pageNum-1)*10);
    }
    @Override
    public Long getAdminCategoryCardByNameCount(String name) {
        return categoriesDao.selectAdminCategoryCardByNameCount(name);
    }
}
