package ht.board.hotarticle.service;

import ht.board.hotarticle.repository.ArticleViewCountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HotArticleScoreCalculator {
    private final ArticleViewCountRepository articleViewCountRepository;

    private static final long ARTICLE_VIEW_COUNT_WEIGHT = 1;

    public long calculate(Long articleId) {
        Long articleViewCount = articleViewCountRepository.read(articleId);

        return articleViewCount * ARTICLE_VIEW_COUNT_WEIGHT;
    }
}
