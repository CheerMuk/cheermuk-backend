package cheermuk.cheermukbackend.domain.article.repository;

import cheermuk.cheermukbackend.domain.article.entity.Article;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS)
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(
            nativeQuery = true,
            countQuery = "select count(*) from articles",
            value =
                    """
                            select * from articles a left outer join (
                                select r.id
                                from restaurants r
                                where st_dwithin(:point, r.coordinates, :meter, false)) as r_id
                            on a.restaurant_id = r_id.id
                            order by a.created_at desc
                            """)
    Page<Article> findAllNearest(@Param("point") Point point, @Param("meter") Integer distance, Pageable pageable);

    Page<Article> findAllByMemberId(Long memberId, Pageable pageable);

    @Query(
            nativeQuery = true,
            countQuery = "select count(*) from articles where reported_at is not null",
            value = "select * from articles a where a.reported_at is not null")
    Page<Article> findAllByReportedAtNotNull(Pageable pageable);

    boolean existsByIdAndMemberId(Long articleId, Long memberId);

    @Modifying
    @Query("update Article a set a.likeCnt = a.likeCnt + :cnt where a.id = :id")
    void updateLikeCnt(@Param("id") Long articleId, @Param("cnt") long updateCount);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update articles a set view_cnt = view_cnt + 1 where a.id = :id")
    void updateViewCnt(@Param("id") Long articleId);

    @Modifying
    @Query("update Article a set a.reportedAt = CURRENT_TIMESTAMP where a.id = :id")
    void updateReportedAtById(@Param("id") Long articleId);
}
