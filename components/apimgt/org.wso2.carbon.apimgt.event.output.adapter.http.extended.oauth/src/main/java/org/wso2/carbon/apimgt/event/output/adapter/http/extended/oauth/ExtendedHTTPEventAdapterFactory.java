/*
 *  Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.apimgt.event.output.adapter.http.extended.oauth;

import org.wso2.carbon.apimgt.event.output.adapter.http.extended.oauth.internal.util.ExtendedHTTPEventAdapterConstants;
import org.wso2.carbon.event.output.adapter.core.*;

import java.util.*;

/**
 * The recommendation event adapter factory class to create a recommendation output adapter
 */
public class ExtendedHTTPEventAdapterFactory extends OutputEventAdapterFactory {
    private ResourceBundle resourceBundle =
            ResourceBundle.getBundle("org.wso2.carbon.event.output.adapter.recommendationevent.i18n.Resources", Locale.getDefault());

    @Override
    public String getType() {
        return ExtendedHTTPEventAdapterConstants.ADAPTER_TYPE_HTTP;
    }

    @Override
    public List<String> getSupportedMessageFormats() {
        List<String> supportedMessageFormats = new ArrayList<String>();
        supportedMessageFormats.add(MessageType.TEXT);
        supportedMessageFormats.add(MessageType.XML);
        supportedMessageFormats.add(MessageType.JSON);
        return supportedMessageFormats;
    }

    @Override
    public List<Property> getStaticPropertyList() {

        List<Property> staticPropertyList = new ArrayList<Property>();

        Property proxyHostProp = new Property(ExtendedHTTPEventAdapterConstants.ADAPTER_PROXY_HOST);
        proxyHostProp.setDisplayName(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_PROXY_HOST));
        proxyHostProp.setHint(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_PROXY_HOST_HINT));
        proxyHostProp.setRequired(false);

        Property proxyPortProp = new Property(ExtendedHTTPEventAdapterConstants.ADAPTER_PROXY_PORT);
        proxyPortProp.setDisplayName(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_PROXY_PORT));
        proxyPortProp.setHint(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_PROXY_PORT_HINT));
        proxyPortProp.setRequired(false);

        Property clientMethod = new Property(ExtendedHTTPEventAdapterConstants.ADAPTER_HTTP_CLIENT_METHOD);
        clientMethod.setDisplayName(
                resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_HTTP_CLIENT_METHOD));
        clientMethod.setRequired(true);
        clientMethod.setOptions(new String[]{ExtendedHTTPEventAdapterConstants.CONSTANT_HTTP_POST,
                ExtendedHTTPEventAdapterConstants.CONSTANT_HTTP_PUT});
        clientMethod.setDefaultValue(ExtendedHTTPEventAdapterConstants.CONSTANT_HTTP_POST);

        staticPropertyList.add(proxyHostProp);
        staticPropertyList.add(proxyPortProp);
        staticPropertyList.add(clientMethod);

        return staticPropertyList;

    }

    @Override
    public List<Property> getDynamicPropertyList() {
        List<Property> dynamicPropertyList = new ArrayList<Property>();

        Property urlProp = new Property(ExtendedHTTPEventAdapterConstants.ADAPTER_MESSAGE_URL);
        urlProp.setDisplayName(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_MESSAGE_URL));
        urlProp.setHint(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_MESSAGE_URL_HINT));
        urlProp.setRequired(true);

        Property usernameProp = new Property(ExtendedHTTPEventAdapterConstants.ADAPTER_USERNAME);
        usernameProp.setDisplayName(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_USERNAME));
        usernameProp.setHint(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_USERNAME_HINT));
        usernameProp.setRequired(false);

        Property passwordProp = new Property(ExtendedHTTPEventAdapterConstants.ADAPTER_PASSWORD);
        passwordProp.setDisplayName(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_PASSWORD));
        passwordProp.setHint(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_PASSWORD_HINT));
        passwordProp.setRequired(false);
        passwordProp.setSecured(true);
        passwordProp.setEncrypted(true);

        Property authUrlProp = new Property(ExtendedHTTPEventAdapterConstants.ADAPTER_OAUTH_URL);
        authUrlProp.setDisplayName(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_OAUTH_URL));
        authUrlProp.setHint(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_OAUTH_URL_HINT));
        authUrlProp.setRequired(false);

        Property consumerKeyProp = new Property(ExtendedHTTPEventAdapterConstants.ADAPTER_OAUTH_CONSUMER_KEY);
        consumerKeyProp.setDisplayName(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_OAUTH_CONSUMER_KEY));
        consumerKeyProp.setHint(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_OAUTH_CONSUMER_KEY_HINT));
        consumerKeyProp.setRequired(false);
        consumerKeyProp.setSecured(true);
        consumerKeyProp.setEncrypted(true);

        Property consumerSecretProp = new Property(ExtendedHTTPEventAdapterConstants.ADAPTER_OAUTH_CONSUMER_SECRET);
        consumerSecretProp.setDisplayName(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_OAUTH_CONSUMER_SECRET));
        consumerSecretProp.setHint(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_OAUTH_CONSUMER_SECRET_HINT));
        consumerSecretProp.setRequired(false);
        consumerSecretProp.setSecured(true);
        consumerSecretProp.setEncrypted(true);

        Property headersProp = new Property(ExtendedHTTPEventAdapterConstants.ADAPTER_HEADERS);
        headersProp.setDisplayName(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_HEADERS));
        headersProp.setHint(resourceBundle.getString(ExtendedHTTPEventAdapterConstants.ADAPTER_HEADERS_HINT));
        headersProp.setRequired(false);

        dynamicPropertyList.add(urlProp);
        dynamicPropertyList.add(usernameProp);
        dynamicPropertyList.add(passwordProp);
        dynamicPropertyList.add(authUrlProp);
        dynamicPropertyList.add(consumerKeyProp);
        dynamicPropertyList.add(consumerSecretProp);
        dynamicPropertyList.add(headersProp);

        return dynamicPropertyList;
    }

    @Override
    public String getUsageTips() {
        return null;
    }

    @Override
    public OutputEventAdapter createEventAdapter(OutputEventAdapterConfiguration eventAdapterConfiguration,
                                                 Map<String, String> globalProperties) {
        return new ExtendedHTTPEventAdapter(eventAdapterConfiguration, globalProperties);
    }
}
