package cheermuk.cheermukbackend.domain.articleReport.controller;

import cheermuk.cheermukbackend.domain.articleReport.entity.constants.ReportType;
import cheermuk.cheermukbackend.domain.articleReport.service.ArticleReportService;
import cheermuk.cheermukbackend.domain.member.dto.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/articles/{articleId}/reports")
@RequiredArgsConstructor
public class ArticleReportController {
    private final ArticleReportService articleReportService;

    @PostMapping
    public ResponseEntity<Void> addReport(@PathVariable Long articleId, @RequestParam ReportType reportType, @AuthenticationPrincipal MemberPrincipal memberPrincipal) {
        articleReportService.addReport(articleId, reportType, memberPrincipal.id());
        return ResponseEntity.ok(null);
    }
}
