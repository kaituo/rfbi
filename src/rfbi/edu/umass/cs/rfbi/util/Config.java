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

package edu.umass.cs.rfbi.util;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author kaituo
 */
public class Config {
    private static Config instance = null;
    Properties userConfigValues;
    protected Config() {
        // Exists only to defeat instantiation.
        userConfigValues = loadAndApplyProperties("rfbi.mf");
    }

    public static Config getInstance() {
        if(instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private String getProperty(String key) {
        return userConfigValues.getProperty(key);
    }

    public String getStringProperty(String key) {
        return getProperty(key);
    }

    public boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }

    private Properties loadAndApplyProperties(final String fileName)
    {
        final Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));

            return properties;
        }
        catch (final Throwable t)
        {
            t.printStackTrace();
            return null; // could not apply configuration file
        }
    }

    // used for logging mapping from types to its aspectj file name
    public static final String LOG_STREAM_FILE = "logStream";
    // used for keep record of confirmed perms
    public static final String RUNTIME_FILE = "runtimeFile";
    // used for keep record of all perms
    public static final String ALL_RECORDS_FILE = "allRecordFile";

    public static final String HE_PERM_FOLDER = "he.codegen.perm";

    public static final String INSTANCE_RECORDS_FILE = "instanceRecords";

    public static final String STATIC_RECORDS_FILE = "staticRecords";

    public static final String HE_BATCH_PACKAGE = "rfbi.";

    public static final String HE_BATCH_LOG = "statesmap";
}
