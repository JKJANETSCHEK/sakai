/**
 * Copyright (c) 2005-2014 The Apereo Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *             http://opensource.org/licenses/ecl2
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sakaiproject.importer.impl.translators;

import org.sakaiproject.importer.api.Importable;
import org.sakaiproject.importer.api.IMSResourceTranslator;
import org.sakaiproject.importer.impl.XPathHelper;
import org.sakaiproject.importer.impl.importables.WebLink;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Bb55ExternalLinkTranslator implements IMSResourceTranslator {

	public String getTypeName() {
		return "resource/x-bb-externallink";
	}

	public Importable translate(Node resourceNode, Document descriptor, String contextPath, String archiveBasePath) {
		String url = XPathHelper.getNodeValue("/EXTERNALLINK/URL/@value", descriptor);
		String title = XPathHelper.getNodeValue("/EXTERNALLINK/TITLE/@value", descriptor);
		int priority = Integer.parseInt(((Element)resourceNode).getAttribute("priority"));
		contextPath = contextPath + title; //Validator.escapeResourceName(title);
		String description = XPathHelper.getNodeValue("/EXTERNALLINK/TEXT", descriptor);
		WebLink link = new WebLink();
		link.setUrl(url);
		link.setTitle(title);
		link.setContextPath(contextPath);
		link.setDescription(description);
		link.setAbsolute(url.startsWith("http://"));
		link.setSequenceNum(priority);
		return link;
	}

	public boolean processResourceChildren() {
		return true;
	}

}
