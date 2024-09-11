package cheermuk.cheermukbackend.global.base;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;

@Getter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseSoftDeleteEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected Timestamp createdAt;

    @Column
    protected Timestamp modifiedAt;

    @Column
    protected Timestamp deletedAt;

    @PreUpdate
    void modifiedAt() {
        this.modifiedAt = new Timestamp(System.currentTimeMillis());
    }
}
