package com.qf.my.worker.consumer.feign.service;

import com.qf.my.common.po.TbWorker;
import com.qf.my.common.result.ResultModel;

/**
 * @author Cjl
 * @date 2021/8/17 19:03
 */
public interface WorkerService {
    ResultModel deleteWorker(Long id);

    ResultModel updateWorker(TbWorker worker);


    String updateWorkerWithCookie(TbWorker worker);
}
