package com.spring.view.admin.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.admin.order.AdminOrderCntDTO;
import com.spring.biz.admin.order.AdminOrderDTO;
import com.spring.biz.admin.order.AdminOrderService;
import com.spring.biz.admin.order.SumVO;
import com.spring.biz.admin.store.AdminStoreDTO;
import com.spring.biz.admin.user.AdminUserDTO;

@Controller
public class AdminOrderController {
	@Autowired
	private AdminOrderService adminOrderService;

	@RequestMapping("/getAdminOrderList.do") // store_name;
	public String getOrder_List(HttpSession session, AdminOrderDTO vo, Model model) {

		//�����̷�Ʈ ��.. �� �����ϱ� ���� ���
		AdminUserDTO userVO = (AdminUserDTO) session.getAttribute("adminUser");
		vo.setStore_name(userVO.getStore_name());
	
		List<AdminOrderDTO> list = adminOrderService.getOrder_List(vo);
		
		for (int i = 0; i < list.size(); i++) { // �ֹ� ����Ʈ ==> ������ �ֹ�
			
			String phone = adminOrderService.getPhone(list.get(i).getUser_e_mail());//�̸��Ϸ� tel������
			list.get(i).setPhone(phone);
			
			List<JSONObject> foodJsonList = new ArrayList<JSONObject>();
			List<String> foodStringArray = new ArrayList<String>();
			String food = null;
			int food_cnt = 0, food_price = 0;
			ArrayList<String> opt = null;
			ArrayList<Integer> opt_value = null;

	// (String)json => List<String> [�����ŭ]
			foodStringArray.add(list.get(i).getFood1());
			if (list.get(i).getFood2() != null) {	foodStringArray.add(list.get(i).getFood2());}
			if (list.get(i).getFood3() != null) {	foodStringArray.add(list.get(i).getFood3());}
			if (list.get(i).getFood4() != null) {	foodStringArray.add(list.get(i).getFood4());}
			if (list.get(i).getFood5() != null) {	foodStringArray.add(list.get(i).getFood5());}
	// List<String> => List<JSONObject>
			for (int j = 0; j < foodStringArray.size(); j++) {
				String jsonString = foodStringArray.get(j);
				foodJsonList.add(new JSONObject(jsonString));
			}
	// Start of Food1 ~ Food5(MAX)
			for (int k = 0; k < foodJsonList.size(); k++) {
				StringBuilder builder = new StringBuilder();

				food = (String) foodJsonList.get(k).get("food" + (k + 1));
				food_cnt = (Integer) foodJsonList.get(k).get("food" + (k + 1) + "_cnt");
				food_price = (Integer) foodJsonList.get(k).get("food" + (k + 1) + "_price");

				opt = new ArrayList<String>();
				opt_value = new ArrayList<Integer>();
				int n = 1;
				while (foodJsonList.get(k).has("opt" + n)) {
					opt.add((String) foodJsonList.get(k).get("opt" + n));
					opt_value.add((Integer) foodJsonList.get(k).get("opt" + n + "_value"));
					n++;
				}
				builder.append("<h3 class=\"h3 my-0 text-gray-800\">" + food + "</h3>");
				builder.append("����: " + food_cnt);
//    			builder.append("����: "+food_price);
				for (int m = 0; m < opt.size(); m++) {
					builder.append("<br>");
					builder.append("�ɼ�: " + opt.get(m));
//    				builder.append("�ɼǰ���: "+opt_value.get(m));
				}
				builder.append("<br>------------------<br>");
				if (k == 0) {	list.get(i).setFood1(builder.toString());}
				if (k == 1) {	list.get(i).setFood2(builder.toString());}
				if (k == 2) {	list.get(i).setFood3(builder.toString());}
				if (k == 3) {	list.get(i).setFood4(builder.toString());}
				if (k == 4) {	list.get(i).setFood5(builder.toString());}
				// 1ȸ�� ���� ... builder => list.get(i).setFood1
			}
			// End of Food1 ~ Food5(MAX)
		}
		model.addAttribute("orderList", list);
		return "order.jsp";
	}

	@RequestMapping("/updateType.do")
	public void updateType(HttpServletResponse response, AdminOrderDTO vo) throws IOException {
		adminOrderService.typeUpdate(vo);
		response.sendRedirect("getAdminOrderList.do");
	}
	
