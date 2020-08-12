package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CmsPageRepository extends MongoRepository<CmsPage, String> {
    /**
     * 根据页面名称查询
     *
     * @param pageName
     * @return
     */
    CmsPage findByPageName(String pageName);

    /**
     * 根据称，站点id，页面webpath查询
     *
     * @param pageName
     * @param siteId
     * @param pageWebPath
     * @return
     */
    CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath);
}
