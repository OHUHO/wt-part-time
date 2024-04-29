package com.walker.part.generator;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collections;

/**
 * @description: FastAutoGenerator
 * @author: Walker
 * @date: 2024-04-21 08:08:58
 * @version: 1.0.0
 */
public class FastAutoGenerator {

    public static void main(String[] args) {
        com.baomidou.mybatisplus.generator.FastAutoGenerator.create(
                        "jdbc:mysql://walker.top:3306/part_time?useUnicode=true&useSSL=true&characterEncoding=utf8", "root", "123456")
                .globalConfig(builder -> {
                    builder // 设置作者
                            .author("Walker")
                            // 开启 swagger 模式
                            // .enableSwagger()
                            // 指定输出目录
                            //.outputDir("E://Temporary//mybatis_plus//gp//")
                            .outputDir("/Users/walker/Projects/PartTime/part-time/part-time-server/src/main/java")
                            // 注释日期
                            .commentDate("yyyy-MM-dd HH:mm:ss");

                })
                .packageConfig(builder -> {
                    builder // 设置父包名
                            .parent("com")
                            // 设置父包模块名
                            .moduleName("walker.part")
                            .entity("entity")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .controller("controller")
                            // 设置mapperXml生成路径
                            //.pathInfo(Collections.singletonMap(OutputFile.xml, "E://Temporary//mybatis_plus//gp//"));
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/walker/Projects/PartTime/part-time/part-time-server/src/main/resources/mapper/"));
                })
                .strategyConfig(builder -> {
                    builder //#######################################################
                            // 设置需要生成的表名
                            //.addInclude("business_info")
                            //.addInclude("carousel")
                            //.addInclude("comment")
                            //.addInclude("experience")
                            //.addInclude("experience_type")
                            //.addInclude("job_info")
                            //.addInclude("job_type")
                            //.addInclude("registration_sheet")
                            //.addInclude("reviews")
                            //.addInclude("user_info")
                            //.addInclude("admin_info")
                            .addInclude("user_fans")
                            .addInclude("experience_love")
                            .addInclude("experience_good")
                            //#######################################################

                            // 设置过滤表前缀
                            .addTablePrefix("w_", "t_")

                            .entityBuilder()
                            // 开启Lombok注解
                            .enableLombok()
                            // 开启链式模式
                            .enableChainModel()
                            // 逻辑删除字段名
                            .logicDeleteColumnName("logic_delete")
                            //允许文件覆盖
                            .enableFileOverride()

                            .controllerBuilder()
                            // 开启 @RestController控制器
                            .enableRestStyle()
                            //允许文件覆盖
                            .enableFileOverride()

                            .serviceBuilder()
                            //去除Service前面的I
                            //.formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            //允许文件覆盖
                            .enableFileOverride()

                            .mapperBuilder()
                            // 开启@Mapper注解
                            .mapperAnnotation(Mapper.class)
                            .superClass(BaseMapper.class)
                            // 启用BaseResultMap生成
                            .enableBaseResultMap()
                            // 生成基本的SQL片段
                            .enableBaseColumnList()
                            // 生成基本的resultMap
                            .enableBaseResultMap()
                            //允许文件覆盖
                            .enableFileOverride();
                })
                // 使用Freemarker 引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