	@RequestMapping("/regularList.do")
	public String regularList(AdminStoreDTO vo, Model model) throws IOException {	
		/*group by user_e_mail*/
		/*�� ������ ��Ʈ�ѷ����� condition_value ���� ������ �������� ����*/
		if(!(vo.getCondition().equals("") && vo.getCondition().equals("�̼���"))) {
			List<AdminOrderCntDTO> rawCntList = adminOrderService.countRegular_list(vo);
			
			if(vo.getCondition().equals("�ֹ�Ƚ��")) {
				List<AdminOrderCntDTO> cntList = new ArrayList<AdminOrderCntDTO>();
				for(int i =0; i<rawCntList.size(); i++) { 
					if(rawCntList.get(i).getCnt() >= vo.getCondition_value()) {
						cntList.add(rawCntList.get(i));
					}	
				}
				model.addAttribute("cntList",cntList);
			}
		
			if(vo.getCondition().equals("�ջ�ݾ�")) {
				List<AdminOrderDTO> rawList = adminOrderService.sumRegular_list(vo);
				///*�� ������ �� ��Ʈ�ѷ����� ��� �� condition_value ���� ������ ��Ʈ�ѷ����� �������� ����*/		
				//������ �ֹ��� �����հ踦 ������ ����Ʈ
				List<SumVO> sumList = new ArrayList<SumVO>();
				
				for (int i = 0; i < rawList.size(); i++) { // ������ ���͸��� �ֹ� ����Ʈ ==> ������ �ֹ�		
				//�ݺ��� ���� ����
					List<JSONObject> foodJsonList = new ArrayList<JSONObject>();
					List<String> foodStringArray = new ArrayList<String>();

					int food_cnt = 0, food_price = 0;
					ArrayList<Integer> opt_value = null;

					SumVO sumVO = new SumVO();
					sumVO.setUser_e_mail(rawList.get(i).getUser_e_mail());
					
				// (String)json => List<String> [�����ŭ]
					foodStringArray.add(rawList.get(i).getFood1());
					if (rawList.get(i).getFood2() != null) {	foodStringArray.add(rawList.get(i).getFood2());}
					if (rawList.get(i).getFood3() != null) {	foodStringArray.add(rawList.get(i).getFood3());}
					if (rawList.get(i).getFood4() != null) {	foodStringArray.add(rawList.get(i).getFood4());}
					if (rawList.get(i).getFood5() != null) {	foodStringArray.add(rawList.get(i).getFood5());}
					
				// List<String> => List<JSONObject>
					for (int j = 0; j < foodStringArray.size(); j++) {
						String jsonString = foodStringArray.get(j);
						foodJsonList.add(new JSONObject(jsonString));
					}
				// Start of Food1 ~ Food5(MAX)
					for (int k = 0; k < foodJsonList.size(); k++) {
						food_cnt = (Integer) foodJsonList.get(k).get("food" + (k + 1) + "_cnt");
						food_price = (Integer) foodJsonList.get(k).get("food" + (k + 1) + "_price");
						sumVO.setSum(sumVO.getSum()+food_price * food_cnt);
						
						opt_value = new ArrayList<Integer>();
						int n = 1;
						while (foodJsonList.get(k).has("opt" + n)) {
							opt_value.add((Integer) foodJsonList.get(k).get("opt" + n + "_value"));
							sumVO.setSum(sumVO.getSum() + opt_value.get(n-1) * food_cnt);
							n++;
						}
						// 1ȸ�� ���� ... 
					}
					// End of Food1 ~ Food5(MAX)			
					sumList.add(sumVO);
				}
				//�׽�Ʈ
				for(int i =0; i<sumList.size(); i++) {
					System.out.println(sumList.get(i).getUser_e_mail() +" : " +sumList.get(i).getSum());
				}
				Collections.sort(sumList);
				System.out.println("����");
				for(int i =0; i<sumList.size(); i++) {
					System.out.println(sumList.get(i).getUser_e_mail() +" : " +sumList.get(i).getSum());
				}
				
				//grouping �۾� 
				List<SumVO> groupBySumList = new ArrayList<SumVO>();
				String e_mail = null;
				int sum = 0;
				for(int i =0; i<sumList.size(); i++) {
					
					e_mail = sumList.get(i).getUser_e_mail();
					sum += sumList.get(i).getSum();
					if(i != sumList.size()-1) { //������ �ε����� �ƴϰ�
						if(!e_mail.equals(sumList.get(i+1).getUser_e_mail())) { //���� �ε����� �̸��ϰ� ��ġ���� �ʴ´ٸ�
							SumVO sumVO = new SumVO();
							sumVO.setUser_e_mail(e_mail);
							sumVO.setSum(sum);
							groupBySumList.add(sumVO);
							e_mail = null;
							sum = 0;
						}
					}
					else {//������ �ε���
						SumVO sumVO = new SumVO();
						sumVO.setUser_e_mail(e_mail);
						sumVO.setSum(sum);
						groupBySumList.add(sumVO);
					}
				}
				
				//���� �Ǻ�
				List<SumVO> conSumList = new ArrayList<SumVO>();
				for(int i =0; i<groupBySumList.size(); i++) {
					if(groupBySumList.get(i).getSum() >= vo.getCondition_value()) {
						conSumList.add(groupBySumList.get(i));
					}
				}
				model.addAttribute("sumList",conSumList);
			}
		}
		
		return "regular.jsp";
	}
	
}
