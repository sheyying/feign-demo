package com.common.config;

import com.common.annotation.EnableO2OFeignConfig;
import com.common.annotation.ResponseValidate;
import lombok.Setter;
import lombok.val;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheying on 2018/06/20.
 */
public class ResponseVallidateRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    private static final String EXPRESSION = "expression";

    @Setter
    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        if (resourceLoader == null) {
            return;
        }
        AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata
                .getAnnotationAttributes(EnableO2OFeignConfig.class.getName()));

        String[] basePackages = attributes.getStringArray("basePackages");
        if(basePackages.length == 0) {
            return;
        }
        // 扫描获取interface
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.setResourceLoader(resourceLoader);

        final List<String> interfaceNames = new ArrayList<>();

        // 扫描@ResponseValidate的接口
        scanner.addIncludeFilter(new TypeFilter(){
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                ClassMetadata metadata = metadataReader.getClassMetadata();
                try {
                    Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(metadata.getClassName());
                    if(clazz.isInterface() && clazz.isAnnotationPresent(ResponseValidate.class)){
                        interfaceNames.add(clazz.getName());
                    }else if (clazz.isInterface() && !clazz.isAnnotationPresent(ResponseValidate.class)){
                        for (Method method : clazz.getMethods()) {
                            // 方法中有注解
                            if (method.isAnnotationPresent(ResponseValidate.class)) {
                                interfaceNames.add(clazz.getName());
                                break;
                            }
                        }
                    }
                } catch (ClassNotFoundException ignored) {
                }
                return false;
            }
        });
        scanner.scan(basePackages);

        if (interfaceNames.isEmpty()) {
            return;
        }
        val sb = new StringBuilder();
        for (int i = 0, len = interfaceNames.size(); i < len; i++) {
            sb.append("this(").append(interfaceNames.get(i)).append(")");
            if (i + 1 < len) {
                sb.append("||");
            }
        }
        // 注册pointcut
        BeanDefinition bd = BeanDefinitionBuilder.genericBeanDefinition(AspectJExpressionPointcut.class)
                .addPropertyValue(EXPRESSION, sb.toString())
                .setScope(BeanDefinition.SCOPE_PROTOTYPE)
                .getBeanDefinition();
        registry.registerBeanDefinition("responseValidatePointCut", bd);

        // 注册配置
        bd = BeanDefinitionBuilder.genericBeanDefinition(ResponseValidateAspectConfig.class).getBeanDefinition();
        registry.registerBeanDefinition(ResponseValidateAspectConfig.class.getName(), bd);

    }
}
