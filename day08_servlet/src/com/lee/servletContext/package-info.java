/**
 * ServletContext学习
 * Demo1和Demo2演示了Attribute的读写及Attribute的作用域：多个Servlet共用
 * Demo3演示了通过Context对象读取InitParameters(写死在web.xml web-app元素下，全局，只读)
 * Demo4演示了Context对象获取项目下下任意文件的真实路径的方法getReadPath(vPath)
 * Demo5和Demo6演示了RequestDIspatcher实现两个Servlet之间的转发功能
 */
/**
 * @author Lee
 *
 */
package com.lee.servletContext;