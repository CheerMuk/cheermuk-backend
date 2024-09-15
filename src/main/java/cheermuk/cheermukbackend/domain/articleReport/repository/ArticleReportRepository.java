package cheermuk.cheermukbackend.domain.articleReport.repository;

import cheermuk.cheermukbackend.domain.articleReport.entity.ArticleReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleReportRepository extends JpaRepository<ArticleReport, Long> {
    @Query("select count(*) from ArticleReport ar where ar.articleId = :articleId")
    int findCountByArticleId(@Param("articleId") Long articleId);
}
