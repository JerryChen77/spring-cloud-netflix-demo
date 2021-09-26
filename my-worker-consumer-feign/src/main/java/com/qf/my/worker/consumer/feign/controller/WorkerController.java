package com.qf.my.worker.consumer.feign.controller;

import com.qf.my.common.po.TbWorker;
import com.qf.my.common.result.ResultModel;
import com.qf.my.worker.consumer.feign.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cjl
 * @date 2021/8/17 19:07
 */
@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerService workerService;

    @RequestMapping("/delete/{id}")
    public ResultModel deleteWorker(@PathVariable("id") long id){
       return workerService.deleteWorker(id);
    }

    @PostMapping("/update")
    public ResultModel updateWorker(@RequestBody TbWorker worker){
       return workerService.updateWorker(worker);
    }

    @PostMapping("/updateWithCookie")
    public String  updateWorkerWithCookie(@RequestBody TbWorker worker){
        return workerService.updateWorkerWithCookie(worker);
    }
}
