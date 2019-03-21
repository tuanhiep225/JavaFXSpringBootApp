/**
 * 
 */
package app.service.impl;

import app.service.MediaService;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * @author jhon
 *
 */
public class MediaServiceImpl extends Service<Void> implements MediaService {

	@Override
	public void onDownload(String url, String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Task<Void> createTask() {
		// TODO Auto-generated method stub
		return null;
	}

}
