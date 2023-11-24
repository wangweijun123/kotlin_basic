package com.wangweijun.myapplication.packageutil;

import android.content.pm.Signature;
import android.os.Build;
import android.util.DisplayMetrics;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PackageUtil {

    /**
     * 获取该目录下的apk签名
     * @param path 当前apk路径
     * @return
     */
    public static String getSignature(String path) throws Exception{
        // 1.反射实例化PackageParser对象
        Object packageParser = getPackageParser(path);

        // 2.反射获取parsePackage方法
        Object packageObject = getPackageInfo(path,packageParser);

        // 3.调用collectCertificates方法
        Method collectCertificatesMethod = packageParser.getClass().
                getDeclaredMethod("collectCertificates",packageObject.getClass(),int.class);
        collectCertificatesMethod.invoke(packageParser,packageObject,0);

        // 4.获取mSignatures属性
        Field signaturesField = packageObject.getClass().getDeclaredField("mSignatures");
        signaturesField.setAccessible(true);
        Signature[] mSignatures = (Signature[]) signaturesField.get(packageObject);
        return mSignatures[0].toCharsString();
    }

    /**
     * 创建PackageParser.Package类，兼容5.0
     * @param path
     * @return
     * @throws Exception
     */
    private static Object getPackageInfo(String path, Object packageParser) throws Exception {
        if(Build.VERSION.SDK_INT >=21){
            Class<?>[] paramClass = new Class[2];
            paramClass[0] = File.class;
            paramClass[1] = int.class;
            Method parsePackageMethod = packageParser.getClass().getDeclaredMethod("parsePackage",paramClass);

            // 3.反射执行parsePackage方法
            Object[] paramObject = new Object[2];
            paramObject[0] = new File(path);
            paramObject[1] = 0;
            parsePackageMethod.setAccessible(true);
            return parsePackageMethod.invoke(packageParser,paramObject);
        }else{
            Class<?>[] paramClass = new Class[4];
            paramClass[0] = File.class;
            paramClass[1] = String.class;
            paramClass[2] = DisplayMetrics.class;
            paramClass[3] = int.class;
            Method parsePackageMethod = packageParser.getClass().getDeclaredMethod("parsePackage",paramClass);

            // 3.反射执行parsePackage方法
            Object[] paramObject = new Object[4];
            paramObject[0] = new File(path);
            paramObject[1] = path;
            DisplayMetrics metrics = new DisplayMetrics();
            metrics.setToDefaults();
            paramObject[2] = metrics;
            paramObject[3] = 0;
            parsePackageMethod.setAccessible(true);
            return parsePackageMethod.invoke(packageParser,paramObject);
        }
    }

    /**
     * 创建PackageParser类
     * @param path
     * @return
     * @throws Exception
     */
    private static Object getPackageParser(String path) throws Exception{
        Class<?> packageParserClazz = Class.forName("android.content.pm.PackageParser");
        // 版本兼容
        if(Build.VERSION.SDK_INT >=21 ){
            Constructor<?> packageParserConstructor = packageParserClazz.getDeclaredConstructor();
            return packageParserConstructor.newInstance();
        }else{
            Constructor<?> packageParserConstructor = packageParserClazz.getDeclaredConstructor(String.class);
            return packageParserConstructor.newInstance(path);
        }
    }


}
