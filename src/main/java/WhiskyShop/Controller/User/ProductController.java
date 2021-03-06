package WhiskyShop.Controller.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import WhiskyShop.Dto.PaginateDto;
import WhiskyShop.Service.User.BrandsServiceImpl;
import WhiskyShop.Service.User.PaginateServiceImpl;
import WhiskyShop.Service.User.ProductServiceImpl;




@Controller
public class ProductController extends BaseController {

	@Autowired
	private ProductServiceImpl productsService;

	@Autowired
	private PaginateServiceImpl paginateService; 
	
	@Autowired
	private BrandsServiceImpl brandsService;

	final private int totalProducts_PerPage = 12;
	
	@RequestMapping(value = { "/san-pham" })
	public ModelAndView Index(@RequestParam(value = "search",required = false ) String search) {
		int totalProduct = 0;
		_mvShare.addObject("categorys", _homeService.getDataCategory());
		_mvShare.addObject("brands",brandsService.getAllDataBrands());
		
		if(search != null)
		{
			 totalProduct = productsService.SearchProductByName(search).size();
			 PaginateDto paginateInfo = paginateService.GetInfoPaginate(totalProduct, totalProducts_PerPage, 1);
			 _mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("products", productsService.GetDataProductsPaginateBySeachName(search, paginateInfo.getStart() - 1, paginateInfo.getEnd()));
			
			
		}else {
			 totalProduct = _homeService.getDataProducts().size();
			PaginateDto paginateInfo = paginateService.GetInfoPaginate(totalProduct, totalProducts_PerPage, 1);
			_mvShare.addObject("paginateInfo", paginateInfo);
			_mvShare.addObject("products", productsService.GetAllDataPaginate(paginateInfo.getStart() - 1, paginateInfo.getEnd()));
		}
		
		_mvShare.setViewName("user/products/product");
		return _mvShare;
	}
	

	
	

	//T???t c??? s???n ph???m c?? ph??n trang
	@RequestMapping(value = { "/san-pham/{currentPage}" })
	public ModelAndView Index123(@PathVariable int currentPage) {
		_mvShare.addObject("categorys", _homeService.getDataCategory());
		
		int totalProduct = _homeService.getDataProducts().size();
		PaginateDto paginateInfo = paginateService.GetInfoPaginate(totalProduct, totalProducts_PerPage, currentPage);
		_mvShare.addObject("paginateInfo", paginateInfo);
		_mvShare.addObject("products", productsService.GetAllDataPaginate(paginateInfo.getStart() - 1, paginateInfo.getEnd()));
		_mvShare.setViewName("user/products/product");
		return _mvShare;
	}
	
	
	////////////////////////////////////////////////////////////////////////
	
	//T???t c??? s???n ph???m theo lo???i c?? ph??n trang
	@RequestMapping(value = { "/san-pham/category/{id}" })
	public ModelAndView ProductsByCategoryFirstPage(@PathVariable int id) {
		
		_mvShare.addObject("brands",brandsService.getAllDataBrands());
		_mvShare.addObject("categorys", _homeService.getDataCategory());

		int totalProduct = productsService.GetDataProductsByID(id).size();
		PaginateDto paginateInfo = paginateService.GetInfoPaginate(totalProduct, totalProducts_PerPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);

		_mvShare.addObject("productsPaginate",
				productsService.GetDataProductsPaginateByID(id, paginateInfo.getStart() - 1, paginateInfo.getEnd()));
		_mvShare.setViewName("user/products/productbyID");
		return _mvShare;

	}

	@RequestMapping(value = { "/san-pham/category/{id}/{currentPage}" })
	public ModelAndView ProductsByCategoryAnotherPage(@PathVariable int id, @PathVariable int currentPage) {
		
		_mvShare.addObject("categorys", _homeService.getDataCategory());
		_mvShare.addObject("brands",brandsService.getAllDataBrands());
		int totalProduct = productsService.GetDataProductsByID(id).size();
		PaginateDto paginateInfo = paginateService.GetInfoPaginate(totalProduct, totalProducts_PerPage, currentPage);
		_mvShare.addObject("paginateInfo", paginateInfo);

		_mvShare.addObject("productsPaginate",
				productsService.GetDataProductsPaginateByID(id, paginateInfo.getStart() - 1, paginateInfo.getEnd()));

		_mvShare.setViewName("user/products/productbyID");
		return _mvShare;

	}

	
	////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = { "/san-pham/brands/{id}" })
	public ModelAndView ProductsByBrandFirstPage(@PathVariable int id) {
		_mvShare.addObject("categorys", _homeService.getDataCategory());
		_mvShare.addObject("brands",brandsService.getAllDataBrands());
		int totalProduct = productsService.GetDataProductsByBrand(id).size();
		
		PaginateDto paginateInfo = paginateService.GetInfoPaginate(totalProduct, totalProducts_PerPage, 1);
		_mvShare.addObject("paginateInfo", paginateInfo);
		
		_mvShare.addObject("productsPaginate",
				productsService.GetDataProductsPaginateByBrand(id, paginateInfo.getStart() - 1, paginateInfo.getEnd()));
		
		
		_mvShare.setViewName("user/products/productbyBrand");
		return _mvShare;
	}
	
	
	@RequestMapping(value = { "/san-pham/brands/{id}/{currentPage}" })
	public ModelAndView ProductsByBrandFirstPage(@PathVariable int id,@PathVariable int currentPage) {
		_mvShare.addObject("categorys", _homeService.getDataCategory());
		_mvShare.addObject("brands",brandsService.getAllDataBrands());
		int totalProduct = productsService.GetDataProductsByBrand(id).size();
		
		PaginateDto paginateInfo = paginateService.GetInfoPaginate(totalProduct, totalProducts_PerPage, currentPage);
		_mvShare.addObject("paginateInfo", paginateInfo);
		
		_mvShare.addObject("productsPaginate",
				productsService.GetDataProductsPaginateByBrand(id, paginateInfo.getStart() - 1, paginateInfo.getEnd()));
		
		
		_mvShare.setViewName("user/products/productbyBrand");
		return _mvShare;
	}
	//////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = { "/detail/{id}" })
	public ModelAndView DetailProduct(@PathVariable int id) {
		_mvShare.addObject("categorys", _homeService.getDataCategory());
		_mvShare.addObject("brands",brandsService.getAllDataBrands());
		
		//L???y c??c s???n ph???m theo id s???n ph???m
		_mvShare.addObject("detail",productsService.GetDataDeatilProduct(id));
		//L???y c??c t???t c??? s???n ph???m theo id lo???i sp c???a s???n ph???m => xu???t nh???ng sp c??ng lo???i suggest ng d??ng		
		//V?? m??nh tr??? v??? l?? 1 list, nh??ng list n??y ch??? c?? 1 th??i v?? l?? deatil > m??nh l???y c??i ?????u ti??n > get id 
		int idCate = productsService.GetDataDeatilProduct(id).get(0).getIdCate();
		_mvShare.addObject("productsByCate",productsService.GetDataProductsByID(idCate));
		
		
		_mvShare.setViewName("user/products/detailProduct");
		return _mvShare;
		
	}
	
}
