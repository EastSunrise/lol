<#import "/templates/base/baseLayout.ftl" as base/>
<@base.m_html title="Home" tab_index=0>
    <div class="input-group">
        <input type="text" class="form-control" placeholder="Summoner's Name">
        <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button">Search</button>
        </div>
    </div>
</@base.m_html>