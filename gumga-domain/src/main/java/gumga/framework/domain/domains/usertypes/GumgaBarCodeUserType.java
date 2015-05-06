/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gumga.framework.domain.domains.usertypes;

import gumga.framework.domain.domains.GumgaBarCode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;

/**
 *
 * @author munif
 */
public class GumgaBarCodeUserType extends MutableUserType {

    @Override
    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    @Override
    public Class returnedClass() {
        return GumgaBarCode.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        if (o == null) {
            return false;
        }
        return o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        if (o == null) {
            return 0;
        }
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(final ResultSet resultSet,
            final String[] names,
            final SessionImplementor paramSessionImplementor, final Object paramObject)
            throws HibernateException, SQLException {
        GumgaBarCode object = null;
        final String valor = resultSet.getString(names[0]);
        if (!resultSet.wasNull()) {
            object = new GumgaBarCode(valor);
        }
        return object;
    }

    @Override
    public void nullSafeSet(final PreparedStatement preparedStatement,
            final Object value, final int property,
            final SessionImplementor sessionImplementor)
            throws HibernateException, SQLException {
        if (null == value) {
            preparedStatement.setNull(property, java.sql.Types.VARCHAR);
        } else {
            final GumgaBarCode object = (GumgaBarCode) value;
            preparedStatement.setString(property, object.getValue());
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        final GumgaBarCode recebido = (GumgaBarCode) value;
        final GumgaBarCode aRetornar = new GumgaBarCode(recebido);
        return aRetornar;
    }
    
}