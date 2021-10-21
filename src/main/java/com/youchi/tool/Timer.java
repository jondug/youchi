package com.youchi.tool;

import com.youchi.mapper.StoreMapper;
import com.youchi.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Component
public class Timer {
    @Autowired
    private StoreMapper storeMapper;


    @Scheduled(cron = "* * 1 * * *")
    public void delStorePhoto(){
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()));
        final List<Store> stores = storeMapper.selectList(null);
        File file = new File("D:\\file\\youchi\\store\\");
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                boolean isOk = false;
                final String s = "\\\\";
                final String[] split = tempList[i].toString().split(s);
                for (Store store : stores) {
                    final String[] photo = store.getPhoto().split("/");
//                    System.out.println(split[split.length-1]+":"+photo[photo.length-1]);
                        if(split[split.length-1].equals(photo[photo.length-1])){
                            isOk = true;
                        }
                }
                if(!isOk){
                    tempList[i].delete();
//                    System.out.println(tempList[i]);
                }
            }
        }

    }
}
