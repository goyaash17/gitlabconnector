
package org.mule.modules.gitlabconnect.generated.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.gitlabconnect.GitlabconnectConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>GitlabconnectConnectorProcessAdapter</code> is a wrapper around {@link GitlabconnectConnector } that enables custom processing strategies.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2017-11-02T12:27:54-07:00", comments = "Build UNNAMED.2793.f49b6c7")
public class GitlabconnectConnectorProcessAdapter
    extends GitlabconnectConnectorLifecycleInjectionAdapter
    implements ProcessAdapter<GitlabconnectConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, GitlabconnectConnectorCapabilitiesAdapter> getProcessTemplate() {
        final GitlabconnectConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,GitlabconnectConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, GitlabconnectConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, GitlabconnectConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
