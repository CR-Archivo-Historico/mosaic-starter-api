package mosaic.modules.appInfo;

import java.util.logging.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Repository
public class AppInfoRepository {

	    // Spring Boot will create and configure DataSource and JdbcTemplate
	    @Autowired
	    private JdbcTemplate jdbcTemplate;

		private static Logger logger = Logger.getLogger(AppInfoController.class.getName());

		
	    public String dbVersion() {
	    	logger.info("Inicio AppInfoRepository/dbVersion");
	    	
    		String resp;
	        resp = jdbcTemplate
	                .queryForObject("select version()", String.class);

			logger.info(resp.toString());
			logger.info("Fin AppInfoRepository/dbVersion");
			
			return resp;
	    }

		public Integer count() {
	    	Integer resp;
	        resp = jdbcTemplate
	                .queryForObject("select count(*) from db", Integer.class);
	        return resp;
	    }

}
