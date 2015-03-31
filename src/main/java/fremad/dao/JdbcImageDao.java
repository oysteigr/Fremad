package fremad.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import fremad.domain.ImageObject;

@Repository
public class JdbcImageDao extends JdbcConnection implements ImageDao {

	public JdbcImageDao() {
		super();
	}

	public ImageObject addImage(ImageObject image) {

		SimpleJdbcInsert insertImage = new SimpleJdbcInsert(this.getDataSource())
			.withTableName(SqlTablesConstants.SQL_TABLE_NAME_IMAGE)
			.usingGeneratedKeyColumns("id");
		SqlParameterSource parameters = new BeanPropertySqlParameterSource(image);
		Number newId = insertImage.executeAndReturnKey(parameters);
		
		if(newId != null){
			image.setId(newId.intValue());
			return image;
		}
		
		return null;
	}
	
	
}
