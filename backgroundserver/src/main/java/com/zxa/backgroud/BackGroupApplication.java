/**
 * Copyright 2018-2020 zxa & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zxa.backgroud;

import cn.stylefeng.roses.core.config.WebAutoConfiguration;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * SpringBoot方式启动类
 *
 * @author zxa
 * @Date 2017/5/21 12:06
 */
@SpringBootApplication(exclude = {WebAutoConfiguration.class})
@Import(FdfsClientConfig.class)
public class BackGroupApplication {

    private final static Logger logger = LoggerFactory.getLogger(BackGroupApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackGroupApplication.class, args);
        logger.info(BackGroupApplication.class.getSimpleName() + " is success!");
    }
}