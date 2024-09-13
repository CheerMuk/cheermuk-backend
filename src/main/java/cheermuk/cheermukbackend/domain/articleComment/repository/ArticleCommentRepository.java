package cheermuk.cheermukbackend.domain.articleComment.repository;

import cheermuk.cheermukbackend.domain.articleComment.entity.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
    Page<ArticleComment> findAllByParentCommentId(Pageable pageable, Long parentCommentId);

    boolean existsByIdAndMemberId(Long commentId, Long memberId);
}
