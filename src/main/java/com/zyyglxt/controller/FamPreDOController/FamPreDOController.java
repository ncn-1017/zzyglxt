package com.zyyglxt.controller.FamPreDOController;

import com.zyyglxt.annotation.LogAnnotation;
import com.zyyglxt.dataobject.ChineseCulturalDOKey;
import com.zyyglxt.dataobject.FamPreDO;
import com.zyyglxt.dataobject.FamPreDOKey;
import com.zyyglxt.dataobject.HealthCareChineseMedicineDO;
import com.zyyglxt.dto.HealthCareChineseMedicineDto;
import com.zyyglxt.error.BusinessException;
import com.zyyglxt.error.EmBusinessError;
import com.zyyglxt.response.ResponseData;
import com.zyyglxt.service.FamPreDOService;
import com.zyyglxt.service.IFileService;
import io.swagger.annotations.Api;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author huangwj
 * @time 2020/10/31 12:22
 */
@Api(tags="养生保健-历史名方")
@RestController
public class FamPreDOController {
    /*
       历史名方控制器类
    */
    @Resource
     private FamPreDOService famPreDOService;

    /*历史名方数据添加*/
  @RequestMapping(value = "insertfampredo",method = RequestMethod.POST)
  @LogAnnotation(appCode ="",logTitle ="历史名方数据添加",logLevel ="3")
    public ResponseData insertFamPreDOMapper(@RequestBody FamPreDO key)  {
          famPreDOService.insertSelective(key);
          return new ResponseData(EmBusinessError.success);
  }
  /*历史名方数据删除*/
    @RequestMapping(value ="deletefamprerdo/{itemID}/{itemCode}",method = RequestMethod.DELETE)
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="删除历史名方",logLevel ="4")
    public ResponseData deleteFamPreDOMapper(@PathVariable("itemID") Integer itemID, @PathVariable("itemCode")String itemCode){
            FamPreDOKey famPreDOKey=new FamPreDOKey();
            famPreDOKey.setItemid(itemID);
            famPreDOKey.setItemcode(itemCode);
            famPreDOService.deleteByPrimaryKey(famPreDOKey);
            return new ResponseData(EmBusinessError.success);
        }
    /*历史名方数据修改*/
    @RequestMapping(value ="updatefampredo",method = RequestMethod.POST )
    @LogAnnotation(appCode ="",logTitle ="修改历史名方",logLevel ="2")
    public ResponseData updateFamPreDOMapper(@RequestBody FamPreDO key)  {
        famPreDOService.updateByPrimaryKeySelective(key);
        return new ResponseData(EmBusinessError.success);
    }
    /*历史名方数据查询(通过id和编号)*/
    @RequestMapping(value ="selectfampredo",method = RequestMethod.POST )
    @LogAnnotation(appCode ="",logTitle ="通过ID及Code查询历史名方",logLevel ="1")
    public ResponseData selectFamPreDOMapper(@RequestBody FamPreDOKey key){
        famPreDOService.selectByPrimaryKey(key);
        return new ResponseData(EmBusinessError.success);
    }
    /*历史名方所有数据查询*/
    @RequestMapping(value ="selectallfampredo",method = RequestMethod.GET )
    @ResponseBody
    @LogAnnotation(appCode ="",logTitle ="查看所有历史名方",logLevel ="1")
    public ResponseData selectAllFamPreDOMapper(@RequestParam(value = "status")String status){
        List<FamPreDO> famPreDOList = famPreDOService.selectAllFamPre(status);
        return new ResponseData(EmBusinessError.success,famPreDOList);
    }
         /*历史名方数据状态*/
    @RequestMapping(value = "changestatustofampre/{itemID}/{itemCode}" , method = RequestMethod.POST)
    @ResponseBody
    @LogAnnotation(logTitle = "修改历史名方数据状态", logLevel = "2")
    public ResponseData changeStatusToFamPre(@RequestParam("status") String status , @PathVariable("itemID") Integer itemID , @PathVariable("itemCode")String itemCode){
        FamPreDOKey famPreDOKey = new FamPreDOKey();
        famPreDOKey.setItemid(itemID);
        famPreDOKey.setItemcode(itemCode);
        famPreDOService.changeStatusToFamPre(famPreDOKey,status);
        return new ResponseData(EmBusinessError.success);
    }
    /**
     * 增加点击数
     * @param key
     */
    @RequestMapping(value = "visitnumfampredo", method = RequestMethod.POST)
    @LogAnnotation(appCode ="",logTitle ="历史名方点击浏览数",logLevel ="2",creater ="huangwj",updater = "huangwj")
    public int increaseVisitNum(@RequestBody FamPreDOKey key) {
        return famPreDOService.increaseVisitNumFamPre(key);
    }
    /*关键字查询*/
    @GetMapping("/searchFamPre/{keyWord}")
    @ResponseBody
    public ResponseData searchFamPre(@PathVariable("keyWord") String keyWord) {
        List<FamPreDO> famPreDOList = famPreDOService.searchFamPre(keyWord);
        return new ResponseData(EmBusinessError.success,famPreDOList);
    }
}
