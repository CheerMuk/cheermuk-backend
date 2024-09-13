package cheermuk.cheermukbackend.domain.article.repository;

import cheermuk.cheermukbackend.domain.article.entity.Article;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
    Page<Article> findAllNearest(
            @Param("point") Point point, @Param("meter") Integer distance, Pageable pageable);

    Page<Article> findAllByMemberId(Long memberId, Pageable pageable);
}
