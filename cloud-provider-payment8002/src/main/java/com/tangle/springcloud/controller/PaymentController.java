package com.tangle.springcloud.controller;

import com.tangle.springcloud.entities.CommonResult;
import com.tangle.springcloud.entities.Payment;

import com.tangle.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("payment/create")
    public CommonResult create(Payment payment){

        int result = paymentService.create(payment);
        log.info("*****插入结果："+result);

        if(result > 0){
            return new CommonResult(200,"插入数据库成功",result);
        }else {
            return new CommonResult(444,"插入数据库失败");
        }

    }

    @GetMapping("payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable Long id){

        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果："+payment);

        if(payment != null){
            return new CommonResult(200,"查询成功,"+serverPort,payment);
        }else {
            return new CommonResult(444,"没有对应记录，查询id："+id,null);
        }

    }
}
