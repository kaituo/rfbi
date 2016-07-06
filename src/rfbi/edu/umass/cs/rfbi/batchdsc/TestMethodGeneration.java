/*
 * FindBugs - Find Bugs in Java programs
 * Copyright (C) 2003-2008 University of Maryland
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package edu.umass.cs.rfbi.batchdsc;

import static edu.umass.cs.rfbi.util.Assertions.*;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.umass.cs.rfbi.util.Config;

/**
 * @author kaituo
 */
public class TestMethodGeneration {
    Map<String, Class<?>> classes = new HashMap<>();

    // public static void testAddLocalVariable_01(String name, Type type,
    // InstructionHandle start,
    // InstructionHandle end ) {
    //
    // ((org.apache.bcel.generic.MethodGen) DeSerializedState.getInstance()
    // .getDeserializedObj()).addLocalVariable(name, type, start, end);
    // }
    public void generate(String packageName, String stateFolder) {
        File folder = new File(stateFolder);
        File[] listOfFiles = folder.listFiles();

        Map<String, StringBuffer> generatedMethods = new HashMap<>();


        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String fileName = listOfFiles[i].getName();
                int dollarIndex = fileName.indexOf("$");
                check(dollarIndex != -1, "A state file name has to contain a dollar sign.");
                String canonicalMethName = fileName.substring(0, dollarIndex);
                int lastDotMethod = canonicalMethName.lastIndexOf(".");
                String className = canonicalMethName.substring(0, lastDotMethod);
                if (!generatedMethods.containsKey(className)) {
                    StringBuffer sb = new StringBuffer();
                    generatedMethods.put(className, sb);
                    sb.append("import ");
                    sb.append(packageName);
                    sb.append(";\n");
                    sb.append("import edu.umass.cs.rfbi.DeSerializedState;\n\n");

                    sb.append("public class ");

                    int lastDotClass = className.lastIndexOf(".");
                    String simpleClassName = className.substring(lastDotClass + 1);
                    sb.append(simpleClassName);
                    sb.append("\n{\n");

                    try {
                        Class<?> clazz = getClassForName(className);
                        classes.put(className, clazz);
                    } catch (final Exception e) {
                        final ClassLoader cl = ClassLoader.getSystemClassLoader();
                        final URL[] urls = ((URLClassLoader) cl).getURLs();
                        for (URL url : urls) {
                            System.out.println(url.getFile());
                        }
                    }
                }
                StringBuffer sb = generatedMethods.get(className);
                String methName = canonicalMethName.substring(lastDotMethod + 1);

                List<Method> methods = getMethod(className, methName);
                if(methods == null || methods.isEmpty()) {
                    continue;
                }

                for(int j=0; j<methods.size(); j++) {
                    Method meth = methods.get(j);

                    sb.append(" public static void test");
                    sb.append(methName);
                    sb.append("(");

                    Class<?>[] paramsType = meth.getParameterTypes();
                    int paramCount = paramsType.length;
                    String[] paramNameList = new String[paramCount];
                    for(int k=0; k<paramCount; k++) {
                        if(k!=0) {
                            sb.append(", ");
                        }
                        sb.append(paramsType[k].getName());
                        sb.append(" ");
                        paramNameList[k] = String.valueOf('a'+k);
                        sb.append(paramNameList[k]);
                    }

                    sb.append(")");

                    Class<?>[] exceptions = meth.getExceptionTypes();
                    for(int k=0; k<exceptions.length; k++) {
                        if(k==0) {
                            sb.append(" throws ");
                        } else {
                            sb.append(", ");
                        }
                        sb.append(exceptions[k].getName());
                    }

                    sb.append(" {\n        ((");
                    sb.append(className);
                    sb.append(") DeSerializedState.getInstance().getDeserializedObj()).");
                    sb.append(methName);
                    sb.append("(");
                    for(int k=0; k<paramCount; k++) {
                        if(k!=0) {
                            sb.append(", ");
                        }
                        sb.append(paramNameList[k]);
                    }
                    sb.append(");\n}\n\n");
                }

            }
        }

        for (StringBuffer sb : generatedMethods.values()) {
            sb.append("\n}");
        }
    }

    public void generate() {
        Config conf = Config.getInstance();
        if (!conf.getStringProperty("dscbatch.he.state.dir").isEmpty()) {
            generate(Config.HE_BATCH_PACKAGE, conf.getStringProperty("dscbatch.he.state.dir"));
        }
    }

    List<Method> getMethod(String className, String methName) {
        if(!classes.containsKey(className)) {
            return null;
        }

        Class<?> clazz = classes.get(className);
        Method method = null;
        List<Method> res = new ArrayList<>();

        final Method[] declMeths = clazz.getDeclaredMethods();
        for (final Method declMeth : declMeths) {
            if (!Modifier.isPublic(declMeth.getModifiers())) {
                continue;
            }

            // Kaituo: we would need a method with at least 1 argument to do sth
            if (declMeth.getName().equals(methName) && declMeth.getParameterTypes().length > 0) {
                res.add(method);

            }
        }
        return res;
    }

    public static Class<?> getClassForName(final String className) {
        notNull(className);

        Class<?> res = null;
        final String classNameDot = className.replace("/", ".");
        try {
            res = Class.forName(classNameDot, false, Thread.currentThread().getContextClassLoader());
        } catch (final ClassNotFoundException cnfe) {
            check(false, cnfe);
        }
        return notNull(res);
    }
}
