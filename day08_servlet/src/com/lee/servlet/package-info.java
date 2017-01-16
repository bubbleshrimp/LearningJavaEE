/**
 * Servlet类学习
 * Demo1, Demo2, Demo3分别演示Servlet应用实现的三种方式。
 * Demo4演示了多线程调用service()方法时可能存在的问题，以及一种不推荐的
 * 处理方式——将Servlet实现为单线程实例。推荐的做法是不要用全局变量，而用局部变量。
 * Demo5演示了response发送的时间为service()方法结束之后
/**
 * @author Lee
 *
 */
package com.lee.servlet;