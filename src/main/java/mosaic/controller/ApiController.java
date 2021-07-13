package mosaic.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mosaic.modules.info.InfoRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class ApiController {

	@Autowired
	BuildProperties buildProperties;
	
	// Spring Boot will create and configure DataSource and JdbcTemplate
    // To use it, just @Autowired
    @Autowired
    private InfoRepository infoRepository;
    //private JdbcTemplate jdbcTemplate;

	private static Logger logger = Logger.getLogger(ApiController.class.getName());

	/******************************************************************************************
	 * System info
	 ******************************************************************************************/

	@GetMapping("/info")
	public BuildProperties jinfoProp() {
		logger.info("Inicio ApiController infoProp");
		
		return buildProperties;
	}

	@GetMapping("/infodb")
	public Integer infoDb() {
		logger.info("Inicio ApiController infoDb");
		
		//Integer resp = jdbcTemplate.queryForObject("select count(*) from db", Integer.class);
		Integer resp = infoRepository.count();

        return resp;
	}

	/******************************************************************************************
	 * Status!
	 ******************************************************************************************/

	@GetMapping("/status")
	public String status() {
		logger.info("Inicio ApiController status");
		return "Mosaic API Server Status: Works!";
	}

}