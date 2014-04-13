package xg.precredit.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xg.framework.web.controller.BaseController;
import xg.framework.web.support.JsonFormResult;
import xg.precredit.domain.system.Orginfo;
import xg.precredit.service.system.OrgService;

/**
 * @Description
 * @author lujx
 * @date   2014-4-11
 * @change
 */
@Controller
@RequestMapping("/orgController")
public class OrgController extends BaseController{
	@Autowired
	private OrgService orgService;
	

	@RequestMapping("/index")
	public String index() {
		return "org_index";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public JsonFormResult<String> save(Orginfo o) {
		JsonFormResult<String> ret = new JsonFormResult<String>();
		if (!o.existed()) {
			orgService.save(o);
		} else {
			ret.setSuccess(false);
			ret.setErrorMessage("对象已存在！");
		}
		return ret;
	}
}
