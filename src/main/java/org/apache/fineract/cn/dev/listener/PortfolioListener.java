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
package org.apache.fineract.cn.dev.listener;

import org.apache.fineract.cn.test.listener.EventRecorder;
import org.apache.fineract.cn.portfolio.api.v1.events.EventConstants;
import org.apache.fineract.cn.lang.config.TenantHeaderFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * @author Myrle Krantz
 */
@SuppressWarnings("unused")
@Component
public class PortfolioListener {

  private final EventRecorder eventRecorder;

  @Autowired
  public PortfolioListener(final EventRecorder eventRecorder) {
    this.eventRecorder = eventRecorder;
  }

  @JmsListener(
          subscription = EventConstants.DESTINATION,
          destination = EventConstants.DESTINATION,
          selector = EventConstants.SELECTOR_INITIALIZE
  )
  public void onInitialization(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                               final String payload) {
    this.eventRecorder.event(tenant, EventConstants.INITIALIZE, payload, String.class);
  }

  @JmsListener(
          subscription = EventConstants.DESTINATION,
          destination = EventConstants.DESTINATION,
          selector = EventConstants.SELECTOR_POST_PRODUCT
  )
  public void onCreateProduct(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                              final String payload) {
    this.eventRecorder.event(tenant, EventConstants.POST_PRODUCT, payload, String.class);
  }

  @JmsListener(
          subscription = EventConstants.DESTINATION,
          destination = EventConstants.DESTINATION,
          selector = EventConstants.SELECTOR_PUT_PRODUCT
  )
  public void onChangeProduct(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                              final String payload) {
    this.eventRecorder.event(tenant, EventConstants.PUT_PRODUCT, payload, String.class);
  }

  @JmsListener(
          subscription = EventConstants.DESTINATION,
          destination = EventConstants.DESTINATION,
          selector = EventConstants.SELECTOR_PUT_PRODUCT_ENABLE
  )
  public void onEnableProduct(@Header(TenantHeaderFilter.TENANT_HEADER) final String tenant,
                              final String payload) {
    this.eventRecorder.event(tenant, EventConstants.PUT_PRODUCT_ENABLE, payload, String.class);
  }
}
