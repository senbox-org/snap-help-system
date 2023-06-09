/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package eu.esa.snap.netbeans.javahelp;

import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

/**
 * This class provides information about getter/setter methods within
 * BrowserDisplayer. It is usefull for reflection.
 *
 * @author Marek Slama
 * @see BrowserDisplayer
 */
public class BrowserDisplayerBeanInfo extends SimpleBeanInfo {

    public BrowserDisplayerBeanInfo() {
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        PropertyDescriptor back[] = new PropertyDescriptor[7];
        try {
            back[0] = new PropertyDescriptor("content", BrowserDisplayer.class);
            back[1] = new PropertyDescriptor("text", BrowserDisplayer.class);
            back[2] = new PropertyDescriptor("textFontFamily", BrowserDisplayer.class);
            back[3] = new PropertyDescriptor("textFontSize", BrowserDisplayer.class);
            back[4] = new PropertyDescriptor("textFontWeight", BrowserDisplayer.class);
            back[5] = new PropertyDescriptor("textFontStyle", BrowserDisplayer.class);
            back[6] = new PropertyDescriptor("textColor", BrowserDisplayer.class);
            return back;
        } catch (Exception ex) {
            return null;
        }
    }
}
