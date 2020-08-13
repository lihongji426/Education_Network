package com.xuecheng.manage_cms.dao;

import com.xuecheng.manage_cms.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PageServiceTest {

    @Autowired
    private PageService pageService;

    @Test
    public void testGetPageHtml() {
        String pageHtml = pageService.getPageHtml("5f339d0287e6cc36189a5b44");
        System.out.println(pageHtml);
    }
}
