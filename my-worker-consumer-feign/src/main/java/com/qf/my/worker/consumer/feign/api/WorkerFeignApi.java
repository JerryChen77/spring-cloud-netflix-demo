package com.qf.my.worker.consumer.feign.api;

import com.qf.my.common.po.TbWorker;
import com.qf.my.common.result.ResultModel;
import com.qf.my.worker.consumer.feign.fallback.WorkerFeignFallback;
import com.qf.my.worker.consumer.feign.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Cjl
 * @date 2021/8/17 18:50
 */
//@FeignClient("WORKER-PROVIDER")
@FeignClient(value = "WORKER-PROVIDER",configuration = FeignInterceptor.class)
//@FeignClient(value = "WORKER-PROVIDER",configuration = FeignInterceptor.class,fallback = WorkerFeignFallback.class)
//@FeignClient(value="WORKER-PROVIDER",fallback=WorkerFeignFallback.class)

public interface WorkerFeignApi {

    @RequestMapping("/worker/delete/{id}")
    public ResultModel deleteWorker(@PathVariable("id") Long id);

    @PostMapping("/worker/update")
    public ResultModel updateWorker(@RequestBody TbWorker worker);

    @PostMapping("/worker/updateWithCookie")
    public String updateWithCookie(@RequestBody TbWorker worker);
}
