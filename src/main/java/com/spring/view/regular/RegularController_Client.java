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
//order table���� Ư�� ���� �̸����� �������� store_name�� distinct�� ����Ʈ
		List<ConditionDTO> distinctList = regularService.getStoreList(user_e_mail);
//���� ����Ʈ
		List<ConditionDTO> sendList = new ArrayList<ConditionDTO>();
//�׸�
		ConditionListDTO conditionListDTO = new ConditionListDTO();
		
		for(int i =0; i<distinctList.size(); i++) {
			if(distinctList.get(i).getCondition().equals("�̼���")) {
				continue;
			}
			
			if(distinctList.get(i).getCondition().equals("�ֹ�Ƚ��")) {
				//���⼭ i�� �ֹ��� ������ �ε����� �ȴ�.
				//num�� Ư�� ������ Ư�� ���Կ� �ֹ��� Ƚ���� �ȴ�. �ε����� ������ �� ���� �ʱ�ȭ
				int num = regularService.getCnt(
						distinctList.get(i).getCondition_day(), distinctList.get(i).getName(), user_e_mail);
				//���� ������ sendList�� �߰�
				if(distinctList.get(i).getCondition_value() <= num) {
					distinctList.get(i).setMyValue(num);					
					sendList.add(distinctList.get(i));					
				}
			}
			if(distinctList.get(i).getCondition().equals("�ջ�ݾ�")) {
				//distinct�� ���� ����� �̸�, user_e_mail�� �������� �ٽ� DAO�� ���� ������ �ֹ��� �ݺ������� ���� �ջ��ؾߵ�
				List<OrderDetailDTO> rawList = regularService.getSum(distinctList.get(i).getCondition_day(), distinctList.get(i).getName(), user_e_mail);
				
				int sum = 0;
				for(int j =0; j<rawList.size(); j++) {
				//j�� food �ֹ��ε���
				//�ݺ��� ���� ����
					List<JSONObject> foodJsonList = new ArrayList<JSONObject>();
					List<String> foodStringArray = new ArrayList<String>();
					
					int food_cnt = 0, food_price = 0;
					ArrayList<Integer> opt_value = null;
					
					// (String)json => List<String> [�����ŭ]
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
						// 1ȸ�� ���� ... 
					}
					// End of Food1 ~ Food5(MAX)						
				}
				//���� �Ǻ�
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
	//order table���� Ư�� ���� �̸����� �������� store_name�� distinct�� ����Ʈ
			List<ConditionDTO> distinctList = regularService.getStoreList(user_e_mail);
	//���� ����Ʈ
			List<ConditionDTO> sendList = new ArrayList<ConditionDTO>();
	//�׸�
			ConditionListDTO conditionListDTO = new ConditionListDTO();
			
			for(int i =0; i<distinctList.size(); i++) {
				if(distinctList.get(i).getCondition().equals("�̼���")) {
					continue;
				}
				
				if(distinctList.get(i).getCondition().equals("�ֹ�Ƚ��")) {
					//���⼭ i�� �ֹ��� ������ �ε����� �ȴ�.
					//num�� Ư�� ������ Ư�� ���Կ� �ֹ��� Ƚ���� �ȴ�. �ε����� ������ �� ���� �ʱ�ȭ
					int num = regularService.getCnt(
							distinctList.get(i).getCondition_day(), distinctList.get(i).getName(), user_e_mail);
					//���� ������ sendList�� �߰�
					if(distinctList.get(i).getCondition_value() > num && num != 0) {
						distinctList.get(i).setMyValue(num);					
						sendList.add(distinctList.get(i));					
					}
				}
				if(distinctList.get(i).getCondition().equals("�ջ�ݾ�")) {
					//distinct�� ���� ����� �̸�, user_e_mail�� �������� �ٽ� DAO�� ���� ������ �ֹ��� �ݺ������� ���� �ջ��ؾߵ�
					List<OrderDetailDTO> rawList = regularService.getSum(distinctList.get(i).getCondition_day(), distinctList.get(i).getName(), user_e_mail);
					
					int sum = 0;
					for(int j =0; j<rawList.size(); j++) {
					//j�� food �ֹ��ε���
					//�ݺ��� ���� ����
						List<JSONObject> foodJsonList = new ArrayList<JSONObject>();
						List<String> foodStringArray = new ArrayList<String>();
						
						int food_cnt = 0, food_price = 0;
						ArrayList<Integer> opt_value = null;
						
						// (String)json => List<String> [�����ŭ]
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
							// 1ȸ�� ���� ... 
						}
						// End of Food1 ~ Food5(MAX)						
					}
					//���� �Ǻ�
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
