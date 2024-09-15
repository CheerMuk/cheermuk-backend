package cheermuk.cheermukbackend.domain.articleReport.entity;

import cheermuk.cheermukbackend.domain.article.entity.Article;
import cheermuk.cheermukbackend.domain.articleReport.entity.constants.ReportType;
import cheermuk.cheermukbackend.domain.member.entity.Member;
import cheermuk.cheermukbackend.global.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "article_reports")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ArticleReport extends BaseEntity {
    @Id
    @SequenceGenerator(name = "REPORT_ID_GENERATOR", sequenceName = "seq_article_report", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REPORT_ID_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", insertable = false, updatable = false)
    private Member member;
    @Column(nullable = false)
    private Long memberId;

    @ToString.Exclude
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "articleId", insertable = false, updatable = false)
    private Article article;
    @Column(nullable = false)
    private Long articleId;

    private ArticleReport(ReportType reportType, Long memberId, Long articleId) {
        this.reportType = reportType;
        this.memberId = memberId;
        this.articleId = articleId;
    }

    public static ArticleReport of(ReportType reportType, Long memberId, Long articleId) {
        return new ArticleReport(reportType, memberId, articleId);
    }
}
