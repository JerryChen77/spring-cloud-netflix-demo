package com.qf.my.worker.consumer.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.qf.my.common.po.TbWorker;
import com.qf.my.common.result.ResultModel;
import com.qf.my.worker.consumer.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Cjl
 * @date 2021/8/17 13:08
 */
@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getWorkerError")
    @Override
    public ResultModel getWorker(Long id) {
        String url="http://WORKER-PROVIDER/worker/get/"+id;
        TbWorker worker = restTemplate.getForObject(url, TbWorker.class);
        return ResultModel.success(worker);
    }

    @HystrixCommand(fallbackMethod = "addWorkerError")
    @Override
    public ResultModel addWorker(TbWorker worker) {
        String url="http://WORKER-PROVIDER/worker/add";
        ResultModel resultModel = restTemplate.postForObject(url, worker, ResultModel.class);
        return resultModel;
    }
    @HystrixCommand(fallbackMethod = "hiError")
    @Override
    public String updateWithCookie(TbWorker worker, String loginToken) {
        String url="http://WORKER-PROVIDER/worker/updateWithCookie";

        HttpHeaders headers = new HttpHeaders();
            List<String> cookieValues = new ArrayList<>();
                cookieValues.add("login_token="+loginToken);
        headers.put("Cookie",cookieValues);

        HttpEntity entity = new HttpEntity(worker, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, entity, String.class);
        return responseEntity.getBody();
    }
    public ResultModel getWorkerError(Long id) {
        return ResultModel.error("服务提供者出错,熔断");
    }
    public ResultModel addWorkerError(TbWorker worker) {
        return ResultModel.error("服务提供者出错,熔断");
    }
    public String hiError(TbWorker worker, String loginToken) {
        return "你的网络有问题，请联系当地的电信服务提供商";
    }

}
