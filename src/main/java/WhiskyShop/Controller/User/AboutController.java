package WhiskyShop.Controller.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController extends BaseController{
	
	@GetMapping("/about")
	public ModelAndView about()
	{
		
		_mvShare.setViewName("user/about");
		return _mvShare;
	}
	
}
