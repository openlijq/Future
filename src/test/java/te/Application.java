package te;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import util.RealData;  
  
public class Application {  
    public static void main(String[] args) throws Exception {  
        /**
         * FutureTask 方式
         */
    	FutureTask<String> futureTask = new FutureTask<String>(new RealData("na1me"));  
        ExecutorService executor =   
                Executors.newFixedThreadPool(1); //使用线程池  
        //执行FutureTask，相当于上例中的client.request("name")发送请求  
        executor.submit(futureTask);  
        //这里可以用一个sleep代替对其他业务逻辑的处理  
        //在处理这些业务逻辑过程中，RealData也正在创建，从而充分了利用等待时间  
        Thread.sleep(2000);  
        //使用真实数据  
        //如果call()没有执行完成依然会等待  
        System.out.println("数据=" + futureTask.get());
        
        /**
         * Future方式
         */
        @SuppressWarnings("rawtypes")
		Future fu = executor.submit(new RealData("future style"));
        System.out.println("数据=" + fu.get());
        System.exit(0);
    }  
}