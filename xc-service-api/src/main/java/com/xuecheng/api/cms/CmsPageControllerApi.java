package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "cms页面管理接口", description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {
    /**
     * 页面查询
     *
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, paramType = "path", dataType = "int")
    })
    QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * 新增页面
     *
     * @param cmsPage
     * @return
     */
    @ApiOperation("新增页面")
    public CmsPageResult add(CmsPage cmsPage);

    /**
     * 根据页面ID查询页面信息
     *
     * @param id
     * @return
     */
    @ApiOperation("根据页面ID查询页面信息")
    public CmsPage findById(String id);

    /**
     * 修改页面
     *
     * @param id
     * @param cmsPage
     * @return
     */
    @ApiOperation("修改页面")
    public CmsPageResult edit(String id, CmsPage cmsPage);

    /**
     * 删除页面
     *
     * @param id
     * @return
     */
    @ApiOperation("删除页面")
    public ResponseResult delete(String id);

    /**
     * 页面发布
     *
     * @param pageId
     * @return
     */
    @ApiOperation("页面发布")
    public ResponseResult post(String pageId);

    @ApiOperation("保存页面")
    public CmsPageResult save(CmsPage cmsPage);
}
