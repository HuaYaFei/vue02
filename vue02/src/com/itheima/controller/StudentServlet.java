package com.itheima.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.itheima.bean.Student;
import com.itheima.service.StudentService;

import com.itheima.service.impl.StudentServiceimpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {
    StudentServiceimpl studentServiceimpl = new StudentServiceimpl();

    /**
     * 判断用户信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符编码
        System.out.println("加载了");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        //获取用户请求
        String method = req.getParameter("method");
        System.out.println(method);
        if (method.equals("stuPage")) {
            //学生分页
            stuPage(req, resp);
        } else if (method.equals("stuAdd")) {
            //添加学生
            stuAdd(req, resp);
        } else if (method.equals("stuUpdate")) {
            stuUpdate(req, resp);
        } else if (method.equals("studele")) {
            stuDele(req, resp);
        }
    }

    /**
     * 删除学生信息
     *
     * @param req
     * @param resp
     */
    private void stuDele(HttpServletRequest req, HttpServletResponse resp) {
        //获取删除id
        String number = req.getParameter("number");
        //获取当前页
        String page = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");
        //删除
        studentServiceimpl.stuDele(number);
        //请求转发
        try {
            resp.sendRedirect(req.getContextPath() + "studentServlet?method=stuPage&page=" + page + "&pageSize=" + pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stuUpdate(HttpServletRequest req, HttpServletResponse resp) {
        //获取学生信息
        Map<String, String[]> parameterMap = req.getParameterMap();
        //获取列值
        String page = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");
        //创建学生对象
        Student stu = new Student();
        //日期转换
        dateConvert();
        //装载对象
        try {
            BeanUtils.populate(stu, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //修改学生数据
        studentServiceimpl.stuUpdate(stu);

        //请求转发
        try {
            resp.sendRedirect(req.getContextPath() + "studentServlet?method=stuPage&page=" + page + "&pageSize=" + pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 学生添加
     *
     * @param req
     * @param resp
     */
    private void stuAdd(HttpServletRequest req, HttpServletResponse resp) {
        //接收用户数据
        Map<String, String[]> parameterMap = req.getParameterMap();
        //获取页值
        String page = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");

        //创建学生对象
        Student stu = new Student();
        //日期转换
        dateConvert();
        try {
            //封装数据
            BeanUtils.populate(stu, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //添加学生
        studentServiceimpl.stuAdd(stu);
        //重定向
        try {
            resp.sendRedirect(req.getContextPath() + "/studentServlet?method=stuPage&page=" + page + "&pageSize=" + pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 学生分页
     *
     * @param req
     * @param resp
     */
    private void stuPage(HttpServletRequest req, HttpServletResponse resp) {
        //获取请求参数
        String page = req.getParameter("page");
        String pageSize = req.getParameter("pageSize");

        //调用分页查询方法
        Page page1 = studentServiceimpl.stuPage(Integer.parseInt(page), Integer.parseInt(pageSize));
        //封装pageinfo
        PageInfo pageInfo = new PageInfo(page1);


        try {
            //转json
            String json = new ObjectMapper().writeValueAsString(pageInfo);
            //放回数据
            resp.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void dateConvert() {
        ConvertUtils.register(new Converter() {
            @Override
            public Object convert(Class type, Object value) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return simpleDateFormat.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }, Date.class);
    }
}
