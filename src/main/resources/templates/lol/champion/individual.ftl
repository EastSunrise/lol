<#import "/templates/base/baseLayout.ftl" as base/>
<@base.m_html title="Champions" tab_index=1>
    <#assign championInfo = championInfo/>
    <div>
        <#assign champion = championInfo.championDmo/>
        This is the information of ${champion.name}.
    </div>
    <div>
        <#assign skins = championInfo.skinDmoList/>
        <div id="carousel-container" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators radiou">
                <#list skins as skin>
                    <li data-target="#carousel-container" data-slide-to="${skin_index}"></li>
                </#list>
            </ol>
            <div class="carousel-inner">
                <#list skins as skin>
                    <div class="carousel-item">
                        <img src="/static/img/lol/champion/splash/${champion.enKey}_${skin.num}.jpg"
                             class="col-md-2 d-block w-100" alt="...">
                        <div class="carousel-caption">
                            <h4>${skin.name}</h4>
                        </div>
                    </div>
                </#list>
            </div>
            <a class="carousel-control-prev" href="#carousel-container" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carousel-container" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
</@base.m_html>