package be.ordina.web;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import be.ordina.domain.Pizza;
import be.ordina.service.BaseService;
import be.ordina.service.PizzaService;
import be.ordina.service.ToppingService;

@RequestMapping("/pizzas")
@Controller(value="PizzaController")
public class PizzaController {
	
	private static Logger logger = Logger.getLogger(PizzaController.class);

	@Autowired
    PizzaService pizzaService;

//	@Autowired
//    BaseService baseService;
//
//	@Autowired
//    ToppingService toppingService;

//	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
//    public String create(@Valid Pizza pizza, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
//        if (bindingResult.hasErrors()) {
//            populateEditForm(uiModel, pizza);
//            return "pizzas/create";
//        }
//        uiModel.asMap().clear();
//        pizzaService.savePizza(pizza);
//        return "redirect:/pizzas/" + encodeUrlPathSegment(pizza.getId().toString(), httpServletRequest);
//    }
//
//	@RequestMapping(params = "form", produces = "text/html")
//    public String createForm(Model uiModel) {
//        populateEditForm(uiModel, new Pizza());
//        return "pizzas/create";
//    }
//
//	@RequestMapping(value = "/{id}", produces = "text/html")
//    public String show(@PathVariable("id") Long id, Model uiModel) {
//        uiModel.addAttribute("pizza", pizzaService.findPizza(id));
//        uiModel.addAttribute("itemId", id);
//        return "pizzas/show";
//    }
//
//	@RequestMapping(produces = "text/html")
//    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
//        if (page != null || size != null) {
//            int sizeNo = size == null ? 10 : size.intValue();
//            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
//            uiModel.addAttribute("pizzas", pizzaService.findPizzaEntries(firstResult, sizeNo));
//            float nrOfPages = (float) pizzaService.countAllPizzas() / sizeNo;
//            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
//        } else {
//            uiModel.addAttribute("pizzas", pizzaService.findAllPizzas());
//        }
//        return "pizzas/list";
//    }
//
//	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
//    public String update(@Valid Pizza pizza, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
//        if (bindingResult.hasErrors()) {
//            populateEditForm(uiModel, pizza);
//            return "pizzas/update";
//        }
//        uiModel.asMap().clear();
//        pizzaService.updatePizza(pizza);
//        return "redirect:/pizzas/" + encodeUrlPathSegment(pizza.getId().toString(), httpServletRequest);
//    }
//
//	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
//    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
//        populateEditForm(uiModel, pizzaService.findPizza(id));
//        return "pizzas/update";
//    }
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
//    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
//        Pizza pizza = pizzaService.findPizza(id);
//        pizzaService.deletePizza(pizza);
//        uiModel.asMap().clear();
//        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
//        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
//        return "redirect:/pizzas";
//    }
//
//	void populateEditForm(Model uiModel, Pizza pizza) {
//        uiModel.addAttribute("pizza", pizza);
//        uiModel.addAttribute("bases", baseService.findAllBases());
//        uiModel.addAttribute("toppings", toppingService.findAllToppings());
//    }
//
//	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
//        String enc = httpServletRequest.getCharacterEncoding();
//        if (enc == null) {
//            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
//        }
//        try {
//            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
//        } catch (UnsupportedEncodingException uee) {}
//        return pathSegment;
//    }
	
	
	
	//add jackson to pom
	@RequestMapping(value="/findPizzas", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Pizza> getPizzaJson(){	
		return pizzaService.findAllPizzas();	
	}
	
	@RequestMapping(value="/findPizza/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Pizza getPizza(@PathVariable("id") String id){	
		return pizzaService.findPizza(Long.valueOf(id));
	}
	
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Pizza> findPizzas(Locale locale, HttpServletRequest request){	
		Locale loc = RequestContextUtils.getLocale(request);
		logger.info("locale1:" + loc.getLanguage());
		logger.info("locale2:" + locale.getLanguage());
		return pizzaService.findAllPizzas();	
	}
	
	@RequestMapping(value="/{Id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Pizza findPizza(@PathVariable(value="Id") String id){	
		return pizzaService.findPizza(Long.valueOf(id));
	}
	
	@RequestMapping (method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String postPizza(@RequestBody Pizza pizza) {
		 pizzaService.savePizza(pizza);
		return "pizza posted";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method=RequestMethod.DELETE, params="id")
	public @ResponseBody void removePizza(@RequestParam(value="id") Long id, @RequestParam(value="test") String t ){
		Pizza pizza = pizzaService.findPizza(id);
		pizzaService.deletePizza(pizza);
	}
	
	
	@RequestMapping(value="/addPizza", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String addPizza(@RequestBody Pizza pizza) {
		 pizzaService.savePizza(pizza);
		return "Mapped by path + method + consumable media type (javaBean '" + pizza + "')";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value="/deletePizza/{id}", method=RequestMethod.DELETE)
	public @ResponseBody void deletePizza(@PathVariable("id") Long id){
		Pizza pizza = pizzaService.findPizza(id);
		pizzaService.deletePizza(pizza);
	}
	
	@Value("${ex.text}")
	private String message; //property
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method=RequestMethod.DELETE)
	public @ResponseBody String deleteAllPizzas(Locale locale) {
		List<Pizza> pizzas = pizzaService.findAllPizzas();
		for (Pizza pizza : pizzas) {
			pizzaService.deletePizza(pizza);
		}
		String msg = messageSource.getMessage("message.text", null, locale);
		return msg;	
	}
	
	@RequestMapping(value="/calc",method=RequestMethod.GET)
	public @ResponseBody BigDecimal calculate() {
		return pizzaService.priceCalculation();
	}
	
//	@ExceptionHandler
//	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//	public ModelAndView handle(Exception e) {
//		MappingJacksonJsonView view = new MappingJacksonJsonView();
//		Map<String, String> map = new HashMap<String, String>();
//		if (e instanceof ConstraintViolationException) {
//			ConstraintViolationException exception = (ConstraintViolationException) e;
//			Set<ConstraintViolation<?>> constraints = exception.getConstraintViolations();
//			for (ConstraintViolation<?> constraintViolation : constraints) {
//				String msg = constraintViolation.getPropertyPath().toString()
//						+ " " +  constraintViolation.getMessage();
//				map.put(constraintViolation.getRootBeanClass().getSimpleName(), msg);
//			}
//			
//		} if (e instanceof AccessDeniedException) {
//			map.put("access", "access is denied");
//		}
//		else{
//			map.put(e.getCause().toString(), e.getCause().getMessage());
//		}
//		return new ModelAndView(view, map);
//	}

}
