package com.qf.my.worker.provider.controller;

import com.qf.my.common.po.TbWorker;
import com.qf.my.common.result.ResultModel;
import com.qf.my.worker.provider.mapper.TbWorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Cjl
 * @date 2021/8/17 13:01
 */
@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    TbWorkerMapper workerMapper;
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/get/{id}")
    public TbWorker getWorker(@PathVariable Long id) throws InterruptedException {
        if(id <= 0){
            throw new RuntimeException("出现了异常");
        }
        TbWorker worker = workerMapper.selectByPrimaryKey(id);
        Thread.sleep(1500);
        return worker;
    }

    @PostMapping("/add")
    public ResultModel addWorker(@RequestBody TbWorker worker){
        int insert = workerMapper.insert(worker);
        if (insert==1){
            return ResultModel.success();
        }
        return ResultModel.error();
    }

    @RequestMapping("/delete/{id}")
    public ResultModel deleteWorker(@PathVariable("id") Long id) throws InterruptedException {
        int delete = workerMapper.deleteByPrimaryKey(id);
        if (delete==1){
            return ResultModel.success();
        }
        Thread.sleep(1500);
        return ResultModel.error();
    }
    @PostMapping("/update")
    public ResultModel updateWorker(@RequestBody TbWorker worker){
        if (Objects.nonNull(worker.getId())){
            int update = workerMapper.updateByPrimaryKeySelective(worker);
            if (update==1){
                return ResultModel.success();
            }
            return ResultModel.error("更新失败");

        }
        return ResultModel.error("请输入id");
    }

    @PostMapping("/updateWithCookie")
    public String updateWithCookie(@RequestBody TbWorker worker, @CookieValue(value = "login_token",required = false) String loginToken) throws InterruptedException {
        if ("login".equals(loginToken)){
            int update = workerMapper.updateByPrimaryKeySelective(worker);
            return String.format("loginToken:%s,更新结果为:%d",loginToken,update);
        }
        return String.format("loginToken:%s,令牌错误,无法更新!,serverPort=%s",loginToken,serverPort);
    }
}
