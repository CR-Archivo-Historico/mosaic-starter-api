package mosaic.modules.appInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Service;

@Service
public class AppInfoService {
	
	@Autowired
	BuildProperties buildProperties;
	
    @Autowired
    private AppInfoRepository infoRepository;
	
	public AppInfo getInfo() {
		//logger.info("Inicio AppInfo/getInfo");
		
		AppInfo resp = new AppInfo();
		resp.setAppName(buildProperties.getName()); 
		resp.setAppVersion(buildProperties.getVersion()); 
		
		resp.setDbName("MySQL"); 
		resp.setDbVersion(infoRepository.dbVersion()); 
		
		//logger.info(resp);
		return resp;
	}


}
