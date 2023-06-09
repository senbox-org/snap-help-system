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

import org.openide.util.NbBundle;

import javax.swing.AbstractAction;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

/**
 * The <code>CopyLinkLocationAction</code>.
 *
 * @author Victor G. Vasilyev
 */
public class CopyLinkLocationAction extends AbstractAction {

    private static final String LBL_CopyLinkLocationAction =
            NbBundle.getMessage(HyperlinkEventProcessor.class,
                    "LBL_CopyLinkLocationAction");

    private LinkOwner linkOwner;

    public CopyLinkLocationAction(LinkOwner link) {
        super(LBL_CopyLinkLocationAction);
        // putValue(SHORT_DESCRIPTION, desc);
        // putValue(MNEMONIC_KEY, mnemonic);
        this.linkOwner = link;
    }

    public void actionPerformed(ActionEvent e) {
        textToSystemClipboard(linkOwner.getURLExternalForm());
    }

    private void textToSystemClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = linkOwner.getClipboard();
        clipboard.setContents(selection, linkOwner);
    }

    public interface LinkOwner extends ClipboardOwner {
        String getURLExternalForm();

        Clipboard getClipboard();
    }

}
