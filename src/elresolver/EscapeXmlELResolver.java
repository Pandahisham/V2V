/*
Copyright (c) 2010, Chin Huang
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice,
   this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package elresolver;

import java.beans.FeatureDescriptor;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.servlet.jsp.JspContext;

/**
 * {@link ELResolver} which escapes XML in String values.
 */
public class EscapeXmlELResolver extends ELResolver {

    /** pageContext attribute name for flag to enable XML escaping */
    static final String ESCAPE_XML_ATTRIBUTE =
            EscapeXmlELResolver.class.getName() + ".escapeXml";

    /**
     * Properties meant for internal use. Need not be escaped.
     * In particular required to prevent escaping URLs passed as arguments
     * between HTML page and controller. If the URLs are escaped the parameters
     * will be messed up. Simple fix is to whitelist these properties.
     */
	private static final Set<String> WHITELISTED_PROPERTIES = new HashSet<String>();

	static {
   	    // Result generated using
		// grep -ir -h "Url\"," **/*.java | sed 's/\([^,]*\).*/\1);/' | sed "s/[ \t]*//g" | sort | uniq | sed s/[^\(]*/WHITELISTED_PROPERTIES.add/
		WHITELISTED_PROPERTIES.add("refreshUrl");
		WHITELISTED_PROPERTIES.add("requestUrl");
		WHITELISTED_PROPERTIES.add("donorRowClickUrl");
		WHITELISTED_PROPERTIES.add("nextPageUrl");
		WHITELISTED_PROPERTIES.add("refreshUrl");
		WHITELISTED_PROPERTIES.add("requestUrl");
		WHITELISTED_PROPERTIES.add("saveToWorksheetUrl");
		WHITELISTED_PROPERTIES.add("addAnotherCollectionBatchUrl");
		WHITELISTED_PROPERTIES.add("addAnotherCollectionUrl");
		WHITELISTED_PROPERTIES.add("addAnotherDonorUrl");
		WHITELISTED_PROPERTIES.add("addAnotherProductUrl");
		WHITELISTED_PROPERTIES.add("addAnotherRequestUrl");
		WHITELISTED_PROPERTIES.add("addAnotherUsageUrl");
		WHITELISTED_PROPERTIES.add("addAnotherWorksheetUrl");
		WHITELISTED_PROPERTIES.add("nextPageUrl");
		WHITELISTED_PROPERTIES.add("refreshUrl");
		WHITELISTED_PROPERTIES.add("requestUrl");
		WHITELISTED_PROPERTIES.add("worksheetResultClickUrl");
	}

    private ThreadLocal<Boolean> excludeMe = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return Boolean.FALSE;
        }
    };

    @Override
    public Class<?> getCommonPropertyType(ELContext context, Object base) {
        return null;
    }

    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(
            ELContext context, Object base)
    {
        return null;
    }

    @Override
    public Class<?> getType(ELContext context, Object base, Object property) {
        return null;
    }

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
    	JspContext pageContext = (JspContext) context.getContext(JspContext.class);
        Boolean escapeXml = (Boolean) pageContext.getAttribute(ESCAPE_XML_ATTRIBUTE);
        if (escapeXml != null && !escapeXml) {
            return null;
        }

        try {
            if (excludeMe.get()) {
                return null;
            }

            // This resolver is in the original resolver chain. To prevent
            // infinite recursion, set a flag to prevent this resolver from
            // invoking the original resolver chain again when its turn in the
            // chain comes around.
            excludeMe.set(Boolean.TRUE);
            Object value = context.getELResolver().getValue(
                    context, base, property);

            if (value != null)
            if (value instanceof String && !WHITELISTED_PROPERTIES.contains(property)) {
                value = EscapeXml.escape((String) value);
            }
            return value;

        } finally {
            excludeMe.remove();
        }
    }

	@Override
    public boolean isReadOnly(ELContext context, Object base, Object property) {
        return false;
    }

    @Override
    public void setValue(
            ELContext context, Object base, Object property, Object value)
    {
    }
}