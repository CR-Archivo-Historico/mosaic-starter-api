package mosaic.modules.info;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class InfoRepository {

	    // Spring Boot will create and configure DataSource and JdbcTemplate
	    @Autowired
	    private JdbcTemplate jdbcTemplate;

	    public Integer count() {
	    	Integer resp;
	        resp = jdbcTemplate
	                .queryForObject("select count(*) from db", Integer.class);
	        return resp;
	    }

}
