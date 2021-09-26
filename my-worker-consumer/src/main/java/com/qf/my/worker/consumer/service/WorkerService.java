package com.qf.my.worker.consumer.service;

import com.qf.my.common.po.TbWorker;
import com.qf.my.common.result.ResultModel;

/**
 * @author Cjl
 * @date 2021/8/17 13:07
 */
public interface WorkerService {
    ResultModel getWorker(Long id);

    ResultModel addWorker(TbWorker worker);


    String updateWithCookie(TbWorker worker,String loginToken);
}
