/**
 * Project Name:MyUtil <br>
 * File Name:Streams.java <br>
 * Package Name:com.xifeng.util.stream <br>
 * @author xiezbmf
 * Date:2018年5月28日上午9:51:13 <br>
 * Copyright (c) 2018, 深圳市彩付宝科技有限公司 All Rights Reserved.
 */

package com.xifeng.util.stream;

import java.util.Arrays;
import java.util.Collection;

import javax.net.ssl.SSLEngineResult.Status;

/**
 * ClassName: Streams <br>
 * Description: TODO
 * @author xiezbmf
 * @Date 2018年5月28日上午9:51:13 <br>
 * @version
 * @since JDK 1.8
 */
public class Streams {
	private enum Status {
        OPEN, CLOSED
    };
     
    private static final class Task {
        private final Status status;
        private final Integer points;
 
        Task( final Status status, final Integer points ) {
            this.status = status;
            this.points = points;
        }
         
        public Integer getPoints() {
            return points;
        }
         
        public Status getStatus() {
            return status;
        }
         
        @Override
        public String toString() {
            return String.format( "[%s, %d]", status, points );
        }
    }
    public static void main(String[] args) {
    	final Collection< Task > tasks = Arrays.asList(
    		    new Task( Status.OPEN, 5 ),
    		    new Task( Status.OPEN, 13 ),
    		    new Task( Status.CLOSED, 8 ) 
    		);
    	final long totalPointsOfOpenTasks = tasks
    		    .stream()
    		    .filter( task -> task.getStatus() == Status.OPEN )
    		    .mapToInt( Task::getPoints )
    		    .sum();
    		         
    		System.out.println( "Total points: " + totalPointsOfOpenTasks );
	}
}


	