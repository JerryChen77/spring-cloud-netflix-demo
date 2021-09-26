package com.qf.my.worker.consumer.controller;

import com.qf.my.common.po.TbWorker;
import com.qf.my.common.result.ResultModel;
import com.qf.my.worker.consumer.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cjl
 * @date 2021/8/17 13:12
 */

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @RequestMapping("/get/{id}")
    public ResultModel getWorker(@PathVariable long id){
        ResultModel resultModel = workerService.getWorker(id);
        return resultModel;
    }

    @PostMapping("/add")
    public ResultModel addWorker(@RequestBody TbWorker worker){
        ResultModel resultModel = workerService.addWorker(worker);
        return resultModel;
    }

    @PostMapping("/updateWithCookie")
    public String getCookie(@RequestBody TbWorker worker, @CookieValue("login_token") String loginToken){
        return workerService.updateWithCookie(worker,loginToken);
    }

}
