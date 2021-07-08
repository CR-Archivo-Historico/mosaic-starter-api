package mosaic.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")

public class ApiController {
	
	/******************************************************************************************
	 * Status!
	 ******************************************************************************************/

	@GetMapping("/status")
	public String status() {
		return "Mosaic API Server Status: Works!";
	}

}