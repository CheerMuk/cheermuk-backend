package cheermuk.cheermukbackend.global.config;

import cheermuk.cheermukbackend.global.ShortArrayType;
import org.hibernate.spatial.dialect.postgis.PostgisPG95Dialect;

public class PostgreSQL94CustomDialect extends PostgisPG95Dialect {

    public PostgreSQL94CustomDialect() {
        super();
        this.registerHibernateType(2003, ShortArrayType.class.getName());
    }
}