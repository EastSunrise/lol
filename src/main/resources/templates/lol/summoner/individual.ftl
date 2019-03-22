<#import "/templates/base/baseLayout.ftl" as base/>
<#assign summoner = summoner/>
<@base.m_html title=summoner.name tab_index=0>
    This is View of ${summoner.name}.
    <#if position??>
        <#assign position = position/>
    <#else>
        ${summoner.name} hasn't rank yet.
    </#if>
</@base.m_html>