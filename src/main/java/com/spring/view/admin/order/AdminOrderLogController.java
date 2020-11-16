package com.spring.view.admin.order;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.biz.admin.order.AdminOrderDTO;
import com.spring.biz.admin.order.AdminOrderService;

@Controller()
public class AdminOrderLogController {
	@Autowired
	private AdminOrderService adminOrderService;
	
	@RequestMapping("/getAdminOrderLogList.do")
	public String getOrder_List(AdminOrderDTO vo, Model model) {
		
		//리다이렉트 할 이유 없음 .. nav 를 통해 store_name 전달됨
		List<AdminOrderDTO> list = adminOrderService.getOrderLog_List(vo);
		
		for (int i = 0; i < list.size(); i++) { // 주문 리스트 ==> 각각의 주문
			
			String phone = adminOrderService.getPhone(list.get(i).getUser_e_mail());//이메일로 tel얻어오기
			list.get(i).setPhone(phone);
			
			List<JSONObject> foodJsonList = new ArrayList<JSONObject>();
			List<String> foodStringArray = new ArrayList<String>();
			String food = null;
			int food_cnt = 0, food_price = 0;
			ArrayList<String> opt = null;
			ArrayList<Integer> opt_value = null;

	// (String)json => List<String> [사이즈만큼]
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
				builder.append("수량: " + food_cnt);
//    			builder.append("가격: "+food_price);
				for (int m = 0; m < opt.size(); m++) {
					builder.append("<br>");
					builder.append("옵션: " + opt.get(m));
//    				builder.append("옵션가격: "+opt_value.get(m));
				}
				builder.append("<br>------------------<br>");
				if (k == 0) {	list.get(i).setFood1(builder.toString());}
				if (k == 1) {	list.get(i).setFood2(builder.toString());}
				if (k == 2) {	list.get(i).setFood3(builder.toString());}
				if (k == 3) {	list.get(i).setFood4(builder.toString());}
				if (k == 4) {	list.get(i).setFood5(builder.toString());}
				// 1회전 끝남 ... builder => list.get(i).setFood1
			}
			// End of Food1 ~ Food5(MAX)
		}
		
		model.addAttribute("orderLog", list);
		return "orderLog.jsp";
	}
	
	
}
