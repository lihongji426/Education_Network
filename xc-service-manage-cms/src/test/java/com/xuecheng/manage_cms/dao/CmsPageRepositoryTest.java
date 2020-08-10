package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest // 找SpringBoot的启动类
@RunWith(SpringRunner.class) // 让测试运行在Spring环境中
public class CmsPageRepositoryTest {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    /**
     * 测试查询全部
     */
    @Test
    public void testFindAll() {

        List<CmsPage> all = cmsPageRepository.findAll();

        System.out.println(all);
    }

    /**
     * 测试分页查询
     */
    @Test
    public void testFindPage() {

        // 分页参数
        int page = 0; // 从0开始
        int size = 10;

        Pageable pageable = PageRequest.of(page, size);

        Page<CmsPage> all = cmsPageRepository.findAll(pageable);

        System.out.println(all);
    }

    /**
     * 测试添加方法
     */
    @Test
    public void testInsert() {

        // 定义实体类
        CmsPage cmsPage = new CmsPage();

        cmsPage.setSiteId("s01");
        cmsPage.setTemplateId("t01");
        cmsPage.setPageName("测试页面");
        cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> cmsPageParams = new ArrayList<>();
        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("测试1");
        cmsPageParam.setPageParamValue("测试2");
        cmsPageParams.add(cmsPageParam);

        cmsPageRepository.save(cmsPage);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete() {
        cmsPageRepository.deleteById("5f3108d287e6cc41d010e48a");
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate() {

        // 查询对象
        Optional<CmsPage> optional = cmsPageRepository.findById("5f310a0a87e6cc45a03d68c9");

        if (optional.isPresent()) {
            // 获取CmsPage对象
            CmsPage cmsPage = optional.get();

            // 设置要修改的值
            cmsPage.setPageName("李哈哈的测试");

            // 修改
            cmsPageRepository.save(cmsPage);
        }
    }

    /**
     * 根据页面名称查询
     */
    @Test
    public void testFindByPageName() {
        System.out.println(cmsPageRepository.findByPageName("李哈哈的测试"));
    }
}
