package com.specification.feignDemo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("SPLITARRAYINSTANCE")
public interface FeignDemo {
	
	@GetMapping("/api/get-static-data")
	public String getStaticData();
	
	@GetMapping("/api/get-dynamic-data")
    public String getDynamicData(@RequestParam String name);
}
