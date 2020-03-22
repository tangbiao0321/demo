package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.example.demo.aspect.Config;
import com.example.demo.entity.FreezOptDTO;
import com.example.demo.entity.Users;
import com.example.demo.service.UserService;
import com.googlecode.aviator.AviatorEvaluator;
import org.assertj.core.util.Arrays;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

@SpringBootTest
class DemoApplicationTests {

	@Resource
	private UserService userService;
	@Resource
	private Config config;
	@Resource
	private ApplicationContext applicationContext;

	@Test
	void contextLoads() throws ParseException, InterruptedException, ClassNotFoundException {
//		test();
		recall();

//		Thread.sleep(Long.MAX_VALUE);

		Users users = new Users();
		users.setAge(10);
		users.setName("丽丽");
		users.setSex("女");
		Users users1 = new Users();
		users1.setAge(10);
		users1.setName("丽丽");
		users1.setSex("女1");
		Users users2 = new Users();
		users2.setAge(10);
		users2.setName("丽丽");
		users2.setSex("女2");
		String str = JSONObject.toJSONString( Lists.list(users, users1, users2));
		List<Users> usersList = JSONObject.parseArray(str, Users.class);

		List<Users> usersList2 = JSONObject.parseObject(str, List.class);
		System.out.println(JSONObject.toJSONString(usersList));
		System.out.println(JSONObject.toJSONString(usersList2));
	}

	void test() throws ParseException {
System.out.println("========================");
		Users users = new Users();
		users.setAge(10);
		users.setName("丽丽");
		users.setSex("女");
		Users users1 = new Users();
		users1.setAge(10);
		users1.setName("丽丽");
		users1.setSex("女1");
		Users users2 = new Users();
		users2.setAge(10);
		users2.setName("丽丽");
		users2.setSex("女2");
//		userService.createUser(users,10, Lists.list(users1, users2));
//		userService.createUser(users);
		userService.createUser();

//		Config.recall.set(true);
//		Config.recall12.set(1);
//
//		System.out.println(Config.recall.get());
//		System.out.println(Config.recall12.get());
//		new Thread(()->{
//			System.out.println(Config.recall.get());
//			System.out.println(Config.recall12.get());
//		}).start();
//
//		long intervalTime = System.currentTimeMillis()-new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-02-20 16:40:00").getTime();
//		System.out.println(intervalTime);
//
//
////		ThreadPoolExecutor
//		ExecutorService executorService = Executors.newFixedThreadPool(2);
//		executorService.submit(()->{
//			System.out.println("SET::::"+"\t"+Thread.currentThread().getId());
//			Config.recall12.set(10);
//			config.setRecall22(20);
//		});
//
//		Stream.iterate(1, item->item+1).limit(10).forEach(integer -> {
//			executorService.execute(()->{
//				System.out.println("GET::12::"+Config.recall12.get()+"\t"+Thread.currentThread().getId());
//				System.out.println("GET::::"+config.getRecall22()+"\t"+Thread.currentThread().getId());
//				Config.recall12.set(null);
//				config.setRecall22(0);
//			});
//		});

	}

	void recall() throws ClassNotFoundException {
		System.out.println("==========recall==============");

		Config.recall.set(true);
		String beanName = "userService";
		String methodStr = "createUser";
//		String args = "[{\"age\":10,\"name\":\"丽丽\",\"sex\":\"女\"},10,[{\"age\":10,\"name\":\"丽丽\",\"sex\":\"女1\"},{\"age\":10,\"name\":\"丽丽\",\"sex\":\"女2\"}]]";
		String args ="[]";
		List<Object> dataList = JSONObject.parseArray(args, Object.class);

		/** find Bean **/
		Object object = applicationContext.getBean(beanName);
		try {
			/** find Method **/
			Method[] methodArrs = object.getClass().getMethods();
			Method findMethod = java.util.Arrays.stream(methodArrs).filter(e->e.getName().equals(methodStr) && e.getParameterCount()==dataList.size()).findFirst().get();
			System.out.println("findMehtod=========");
			java.util.Arrays.stream(findMethod.getParameterTypes()).forEach(e->System.out.println(e.getName()));

			//method args Class[]
			Class[] classArrs = findMethod.getParameterTypes();//入参obj[]
			System.out.println(findMethod.getName());

			Object[] inputArrs = new Object[classArrs.length];

			for(int i=0;i<classArrs.length;i++) {
				inputArrs[i] = JSONObject.parseObject(JSONObject.toJSONString(dataList.get(i)), classArrs[i]);
			}
			findMethod.invoke(object, inputArrs);
		} catch (Exception e) {
			System.out.println("异常。。");
			e.printStackTrace();
		} finally {
			Config.recall.remove();
		}

	}

	@Test
	public void freeze(){
		FreezOptDTO freezOptDTO = new FreezOptDTO();
		freezOptDTO.setUserNo("U123");
		freezOptDTO.setRuleCode("bigCreditCnt");
		freezOptDTO.setRuleType("freezeTypeRules");

		FreezOptDTO freezOptDTO1 = new FreezOptDTO();
		freezOptDTO1.setUserNo("U123");
		freezOptDTO1.setRuleCode("accountRemain");
		freezOptDTO1.setRuleType("freezeTypeRules");
		Map<String, Object> envMap = new HashMap<>();
		envMap.put("bigCreditCnt", freezOptDTO);
		envMap.put("accountRemain", freezOptDTO1);
		Object result= AviatorEvaluator.execute("freeze(accountRemain,'<','5')&&freeze(bigCreditCnt,'<','5')", envMap, true);
		System.out.println(result);
	}

}
