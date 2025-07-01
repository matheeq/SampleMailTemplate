package com.splitArray.controller;

import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.splitArray.model.MailsData;
import com.splitArray.model.MailsDataFilter;
import com.splitArray.repo.MailsDataFilterRepo;
import com.splitArray.repo.MailsDataRepo;

@RestController
@RequestMapping("/api")
public class HomeController {
	
	Logger log=LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	MailsDataRepo mailsDataRepo;
	
	@Autowired
	MailsDataFilterRepo mailsDataFilterRepo;
	
	@Autowired
	Environment env;
	
	@GetMapping("/")
    public String sampleTest() {
    	return "Default Method";
    }
	
    @GetMapping("/split-Array")
	public void splitArray() {
		try {
    	final int chunkSize = 20000;
    	final AtomicInteger counter = new AtomicInteger();
		
		List<MailsData> mailsData = mailsDataRepo.findAll().subList(0, 9);
//		List<MailsDataFilter> mailsDataFilter = mailsDataFilterRepo.findAll();
		
		final Collection<List<MailsData>> result = mailsData.stream()
			    .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
			    .values();
		
		/////////////
		final int[] arrayChunkSize = {1,2,3};
//		final Collection<List<MailsData>> result1 = (Collection<List<MailsData>>) mailsData.stream()
//			    .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / arrayChunkSize[counter.getAndIncrement()]));
		/////////
		int temp1 = 0;
		int temp2=0;
		List<List<MailsData>> tempList = new ArrayList<>();
		System.out.println(mailsData.size());
		List<Integer> mailsDataId = mailsData.stream().map(MailsData::getId).collect(Collectors.toList());
		System.out.println(mailsDataId);
		for(int size:arrayChunkSize) {
			temp2=temp2+size;
			List<MailsData> mailsDataSublist = mailsData.subList(temp1, temp2);
			tempList.add(mailsDataSublist) ;
			temp1=temp2;
//			System.out.println(mailsDataSublist);
			List<Integer> idList = mailsDataSublist.stream().map(MailsData::getId).collect(Collectors.toList());
			System.out.println(idList);
			System.out.println(mailsDataSublist.size());
		}
		System.out.println(tempList);
		}catch (Exception e) {
			e.printStackTrace();
		}
//		mailsData
		
		System.out.println("---");
	}


	/*
	 * @Override public T get(int index) { int start = index * chunkSize; int end =
	 * Math.min(start + chunkSize, list.size());
	 * 
	 * if (start > end) { throw new IndexOutOfBoundsException("Index " + index +
	 * " is out of the list range <0," + (size() - 1) + ">"); }
	 * 
	 * return new ArrayList<>(list.subList(start, end)); }
	 * 
	 * 
	 * @Override public int size() { return (int) Math.ceil((double) list.size() /
	 * (double) chunkSize); }
	 */
    
    @GetMapping("/get-static-data")
    public String getStaticData() {
    	String a = env.getProperty("local.server.port")+ "/tStatic Data from Split Array Project ";
    	System.out.println(env.getProperty("local.server.port"));
    	return a;
    }
    
    @GetMapping("/get-dynamic-data")
    public String getDynamicData(@RequestParam String name) {
    	
    	String data = "Hello"+ " "+ name +" "+"From Port:"+ env.getProperty("local.server.port");
    	return data;
    	
    }
    
    @GetMapping("/get-data-from-pathvar/{name}")
    public String getDataFromPathVar( @PathVariable String name) {
    	return name;
    }
    
    @GetMapping("/get-sorted-data")
    public List<Integer> getSortedData(){
    	
    	if(log.isDebugEnabled()) {
    		System.out.println("Debugged Enabled is True");
    	}
    	System.out.println(log.isDebugEnabled());
    	List<Integer> list = Arrays.asList(4,2,3,8,7,9,1,5,6);
    	Collections.sort(list);
//    	Collections.sort(list, Comparator<list>->);
    	List<Integer> list1 = Arrays.asList(4,2,3,8,7,9,1,5,6);
    	list1.sort(Comparator.reverseOrder());
    	ExecutorService  ex= Executors.newSingleThreadExecutor();
     
    	String timeStamp = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
    	System.out.println(timeStamp);
    	ex.execute(new Runnable() {
			
			@Override
			public void run() {
				String timeStamp1 = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
			System.out.println(timeStamp1);
				
			}
		});
    	
		return null;
    	
    }
}
