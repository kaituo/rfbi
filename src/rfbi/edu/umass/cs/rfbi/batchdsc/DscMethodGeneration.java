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
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import edu.umass.cs.rfbi.util.Config;
import edu.umass.cs.rfbi.util.JSON;
import edu.umass.cs.rfbi.util.RFBIUtil;

/**
 * @author kaituo
 */
public class DscMethodGeneration {
    Map<String, Class<?>> classes = new HashMap<>();
    JSONObject jObj = new JSONObject();

    private final String HEDscDir;

    public DscMethodGeneration() {
        HEDscDir = Config.getInstance().getStringProperty("dscbatch.he.dsc.dir");
        RFBIUtil.createFolder(HEDscDir);
    }

    private StringBuffer createFolderPath4Package(String packageName) {
        StringBuffer packageFolderName = new StringBuffer();

        packageFolderName.append(HEDscDir);
        packageFolderName.append(File.separator);

        packageFolderName.append(packageName.replace(".", File.separator));
        RFBIUtil.createFolder(packageFolderName.toString(), false);
        return packageFolderName;
    }

    public void generate(String packageNamePrefix, String stateFolder) {
        File folder = new File(stateFolder);
        File[] listOfFiles = folder.listFiles();

        Map<String, StringBuffer> generatedClasses = new HashMap<>();
        Map<String, StringBuffer> simpleClassNames = new HashMap<>();
        Map<String, Set<Method>> generatedMethods = new HashMap<>();
        // if we use Map<String, Set<String>>, json simple would not
        // put quotes around each member of the set values, which is
        // not standard json and thus causes error.
        Map<String, List<String>> meth2states = new HashMap<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                String fileName = listOfFiles[i].getName();
                int dollarIndex = fileName.indexOf("$");
                check(dollarIndex != -1, "A state file name has to contain a dollar sign.");
                String canonicalMethName = fileName.substring(0, dollarIndex);
                int lastDotMethod = canonicalMethName.lastIndexOf(".");
                String className = canonicalMethName.substring(0, lastDotMethod);
                int lastDotClass = className.lastIndexOf(".");
                String simpleClassName = className.substring(lastDotClass + 1);
                StringBuffer packageName = new StringBuffer();
                packageName.append(packageNamePrefix);
                packageName.append(className.substring(0, className.lastIndexOf(".")));

                if (!generatedClasses.containsKey(className)) {
                    StringBuffer sb = new StringBuffer();
                    generatedClasses.put(className, sb);
                    generatedMethods.put(className, new HashSet<Method>());
                    sb.append("package ");
                    sb.append(packageName);
                    sb.append(";\n\n");
                    sb.append("import edu.umass.cs.rfbi.DeSerializedState;\n\n");

                    sb.append("public class ");
                    sb.append(simpleClassName);
                    sb.append("\n{\n");

                    StringBuffer pkgeFolder = createFolderPath4Package(packageName.toString());
                    StringBuffer filetoWrite = new StringBuffer();
                    filetoWrite.append(pkgeFolder);
                    filetoWrite.append(File.separator);
                    filetoWrite.append(simpleClassName);
                    filetoWrite.append(".java");
                    simpleClassNames.put(filetoWrite.toString(), sb);

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
                StringBuffer sb = generatedClasses.get(className);
                String methName = canonicalMethName.substring(lastDotMethod + 1);

                List<Method> methods = getMethod(className, methName);
                if(methods == null || methods.isEmpty()) {
                    continue;
                }

                StringBuffer simpleClassMethNameBuffer = new StringBuffer();
                simpleClassMethNameBuffer.append(simpleClassName);
                simpleClassMethNameBuffer.append(".test");
                simpleClassMethNameBuffer.append(methName);
                String simpleClassMethName = simpleClassMethNameBuffer.toString();

                if(!meth2states.containsKey(simpleClassMethName)) {
                    meth2states.put(simpleClassMethName, new ArrayList<String>());
                }
                meth2states.get(simpleClassMethName).add(fileName);

                Set<Method> generatedMethodsFor = generatedMethods.get(className);
                for(int j=0; j<methods.size(); j++) {
                    Method meth = methods.get(j);
                    if(generatedMethodsFor.contains(meth)) {
                        continue;
                    } else {
                        generatedMethodsFor.add(meth);
                    }
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
                        sb.append(paramsType[k].getCanonicalName());
                        sb.append(" ");
                        paramNameList[k] = Character.toString((char)('a'+k));
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
                    sb.append(");\n }\n\n");
                }


            }
        }

        for (StringBuffer sb : generatedClasses.values()) {
            sb.append("\n}");
        }

        //        for (Map.Entry<String, StringBuffer> entry : simpleClassNames.entrySet()) {
        //            String fileName = entry.getKey();
        //            StringBuffer fileContent = entry.getValue();
        //
        //            StringBuffer filetoWrite = new StringBuffer();
        //
        //            filetoWrite.append(HEDscDir);
        //            filetoWrite.append(File.separator);
        //
        //            filetoWrite.append(fileName);
        //            filetoWrite.append(".java");
        //
        //            String filePath = filetoWrite.toString();
        //            RFBIUtil.createFile(filePath);
        //
        //            try {
        //                RFBIUtil.write(fileContent.toString(), filePath);
        //            } catch(IOException e) {
        //                e.printStackTrace();
        //            }
        //
        //        }

        for (Map.Entry<String, StringBuffer> entry : simpleClassNames.entrySet()) {
            String filePath = entry.getKey().toString();
            RFBIUtil.createFile(filePath);

            try {
                RFBIUtil.write(entry.getValue().toString(), filePath);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        for(Map.Entry<String, List<String>> meth2state: meth2states.entrySet()) {
            jObj.put(meth2state.getKey(), meth2state.getValue());
        }

        StringBuffer heBatchLogFile = new StringBuffer();
        heBatchLogFile.append(HEDscDir);
        heBatchLogFile.append(File.separator);
        heBatchLogFile.append(Config.HE_BATCH_LOG);
        JSON.createJSONLog(jObj, heBatchLogFile.toString());//false

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
        List<Method> res = new ArrayList<>();

        final Method[] declMeths = clazz.getDeclaredMethods();
        for (final Method declMeth : declMeths) {
            if (!Modifier.isPublic(declMeth.getModifiers())) {
                continue;
            }

            // Kaituo: we would need a method with at least 1 argument to do sth
            if (declMeth.getName().equals(methName) && declMeth.getParameterTypes().length > 0) {
                res.add(declMeth);

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

    public static void main(String[] args) {
        if (!Config.getInstance().getBooleanProperty("dscbatch.enabled")) {
            return;
        }
        (new DscMethodGeneration()).generate();
    }
}
