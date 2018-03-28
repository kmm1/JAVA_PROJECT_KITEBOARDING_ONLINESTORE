package com.company.dao.common;

import com.company.config.TestConfig;
import com.company.entity.BaseEntity;
import com.company.util.TestDataImporter;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Kate M on 07.03.2018.
 */
@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringRunner.class)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class BaseDaoTest<T extends BaseEntity> {

    @Autowired
    private TestDataImporter testDataImporter;

    protected TestDataImporter getTestDataImporter() {
        return testDataImporter;
    }
}