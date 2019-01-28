package com.bonc.boot.controller;

import com.bonc.boot.aop.annotaion.WebLogger;
import com.bonc.boot.service.CityService;
import com.bonc.boot.util.AppReply;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.bonc.boot.model.City;

import java.util.List;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping
    @WebLogger
    public AppReply getAll(City city) {
        List<City> countryList = cityService.getAll(city);
        return AppReply.success(new PageInfo<City>(countryList));
    }

    @RequestMapping(value = "/add")
    public AppReply add() {
        return AppReply.success();
    }

    @RequestMapping(value = "/view/{id}")
    public AppReply view(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView();
        City city = cityService.getById(id);
        return AppReply.success(city);
    }

    @RequestMapping(value = "/delete/{id}")
    public AppReply delete(@PathVariable Integer id) {
        cityService.deleteById(id);
        return AppReply.success();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AppReply save(City city) {
        ModelMap result = new ModelMap();
        String msg = city.getId() == null ? "新增成功!" : "更新成功!";
        cityService.save(city);
        result.put("city", city);
        result.put("msg", msg);
        return AppReply.success();
    }
}
