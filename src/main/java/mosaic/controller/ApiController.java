package mosaic.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class ApiController {

	@Autowired
	BuildProperties buildProperties;

	private static Logger logger = Logger.getLogger(ApiController.class.getName());

	/******************************************************************************************
	 * System info
	 ******************************************************************************************/

	@GetMapping("/info")
	public BuildProperties jinfo() {
		logger.info("Inicio ApiController info");
		return buildProperties;
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