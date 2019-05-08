package com.youyd.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youyd.base.service.DictService;
import com.youyd.enums.StatusEnum;
import com.youyd.pojo.QueryVO;
import com.youyd.pojo.base.Dict;
import com.youyd.utils.DateUtil;
import com.youyd.utils.JsonData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 *  字典管理
 * @author : LGG
 * @create : 2018-12-23
 **/

@Api(tags = "字典")
@RestController
@RequestMapping(value = "/dict")
public class DictController {


	private final DictService dictService;

	@Autowired
	public DictController(DictService dictService) {
		this.dictService = dictService;
	}


	/**
	 * 条件查询菜单
	 * @param 菜单实体 查询参数
	 * @param queryVO 查询参数
	 * @return JsonData
	 */
	@GetMapping
	public JsonData findResByCondition(Dict dict, QueryVO queryVO) {
		IPage<Dict> resData = dictService.findDictByCondition(dict,queryVO);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), resData);
	}

	/**
	 * 根据id查询单条
	 * @param resId:资源数据
	 * @return  JsonData
	 */
	@GetMapping(value = "/{id}")
	public JsonData findById(@PathVariable String id) {
		Dict resData = dictService.findDictById(id);
		return new JsonData(true, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg(), resData);
	}

	/**
	 * 更新资源
	 * @param  dict 菜单
	 * @return JsonData
	 */
	@PutMapping
	public JsonData updateByPrimaryKey(@RequestBody Dict dict) {
		boolean state = dictService.updateByPrimaryKey(dict);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 添加一条数据
	 * @param dict 菜单
	 * @return JsonData
	 */
	@PostMapping
	public JsonData insertSelective(@RequestBody Dict dict) {
		dict.setCreateAt(DateUtil.getTimestamp());
		dict.setUpdateAt(DateUtil.getTimestamp());
		boolean state = dictService.insertDictSelective(dict);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}

	/**
	 * 删除资源
	 * @param resId 资源id数组
	 * @return JsonData
	 */
	@DeleteMapping()
	public JsonData deleteByIds(@RequestBody List<String> resId) {
		boolean state = dictService.deleteDictByIds(resId);
		return new JsonData(state, StatusEnum.OK.getCode(), StatusEnum.OK.getMsg());
	}
}