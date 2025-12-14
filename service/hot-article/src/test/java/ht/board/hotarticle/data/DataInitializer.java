package ht.board.hotarticle.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.util.random.RandomGenerator;

public class DataInitializer {
    RestClient articleServiceClient = RestClient.create("http://localhost:9000");
    RestClient viewServiceClient = RestClient.create("http://localhost:9003");

    @Test
    void initialize() {
        for(int i=0; i<30; i++) {
            Long articleId = createArticle();
            long viewCount = RandomGenerator.getDefault().nextLong(200);

            view(articleId, viewCount);
        }
    }

    Long createArticle() {
        return articleServiceClient.post()
                .uri("/v1/articles")
                .body(new ArticleCreateRequest("title", "content", 1L, 1L))
                .retrieve()
                .body(ArticleResponse.class)
                .getArticleId();
    }

    @Getter
    @AllArgsConstructor
    static class ArticleCreateRequest {
        private String title;
        private String content;
        private Long writerId;
        private Long boardId;
    }

    @Getter
    static class ArticleResponse {
        private Long articleId;
    }

    @Getter
    @AllArgsConstructor
    static class CommentCreateRequest {
        private Long articleId;
        private String content;
        private Long writerId;
    }


    void view(Long articleId, long viewCount) {
        while(viewCount-- > 0) {
            viewServiceClient.post()
                    .uri("/v1/article-views/articles/{articleId}/users/{userId}", articleId, viewCount)
                    .retrieve();
        }
    }
}
