package mosaic.modules.appInfo;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

@Service
public class AppInfoService {
	
	@Autowired
	BuildProperties buildProperties;
	
    @Autowired
    private AppInfoRepository infoRepository;
	
	private static Logger logger = Logger.getLogger(AppInfoController.class.getName());

	public AppInfo getInfo() {
		logger.info("Inicio AppInfoService/getInfo");
		
		AppInfo resp = new AppInfo();
		resp.setAppName(buildProperties.getName()); 
		resp.setAppVersion(buildProperties.getVersion()); 
		
		resp.setDbName("MySQL"); 
		resp.setDbVersion(infoRepository.dbVersion()); 
		
		logger.info(resp.toString());
		logger.info("Inicio AppInfoService/getInfo");
		return resp;
	}


}
