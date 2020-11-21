package com.spring.view.regular;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.biz.regular.ConditionDTO;
import com.spring.biz.regular.ConditionListDTO;
import com.spring.biz.regular.OrderDetailDTO;
import com.spring.biz.regular.RegularService;

@RestController
public class RegularController_Client {
	
	@Autowired
	private RegularService regularService;

	//regular
	@RequestMapping("/clientRegularList.do")
	public ConditionListDTO clientRegularList(String user_e_mail) {
//order table에서 특정 유저 이메일을 조건으로 store_name을 distinct한 리스트
		List<ConditionDTO> distinctList = regularService.getStoreList(user_e_mail);
//보낼 리스트
		List<ConditionDTO> sendList = new ArrayList<ConditionDTO>();
//그릇
		ConditionListDTO conditionListDTO = new ConditionListDTO();
		
		for(int i =0; i<distinctList.size(); i++) {
			if(distinctList.get(i).getCondition().equals("미설정")) {
				continue;
			}
			
			if(distinctList.get(i).getCondition().equals("주문횟수")) {
				//여기서 i는 주문한 가게의 인덱스가 된다.
				//num은 특정 유저가 특정 가게에 주문한 횟수가 된다. 인덱스가 증가할 때 마다 초기화
				int num = regularService.getCnt(
						distinctList.get(i).getCondition_day(), distinctList.get(i).getName(), user_e_mail);
				//조건 만족시 sendList에 추가
				if(distinctList.get(i).getCondition_value() <= num) {
					distinctList.get(i).setMyValue(num);					
					sendList.add(distinctList.get(i));					
				}
			}
			if(distinctList.get(i).getCondition().equals("합산금액")) {
				//distinct된 가게 목록의 이름, user_e_mail을 조건으로 다시 DAO로 보내 각각의 주문을 반복문으로 돌려 합산해야됨
				List<OrderDetailDTO> rawList = regularService.getSum(distinctList.get(i).getCondition_day(), distinctList.get(i).getName(), user_e_mail);
				
				int sum = 0;
				for(int j =0; j<rawList.size(); j++) {
				//j는 food 주문인덱스
				//반복을 위한 변수
					List<JSONObject> foodJsonList = new ArrayList<JSONObject>();
					List<String> foodStringArray = new ArrayList<String>();
					
					int food_cnt = 0, food_price = 0;
					ArrayList<Integer> opt_value = null;
					
					// (String)json => List<String> [사이즈만큼]
					foodStringArray.add(rawList.get(j).getFood1());
					if (rawList.get(j).getFood2() != null) {	foodStringArray.add(rawList.get(j).getFood2());}
					if (rawList.get(j).getFood3() != null) {	foodStringArray.add(rawList.get(j).getFood3());}
					if (rawList.get(j).getFood4() != null) {	foodStringArray.add(rawList.get(j).getFood4());}
					if (rawList.get(j).getFood5() != null) {	foodStringArray.add(rawList.get(j).getFood5());}
					
					// List<String> => List<JSONObject>
					for (int k = 0; k < foodStringArray.size(); k++) {
						String jsonString = foodStringArray.get(k);
						foodJsonList.add(new JSONObject(jsonString));
					}
					// Start of Food1 ~ Food5(MAX)
					for (int n = 0; n < foodJsonList.size(); n++) {
						food_cnt = (Integer) foodJsonList.get(n).get("food" + (n + 1) + "_cnt");
						food_price = (Integer) foodJsonList.get(n).get("food" + (n + 1) + "_price");
						sum = (sum+food_price * food_cnt);
						
						opt_value = new ArrayList<Integer>();
						int m = 1;
						while (foodJsonList.get(n).has("opt" + m)) {
							opt_value.add((Integer) foodJsonList.get(n).get("opt" + m + "_value"));
							sum = (sum + opt_value.get(m-1) * food_cnt);
							m++;
						}
						// 1회전 끝남 ... 
					}
					// End of Food1 ~ Food5(MAX)						
				}
				//조건 판별
				if(distinctList.get(i).getCondition_value() <= sum) {
					distinctList.get(i).setMyValue(sum);
					sendList.add(distinctList.get(i));
				}	
			}
		}
		System.out.println(sendList.toString());
		conditionListDTO.setList(sendList);
		return conditionListDTO;
	}
	
	
	//regular
		@RequestMapping("/clientNotRegularList.do")
		public ConditionListDTO clientNotRegularList(String user_e_mail) {
	//order table에서 특정 유저 이메일을 조건으로 store_name을 distinct한 리스트
			List<ConditionDTO> distinctList = regularService.getStoreList(user_e_mail);
	//보낼 리스트
			List<ConditionDTO> sendList = new ArrayList<ConditionDTO>();
	//그릇
			ConditionListDTO conditionListDTO = new ConditionListDTO();
			
			for(int i =0; i<distinctList.size(); i++) {
				if(distinctList.get(i).getCondition().equals("미설정")) {
					continue;
				}
				
				if(distinctList.get(i).getCondition().equals("주문횟수")) {
					//여기서 i는 주문한 가게의 인덱스가 된다.
					//num은 특정 유저가 특정 가게에 주문한 횟수가 된다. 인덱스가 증가할 때 마다 초기화
					int num = regularService.getCnt(
							distinctList.get(i).getCondition_day(), distinctList.get(i).getName(), user_e_mail);
					//조건 만족시 sendList에 추가
					if(distinctList.get(i).getCondition_value() > num && num != 0) {
						distinctList.get(i).setMyValue(num);					
						sendList.add(distinctList.get(i));					
					}
				}
				if(distinctList.get(i).getCondition().equals("합산금액")) {
					//distinct된 가게 목록의 이름, user_e_mail을 조건으로 다시 DAO로 보내 각각의 주문을 반복문으로 돌려 합산해야됨
					List<OrderDetailDTO> rawList = regularService.getSum(distinctList.get(i).getCondition_day(), distinctList.get(i).getName(), user_e_mail);
					
					int sum = 0;
					for(int j =0; j<rawList.size(); j++) {
					//j는 food 주문인덱스
					//반복을 위한 변수
						List<JSONObject> foodJsonList = new ArrayList<JSONObject>();
						List<String> foodStringArray = new ArrayList<String>();
						
						int food_cnt = 0, food_price = 0;
						ArrayList<Integer> opt_value = null;
						
						// (String)json => List<String> [사이즈만큼]
						foodStringArray.add(rawList.get(j).getFood1());
						if (rawList.get(j).getFood2() != null) {	foodStringArray.add(rawList.get(j).getFood2());}
						if (rawList.get(j).getFood3() != null) {	foodStringArray.add(rawList.get(j).getFood3());}
						if (rawList.get(j).getFood4() != null) {	foodStringArray.add(rawList.get(j).getFood4());}
						if (rawList.get(j).getFood5() != null) {	foodStringArray.add(rawList.get(j).getFood5());}
						
						// List<String> => List<JSONObject>
						for (int k = 0; k < foodStringArray.size(); k++) {
							String jsonString = foodStringArray.get(k);
							foodJsonList.add(new JSONObject(jsonString));
						}
						// Start of Food1 ~ Food5(MAX)
						for (int n = 0; n < foodJsonList.size(); n++) {
							food_cnt = (Integer) foodJsonList.get(n).get("food" + (n + 1) + "_cnt");
							food_price = (Integer) foodJsonList.get(n).get("food" + (n + 1) + "_price");
							sum = (sum+food_price * food_cnt);
							
							opt_value = new ArrayList<Integer>();
							int m = 1;
							while (foodJsonList.get(n).has("opt" + m)) {
								opt_value.add((Integer) foodJsonList.get(n).get("opt" + m + "_value"));
								sum = (sum + opt_value.get(m-1) * food_cnt);
								m++;
							}
							// 1회전 끝남 ... 
						}
						// End of Food1 ~ Food5(MAX)						
					}
					//조건 판별
					if(distinctList.get(i).getCondition_value() > sum && sum != 0) {
						distinctList.get(i).setMyValue(sum);
						sendList.add(distinctList.get(i));
					}	
				}
			}
			System.out.println(sendList.toString());
			conditionListDTO.setList(sendList);
			return conditionListDTO;
		}
}
