package mosaic.modules.appInfo;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import mosaic.modules.appInfo.AppInfo;

//import org.springframework.jdbc.core.JdbcTemplate;

//import mosaic.modules.appInfo.AppInfoRepository;
//import mosaic.modules.appInfo.AppInfoService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class AppInfoController {

	@Autowired
	BuildProperties buildProperties;

	@Autowired
    private AppInfoService appInfoService;
	
	// Spring Boot will create and configure DataSource and JdbcTemplate
    // To use it, just @Autowired
    @Autowired
    private AppInfoRepository infoRepository;
    //private JdbcTemplate jdbcTemplate;

	private static Logger logger = Logger.getLogger(AppInfoService.class.getName());

	/******************************************************************************************
	 * System info
	 ******************************************************************************************/

	@GetMapping("/info2")
	public BuildProperties jinfoProp() {
		logger.info("Inicio ApiController infoProp");
		
		return buildProperties;
	}

	@GetMapping("/info")
	public AppInfo info() {
		logger.info("Inicio ApiController info");
		
		AppInfo resp = appInfoService.getInfo();
		
		return resp;
	}

	@GetMapping("/infodb")
	public String infoDb() {
		logger.info("Inicio ApiController infoDb");
		
		//Integer resp = jdbcTemplate.queryForObject("select count(*) from db", Integer.class);
		//Integer resp = infoRepository.count();
		String resp = infoRepository.dbVersion();

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