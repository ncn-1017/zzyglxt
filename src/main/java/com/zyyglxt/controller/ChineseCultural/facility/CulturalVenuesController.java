package com.zyyglxt.controller.ChineseCultural.facility;

import com.zyyglxt.annotation.LogAnnotation;
import com.zyyglxt.dataobject.ChineseCulturalDO;
import com.zyyglxt.dataobject.ChineseCulturalDOKey;
import com.zyyglxt.dto.ChineseCulturalDto;
import com.zyyglxt.error.EmBusinessError;
import com.zyyglxt.response.ResponseData;
import com.zyyglxt.service.ICulturalVenuesService;
import com.zyyglxt.service.IFileService;
import com.zyyglxt.util.ConvertDOToDTOUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:wangzh
 * Date: 2020/10/30 12:22
 * Version: 1.0
 * 文化场馆控制器
 */
//@Controller
@RestController
@RequestMapping("/cul/fac/culVen")
@SuppressWarnings("unchecked")
public class   CulturalVenuesController {

    @Resource
    private ICulturalVenuesService iCulturalVenuesService;

    @Resource
    private IFileService iFileService;


    //获取所有的文化场馆
    @RequestMapping(value = "/getAll" , method = RequestMethod.GET)
    @ResponseBody
    @LogAnnotation(logTitle = "查询所有的文化场馆", logLevel = "1")
    public ResponseData getAllCulturalVenues(@RequestParam(value = "chineseCulturalStatus")String chineseCulturalStatus){
        return new ResponseData(EmBusinessError.success,iCulturalVenuesService.getCulturalVenuesList(chineseCulturalStatus));
    }



    //增加一个文化场馆
    @RequestMapping(value = "/addCulVen" , method = RequestMethod.POST)
    @ResponseBody
    @LogAnnotation(logTitle = "增加一个文化场馆", logLevel = "3")
    public ResponseData addCulturalVenues(@RequestBody ChineseCulturalDO chineseCulturalDO) {
        iCulturalVenuesService.addCulturalVenues(chineseCulturalDO);
        return new ResponseData(EmBusinessError.success);
    }

    //删除一个文化场馆（真正的数据库中删除）
    @RequestMapping(value = "/delCulVen/{itemID}/{itemCode}" , method = RequestMethod.DELETE)
    @ResponseBody
    @LogAnnotation(logTitle = "删除一个文化场馆", logLevel = "4")
    public ResponseData deleteCulturalVenues(@PathVariable("itemID") Integer itemID, @PathVariable("itemCode")String itemCode){
        ChineseCulturalDOKey chineseCulturalDOKey = new ChineseCulturalDOKey();
        chineseCulturalDOKey.setItemid(itemID);
        chineseCulturalDOKey.setItemcode(itemCode);
        iCulturalVenuesService.removeCulturalVenues(chineseCulturalDOKey);
        return new ResponseData(EmBusinessError.success);
    }



    //修改一个文化场馆
    @RequestMapping(value = "/updCulVen" , method = RequestMethod.POST)
    @ResponseBody
    @LogAnnotation(logTitle = "修改一个文化场馆", logLevel = "2")
    public ResponseData updateCulturalVenues(@RequestBody ChineseCulturalDO chineseCulturalDO){
        iCulturalVenuesService.updateCulturalVenues(chineseCulturalDO);
        return new ResponseData(EmBusinessError.success);
    }

    //修改一个文化场馆状态 （逻辑删除，但是是将状态改成下架状态,也可以是处长页面 通过->上架， 未通过->下架）
    @RequestMapping(value = "/cgCulVenSta/{itemID}/{itemCode}" , method = RequestMethod.POST)
    @ResponseBody
    @LogAnnotation(logTitle = "修改一个文化场馆状态", logLevel = "2")
    public ResponseData changeStatus(@RequestParam("chineseCulturalStatus") String chineseCulturalStatus , @PathVariable("itemID") Integer itemID, @PathVariable("itemCode")String itemCode){
        ChineseCulturalDOKey chineseCulturalDOKey = new ChineseCulturalDOKey();
        chineseCulturalDOKey.setItemid(itemID);
        chineseCulturalDOKey.setItemcode(itemCode);
        iCulturalVenuesService.changeCulturalVenuesStatus(chineseCulturalDOKey,chineseCulturalStatus);
        return new ResponseData(EmBusinessError.success);
    }
}
