/*
 * Copyright (c) 2021 Mark A. Hunter (ACT Health)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.fhirfactory.pegacorn.itops.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ITOpsPubSubReport {
    private Instant timestamp;
    private List<ITOpsTopicSubscriptionReport> subscriptions;

    public ITOpsPubSubReport(){
        this.timestamp = Instant.now();
        subscriptions = new ArrayList<>();
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public List<ITOpsTopicSubscriptionReport> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<ITOpsTopicSubscriptionReport> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @JsonIgnore
    public void addTopicSubscriber(String topic, String subscriber){
        for(ITOpsTopicSubscriptionReport currentReport: getSubscriptions()){
            if(currentReport.getTopicName().contentEquals(topic)){
                currentReport.addTopicSubscriber(subscriber);
                return;
            }
        }
        ITOpsTopicSubscriptionReport newReport = new ITOpsTopicSubscriptionReport();
        newReport.setTopicName(topic);
        newReport.addTopicSubscriber(subscriber);
        getSubscriptions().add(newReport);
    }
}
