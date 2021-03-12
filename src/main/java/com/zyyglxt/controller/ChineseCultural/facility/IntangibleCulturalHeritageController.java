package com.zyyglxt.controller.ChineseCultural.facility;

import com.zyyglxt.annotation.LogAnnotation;
import com.zyyglxt.dataobject.ChineseCulturalDO;
import com.zyyglxt.dataobject.ChineseCulturalDOKey;
import com.zyyglxt.dto.ChineseCulturalDto;
import com.zyyglxt.error.EmBusinessError;
import com.zyyglxt.response.ResponseData;
import com.zyyglxt.service.IFileService;
import com.zyyglxt.service.IIntangibleCulturalHeritageService;
import com.zyyglxt.util.ConvertDOToDTOUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:wangzh
 * Date: 2020/10/30 12:22
 * Version: 1.0
 * 非物质文化遗产控制器
 */
//@Controller
@RestController
@RequestMapping("/cul/fac/inCuHe")
@SuppressWarnings("unchecked")
public class IntangibleCulturalHeritageController {
    @Resource
    private IIntangibleCulturalHeritageService iIntangibleCulturalHeritageService;

    @Resource
    private IFileService iFileService;

    //获取所有的非物质文化遗产
    @RequestMapping(value = "/getAll" , method = RequestMethod.GET)
    @ResponseBody
    @LogAnnotation(logTitle = "查询非物质文化遗产", logLevel = "1")
    public ResponseData getAllIntangibleCulturalHeritage(@RequestParam(value = "chineseCulturalStatus")String chineseCulturalStatus){
        return new ResponseData(EmBusinessError.success,iIntangibleCulturalHeritageService.getIntangibleCulturalHeritageList(chineseCulturalStatus));
    }



    //增加一个非物质文化遗产
    @RequestMapping(value = "/addInCuHe" , method = RequestMethod.POST)
    @ResponseBody
    @LogAnnotation(logTitle = "增加一个非物质文化遗产", logLevel = "3")
    public ResponseData addIntangibleCulturalHeritage(@RequestBody ChineseCulturalDO chineseCulturalDO)  {
        iIntangibleCulturalHeritageService.addIntangibleCulturalHeritage(chineseCulturalDO);
        return new ResponseData(EmBusinessError.success);
    }

    //删除一个非物质文化遗产（真正的数据库中删除）
    @RequestMapping(value = "/delInCuHe/{itemID}/{itemCode}" , method = RequestMethod.DELETE)
    @ResponseBody
    @LogAnnotation(logTitle = "删除一个非物质文化遗产", logLevel = "4")
    public ResponseData deleteIntangibleCulturalHeritage(@PathVariable("itemID") Integer itemID, @PathVariable("itemCode")String itemCode){
        ChineseCulturalDOKey chineseCulturalDOKey = new ChineseCulturalDOKey();
        chineseCulturalDOKey.setItemid(itemID);
        chineseCulturalDOKey.setItemcode(itemCode);
        iIntangibleCulturalHeritageService.removeIntangibleCulturalHeritage(chineseCulturalDOKey);
        return new ResponseData(EmBusinessError.success);
    }



    //修改一个非物质文化遗产
    @RequestMapping(value = "/updInCuHe" , method = RequestMethod.POST)
    @ResponseBody
    @LogAnnotation(logTitle = "修改一个非物质文化遗产", logLevel = "2")
    public ResponseData updateIntangibleCulturalHeritage(@RequestBody ChineseCulturalDO chineseCulturalDO) {
        iIntangibleCulturalHeritageService.updateIntangibleCulturalHeritage(chineseCulturalDO);
        return new ResponseData(EmBusinessError.success);
    }

    //修改一个非物质文化遗产状态 （逻辑删除，但是是将状态改成下架状态,也可以是处长页面 通过->上架， 未通过->下架）
    @RequestMapping(value = "/cgInCuHeSta/{itemID}/{itemCode}" , method = RequestMethod.POST)
    @ResponseBody
    @LogAnnotation(logTitle = "修改一个非物质文化遗产状态", logLevel = "2")
    public ResponseData changeStatus(@RequestParam("chineseCulturalStatus") String chineseCulturalStatus , @PathVariable("itemID") Integer itemID, @PathVariable("itemCode")String itemCode){
        ChineseCulturalDOKey chineseCulturalDOKey = new ChineseCulturalDOKey();
        chineseCulturalDOKey.setItemid(itemID);
        chineseCulturalDOKey.setItemcode(itemCode);
        iIntangibleCulturalHeritageService.changeIntangibleCulturalHeritageStatus(chineseCulturalDOKey, chineseCulturalStatus);
        return new ResponseData(EmBusinessError.success);
    }
}
