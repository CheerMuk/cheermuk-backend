package cheermuk.cheermukbackend.domain;

import cheermuk.cheermukbackend.domain.base.BaseSoftDeleteEntity;
import cheermuk.cheermukbackend.domain.constants.UserRole;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "members")
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SQLDelete(sql = "UPDATE members SET deleted_at = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "deleted_at is null")
public class Member extends BaseSoftDeleteEntity {
    @Id
    @SequenceGenerator(name = "MEMBER_ID_GENERATOR", sequenceName = "seq_member", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_ID_GENERATOR")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String nickname;
    @Column
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;
}
