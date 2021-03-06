package WhiskyShop.Controller.Admin;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import WhiskyShop.Entity.Users;
import WhiskyShop.Service.Admin.UsersServiceImpl;

@Controller
@RequestMapping(value = "/admin/")
public class UsersController extends BaseAdminController {

	@Autowired
	UsersServiceImpl usersService;

	@ModelAttribute("enable")
	public Map<String, String> listEnable() {
		Map<String, String> enbale = new HashMap<String, String>();
		enbale.put("0", "0");
		enbale.put("1", "1");
		return enbale;
	}

	@GetMapping("Users")
	public ModelAndView User(@RequestParam(value = "status", required = false) String status, ModelMap model) {
		model.addAttribute("status", status);
		_mvShare.addObject("listUsers", usersService.ListUsers());
		_mvShare.setViewName("admin/user/listUser");
		return _mvShare;
	}
	
	
	

	@GetMapping("addUsers")
	public ModelAndView ViewAddUser() {
		_mvShare.addObject("user", new Users());
		_mvShare.setViewName("admin/user/addUser");
		return _mvShare;
	}

	@PostMapping("addUsers")
	public String CreateUser(@ModelAttribute("user") @Valid Users user, BindingResult rs, ModelMap model,
			@RequestParam String nhaplaimatkhau) {
		if (rs.hasErrors() || nhaplaimatkhau.isEmpty()) {
			model.addAttribute("menus", dashboardService.getMenusDashboard());
			model.addAttribute("errorMK", "Kh??ng ???????c b??? tr???ng");
			// rs.rejectValue("first_name", "user","Vui l??ng nh???p h??? t??n");
		} else {
			// N???u kh b??? tr???ng th?? m??nh ti???p t???c x??t t??nh h???p l???
			// ?????u ti??n l?? email
			model.addAttribute("menus", dashboardService.getMenusDashboard());
			boolean checkEmail = validate(user.getEmail());
			if (checkEmail == true) {
				if (user.getPassword().equals(nhaplaimatkhau)) {
					int count = usersService.createAccount(user);
					if (count > 0) {
						model.addAttribute("listUsers", usersService.ListUsers());
						model.addAttribute("status", "????ng k?? t??i kho???n th??nh c??ng");
						// return "admin/user/listUser";
						return "redirect:Users";
					} else {

						model.addAttribute("status", "????ng k?? t??i kho???n th???t b???i");
					}
				} else {

					model.addAttribute("status", "M???t kh???u kh??ng tr??ng kh???p");
				}

			} else {

				model.addAttribute("status", "Email kh??ng h???p l???");
			}

		}

		// "admin/user/addUser"
		// redirect:user/addUser
		return "admin/user/addUser";
	}

	@GetMapping("editUser")
	public String Get_EditUser(@RequestParam("id") int id, @ModelAttribute("user") Users user, ModelMap model) {
		user = usersService.FindUserByID(id);
		model.addAttribute("menus", dashboardService.getMenusDashboard());

		model.addAttribute("user", user);
		return "admin/user/editUser";
	}

	@PostMapping("editUser")
	public String Post_EditUser(@RequestParam("id") int id, @ModelAttribute("user") @Valid Users user, BindingResult rs,
			ModelMap model, HttpServletRequest request) {
		if (rs.hasErrors()) {
			model.addAttribute("menus", dashboardService.getMenusDashboard());

		} else {
			// N???u kh b??? tr???ng th?? m??nh ti???p t???c x??t t??nh h???p l???
			// ?????u ti??n l?? email
			model.addAttribute("menus", dashboardService.getMenusDashboard());
			boolean checkEmail = validate(user.getEmail());
			if (checkEmail == true) {
				int count = usersService.UpdateUserByID(id, user);
				if (count > 0) {
					model.addAttribute("listUsers", usersService.ListUsers());
					model.addAttribute("status", "C???p nh???p t??i kho???n th??nh c??ng");
					return "redirect:Users";
				} else {
					model.addAttribute("status", "C???p nh???p t??i kho???n th???t b???i");
				}
			} else {

				model.addAttribute("status", "Email kh??ng h???p l???");
			}

		}
		return "admin/user/editUser";
		// return "redirect:" + request.getHeader("Referer");
	}

	// X??a th??ng qua url
	@RequestMapping("deleteUser")
	public String DeleteUser(@RequestParam("id") int id, ModelMap model, HttpServletRequest request) {
		int delete = usersService.DeleteUserByID(id);
		if (delete > 0) {
			model.addAttribute("status", "X??a th??nh c??ng");

		} else {
			model.addAttribute("status", "X??a th???t b???i");

		}

		// _mvShare.addObject("listUsers", usersService.ListUsers());
		// _mvShare.setViewName("admin/user/listUser");
		// _mvShare.setViewName("redirect:" + request.getHeader("Referer"));
		// return _mvShare;

		// String
		return "redirect:/admin/Users"; // => /admin/Users?status=X??a+th??nh+c??ng
		// return "admin/user/listUser"; // => admin/deleteUser?id=45
	}

	@RequestMapping("/blockuser/{id}")
	public ModelAndView BlockUser(@PathVariable("id") int id, ModelMap model) {
		int blockuser = usersService.BlockUser(id);
		if (blockuser > 0) {
			_mvShare.setViewName("redirect:/admin/Users");
		} else {
			model.addAttribute("status", "Block th???t b???i");
			_mvShare.setViewName("redirect:/admin/Users");
		}
		return _mvShare;

	}

	@RequestMapping("/unblockuser/{id}")
	public ModelAndView unBlockUser(@PathVariable("id") int id, ModelMap model) {
		int unblockuser = usersService.UnBlockUser(id);
		if (unblockuser > 0) {
		
			_mvShare.setViewName("redirect:/admin/Users");
		} else {
			model.addAttribute("status", "UnBlock th???t b???i");
			_mvShare.setViewName("redirect:/admin/Users");
		}
		return _mvShare;

	}
	
	@PostMapping(value="Users", params = "btnUnBlockUser")
	public ModelAndView UnBlockUserUsingForm(@RequestParam("id") int id,ModelMap model) {
		int unblockuser = usersService.UnBlockUser(id);
		if(unblockuser > 0)
		{
			_mvShare.setViewName("redirect:/admin/Users");
		}else {
			model.addAttribute("status", "UnBlock th???t b???i");
			_mvShare.setViewName("redirect:/admin/Users");
		}
		return _mvShare;
	}
	
	@PostMapping(value="Users", params = "btnBlockUser")
	public ModelAndView BlockUserUsingForm(@RequestParam("id") int id,ModelMap model) {
		int blockuser = usersService.BlockUser(id);
		if(blockuser > 0)
		{
			_mvShare.setViewName("redirect:/admin/Users");
		}
		else {
			model.addAttribute("status", "Block th???t b???i");
			_mvShare.setViewName("redirect:/admin/Users");
		}
		return _mvShare;
	}
	
	
	

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}

}
