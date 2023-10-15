package com.godfazheer.service;

import com.godfazheer.model.CategoryCardDTO;
import com.godfazheer.model.dto.ArchiveCardDTO;
import com.godfazheer.model.dto.CategoriesDTO;
import com.godfazheer.model.vo.CategoriesVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
public interface CategoriesService {
    List<CategoriesDTO> getCategories();

    List<CategoryCardDTO> getCategoryCard();
    List<CategoryCardDTO> getAdminCategoryCard(Long pageNum);

    List<ArchiveCardDTO> getArchiveCard();

    void delCategories(List<Long> tagIds);

    void updateCategories(CategoriesVO categoriesVO);

    void saveCategories(CategoriesVO categoriesVO);

    Long getCategoryCardCount();

    List<CategoryCardDTO> getAdminCategoryCardByName(String name, Long pageNum);

    Long getAdminCategoryCardByNameCount(String name);
}
