package com.study.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.spring.entity.User;
import com.study.spring.service.UserService;
import com.study.spring.utils.JacksonUtils;
import com.study.spring.vo.ResultStatusVO;
/**
 * 用户
 * @author xxx
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user.action")
    public void showUser() {
    }
    /**
     * 通過注解直接返回 json 格式字串
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/query.json")
    @ResponseBody
    public List<User> query() {
          List<User> userList = userService.getAllUser();
          System.out.println("size==="+userList.size());
          Map<String, Object> result = new HashMap<String, Object>();
          result.put("total", userList.size());
          result.put("rows", userList);
          return userList;
    }
   
    /**
     * 將數據轉為 Json 字串，頁面直接呈現
     *
     * @param model
     * @throws Exception
     */
    @RequestMapping(path = "/userList.action")
    public void query(Model model) throws Exception {
          List<User> userList = userService.getAllUser();
     
          Map<String, Object> result = new HashMap<String, Object>();
          result.put("total", userList.size());
          result.put("rows", userList);
          System.out.println("result===>"+JacksonUtils.obj2json(result));
          model.addAttribute("result", JacksonUtils.obj2json(result));
    }
    
	@RequestMapping(path = "/userEdit.action")
	public void userEdit(Model model) throws Exception {
	}

    @RequestMapping(value = "/showAllUsers.json")
    @ResponseBody
    public List<User> showAllUsers() {
          List<User> userList = userService.getAllUser();
          System.out.println("size==="+userList.size());
          Map<String, Object> result = new HashMap<String, Object>();
          result.put("total", userList.size());
          result.put("rows", userList);
          return userList;
    }
    
    @RequestMapping(path = "/saveUser.action")
	@ResponseBody
	public ResultStatusVO saveUser(User user) {
		System.out.println("---add---" + user);
		userService.save(user);

		userService.save(user);
		ResultStatusVO vo = new ResultStatusVO();// 用于向前端发送消息
		vo.setMsg("新建用户成功！");
		vo.setSuccess(true);
		return vo;
	}
	
	@RequestMapping(path = "/updateUser.action")
	@ResponseBody
	public ResultStatusVO updateUser(User user) {
		System.out.println("---update--" + user);
		userService.update(user);
		ResultStatusVO vo = new ResultStatusVO();// 用于向前端发送消息
		vo.setMsg("修改用户成功！");
		vo.setSuccess(true);
		return vo;
	}

	@RequestMapping(path = "/updateSaveUser.action")
	@ResponseBody
	// 通过jquery.edatagrid.js 行编辑无法通过 updateUser(User user) 方法更新，
	// updateUrl 对应 action 只能接收基本类型，如 updateSaveUser(String id, String name),
	// 而无法updateUser(User user)
	public ResultStatusVO updateSaveUser(String id, String name, String pwd) {
		System.out.println("---update-user-id---" + id + ", name = " + name + ", pwd = " + pwd);
		User user = userService.find(id);
		System.out.println("---update-user----" + user);
		user.setName(name);
		user.setPwd(pwd);

		userService.update(user);
		ResultStatusVO vo = new ResultStatusVO();// 用于向前端发送消息
		vo.setMsg("修改用户信息成功！");
		vo.setSuccess(true);
		return vo;
	}
	
	@RequestMapping(path = "/delUser.action")
	@ResponseBody
	public ResultStatusVO delUser(User user) {
		System.out.println("---del----"+user);
		userService.deleteUser(user);
		ResultStatusVO vo = new ResultStatusVO();// 用于向前端发送消息
		vo.setMsg("删除用户成功！");
		vo.setSuccess(true);
		return vo;
	}

}