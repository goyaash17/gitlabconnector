package org.mule.modules.gitlabconnect;

import java.io.IOException;
import java.util.Map;

import org.gitlab.api.models.GitlabMergeRequest;
import org.gitlab.api.models.GitlabProject;
import org.mule.api.annotations.Config;
import org.mule.api.annotations.Connector;
import org.mule.api.annotations.param.MetaDataKeyParam;
import org.mule.api.annotations.param.Optional;
import org.mule.api.annotations.MetaDataScope;
import org.mule.api.annotations.Processor;
import org.mule.api.annotations.display.FriendlyName;
import org.mule.api.annotations.param.Default;

import org.mule.modules.gitlabconnect.config.ConnectorConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.profiq.techresearch.mule.gitlab.GitlabConnector;

@Connector(name="gitlabconnect", friendlyName="Gitlabconnect")
@MetaDataScope( DataSenseResolver.class )
public class GitlabconnectConnector {

    /**
     * DataSense processor

     * @param key Key to be used to populate the entity
     * @param entity Map that represents the entity
     * @return Some string
     */
    @Processor
    public Map<String,Object> addEntity( @MetaDataKeyParam String key, @Default("#[payload]") Map<String,Object> entity) {
        /*
         * USE THE KEY AND THE MAP TO DO SOMETHING
         */
        return entity;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(GitlabConnector.class);

    @Config
    private ConnectorConfig config;

    /**
     * Config getter
     *
     * @return config instance
     */
    public ConnectorConfig getConfig() {
        return this.config;
    }

    /**
     * Config setter
     *
     * @param config config instance
     */
    public void setConfig(final ConnectorConfig config) {
        this.config = config;
    }

    /**
     * Accept merge request
     *
     * @param projectId ID of the project
     * @param mergeRequestId ID of the Merge Request
     * @param mergeCommitMessage merge commit message (optional)
     * @return Gitlab merge request
     * @throws IOException on error with connecting to the Gitlab
     */
    @Processor
    public GitlabMergeRequest acceptMergeRequest(@FriendlyName("Project ID") final Integer projectId,
                                                 @FriendlyName("Merge Request ID") final Integer mergeRequestId,
                                                 @Optional final String mergeCommitMessage) throws IOException {
        final GitlabProject project;
        final GitlabMergeRequest result;

        LOGGER.trace("Trying to load project {}...", projectId);
        try {
            project = this.config.getApiHandler().getProject(projectId);
        }
        catch (final IOException ex) {
            LOGGER.error("Project {} was not loaded.", projectId);
            throw ex;
        }
        if (project == null) {
            LOGGER.error("Project {} was not loaded (return \"null\").", projectId);
            throw new IOException("Project was not loaded (return \"null\").");
        }
        LOGGER.debug("Project {} was loaded correctly.", projectId);

        LOGGER.trace("Trying to accept merge request {}...", mergeRequestId);
        try {
            result = this.config.getApiHandler().acceptMergeRequest(project, mergeRequestId, mergeCommitMessage);
        }
        catch (final IOException ex) {
            LOGGER.error("Accepting merge request {} failed.", mergeRequestId);
            throw ex;
        }
        if (result == null) {
            LOGGER.error("Accepting merge request {} failed (return \"null\").", mergeRequestId);
            throw new IOException("Accepting merge request failed (return \"null\").");
        }
        LOGGER.debug("Merge request {} was accepted correctly.", mergeRequestId);

        return result;
    }


}