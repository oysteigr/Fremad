package fremad.dao;

import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.util.StringUtils;

import fremad.domain.user.UserRoleEnum;

/**
 * Extends BeanPropertyRowMapper to allow for boolean fields
 * mapped to 'Y,'N' type column to get set correctly. Using stock BeanPropertyRowMapper
 * would throw a SQLException.
 * 
 */
public class UserBeanPropertyRowMapper<T> extends BeanPropertyRowMapper<T> {

	private static final Logger LOG = LoggerFactory.getLogger(UserBeanPropertyRowMapper.class);
	
	//Contains valid true values
	public static final Set<String> TRUE_SET = new HashSet<String>(Arrays.asList("SUPPORTER", "PLAYER", "AUTHOR", "EDITOR", "ADMIN", "SUPER"));
	
	public UserBeanPropertyRowMapper(Class<T> class1) {
		super(class1);
	}
	
	@Override
	protected Object getColumnValue(ResultSet rs, int index,
				PropertyDescriptor pd) throws SQLException {
		Class<?> requiredType = pd.getPropertyType();
		if (int.class.equals(requiredType) || Integer.class.equals(requiredType)) {
			String stringValue = rs.getString(index);
			LOG.debug("in RowMapper: " + stringValue);
			if(!StringUtils.isEmpty(stringValue) && TRUE_SET.contains(stringValue)){
				return UserRoleEnum.valueOf(stringValue).getRoleValue();
			}
		}
		return super.getColumnValue(rs, index, pd);
	}
}