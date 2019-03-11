<#assign tabs={"Home":"/lol/index","Champions":"/lol/champion/index","Ranking":"/lol/ranking/index"}/>
<#macro m_html title="League of Legends" tab_index=0>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>
            ${title}
        </title>
        <link rel="stylesheet" href="/static/css/bootstrap/4.1.0/bootstrap.min.css"/>
        <script src="/static/js/jquery/3.2.1/jquery.min.js"></script>
        <script src="/static/js/popper/1.12.5/popper.min.js"></script>
        <script src="/static/js/bootstrap/4.1.0/bootstrap.min.js"></script>
    </head>
    <body>
    <ul class="nav nav-tabs">
        <#list tabs?keys as tabTitle>
            <li <#if tabTitle_index==tab_index>class="active"</#if>>
                <a href="${tabs[tabTitle]}">${tabTitle}</a>
            </li>
        </#list>
    </ul>
    <#nested/>
    </body>
    </html>
</#macro>