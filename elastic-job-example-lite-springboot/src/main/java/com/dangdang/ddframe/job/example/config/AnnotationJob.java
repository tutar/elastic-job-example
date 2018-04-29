package com.dangdang.ddframe.job.example.config;

import com.dangdang.ddframe.job.api.ShardingContext;
//import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import com.dangdang.ddframe.job.example.fixture.entity.Foo;
import com.dangdang.ddframe.job.example.fixture.repository.FooRepository;
import com.dangdang.elasticjob.lite.annotation.ElasticSimpleJob;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tutar on 2018/4/7.
 */

@Component
public class AnnotationJob {

    @ElasticSimpleJob(cron="${simpleJobAnnotation.cron: 0/5 * * * * ?}",jobParameter="job-parameter-test")
    public void taskWithParameter(ShardingContext shardingContext) {
        System.out.println(String.format("JobName: %s | Item: %s | Time: %s | Thread: %s | JobType:%s | JobParameter:%s",
                shardingContext.getJobName(),
                shardingContext.getShardingItem(),
                new SimpleDateFormat("HH:mm:ss").format(new Date()),
                Thread.currentThread().getId(),
                "SIMPLE",
                shardingContext.getJobParameter()));
    }

    @ElasticSimpleJob(value = "0/6 * * * * ?")
    public void taskWithNoParameter(ShardingContext shardingContext) {
        System.out.println("parameter:"+shardingContext.getJobParameter());
    }
}
