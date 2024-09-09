package cheermuk.cheermukbackend.domain.base;

import javax.persistence.Column;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;


public class BaseMutableEntity extends BaseEntity {
    @Column
    protected Timestamp modifiedAt;

    @PreUpdate
    void modifiedAt() {
        this.modifiedAt = new Timestamp(System.currentTimeMillis());
    }
}
