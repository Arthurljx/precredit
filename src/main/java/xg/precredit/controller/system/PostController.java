package xg.precredit.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xg.framework.querychannel.support.GridData;
import xg.framework.querychannel.support.PagingParam;
import xg.framework.web.controller.BaseController;
import xg.framework.web.support.JsonFormResult;
import xg.precredit.domain.system.Postinfo;
import xg.precredit.service.system.PostService;

/**
 * @Description
 * @author lujx
 * @date   2014-4-11
 * @change
 */
@Controller
@RequestMapping("/postController")
public class PostController extends BaseController{
	@Autowired
	private PostService postService;
	
	
	@RequestMapping("/index")
	public String index() {
		return "system/post/post_index";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public GridData list(PagingParam param) {
		param.setEntityClass(Postinfo.class);
		GridData data = getQueryService().queryByParam(param);		
		return data;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public JsonFormResult<String> save(Postinfo o) {
		JsonFormResult<String> ret = new JsonFormResult<String>();
		if (!o.existed()) {
			postService.save(o);
		} else {
			ret.setSuccess(false);
			ret.setErrorMessage("对象已存在！");
		}
		return ret;
	}
}
