package com.youchi.controller;

import cn.hutool.core.io.FileUtil;
import com.youchi.dto.AllStoreDto;
import com.youchi.mapper.MerchantMapper;
import com.youchi.mapper.StoreMapper;
import com.youchi.model.Merchant;
import com.youchi.model.Store;
import com.youchi.model.StoreClassify;
import com.youchi.responseMessage.ResponseMessage;
import com.youchi.vo.StoreVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "商店")
@Controller
@RequestMapping("store")
@ResponseBody
public class StoreController {
    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    MerchantMapper merchantMapper;

    @GetMapping("findAllByMerchantId")
    public ResponseMessage findAllByMerchantId( @RequestHeader(required = false) String token) {
        final Merchant token1 = merchantMapper.findToken(token);
        final List<Store> storeByMerchantId = storeMapper.findStoreByMerchantId(token1.getId());
        System.out.println(storeByMerchantId);
        return ResponseMessage.success(storeByMerchantId);
    }

    @GetMapping("allStore/{size}/{num}")
    public ResponseMessage allStore(@PathVariable(value = "size") int size,
                                    @PathVariable(value = "num") int num,
                                    @RequestHeader(required = false) String token,
                                    String keyword) {
        final Merchant token1 = merchantMapper.findToken(token);
//        if (keyWord != null) {
//            final List<AllStoreDto> page1 = storeMapper.page((num - 1) * size, size,"%"+keyWord+"%",token1.getId());
//            Map<String, Object> map = new HashMap<>();
//            map.put("records", page1);
//            map.put("total", storeMapper.count(token1.getId()));
//            return ResponseMessage.success(map);
//        }
        final List<AllStoreDto> page1 = storeMapper.page((num - 1) * size,size+0,"%"+keyword+"%",token1.getId()+0);
//        System.out.println(page1);
        Map<String, Object> map = new HashMap<>();
        map.put("records", page1);
        map.put("total", storeMapper.count(token1.getId()));
        return ResponseMessage.success(map);
    }

    /**
     * marchent 角色添加store,store<=1;
     * @param storeVo
     * @param token
     * @return
     */
    @PostMapping("addStore")
    public ResponseMessage addStore(@RequestBody StoreVo storeVo  ,@RequestHeader(required = false) String token) {
        final Merchant merchant = merchantMapper.findToken(token);
        final List<Store> storeByMerchantId = storeMapper.findStoreByMerchantId(merchant.getId());
        if(storeByMerchantId.size()>=2){
            return ResponseMessage.error("每个账号只能注册一个store");
        }
        Store store = new Store(storeVo.getName(), storeVo.getAddress(),
                 storeVo.getStoreClassifyId(), merchant.getId(),storeVo.getIntro(),storeVo.getPhoto());
        store.setRegistrationTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date().getTime()));
        storeMapper.insert(store);
        return ResponseMessage.success("添加成功");
    }

    @DeleteMapping("delStore{id}")
    public ResponseMessage delClassify(@PathVariable("id") Long id) {
        storeMapper.deleteById(id);
        return ResponseMessage.success("删除成功");
    }

    @PostMapping("modifyStore")
    public ResponseMessage modifyClassify(@RequestBody Store store) {
        storeMapper.updateById(store);
        return ResponseMessage.success("修改成功");
    }

    @ApiOperation("上传")
    @PostMapping("upload")
    public ResponseMessage upload(@ApiParam("文件") MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "";
        System.out.println(file.getOriginalFilename());
        String split = file.getOriginalFilename();
        String substring = split.substring(split.lastIndexOf("."), split.length());
        if (substring.equals(".exe")) {
            return ResponseMessage.error("0");
        }
        String originalFilename = name + "" + substring;
        String lujing = "D:\\file\\youchi\\store\\";
        File filePath = new File(lujing);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        String url = lujing + originalFilename;
        File winFiledir = new File(url);
//        file.getBytes().length
        FileUtil.writeBytes(file.getBytes(), winFiledir);
        System.out.println("保存文件:" + winFiledir.getAbsolutePath());


        return ResponseMessage.success("youchi/store/"+originalFilename);
    }

}
