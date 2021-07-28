package mosaic.modules.appInfo;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class AppInfoController {

	@Autowired
    private AppInfoService appInfoService;
	
	private static Logger logger = Logger.getLogger(AppInfoController.class.getName());

	/******************************************************************************************
	 * System info
	 ******************************************************************************************/

	@GetMapping("/info")
	public AppInfo info() {
		logger.info("Inicio AppInfoController info");
		
		AppInfo resp = appInfoService.getInfo();
		
		logger.info(resp.toString());
		logger.info("Fin AppInfoController info");
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