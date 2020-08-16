package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.manage_cms.service.PageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SysDictionaryRepositoryTest {

    @Autowired
    private PageService pageService;

    @Test
    public void testFindDictionaryByType() {
        SysDictionary dictionaryByType = pageService.findDictionaryByType("100");
        System.out.println(dictionaryByType);
    }
}
