<#assign tabs={"Home":"/lol/index","Champions":"/lol/champion/index","Ranking":"/lol/ranking/index"}/>
<#global ctx="${request.contextPath}">
<#macro m_html title="League of Legends" tab_index=0>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>
            ${title}
        </title>
        <script src="/static/js/jquery/3.2.1/jquery.min.js"></script>
        <script src="/static/js/popper/1.12.5/popper.min.js"></script>
        <script src="/static/js/bootstrap/4.1.0/bootstrap.bundle.min.js"></script>
        <script src="/static/js/bootstrap/4.1.0/bootstrap.min.js"></script>
        <link rel="stylesheet" href="/static/css/bootstrap/4.1.0/bootstrap.min.css"/>
        <link rel="stylesheet" href="/static/css/bootstrap/4.1.0/bootstrap-grid.min.css">
    </head>
    <body>
    <div class="container-fluid">
        <ul class="nav nav-tabs">
            <#list tabs?keys as tabTitle>
                <li <#if tabTitle_index==tab_index>class="active"</#if>>
                    <a href="${tabs[tabTitle]}">${tabTitle}</a>
                </li>
            </#list>
            <li>
                <div class="dropdown">
                    <a class="btn dropdown-toggle" href="#" id="settings" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">Setting</a>
                    <div class="dropdown-menu" aria-labelledby="settings">
                    </div>
                </div>
            </li>
        </ul>
    </div>
    <div class="container">
        <#nested/>
    </div>
    </body>
    </html>
</#macro>