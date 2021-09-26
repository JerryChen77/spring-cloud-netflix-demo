package com.qf.my.worker.consumer.feign.fallback;

import com.qf.my.common.po.TbWorker;
import com.qf.my.common.result.ResultModel;
import com.qf.my.worker.consumer.feign.api.WorkerFeignApi;
import org.springframework.stereotype.Component;

/**
 * @author Cjl
 * @date 2021/8/18 12:38
 */
@Component
public class WorkerFeignFallback implements WorkerFeignApi {
    @Override
    public ResultModel deleteWorker(Long id) {
        return ResultModel.error("触发服务提供者熔断,请联系后台管理员");
    }

    @Override
    public ResultModel updateWorker(TbWorker worker) {
        return ResultModel.error("触发服务提供者熔断,请联系后台管理员");
    }

    @Override
    public String updateWithCookie(TbWorker worker) {
        return "触发服务提供者熔断,请联系后台管理员";
    }
}
