package com.zyyglxt.controller.HealthSciKnowDOController;

import com.zyyglxt.dataobject.HealthSciKnowDO;
import com.zyyglxt.dataobject.HealthSciKnowDOKey;
import com.zyyglxt.error.BusinessException;
import com.zyyglxt.error.EmBusinessError;
import com.zyyglxt.response.ResponseData;
import com.zyyglxt.service.HealthSciKnowDOService;
import com.zyyglxt.service.IFileService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author huangwj
 * @time 2020/10/30 15:30
 */
@RestController
public class HealthSciKnowDOController {
    @Resource
     private HealthSciKnowDOService healthSciKnowDOService;

    @Resource
    private IFileService iFileService;
    /*
    科普知识相关数据插入
    */
    @RequestMapping(value = "inserthealthsciknowdo",method = RequestMethod.POST)
    public ResponseData insertHealthSciKnowDOMapper(@RequestBody HealthSciKnowDO key) throws BusinessException {
            System.out.println("科普知识名称: " + key.getScienceKnowledgeName());
            healthSciKnowDOService.insertSelective(key);
            return new ResponseData(EmBusinessError.success);
    }
    /*
    科普知识相关数据的删除
    */
    @RequestMapping(value ="deletehealthsciknowdo",method = RequestMethod.POST )
    public ResponseData deleteHealthSciKnowDOMapper(@RequestBody HealthSciKnowDOKey key){
        healthSciKnowDOService.deleteByPrimaryKey(key);
        System.out.println("要删除科普知识编号为："+key.getItemid());
        return new ResponseData(EmBusinessError.success);
    }
    /*
    科普知识相关数据的修改
    */
    @RequestMapping(value ="updatehealthsciknowdo",method = RequestMethod.POST )
    public ResponseData updateHealthSciKnowDOMapper(@RequestBody HealthSciKnowDO key) throws BusinessException {
        healthSciKnowDOService.updateByPrimaryKeySelective(key);
        System.out.println("要修改科普知识编号为："+key.getItemid());
        return new ResponseData(EmBusinessError.success);
    }
    /*
     科普知识相关数据的查询
   */
    @RequestMapping(value ="selecthealthsciknowdo",method = RequestMethod.POST )
    public ResponseData selectHealthSciKnowDOMapper(@RequestBody HealthSciKnowDOKey key){
        healthSciKnowDOService.selectByPrimaryKey(key);
        return new ResponseData(EmBusinessError.success);
    }
    /*查询所有科普知识所有数据*/
    @RequestMapping(value ="selectallhealthsciknowdo",method = RequestMethod.POST )
    public ResponseData selectAllHealthSciKnowDOMapper(Model model){
        List<HealthSciKnowDO> healthSciKnowDOSList = healthSciKnowDOService.selectAllHealthSciKnow();
        model.addAttribute("traditionalCulturalList",healthSciKnowDOSList);
        return new ResponseData(EmBusinessError.success,healthSciKnowDOSList);
    }
    /**
     * 增加点击数
     * @param key
     */
    @RequestMapping(value = "visitnumhealthSciKnowdo", method = RequestMethod.POST)
    public void increaseVisitNum(@RequestBody HealthSciKnowDOKey key) {
       healthSciKnowDOService.updateVisitNumHealthSciKnow(key);
    }

}

