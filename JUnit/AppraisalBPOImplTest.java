package com.defteam.task;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.defteam.emp.dao.OrganizationHierarchyDAO;
import com.defteam.mail.PrepareAndSendEmail;
import com.defteam.model.Department;
import com.defteam.model.EmpDesignation;
import com.defteam.model.EmpModel;
import com.defteam.model.EmpModelWrapper;
import com.defteam.model.Feedback;
import com.defteam.model.Organization;
import com.defteam.model.PromotionRecommendations;
import com.defteam.model.SubProcess;
import com.defteam.task.bpo.AppraisalBPOImpl;
import com.defteam.task.bpo.TaskBPO;
import com.defteam.task.dao.AppraisalDAO;
import com.defteam.task.dao.SubProcessDAO;
import com.defteam.task.model.AppraisalCycle;
import com.defteam.task.model.EmpAppraisalPerformance;
import com.defteam.util.DateUtil;
import com.defteam.util.grid.DtGridData;
import com.defteam.util.grid.QueryParameter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class AppraisalBPOImplTest extends MockObjects{
	
	private final static File file = new File("./src/test/java/mock/EmpModel.json");
	private final static File apraisalBPOImplFile = new File("./src/test/java/mock/ApraisalBPOImpl_data.json");
	private static Map<String, Object> map = null;
	private static Map<String, Object> apraisalBPOImplData = null;
	
	@Spy
	@InjectMocks
	private AppraisalBPOImpl appraisalBPOImpl;
	
	@Mock
	private AppraisalDAO appraisalDAO;
	
	@Mock
	private SubProcessDAO subProcessDAO;
	
	@Mock
	OrganizationHierarchyDAO organizationHierarchyDAO;
	
	@Mock
	TaskBPO taskBPO;

	private List<EmpModel> empList;
	
	private EmpModel empModel;
	
	private List<AppraisalCycle> appraisalCycleList;
	
	private Department deptModel;
	
	@BeforeClass
	public static void setUp() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		map = mapper.readValue(file, Map.class);
		map.putAll(mapper.readValue(apraisalBPOImplFile, Map.class));
		
	}
	
	public AppraisalBPOImplTest() {
		ObjectMapper mapper = new ObjectMapper();
		empList = mapper.convertValue(map.get("empList"), new TypeReference<List<EmpModel>>() {
		});
		empModel = mapper.convertValue(map.get("empModel"), EmpModel.class);

		appraisalCycleList = mapper.convertValue(map.get("appraisalCycleList"),
				new TypeReference<List<AppraisalCycle>>() {
				});
		deptModel = mapper.convertValue(map.get("departmentModel"), Department.class);
	}
	
	
	@Test
	public void sample() {
		try {

			ObjectMapper mapper = new ObjectMapper();
			EmpModel emp = mapper.convertValue(map.get("empModel"), EmpModel.class);
			 List<EmpModel> empList = mapper.convertValue(map.get("empList"), new TypeReference<List<EmpModel>>() {});
			System.out.println("empList");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void saveAppraisalReviewConfigTest() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			AppraisalCycle ap = new AppraisalCycle();
			ap.setId((long) 123);
			ap.setMailToManager(true);
			Department dept = mapper.convertValue(map.get("departmentModel"), Department.class);
			Mockito.when(appraisalDAO.saveAppraisalReviewConfig(ap)).thenReturn(true);
			Mockito.when(organizationHierarchyDAO.getDepartment(ap.getDepartmentId())).thenReturn(dept);
			boolean res = appraisalBPOImpl.saveAppraisalReviewConfig(ap);
			Assert.assertEquals(true, res);
			Mockito.verify(organizationHierarchyDAO, Mockito.times(1)).getDepartment(Mockito.anyInt());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void getAppraisalCycleIdTest() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			AppraisalCycle appraisalCycle = mapper.convertValue(map.get("appraisalCycle"), AppraisalCycle.class);
			SubProcess subProcess = mapper.convertValue(map.get("subProcess"), SubProcess.class);
			Mockito.when(appraisalDAO.getAppraisalCycleId(Mockito.anyLong())).thenReturn(appraisalCycle);
			Mockito.when(subProcessDAO.getSubProcessbyId(Mockito.anyInt())).thenReturn(subProcess);
			Mockito.when(empDAO.getEmpName(Mockito.anyString())).thenReturn("John");
			AppraisalCycle res = appraisalBPOImpl.getAppraisalCycleId(Mockito.anyLong());
			Assert.assertEquals(appraisalCycle.getId(), res.getId());
			Assert.assertEquals(appraisalCycle.getSubProcessId(), res.getSubProcessId());
			Assert.assertEquals(appraisalCycle.getManagerName(), res.getManagerName());
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 
	@Test
	public void getAppraisalCycleTest() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			EmpModel emp = mapper.convertValue(map.get("empModel"), EmpModel.class);
			Timestamp date = DateUtil.now();
			Mockito.when(appraisalDAO.getAppraisalCycle(emp.getDepartmentId(), emp.getSubProcessId(), emp.getEmpId(),
					date, date)).thenReturn(appraisalCycleList);
			List<AppraisalCycle> res = appraisalBPOImpl.getAppraisalCycle(emp, emp.getOrganizationId(),
					emp.getDepartmentId(), emp.getSubProcessId(), emp.getEmpId(),date, date);
			Assert.assertEquals(appraisalCycleList.size(), res.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void checkDuplicateAppraisalCycleEntryTest() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			AppraisalCycle appraisalCycle = mapper.convertValue(map.get("appraisalCycle"), AppraisalCycle.class);
			Mockito.when(appraisalDAO.checkDuplicateAppraisalCycleEntry(appraisalCycle.getDepartmentId(),
					appraisalCycle.getSubProcessId(), appraisalCycle.getManagerId(), DateUtil.now(), DateUtil.now()))
					.thenReturn(appraisalCycleList);
			List<AppraisalCycle> res = appraisalBPOImpl.checkDuplicateAppraisalCycleEntry(appraisalCycle,
					DateUtil.now(), DateUtil.now());
			Assert.assertEquals(appraisalCycleList.size(), res.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAppraiseByManagerTest() {
		try {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> mapObj = new HashMap<>();
		mapObj.put("empId", "E0001");
		mapObj.put("empName", "John");
		Department dept = mapper.convertValue(map.get("departmentModel"), Department.class);
		Mockito.when(empDAO.getDepartmentsNames()).thenReturn(Arrays.asList(dept));
		Mockito.when(empDAO.getAppraiseByManager(1,2)).thenReturn(Arrays.asList(mapObj));
		List<Map<String, String>> res = appraisalBPOImpl.getAppraiseByManager(1, 2);
		Assert.assertEquals(mapObj.get("empId"), res.get(0).get("empId"));
		Assert.assertEquals(mapObj.get("empName"), res.get(0).get("empName"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getAppraisalByTest() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<EmpModel> empList = mapper.convertValue(map.get("empList"), new TypeReference<List<EmpModel>>() {
			});
			EmpModel emp = mapper.convertValue(map.get("empModel"), EmpModel.class);
			Mockito.when(empDAO.getEmployeesByRole(2, Arrays.asList(11))).thenReturn(empList);
			Mockito.when(empDAO.getEmployeesByRole(2, Arrays.asList(1, 2))).thenReturn(empList);
			List<EmpModelWrapper> res = appraisalBPOImpl.getAppraisalBy(emp.getOrganizationId(), emp.getDepartmentId(),
					emp.getSubProcessId());
			Assert.assertEquals(empList.size(), res.size());
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test	
	public void getAppraisalCycleBasedOnEndDateTest() {
		try {
			Mockito.when(appraisalDAO.getAppraisalCycleBasedOnEndDate(2, DateUtil.now()))
					.thenReturn(appraisalCycleList);
			List<AppraisalCycle> res = appraisalBPOImpl.getAppraisalCycleBasedOnEndDate(2, DateUtil.now());
			Assert.assertEquals(appraisalCycleList.size(), res.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	@Test  //not included
	public void getPendingReviewEmpListTest() {
		try {
			Organization org = new Organization();
			LocalDateTime date = DateUtil.now().toLocalDateTime();
			Mockito.doReturn(date).when(appraisalBPOImpl).getAppraisalFromDate();
			Mockito.doReturn(date).when(appraisalBPOImpl).getAppraisalToDate();
			Timestamp ts = DateUtil.now();
			Mockito.when(taskDAO.getFirstTaskStartDate(null)).thenReturn(DateUtil.now());
			Mockito.when(empDAO.getEmpDetail(Mockito.anyString())).thenReturn(empModel);
			Mockito.when(appraisalDAO.getAppraisalCycle(empModel.getDepartmentId(), empModel.getSubProcessId(),
					empModel.getEmpId(), ts, ts)).thenReturn(appraisalCycleList);

			Mockito.when(appraisalDAO.getPendingReviewEmpList(Mockito.anyString(), Mockito.any(Timestamp.class),
					Mockito.any(Timestamp.class))).thenReturn(empList);
			Mockito.when(empDAO.getEmpDetailByEmpId("E0001", 0)).thenReturn(empModel);
			Mockito.when(empDAO.getEmpDepartmentById(empModel.getDepartmentId())).thenReturn(deptModel);
			Mockito.when(empDAO.getBranchDetailsById(empModel.getOrganizationId())).thenReturn(org);
			List<EmpModel> res = appraisalBPOImpl.getPendingReviewEmpList(empModel.getEmpId());
			
			Assert.assertEquals(empList.size(), res.size());
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void getReviewerListTest() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			SubProcess subProcess = mapper.convertValue(map.get("subProcess"), SubProcess.class);
			Mockito.when(empDAO.getEmpDetail(empModel.getEmpId())).thenReturn(empModel);
			Mockito.when(empDAO.getEmployeesByRole(1, Arrays.asList(11))).thenReturn(empList);
			Mockito.when(empDAO.getSubProcessListBy(Mockito.anyInt())).thenReturn(Arrays.asList(subProcess));
			List<EmpModel> res = appraisalBPOImpl.getReviewerList(appraisalCycleList.get(0));
			Assert.assertEquals(empModel.getEmpId(), res.get(0).getEmpId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void savePromotionDetailsTest() {
		try {
			BigDecimal rating = null;
			PromotionRecommendations prRec = new PromotionRecommendations();
			Mockito.doNothing().when(appraisalDAO).savePromotionDetails(prRec);
			Mockito.when(empDAO.getEmpDetail(Mockito.anyString())).thenReturn(empModel);
			boolean res = appraisalBPOImpl.savePromotionDetails(empModel.getEmpId(), empModel.getEmpId(),
					1, rating);
			Assert.assertEquals(true, res);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test	
	public void getDeptWiseReviewDataTest() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			SubProcess subProcess = mapper.convertValue(map.get("subProcess"), SubProcess.class);
			Mockito.when(organizationHierarchyDAO.getDepartment(1)).thenReturn(deptModel);
			Mockito.when(subProcessDAO.getSubProcessListBy(1)).thenReturn(Arrays.asList(subProcess));
			Mockito.when(empDAO.getEmpListBySubProcess(empModel.getOrganizationId(), empModel.getDepartmentId(),
					empModel.getSubProcessId())).thenReturn(empList);
			Mockito.when(empDAO.getEmpDetail(empModel.getEmpId())).thenReturn(empModel);
			Mockito.when(appraisalDAO.getAppraisalCycle(empModel.getDepartmentId(), empModel.getSubProcessId(),
					empModel.getEmpId(), DateUtil.now(), DateUtil.now())).thenReturn(appraisalCycleList);
			Mockito.when(appraisalDAO.getReviewPendingAppraisalCount(empModel.getEmpId(), DateUtil.now(),
					DateUtil.now(), DateUtil.now(), DateUtil.now())).thenReturn(1);
			Map<String, Object> res = appraisalBPOImpl.getDeptWiseReviewData(Arrays.asList(1), DateUtil.now(),
					DateUtil.now());
			Assert.assertEquals(true, res.get("pendingReviewData").toString().contains(deptModel.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getSubprocessReviewPendingTest() {
		try {
			Timestamp date = DateUtil.now();
			Organization org = new Organization();
			LocalDateTime localDate = DateUtil.now().toLocalDateTime();
			Mockito.doReturn(localDate).when(appraisalBPOImpl).getAppraisalFromDate(Mockito.anyInt());
			Mockito.doReturn(localDate).when(appraisalBPOImpl).getAppraisalToDate(Mockito.anyInt());
			Mockito.when(organizationHierarchyDAO.getDepartment(deptModel.getDepartmentId())).thenReturn(deptModel);
			Mockito.when(empDAO.getEmpListBySubProcess(empModel.getOrganizationId(), empModel.getDepartmentId(),
					empModel.getSubProcessId())).thenReturn(empList);
			Mockito.when(empDAO.getEmpDetail(Mockito.anyString())).thenReturn(empModel);
			Mockito.when(appraisalDAO.getAppraisalCycle(1,2,
					"E001", date, date)).thenReturn(appraisalCycleList);
			Mockito.when(appraisalDAO.getReviewPendingAppraisalEmployee(Mockito.anyString(),Mockito.any(Timestamp.class),
					Mockito.any(Timestamp.class), Mockito.any(Timestamp.class), Mockito.any(Timestamp.class))).thenReturn(empModel);
			Mockito.when(empDAO.getEmpDetailByEmpId("E0001", 0)).thenReturn(empModel);
			Mockito.when(empDAO.getEmpDepartmentById(empModel.getDepartmentId())).thenReturn(deptModel);
			Mockito.when(empDAO.getBranchDetailsById(empModel.getOrganizationId())).thenReturn(org);
			List<EmpModel> res = appraisalBPOImpl.getSubprocessReviewPending(deptModel.getDepartmentId(),
					empModel.getSubProcessId(), date, date);
			Assert.assertEquals(empList.size(), res.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test	
	public void approvePromotionRequestTest() {
		try {
			PromotionRecommendations prReq = new PromotionRecommendations();
			Mockito.when(appraisalDAO.getPromotionReqDetailsById(1)).thenReturn(prReq);
			Mockito.doNothing().when(appraisalDAO).savePromotionDetails(prReq);
			boolean res = appraisalBPOImpl.approvePromotionRequest(1, empModel.getEmpId());
			Assert.assertEquals(true, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getEmpRecommendedForPromotionTest() {
		try {
			Map<String, String> mapObj = new HashMap<>();
			mapObj.put("empId", "E0001");
			mapObj.put("empName", "John");
			Mockito.when(appraisalDAO.getEmpsRecommendedForPromotion(empModel, 1)).thenReturn(Arrays.asList(mapObj));
			List<Map<String, String>> res = appraisalBPOImpl.getEmpRecommendedForPromotion(empModel, 1);
			Assert.assertEquals(mapObj.get("empId"), res.get(0).get("empId"));
			Assert.assertEquals(mapObj.get("empName"), res.get(0).get("empName"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void getPromotionDataByYearTest() {
		try {
			String[] empId = new String[2];
			empId[0] = empModel.getEmpId();
			PromotionRecommendations promRecom = new PromotionRecommendations();
			Feedback feedback = new Feedback();
			EmpAppraisalPerformance empAppraisalPerformance = new EmpAppraisalPerformance();
			EmpDesignation empDesignation = new EmpDesignation();
			empDesignation.setName("HR");
			promRecom.setApprovedBy(empModel.getEmpId());
			Mockito.when(appraisalDAO.getPromotionDataByYear(2021, empId)).thenReturn(Arrays.asList(promRecom));
			Mockito.when(taskBPO.getEmpAppraisal(empModel.getEmpId(), 2021)).thenReturn(empAppraisalPerformance);
			Mockito.when(empDAO.getEmpDetail(Mockito.anyString())).thenReturn(empModel);
			Mockito.when(empDAO.getEmployeeFeedbackById(Mockito.anyInt())).thenReturn(feedback);
			Mockito.when(empDAO.getDesignation(Mockito.anyInt())).thenReturn(empDesignation);
			List<PromotionRecommendations> res = appraisalBPOImpl.getPromotionDataByYear(2021, empId);
			Assert.assertEquals(promRecom.getEmpCode(), res.get(0).getEmpCode());
			Assert.assertEquals(promRecom.getDesignation(), res.get(0).getDesignation());
			Assert.assertEquals(promRecom.getApprovedBy(), res.get(0).getApprovedBy());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test	
	public void sendAppraisalCycleMailTest() {
		try {
			Timestamp date = DateUtil.now();
			LocalDateTime localDate = DateUtil.now().toLocalDateTime();
			Mockito.doReturn(localDate).when(appraisalBPOImpl).getAppraisalFromDate();
			Mockito.doReturn(localDate).when(appraisalBPOImpl).getAppraisalToDate();
			Mockito.doReturn("").when(appraisalBPOImpl).getDateFormat();
			Mockito.when(empDAO.getEmpDetail(Mockito.anyString())).thenReturn(empModel);
			Mockito.when(appraisalDAO.getAppraisalCycle(empModel.getDepartmentId(), empModel.getSubProcessId(),
					empModel.getEmpId(), date, date)).thenReturn(appraisalCycleList);
			appraisalBPOImpl.sendAppraisalCycleMail(empModel.getEmpId());
			Mockito.verify(empDAO, Mockito.times(2)).getEmpDetail(Mockito.anyString());
			Mockito.verify(appraisalDAO, Mockito.times(1)).getAppraisalCycle(empModel.getDepartmentId(),
					empModel.getSubProcessId(), empModel.getAppraisedBy(), date,date);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void getEmpListForAppraisalTest() {
		try {
			Timestamp date = DateUtil.now();
			String sidx="id"; 
			String sord="desc";
			int offset=10;
			int limit=2;
			Boolean delegated=true;
			Boolean pendingAppraisal=true;
			List<Integer> tenantIds=new ArrayList<>();
			List<Integer> deptIds=new ArrayList<>();
			tenantIds.add(1);
			deptIds.add(1);
			List<QueryParameter> params=null;
			Organization org = new Organization();
			LocalDateTime localDate = DateUtil.now().toLocalDateTime();
			Mockito.doReturn(localDate).when(appraisalBPOImpl).getAppraisalFromDate();
			Mockito.doReturn(localDate).when(appraisalBPOImpl).getAppraisalToDate();
			Mockito.when(empDAO.getEmpDetail(empModel.getEmpId())).thenReturn(empModel);
			Mockito.when(appraisalDAO.getAppraisalCycle(empModel.getDepartmentId(), empModel.getSubProcessId(),
					empModel.getEmpId(), date, date)).thenReturn(appraisalCycleList);
			Mockito.when(appraisalDAO.getEmpListForAppraisalCount(tenantIds, deptIds, empModel.getEmpId(), date, date,
					delegated, pendingAppraisal, date, date, "test"))
					.thenReturn(10); 
			Mockito.when(appraisalDAO.getEmpListForAppraisal(sidx, sord, offset, limit,params,
					empModel.getEmpId(), tenantIds, deptIds, date, date, delegated, pendingAppraisal, date, date, "test"))
					.thenReturn(empList);
			Mockito.when(empDAO.getEmpDetailByEmpId("E0001", 0)).thenReturn(empModel);
			Mockito.when(empDAO.getEmpDepartmentById(empModel.getDepartmentId())).thenReturn(deptModel);
			Mockito.when(empDAO.getBranchDetailsById(empModel.getOrganizationId())).thenReturn(org);
			DtGridData<?> res = appraisalBPOImpl.getEmpListForAppraisal(sidx, sord, offset, limit,params,
					empModel.getEmpId(), tenantIds,deptIds, 2021, date, date, delegated, pendingAppraisal,"test");
			Assert.assertEquals(offset, res.getPage());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test	
	public void getReviewConfigGridDataTest() {
		try {
			Timestamp date = DateUtil.now();
			String sidx="id"; 
			String sord="desc";
			int offset=10;
			int limit=2;
			int count=10;
			List<Integer> branchIds=new ArrayList<>();
			List<Integer> deptIds=new ArrayList<>();
			branchIds.add(1);
			deptIds.add(1);
			ObjectMapper mapper = new ObjectMapper();
			SubProcess subProcess = mapper.convertValue(map.get("subProcess"), SubProcess.class);
			Mockito.when(appraisalDAO.getReviewConfigGridDataCount(branchIds, deptIds, date,
					date)).thenReturn(count);
			Mockito.when(appraisalDAO.getReviewConfigGridData(sidx,sord, offset, limit,branchIds, deptIds,
					date, date)).thenReturn(appraisalCycleList);
			Mockito.when(subProcessDAO.getSubProcessbyId(Mockito.anyInt())).thenReturn(subProcess);
			Mockito.when(empDAO.getEmpName(Mockito.anyString())).thenReturn("John");
			DtGridData<?> res = appraisalBPOImpl.getReviewConfigGridData(sidx, sord, offset, limit,branchIds,
					deptIds, date, date);
			Assert.assertEquals(count, res.getRecordsTotal());
			Assert.assertEquals(offset, res.getPage());
			Assert.assertEquals(count, res.getRecordsFiltered());
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test	
	public void deleteAppraisalCycleTest() {
		try {
			ObjectMapper mapper =new ObjectMapper();
			AppraisalCycle appraisalCycle = appraisalCycleList.get(0);
			LocalDateTime date = DateUtil.now().toLocalDateTime();
			Mockito.doReturn(date).when(appraisalBPOImpl).getAppraisalFromDate();
			Mockito.doReturn(date).when(appraisalBPOImpl).getAppraisalToDate();
			Mockito.when(appraisalDAO.getAppraisalCycleId(Mockito.anyLong())).thenReturn(appraisalCycleList.get(0));
			Mockito.when(empDAO.getEmpBasedOnTheAppraisalCycle(Mockito.anyString())).thenReturn(empList);
			Mockito.when(appraisalDAO.checkDuplicateAppraisalCycleEntry(Mockito.anyInt(), Mockito.anyInt(),
					Mockito.anyString(), Mockito.any(Timestamp.class), Mockito.any(Timestamp.class)))
					.thenReturn(appraisalCycleList);
			Mockito.when(appraisalDAO.deleteAppraisalCycle(Mockito.anyLong())).thenReturn(false);
			Department dept = mapper.convertValue(map.get("departmentModel"), Department.class);
			Mockito.when(organizationHierarchyDAO.getDepartment(Mockito.anyInt())).thenReturn(dept);
			Mockito.doReturn(empList).when(appraisalBPOImpl).getReviewerList(appraisalCycle);
			appraisalBPOImpl.deleteAppraisalCycle(12L);
			Mockito.verify(appraisalDAO, Mockito.times(1)).getAppraisalCycleId(Mockito.anyLong());
			Mockito.verify(appraisalDAO, Mockito.times(1)).checkDuplicateAppraisalCycleEntry(Mockito.anyInt(), Mockito.anyInt(),
					Mockito.anyString(), Mockito.any(Timestamp.class), Mockito.any(Timestamp.class));
			Mockito.verify(appraisalDAO, Mockito.times(1)).deleteAppraisalCycle(Mockito.anyLong());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test	
	public void getPromotionRecommendationGridTest() {
		try {
			PromotionRecommendations prReq = new PromotionRecommendations();
			String sidx = "id";
			String sord = "desc";
			int rows = 1;
			String[] empId = new String[2];
			empId[0] = "E001";
			Timestamp date = DateUtil.now();
			int year = 2021;
			int count = 10;
			Long countRes = (long) count;
			int offset = 10;
			int page = 11;
			EmpAppraisalPerformance empAppraisalPerformance = new EmpAppraisalPerformance();
			Feedback feedback = new Feedback();
			EmpDesignation empDesignation = new EmpDesignation();
			empDesignation.setName("HR");
			prReq.setEmpRecommended(empModel.getEmpId());
			Mockito.when(appraisalDAO.getPromotionRecommendationGridCount(sidx, sord, rows, empId, date, date, year, "test"))
					.thenReturn(countRes);
			Mockito.when(appraisalDAO.getPromotionRecommendationGrid(sidx, sord, offset, rows, date, date, empId, year, "test"))
					.thenReturn(Arrays.asList(prReq));
			Mockito.when(taskBPO.getEmpAppraisal(empModel.getEmpId(), 2021)).thenReturn(empAppraisalPerformance);
			Mockito.when(empDAO.getEmployeeFeedbackById(Mockito.anyInt())).thenReturn(feedback);
			Mockito.when(empDAO.getEmpDetail(Mockito.anyString())).thenReturn(empModel);
			Mockito.when(empDAO.getDesignation(Mockito.anyInt())).thenReturn(empDesignation);
			DtGridData<?> res = appraisalBPOImpl.getPromotionRecommendationGrid(sidx, sord, page, rows, date, date,
					empId, year, "test");
			Assert.assertEquals(count, res.getRecordsFiltered());
			Assert.assertEquals(prReq.getEmpRecommended(),
					((PromotionRecommendations) res.getData().get(0)).getEmpRecommended());
			Assert.assertEquals(page, res.getPage());
			Assert.assertEquals(count, res.getRecordsTotal());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test	
	public void getEmpListForAppraisalReportTest() {
		try {
			LocalDateTime localDate = DateUtil.now().toLocalDateTime();
			Timestamp date = DateUtil.now();
			List<Integer> deptIds = new ArrayList<>();
			List<Integer> branchIds = new ArrayList<>();
			branchIds.add(1);
			branchIds.add(2);
			deptIds.add(1);
			deptIds.add(2);
			String empId = empModel.getEmpId();
			EmpModel emp = new EmpModel();
			Organization org = new Organization();
			emp.setEmpId("E1");
			Boolean doolean=true;
			Mockito.doReturn(localDate).when(appraisalBPOImpl).getAppraisalFromDate();
			Mockito.doReturn(localDate).when(appraisalBPOImpl).getAppraisalToDate();
			Mockito.when(taskDAO.getFirstTaskStartDate(null)).thenReturn(date);
			Mockito.when(empDAO.getEmpDetail(Mockito.anyString())).thenReturn(empModel);
			Mockito.when(appraisalDAO.getAppraisalCycle(emp.getDepartmentId(), emp.getSubProcessId(), emp.getEmpId(),
					date, date)).thenReturn(appraisalCycleList);
			Mockito.when(appraisalDAO.getEmpListForAppraisalReport(empModel.getEmpId(), branchIds, deptIds, date, date,
					doolean, doolean, date, date)).thenReturn(empList);
			Mockito.when(empDAO.getEmpDetailByEmpId(Mockito.anyString(), Mockito.anyInt())).thenReturn(emp);
			Mockito.when(empDAO.getEmpDepartmentById(empModel.getDepartmentId())).thenReturn(deptModel);
			Mockito.when(empDAO.getBranchDetailsById(empModel.getOrganizationId())).thenReturn(org);
			List<EmpModel> res = appraisalBPOImpl.getEmpListForAppraisalReport(empId, branchIds, deptIds, doolean, doolean);
			Assert.assertEquals(empList.size(), res.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
