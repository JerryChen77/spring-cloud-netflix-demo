package com.qf.my.worker.consumer.feign.service.impl;

import com.qf.my.common.po.TbWorker;
import com.qf.my.common.result.ResultModel;
import com.qf.my.worker.consumer.feign.api.WorkerFeignApi;
import com.qf.my.worker.consumer.feign.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Cjl
 * @date 2021/8/17 19:04
 */
@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerFeignApi feignApi;

    @Override
    public ResultModel deleteWorker(Long id) {
        return  feignApi.deleteWorker(id);
    }

    @Override
    public ResultModel updateWorker(TbWorker worker) {
        return feignApi.updateWorker(worker);
    }

    @Override
    public String updateWorkerWithCookie(TbWorker worker) {
        return feignApi.updateWithCookie(worker);
    }
}
