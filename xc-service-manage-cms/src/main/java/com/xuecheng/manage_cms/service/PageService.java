package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.CustomException;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    /**
     * 页面查询方法
     *
     * @param page             页码，从1开始计数
     * @param size             每页记录数
     * @param queryPageRequest 查询条件
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }

        // 自定义条件查询
        // 定义条件匹配器
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());

        // 条件的值对象
        CmsPage cmsPage = new CmsPage();
        // 设置条件值（站点id）
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        // 设置条件值（模板id）
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())) {
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        // 设置页面别名
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }

        // 定义条件对象
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);

        // 设置页码数
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;

        // 设置每页记录数
        if (size <= 0) {
            size = 10;
        }

        // 设置分页条件
        Pageable pageable = PageRequest.of(page, size);

        // 分页查询&自定义条件查询
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);

        QueryResult queryResult = new QueryResult();
        queryResult.setList(all.getContent()); // 数据列表
        queryResult.setTotal(all.getTotalElements()); // 数据总记录数

        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);

        return queryResponseResult;
    }

//    /**
//     * 新增页面
//     *
//     * @param cmsPage
//     * @return
//     */
//    public CmsPageResult add(CmsPage cmsPage) {
//        // 校验页面名称，站点id，页面webpath的唯一性
//        // 根据页面名称，站点id，页面webpath去查询cms_page集合，如果查询到说明存在，如果没有查到，在继续添加
//        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
//        if (cmsPage1 == null) {
//            // 调用dao，新增页面
//            cmsPage.setPageId(null);
//            cmsPageRepository.save(cmsPage);
//            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
//        }
//        // 添加失败
//        return new CmsPageResult(CommonCode.FAIL, null);
//    }

    /**
     * 新增页面
     *
     * @param cmsPage
     * @return
     */
    public CmsPageResult add(CmsPage cmsPage) {
        if (cmsPage == null) {
            // 抛出异常，非法参数异常。。指定异常信息的内容

        }

        // 校验页面名称，站点id，页面webpath的唯一性
        // 根据页面名称，站点id，页面webpath去查询cms_page集合，如果查询到说明存在，如果没有查到，在继续添加
        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());

        if (cmsPage1 != null) {
            // 页面已经存在
            // 抛出异常，异常内容就是页面已经存在
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }

        // 调用dao，新增页面
        cmsPage.setPageId(null);
        cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
    }

    /**
     * 根据页面id查询页面信息
     *
     * @param id
     * @return
     */
    public CmsPage getById(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            return cmsPage;
        }
        return null;
    }

    /**
     * 修改页面
     *
     * @param id
     * @param cmsPage
     * @return
     */
    public CmsPageResult update(String id, CmsPage cmsPage) {
        // 根据id从数据库中查询页面信息
        CmsPage cmsPage1 = this.getById(id);
        if (cmsPage1 != null) {
            // 设置要修改的数据
            // 更新模板id
            cmsPage1.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            cmsPage1.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            cmsPage1.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            cmsPage1.setPageName(cmsPage.getPageName());
            //更新访问路径
            cmsPage1.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            cmsPage1.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            // 提交修改
            cmsPageRepository.save(cmsPage1);
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage1);
        }
        // 修改失败
        return new CmsPageResult(CommonCode.FAIL, null);
    }

    /**
     * 根据id删除页面
     *
     * @param id
     * @return
     */
    public ResponseResult delete(String id) {
        // 先查询一下
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()) {
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
