package com.godfazheer.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NeighborArticleDTO {
    private Long id;
    private String title;
    private String coverUrl;
}
