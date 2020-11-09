package org.example;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
  public void rateLimiter(){
      RateLimiter rateLimiter = RateLimiter.create(10.0);
      for (int i = 0; i < 1000; i++) {
          rateLimiter.acquire(); //获得许可
          System.out.println("call: " + i);
      }

  }
  public void rateLimiter01(){
      RateLimiter rateLimiter = RateLimiter.create(100.0,5000L, TimeUnit.DAYS);
      for (int i = 0; i < 1000; i++) {
//          System.out.println(rateLimiter.getRate());
          rateLimiter.acquire(); //获得许可
          System.out.println("call: " + i);
      }

  }
}
