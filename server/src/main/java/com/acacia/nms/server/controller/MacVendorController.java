package com.acacia.nms.server.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acacia.common.dao.APDao;
import com.acacia.common.dao.MacVendorDao;
import com.acacia.common.dao.pojo.AP;
import com.acacia.common.dao.pojo.MacVendor;
import com.acacia.common.db.DataAccessException;
import com.acacia.nms.server.view.Pager;
import com.acacia.nms.server.view.Utils;

@Controller
public class MacVendorController {
	private static Logger log = LoggerFactory.getLogger(MacVendorController.class);
	private MacVendorDao macVendorDao;
	private APDao apDao;
	@Autowired
	public void setApDao(APDao apDao) {
		this.apDao = apDao;
	}
	@RequestMapping("testap")
	public@ResponseBody AP getAP(@RequestParam("id")Long id) throws DataAccessException {
		return apDao.getById(id);
	}
	@RequestMapping("getVendor")
	public@ResponseBody MacVendor getVendor(String mac) throws DataAccessException{
		final List<Criterion> criterions = new LinkedList<Criterion>();
		criterions.add(Restrictions.like("macAddress", mac.replace(":", "-").substring(0,8)+"%"));
		return macVendorDao.selectUiqueOne(criterions);
	}
	@RequestMapping(value="macvendors")
	public String list(
            final Pager pager,
            final ModelMap model,
            @RequestParam(value = "keywords", required = false) final String keywords,
            final HttpServletResponse response) {
        try {
            final List<Criterion> criterions = new LinkedList<Criterion>();
            Criterion[] criterionsTemp;
            if (null != keywords && !"".equals(keywords)) {
                criterionsTemp = new Criterion[2];
                // 关键词不为空，组合查询条件
                criterionsTemp[0] = Restrictions.like("macAddress", "%" + keywords
                        + "%");
                criterionsTemp[1] = Restrictions.like("vendor", "%"
                        + keywords + "%");
                criterions.add(Restrictions.or(criterionsTemp));
                model.put("keywords", keywords);
            }

            pager.setTotalCount(macVendorDao.count());
            if (pager.getPageNumber() > pager.getPageCount()) {
                pager.setPageNumber(pager.getPageCount());
            }
            final List<Order> orders = new LinkedList<Order>();
            if (pager.getOrderBy() != null && !"".equals(pager.getOrderBy())) {
                if (pager.getOrderType() == Pager.OrderType.asc) {
                    orders.add(Order.asc(pager.getOrderBy()));
                } else {
                    orders.add(Order.desc(pager.getOrderBy()));
                }
            } else {
                orders.add(Order.desc("id"));
                pager.setOrderBy("id");
                pager.setOrderType(Pager.OrderType.desc);
            }
            pager.setList(macVendorDao.list(
                    (pager.getPageNumber() - 1) * pager.getPageSize(),
                    pager.getPageSize(), criterions, orders));

            model.put("pager", pager);
            return "macvendor/list";
        } catch (final DataAccessException e) {
            log.error("异常:", e);
            return Utils.ajaxJsonDialogErrorMessage(response,
                    "查询厂商列表出错！<br>请检查查询条件！", "", "");
        }
    }
	@Autowired
	public void setMacVendorDao(MacVendorDao macVendorDao) {
		this.macVendorDao = macVendorDao;
	}
	
}
