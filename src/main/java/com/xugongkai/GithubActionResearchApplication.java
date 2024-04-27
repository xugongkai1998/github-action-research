package com.xugongkai;

import cn.hutool.core.util.RuntimeUtil;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.CompilationMXBean;
import java.lang.management.ManagementFactory;

@RestController
@SpringBootApplication
public class GithubActionResearchApplication {

    @Value("${foo.bar:default}")
    private String foobar;

    public static void main(String[] args) {
        SpringApplication.run(GithubActionResearchApplication.class, args);
    }

    @GetMapping("/hi")
    public String hello() {
        CompilationMXBean compilationMXBean = ManagementFactory.getCompilationMXBean();
        String compilationMXBeanName = compilationMXBean.getName();
        return "Hello, I am " + compilationMXBeanName + ", variables foo.bar=" + foobar +", have a good time!";
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> meterRegistryMeterRegistryCustomizer(@Value("${spring.application.name:unknown}") String applicationName) {
        int pid = RuntimeUtil.getPid();
        return registry -> registry.config().commonTags("application", applicationName + "@" + pid);
    }
}
