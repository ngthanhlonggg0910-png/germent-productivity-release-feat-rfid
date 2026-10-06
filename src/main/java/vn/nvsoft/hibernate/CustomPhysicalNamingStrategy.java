package vn.nvsoft.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

/**
 * Cột {@code isActived} trên DB không được đổi thành {@code is_actived} bởi SpringPhysicalNamingStrategy.
 */
public class CustomPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {

    private static final String IS_ACTIVED = "isActived";

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        if (name != null && IS_ACTIVED.equals(name.getText())) {
            return Identifier.toIdentifier(IS_ACTIVED, name.isQuoted());
        }
        return super.toPhysicalColumnName(name, jdbcEnvironment);
    }
}
