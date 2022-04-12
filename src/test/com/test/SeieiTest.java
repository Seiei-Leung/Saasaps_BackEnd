package com.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.seiei.saasaps.service.ArgumentSettingService;
import top.seiei.saasaps.util.PropertiesUtil;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class SeieiTest {

    private final static Log logger = LogFactory.getLog(PropertiesUtil.class);

    //@Autowired
    private ArgumentSettingService argumentSettingService;

    //@Test
    public void test1() {
        logger.debug(argumentSettingService.getAll().isSuccess());
    }
}
