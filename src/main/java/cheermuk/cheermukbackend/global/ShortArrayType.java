package cheermuk.cheermukbackend.global;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.util.Arrays;

public class ShortArrayType implements UserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.ARRAY};
    }

    @Override
    public Class returnedClass() {
        return Short[].class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if (o instanceof Short[] && o1 instanceof Short[]) {
            return Arrays.deepEquals((Short[])o, (Short[])o1);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return Arrays.hashCode((Short[])o);
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        Array array = rs.getArray(strings[0]);
        return array != null ? array.getArray() : null;
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object o, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        if (o != null && st != null) {
            Array array = sharedSessionContractImplementor.connection().createArrayOf("smallint", (Short[])o);
            st.setArray(i, array);
        } else {
            st.setNull(i, sqlTypes()[0]);
        }
    }

    @Override
    public Short[] deepCopy(Object o) throws HibernateException {
        Short[] value = (Short[])o;
        return value != null ? Arrays.copyOf(value, value.length) : null;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o) throws HibernateException {
        return (Short[])o;
    }

    @Override
    public Short[] assemble(Serializable serializable, Object o) throws HibernateException {
        return (Short[])serializable;
    }

    @Override
    public Short[] replace(Object o, Object o1, Object o2) throws HibernateException {
        return (Short[])o;
    }
}
