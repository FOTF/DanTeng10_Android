package fotf.space.danteng10.util;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;

public class util {
	private static final String TAG = "AsynImageLoader";
	
	// 缓存下载过的图片的Map
	private Map<String, SoftReference<Bitmap>> caches;
	
	private List<Task> taskQueue;  
    private boolean isRunning = false; 
    
    
    class Task{  
        // 下载任务的下载路径  
        String path;  
        // 下载的图片  
        Bitmap bitmap;  
        // 回调对象  
//        ImageCallback callback;  
          
        @Override  
        public boolean equals(Object o) {  
            Task task = (Task)o;  
            return task.path.equals(path);  
        }  
    }  

}
