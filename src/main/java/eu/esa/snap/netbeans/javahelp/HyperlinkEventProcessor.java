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

import javax.swing.JEditorPane;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.logging.Level;

/**
 * Processor for the both {@code MouseEvent}s and
 * {@code HyperlinkEvent}s that may occur against hyperlinks
 * displayed in the {@code JEditorPane}.
 * The {@code HyperlinkEventProcessor} is responsible to:
 * <ul>
 *     <li>show a {@code url} of the hyperlink as a tooltip;</li>
 *     <li>show a context menu of the hyperlink</li>
 *     <li>copy text of the {@code url} to the system clipboard</li>
 * </ul>
 *
 * <p>Usage:<br/>
 * {@code HyperlinkEventProcessor.addTo(pane);}
 * </p>
 *
 * @author Victor G. Vasilyev
 */
public class HyperlinkEventProcessor extends MouseAdapter
        implements HyperlinkListener, CopyLinkLocationAction.LinkOwner {

    private boolean isInsideHyperlink = false;
    private URL url;
    private JEditorPane pane;
    private JPopupMenu popupMenu;

    // Use addTo(JEditorPane pane) instead.
    private HyperlinkEventProcessor(JEditorPane pane) {
        this.pane = pane;
        this.popupMenu = getPopupMenu(new CopyLinkLocationAction(this));
    }

    static JPopupMenu getPopupMenu(CopyLinkLocationAction cllAction) {
        JMenuItem copyItem = new JMenuItem(cllAction);
        JPopupMenu menu = new JPopupMenu();
        menu.add(copyItem);
        return menu;
    }

    /**
     * Adds the {@code HyperlinkEventProcessor} to the specified
     * {@code JEditorPane}.
     *
     * @param pane an instance of the {@code JEditorPane}.
     */
    public static void addTo(JEditorPane pane) {
        assert pane != null;
        HyperlinkEventProcessor proc = new HyperlinkEventProcessor(pane);
        pane.addHyperlinkListener(proc);
        pane.addMouseListener(proc);
    }

    public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
        url = hyperlinkEvent.getURL();
        HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
        if (type == HyperlinkEvent.EventType.ENTERED) {
            isInsideHyperlink = true;
            pane.setToolTipText(getURLExternalForm()); // #176141
        } else if (type == HyperlinkEvent.EventType.ACTIVATED) {
            isInsideHyperlink = false;
            pane.setToolTipText(null);
        } else if (type == HyperlinkEvent.EventType.EXITED) {
            isInsideHyperlink = false;
            pane.setToolTipText(null);
        } else {
            Installer.log.log(Level.SEVERE, "Unknown hyperlinkEvent: " +
                    hyperlinkEvent);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isInsideHyperlink && Utils.isMouseRightClick(e)) {
            Utils.showPopupMenu(e, popupMenu, pane);
        }
    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        // do nothing
    }

    public String getURLExternalForm() {
        return url == null ? null : url.toExternalForm(); // #176141
    }

    public Clipboard getClipboard() {
        return pane.getToolkit().getSystemClipboard();
    }

}

