package com.me.hibernate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.me.dao.UserDAO;
import com.me.pojo.User;

@Controller
public class UserController {
	
	@RequestMapping(value = "/user.htm", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, UserDAO userDao) {
		
		String page = request.getParameter("sub");
		if(page.equals("Login")){
			return new ModelAndView("login");
		}else if(page.equals("Register")){
			return new ModelAndView("register");
		}else if(page.contentEquals("reg")){
			if(request.getParameter("FirstName").equals("") || request.getParameter("LastName").equals("") || request.getParameter("Email").equals("") || request.getParameter("Phone").equals("") || request.getParameter("Password").equals("")) {
				System.out.println("All fields are mandatory.");
				return new ModelAndView("register");
			}else {
				User user = userDao.getByEmail(request.getParameter("Email"));
				if(user != null) {
					System.out.println("Email already exist.");
					return new ModelAndView("register");
				}else {
					User newUser = new User();
					newUser.setFirstName(request.getParameter("FirstName"));
					newUser.setLastName(request.getParameter("LastName"));
					newUser.setEmail(request.getParameter("Email"));
					newUser.setPhone((Integer.parseInt(request.getParameter("Phone"))));
					newUser.setPassword(request.getParameter("Password"));
					newUser.setRole("Patient");
					userDao.save(newUser);
					System.out.println("User registered successfully.");
					return new ModelAndView("home");
				}
			}
		}else {
			return new ModelAndView("home");
		}
	}
	
}
