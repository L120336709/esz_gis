package com.sundata.edu.controller.h5;

import com.sundata.common.base.Response;
import com.sundata.edu.domain.Schedules;
import com.sundata.edu.domain.Userinfo;
import com.sundata.edu.framework.web.controller.BaseController;
import com.sundata.edu.service.SchedulesService;
import com.sundata.edu.session.RpcLoginService;
import com.sundata.edu.util.PcUtils;
import com.sundata.edu.vo.SchedulesVo;
import com.sundata.sdcloud.sso.service.ITokenService;
import com.sundata.sdcloud.user.service.IUserService;
import com.sundata.sdcloud.user.vo.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * @Description: App入口
 * @Author: jiang chao
 * @Create: 2021-11-12 13:04
 **/
@Controller
public class H5IndexController extends BaseController {

    @Autowired
    private RpcLoginService rpcLoginService;

    @Autowired
    private ITokenService iTokenService;

    @Autowired
    private SchedulesService schedulesService;


    private String prefix = "xydn/h5";


    @GetMapping(value = "/h5/index")
    public ModelAndView index(String accessToken, ModelMap map) {
        System.err.println("accessToken00=" + accessToken);
        accessToken = iTokenService.getWebTokenByAppToken(accessToken).getData();
        System.err.println("accessToken01=" + accessToken);
        if (StringUtils.isBlank(accessToken)) {
            return new ModelAndView(prefix + "/error");
        }
        rpcLoginService.login(accessToken);
        ModelAndView view = new ModelAndView(prefix + "/index", map);
        return view;
    }


    @GetMapping(value = "/h5/search")
    public String search() {
        return prefix + "/search";
    }

    @GetMapping(value = "/h5/detail")
    public ModelAndView detail(String peopleid, ModelMap map) {
        map.addAttribute("peopleid", peopleid);
        ModelAndView view = new ModelAndView(prefix + "/detail", map);
        return view;
    }

    //    @GetMapping(value = "/h5/gzcxindex")
    @GetMapping(value = "/h5/dzgzzhindex")
    public ModelAndView dzgzzhindex() {
        ModelAndView view = new ModelAndView(prefix + "/index");
        return view;
    }

    @GetMapping(value = "/h5/index2")
    public ModelAndView index2() {
        ModelAndView view = new ModelAndView(prefix + "/index");
        return view;
    }


    @GetMapping(value = "/h5/echarts")
    public ModelAndView echarts(HttpServletRequest request, String accessToken, ModelMap map)  {
        accessToken = iTokenService.getWebTokenByAppToken(accessToken).getData();
        boolean isMoblie = PcUtils.JudgeIsMoblie(request);
        if (StringUtils.isBlank(accessToken)) {
            return new ModelAndView(prefix + "/error");
        }
        rpcLoginService.login(accessToken);

        if (isMoblie) {
            ModelAndView view = new ModelAndView("/h5/echartsh5");
            return view;
        }
        ModelAndView view = new ModelAndView("/h5/echarts");
        return view;
    }


    @GetMapping(value = "/h5/echartsh5")
    public ModelAndView echartsh5(HttpServletRequest request, String accessToken, ModelMap map) {
        accessToken = iTokenService.getWebTokenByAppToken(accessToken).getData();
        if (StringUtils.isBlank(accessToken)) {
            return new ModelAndView(prefix + "/error");
        }
        rpcLoginService.login(accessToken);
        map.put("h5", "h5");
        ModelAndView view = new ModelAndView("/h5/echartsh5", map);
        return view;
    }

    @GetMapping(value = "/h5/phone2023")
    public ModelAndView phone2023(String position,ModelMap map) {
        map.put("position",position);
        ModelAndView view = new ModelAndView("/h5/phone2023", map);
        return view;
    }


    @GetMapping(value = "/h5/phone2024")
    public ModelAndView phone2024() {
        ModelAndView view = new ModelAndView("/h5/phone2024");
        return view;
    }


    @GetMapping(value = "/h5/dwjw2024")
    public ModelAndView dwjw2024() {
        ModelAndView view = new ModelAndView("/h5/dwjw2024");
        return view;
    }

}
