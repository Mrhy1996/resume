package com.mrhy.resumeserver.utils;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author : Luyz
 * @date : 2019/5/10 09:26
 */

public class MyBatisPlusGenerator {
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in, "utf8");
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        String projectPath = System.getProperty("user.dir");
        setGlobalConfig(mpg, projectPath);
        setDataSourceConfig(mpg);
        PackageConfig pc = getPackageConfig(mpg);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return projectPath + "/resume-server/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        setTemplateConfig(mpg);
        setStrategyConfig(mpg, pc);
        mpg.execute();
    }

    private static void setTemplateConfig(AutoGenerator mpg) {
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
    }

    private static void setStrategyConfig(AutoGenerator mpg, PackageConfig pc) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(false);
        strategy.setInclude(scanner("表名"));
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setRestControllerStyle(true);
//        strategy.setTablePrefix("T_");
        mpg.setStrategy(strategy);
    }

    private static PackageConfig getPackageConfig(AutoGenerator mpg) {
        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("base");
        pc.setParent("com.mrhy.resumeserver");
        mpg.setPackageInfo(pc);
        return pc;
    }

    private static void setDataSourceConfig(AutoGenerator mpg) {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/resume?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC");
        dsc.setSchemaName("guarder");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("mrhy@1996");
        mpg.setDataSource(dsc);
    }

    private static void setGlobalConfig(AutoGenerator mpg, String projectPath) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/resume-server/src/main/java/");
        gc.setAuthor("mrhy");
        gc.setOpen(false);
//        gc.setSwagger2(true);
        // 覆盖
         gc.setFileOverride(true);
         gc.setBaseResultMap(true);
         gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);
    }
}
