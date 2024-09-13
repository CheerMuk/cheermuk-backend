package cheermuk.cheermukbackend.domain.articleComment.service;

import cheermuk.cheermukbackend.domain.articleComment.dto.CommentRequest;
import cheermuk.cheermukbackend.domain.articleComment.entity.ArticleComment;
import cheermuk.cheermukbackend.domain.articleComment.repository.ArticleCommentRepository;
import cheermuk.cheermukbackend.global.exception.CommentException;
import cheermuk.cheermukbackend.global.exception.constants.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;

    public Page<ArticleComment> getParentComments(Pageable pageable) {
        return articleCommentRepository.findAll(pageable);
    }

    public Page<ArticleComment> getChildComments(Pageable pageable, Long parentCommentId) {
        return articleCommentRepository.findAllByParentCommentId(pageable, parentCommentId);
    }

    public ArticleComment addComment(Long articleId, Long memberId, CommentRequest commentRequest) {
        ArticleComment articleComment = ArticleComment.fromRequest(articleId, memberId, commentRequest);
        return articleCommentRepository.save(articleComment);
    }

    public ArticleComment updateComment(Long articleId, Long commentId, Long memberId, CommentRequest commentRequest) {
        ArticleComment articleComment = ArticleComment.fromRequest(articleId, memberId, commentRequest);
        articleComment.setId(commentId);
        return articleCommentRepository.save(articleComment);
    }

    public void deleteComment(Long commentId, Long memberId) {
        if (isOwner(commentId, memberId)) throw new CommentException(ErrorCode.FORBIDDEN_COMMENT);
        articleCommentRepository.deleteById(commentId);
    }

    private boolean isOwner(Long commentId, Long memberId) {
        return articleCommentRepository.existsByIdAndMemberId(commentId, memberId);
    }
}
