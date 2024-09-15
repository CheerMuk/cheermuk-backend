package cheermuk.cheermukbackend.domain.articleReport.service;

import cheermuk.cheermukbackend.domain.article.repository.ArticleRepository;
import cheermuk.cheermukbackend.domain.articleReport.entity.ArticleReport;
import cheermuk.cheermukbackend.domain.articleReport.entity.constants.ReportType;
import cheermuk.cheermukbackend.domain.articleReport.repository.ArticleReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleReportService {
    private final ArticleReportRepository articleReportRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public void addReport(Long articleId, ReportType reportType, Long memberId) {
        articleReportRepository.saveAndFlush(ArticleReport.of(reportType, memberId, articleId));
        if (articleReportRepository.findCountByArticleId(articleId) >= 3) {
            articleRepository.updateReportedAtById(articleId);
        }
    }
}
