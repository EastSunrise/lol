<#import "/templates/base/baseLayout.ftl" as base/>
<@base.m_html title="Champions" tab_index=1>
    <#assign championList = championList/>
    <form action="/lol/champion/index">
        <div class="form-group">
            <label for="champion">Search</label>
            <input type="text" class="form-control" id="champion" name="name" placeholder="Enter info of the champion">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

    <div>
        <div class="row">
            <#list championList as champion>
            <div class="col-sm-3">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${champion.name}</h5>
                        <a href="/lol/champion/individual?name=${champion.name}" class="btn btn-primary">Go
                            somewhere</a>
                    </div>
                </div>
            </div>
            <#if champion_index%3==2>
        </div>
        <div class="row">
            </#if>
            </#list>
        </div>
    </div>
</@base.m_html>