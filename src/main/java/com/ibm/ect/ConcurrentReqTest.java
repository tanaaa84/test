package com.ibm.ect;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 多任务并行验证
 * 
 * @author tanyanbing
 *
 */
public class ConcurrentReqTest {

	public static void main(String[] args) {
		Long l1 = System.currentTimeMillis();

		ExecutorService executor = Executors.newCachedThreadPool();
		Future<Integer> result1 = (Future<Integer>) executor.submit(new Task1(
				1024));
		Future<Integer> result2 = (Future<Integer>) executor
				.submit(new Task2());
		Future<Integer> result3 = (Future<Integer>) executor
				.submit(new Task3());
		executor.shutdown();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("主线程在执行任务");

		try {
			System.out.println("task1运行结果" + result1.get());
			System.out.println("task2运行结果" + result2.get());
			System.out.println("task3运行结果" + result3.get());
			System.out.println("运行结果"
					+ (result1.get() + result2.get() + result3.get()));

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("所有任务执行完毕");
		Long l2 = System.currentTimeMillis();
		System.out.println("CONTENT TIME:" + (l2 - l1));
	}
}

class Task1 implements Callable<Integer> {
	int id = 0;

	Task1(int id) {
		this.id = id;
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("子线程1在进行计算");
		Thread.sleep(15000);
		int sum = 0;
		for (int i = 0; i < 100; i++)
			sum += i;
		return sum + id;
	}
}

class Task2 implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		System.out.println("子线程2在进行计算");
		Thread.sleep(8000);
		int sum = 0;
		for (int i = 0; i < 100; i++)
			sum += i;
		return sum;
	}
}

class Task3 implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		System.out.println("子线程3在进行计算");
		Thread.sleep(8000);
		int sum = 0;
		for (int i = 0; i < 100; i++)
			sum += i;
		return sum;
	}
}
