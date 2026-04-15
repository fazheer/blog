package com.godfazheer.controller;

import com.godfazheer.model.vo.DescribeVO;
import com.godfazheer.model.vo.result.ResultVO;
import com.godfazheer.service.impl.DescribeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author M-Y-H
 * @since 2023-05-01
 */
@RestController
@RequestMapping("describe")
public class DescribeController {
    @Autowired
    private DescribeServiceImpl describeService;
    @GetMapping
    @ResponseBody
    public ResultVO<?> getDescribe(){
        return ResultVO.ok(describeService.getDescribe());
    }
    @PutMapping
    @ResponseBody
    public ResultVO<?> updateDescribe(@RequestBody DescribeVO describeVO){
        return ResultVO.ok(describeService.updateDescribe(describeVO));
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResultVO<?> delDescribe(@PathVariable("id") Long id){
        return ResultVO.ok(describeService.delDescribe(id));
    }
    @PostMapping
    @ResponseBody
    public ResultVO<?> saveDescribe(@RequestBody  DescribeVO describeVO){
        return ResultVO.ok(describeService.saveDescribe(describeVO));
    }

}
