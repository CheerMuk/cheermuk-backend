package cheermuk.cheermukbackend.domain.base;

import javax.persistence.Column;
import java.sql.Timestamp;

public class BaseSoftDeleteEntity extends BaseMutableEntity {
    @Column
    protected Timestamp deletedAt;
}
